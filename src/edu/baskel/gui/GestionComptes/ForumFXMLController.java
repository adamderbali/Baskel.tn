package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Forum1;
import edu.baskel.entities.Membre;
import edu.baskel.services.ForumCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ForumFXMLController implements Initializable {

    @FXML
    private JFXListView<Forum1> listeforum;

    @FXML
    private JFXTextArea message;

    ForumCRUD fs = new ForumCRUD();

    private List<Forum1> forum;

    @FXML
    private Label lbl_titr;

    @FXML
    private ImageView retouraffich;

    @FXML
    private FontAwesomeIconView btnActualiser;
    MembreCRUD mc = new MembreCRUD();

    Membre m = SessionInfo.getLoggedM();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        forum = fs.displayAll();
        if (forum != null) {
            ObservableList<Forum1> list = FXCollections.observableArrayList(forum);
            listeforum.setCellFactory((ListView<Forum1> arg0) -> {
                ListCell<Forum1> cell = new ListCell<Forum1>() {
                    @Override
                    protected void updateItem(Forum1 e, boolean btl) {
                        super.updateItem(e, btl);
                        if (e != null) {
                            Image IMAGE_RUBY = null;
                            if (e.getImage_uf() != null) {
                                IMAGE_RUBY = new Image("file:/C:\\wamp\\www\\baskel\\images\\" + e.getImage_uf());
                            } else {
                                System.out.println("image null");
                                File file = new File("src\\images\\2.jpg");
                                IMAGE_RUBY = new Image(file.toURI().toString());
                            }
                            ImageView imgview = new ImageView(IMAGE_RUBY);
                            setGraphic(imgview);
                            imgview.setFitHeight(70);
                            imgview.setFitWidth(70);
                            Rectangle clip = new Rectangle(
                                    imgview.getFitWidth(), imgview.getFitHeight()
                            );
                            clip.setArcWidth(20);
                            clip.setArcHeight(20);
                            imgview.setClip(clip);
                            // snapshot the rounded image.
                            SnapshotParameters parameters = new SnapshotParameters();
                            parameters.setFill(Color.TRANSPARENT);
                            WritableImage image = imgview.snapshot(parameters, null);
                            // remove the rounding clip so that our effect can show through.
                            imgview.setClip(null);
                            // apply a shadow effect.
                            imgview.setEffect(new DropShadow(20, Color.BLACK));
                            // store the rounded image in the imageView.
                            imgview.setImage(image);
                            TextField id = new TextField(String.valueOf(e.getId_f()));
                            id.setVisible(true);
                            setId(String.valueOf(e.getId_f()));
                            listeforum.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                        Forum1 M = listeforum.getItems().get(listeforum.getSelectionModel().getSelectedIndex());
                                        if(m.getId_u()==M.getId_u()){
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Confirmation ");
                                        alert.setHeaderText("Supprimer ce  commentaire !?");
                                        alert.setContentText("OK?");
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == ButtonType.OK) {
                                            fs.supprimerForum(M);
                                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert1.setHeaderText("Votre commentaire a été supprimé avec succés ");
                                            Optional<ButtonType> result1 = alert1.showAndWait();
                                        } else {
                                            System.out.println("Rien");
                                        }
                                        }else{
                                            InputValidation.notificationError("Commentaire", "Vous ne pouver pas supprimer les commentaires des autres membres");
                                        }
                                    }
                                }
                            });
                            if (e.getId_u() != 0) {
                                Membre fs = mc.AfficherMembreById(e.getId_u());
                                setText("Membre  : " + fs.getNom_u() + " " + fs.getPrenom_u()
                                        + " ,  DATE : " + e.getDate_f()
                                        + "\n" + "Commantaire :  \n " + e.getText()
                                );
                            }
                        }
                    }
                };

                return cell;
            });
            listeforum.setItems(list);

        }
    }

    @FXML
    void envoyer(MouseEvent event) throws NoSuchAlgorithmException, IOException {
        java.util.Date date_util = new java.util.Date();
        java.sql.Date datee = new java.sql.Date(date_util.getTime());
        Forum1 f = new Forum1(0, m.getId_u(), message.getText(), datee.toString(), m.getImage_u());
        fs.ajouterForum(f);
        message.clear();
        actua();;

    }

    @FXML
    public void actualiser(MouseEvent event) {

        forum = fs.displayAll();
        if (forum != null) {
            ObservableList<Forum1> list = FXCollections.observableArrayList(forum);
            listeforum.setCellFactory((ListView<Forum1> arg0) -> {
                ListCell<Forum1> cell = new ListCell<Forum1>() {
                    @Override
                    protected void updateItem(Forum1 e, boolean btl) {
                        super.updateItem(e, btl);
                        if (e != null) {
                            Image IMAGE_RUBY = null;
                            if (e.getImage_uf() != null) {
                                IMAGE_RUBY = new Image("file:/C:\\wamp\\www\\baskel\\images\\" + e.getImage_uf());
                            } else {
                                System.out.println("image null");
                                File file = new File("src\\images\\2.jpg");
                                IMAGE_RUBY = new Image(file.toURI().toString());
                            }
                            ImageView imgview = new ImageView(IMAGE_RUBY);
                            setGraphic(imgview);
                            imgview.setFitHeight(70);
                            imgview.setFitWidth(70);
                            Rectangle clip = new Rectangle(
                                    imgview.getFitWidth(), imgview.getFitHeight()
                            );
                            clip.setArcWidth(20);
                            clip.setArcHeight(20);
                            imgview.setClip(clip);
                            // snapshot the rounded image.
                            SnapshotParameters parameters = new SnapshotParameters();
                            parameters.setFill(Color.TRANSPARENT);
                            WritableImage image = imgview.snapshot(parameters, null);
                            // remove the rounding clip so that our effect can show through.
                            imgview.setClip(null);
                            // apply a shadow effect.
                            imgview.setEffect(new DropShadow(20, Color.BLACK));
                            // store the rounded image in the imageView.
                            imgview.setImage(image);
                            TextField id = new TextField(String.valueOf(e.getId_f()));
                            id.setVisible(true);
                            setId(String.valueOf(e.getId_f()));
                            if (e.getId_u() != 0) {
                                Membre fs = mc.AfficherMembreById(e.getId_u());
                                setText("Membre  : " + fs.getNom_u() + " " + fs.getPrenom_u()
                                        + " ,  DATE : " + e.getDate_f()
                                        + "\n" + "Commantaire :  \n " + e.getText()
                                );
                            }
                        }
                    }
                };

                return cell;
            });
            listeforum.setItems(list);

        }
    }

    public void actua() {
        forum = fs.displayAll();
        if (forum != null) {
            ObservableList<Forum1> list = FXCollections.observableArrayList(forum);
            listeforum.setCellFactory((ListView<Forum1> arg0) -> {
                ListCell<Forum1> cell = new ListCell<Forum1>() {
                    @Override
                    protected void updateItem(Forum1 e, boolean btl) {
                        super.updateItem(e, btl);
                        if (e != null) {
                            Image IMAGE_RUBY = null;
                            if (e.getImage_uf() != null) {
                                IMAGE_RUBY = new Image("file:/C:\\wamp\\www\\baskel\\images\\" + e.getImage_uf());
                            } else {
                                System.out.println("image null");
                                File file = new File("src\\images\\2.jpg");
                                IMAGE_RUBY = new Image(file.toURI().toString());
                            }
                            ImageView imgview = new ImageView(IMAGE_RUBY);
                            setGraphic(imgview);
                            imgview.setFitHeight(70);
                            imgview.setFitWidth(70);
                            Rectangle clip = new Rectangle(
                                    imgview.getFitWidth(), imgview.getFitHeight()
                            );
                            clip.setArcWidth(20);
                            clip.setArcHeight(20);
                            imgview.setClip(clip);
                            // snapshot the rounded image.
                            SnapshotParameters parameters = new SnapshotParameters();
                            parameters.setFill(Color.TRANSPARENT);
                            WritableImage image = imgview.snapshot(parameters, null);
                            // remove the rounding clip so that our effect can show through.
                            imgview.setClip(null);
                            // apply a shadow effect.
                            imgview.setEffect(new DropShadow(20, Color.BLACK));
                            // store the rounded image in the imageView.
                            imgview.setImage(image);
                            TextField id = new TextField(String.valueOf(e.getId_f()));
                            id.setVisible(true);
                            setId(String.valueOf(e.getId_f()));
                            if (e.getId_u() != 0) {
                                Membre fs = mc.AfficherMembreById(e.getId_u());
                                setText("Membre  : " + fs.getNom_u() + " " + fs.getPrenom_u()
                                        + " ,  DATE : " + e.getDate_f()
                                        + "\n" + "Commantaire :  \n " + e.getText()
                                );
                            }
                        }
                    }
                };

                return cell;
            });
            listeforum.setItems(list);

        }
    }

}
