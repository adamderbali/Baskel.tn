package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Forum;
import edu.baskel.entities.Membre;
import edu.baskel.services.ForumCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.StatCRUD;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ForumFXMLController implements Initializable {

    @FXML
    private JFXListView<Forum> listeforum;
    @FXML
    private JFXTextArea message;
    @FXML
    private Label lbl_titr;
    @FXML
    private ImageView retouraffich;
    @FXML
    private Button btnSupprimer;
    @FXML
    private FontAwesomeIconView btnActualiser;
    @FXML
    private Pane PaneProfilCom;
    @FXML
    private ImageView imgProCom;
    @FXML
    private Label lblNomPrenom;
    @FXML
    private Label lblage;
    @FXML
    private Label lbladresse;
    @FXML
    private Label lblsexe;
    @FXML
    private Button btnModifié;
    @FXML
    private Button btnEnregistré;
    @FXML
    private ImageView btnHome;

    MembreCRUD mc = new MembreCRUD();
    ForumCRUD fs = new ForumCRUD();
    private List<Forum> forum;
    Membre m = SessionInfo.loggedM;
    private static final String DEFAULT_CONTROL_INNER_BACKGROUND = "derive(-fx-base,80%)";
    private static final String HIGHLIGHTED_CONTROL_INNER_BACKGROUND = "derive(#98E0FB, 50%)";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        StatCRUD sc = new StatCRUD();
        try {
            sc.Stat_methode("contact", m.getId_u());
        } catch (SQLException ex) {
        }
        
        PaneProfilCom.setVisible(false);
        forum = fs.displayAll();
        if (forum != null) {
            ObservableList<Forum> list = FXCollections.observableArrayList(forum);
            listeforum.setCellFactory((ListView<Forum> arg0) -> {
                ListCell<Forum> cell = new ListCell<Forum>() {
                    @Override
                    protected void updateItem(Forum e, boolean btl) {
                        super.updateItem(e, btl);
                        if (e != null) {

                            Image IMAGE_RUBY = null;
                            if (!e.getImage_uf().equalsIgnoreCase("")) {
                                IMAGE_RUBY = new Image("file:/C:\\wamp\\www\\baskel\\images\\" + e.getImage_uf());
                            } else {
                                Membre l = mc.AfficherMembreById(e.getId_u());
                                if (l.getSexe_u().equals("Femme")) {
                                    File file = new File("src\\images\\femme.png");
                                    IMAGE_RUBY = new Image(file.toURI().toString());
                                }
                                if (l.getSexe_u().equals("Homme")) {
                                    File file = new File("src\\images\\homme.png");
                                    IMAGE_RUBY = new Image(file.toURI().toString());
                                }
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
                                        Forum M = listeforum.getItems().get(listeforum.getSelectionModel().getSelectedIndex());
                                        Membre mp = mc.AfficherMembreById(M.getId_u());
                                        String d = mp.getDate_u().toString();
                                        LocalDate l = LocalDate.parse(d);
                                        LocalDate now = LocalDate.now();
                                        Period diff = Period.between(l, now);
                                        lblNomPrenom.setText(mp.getNom_u() + " " + mp.getPrenom_u());
                                        String age = String.valueOf(diff.getYears());
                                        lblage.setText(age);
                                        lbladresse.setText(mp.getAdresse_u());
                                        lblsexe.setText(mp.getSexe_u());
                                        Image imag;
                                        if (!mp.getImage_u().equalsIgnoreCase("")) {
                                            imag = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + mp.getImage_u());
                                            imgProCom.setImage(imag);
                                        } else {
                                            if (mp.getSexe_u().equals("Femme")) {
                                                File file = new File("src\\images\\femme.png");
                                                imag = new Image(file.toURI().toString());
                                                imgProCom.setImage(imag);

                                            }
                                            if (mp.getSexe_u().equals("Homme")) {
                                                File file = new File("src\\images\\homme.png");
                                                imag = new Image(file.toURI().toString());
                                                imgProCom.setImage(imag);

                                            }
                                        }
                                        if (m.getId_u() != M.getId_u()) {
                                            PaneProfilCom.setVisible(true);
                                        }

                                    }
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                                        PaneProfilCom.setVisible(false);
                                    }

                                }
                            }
                            );
                            if (e.getId_u() != 0) {
                                Membre fs = mc.AfficherMembreById(e.getId_u());
                                setText("Membre  : " + fs.getNom_u() + " " + fs.getPrenom_u()
                                        + " ,  DATE : " + e.getDate_f()
                                        + "\n" + "Commantaire :  \n " + e.getText()
                                );
                                if (fs.getId_u() == m.getId_u()) {
                                    setStyle("-fx-control-inner-background: " + HIGHLIGHTED_CONTROL_INNER_BACKGROUND + ";");

                                }
                            }
                        }
                    }
                };

                return cell;
            }
            );
            listeforum.setItems(list);

        }
    }

    //publier un commentaire
    @FXML
    void envoyer(MouseEvent event) throws NoSuchAlgorithmException, IOException {
        if (!message.getText().isEmpty()) {
            if (AutoCompleteAdresse.nb(message.getText()) == true) {
                
                Forum f = new Forum(0, m.getId_u(), message.getText(), InputValidation.dateCalendar(), m.getImage_u());
                fs.ajouterForum(f);
                InputValidation.notificationsucces("Commentaire", "Votre commentaire a été ajouter avec succés");
                message.clear();
                actua();
            } else {
                InputValidation.notificationError("Commentaire", "Nous n'acceptons pas les gros mots, soyez responsable svp!");
            }
        } else {
            InputValidation.notificationError("Commentaire", "Champs commentaire vide");

        }
    }

    //supprimer un commemntaire
    @FXML
    void SupprimerCom(MouseEvent event) throws NoSuchAlgorithmException, IOException {
        if (listeforum.getSelectionModel().getSelectedItem() != null) {
            Forum M = listeforum.getItems().get(listeforum.getSelectionModel().getSelectedIndex());
            if (m.getId_u() == M.getId_u()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation ");
                alert.setHeaderText("Supprimer ce  commentaire !?");
                alert.setContentText("OK?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    fs.supprimerForum(M);
                    InputValidation.notificationsucces("Commentaire", "Votre commentaire a été supprimer avec succée");
                    actua();
                } else {
                    System.out.println("Rien");
                }
            } else {
                InputValidation.notificationError("Commentaire", "Vous pouvez ne supprimer que vos commentaires ");
            }
        } else {
            InputValidation.notificationError("Commentaire", "Vous devez selectionner le commentaire a supprimer");

        }
    }

    //actulaiser liste commentaires
    @FXML
    public void actualiser(MouseEvent event
    ) {

        forum = fs.displayAll();
        if (forum != null) {
            ObservableList<Forum> list = FXCollections.observableArrayList(forum);
            listeforum.setCellFactory((ListView<Forum> arg0) -> {
                ListCell<Forum> cell = new ListCell<Forum>() {
                    @Override
                    protected void updateItem(Forum e, boolean btl) {
                        super.updateItem(e, btl);
                        if (e != null) {
                            Image IMAGE_RUBY = null;
                            if (!e.getImage_uf().equalsIgnoreCase("")) {
                                System.out.println("+/-59");
                                IMAGE_RUBY = new Image("file:/C:\\wamp\\www\\baskel\\images\\" + e.getImage_uf());
                            } else {
                                Membre l = mc.AfficherMembreById(e.getId_u());
                                if (l.getSexe_u().equals("Femme")) {
                                    File file = new File("src\\images\\femme.png");
                                    IMAGE_RUBY = new Image(file.toURI().toString());
                                }
                                if (l.getSexe_u().equals("Homme")) {
                                    File file = new File("src\\images\\homme.png");
                                    IMAGE_RUBY = new Image(file.toURI().toString());
                                }
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
                                        + "\n" + "Commentaire :  \n " + e.getText()
                                );
                                if (fs.getId_u() == m.getId_u()) {
                                    setStyle("-fx-control-inner-background: " + HIGHLIGHTED_CONTROL_INNER_BACKGROUND + ";");

                                }
                            }
                        }
                    }
                };

                return cell;
            });
            listeforum.setItems(list);

        }
    }
    //actualiser liste commentaire automatique

    public void actua() {
        forum = fs.displayAll();
        if (forum != null) {
            ObservableList<Forum> list = FXCollections.observableArrayList(forum);
            listeforum.setCellFactory((ListView<Forum> arg0) -> {
                ListCell<Forum> cell = new ListCell<Forum>() {
                    @Override
                    protected void updateItem(Forum e, boolean btl) {
                        super.updateItem(e, btl);
                        if (e != null) {
                            Image IMAGE_RUBY = null;
                            if (!e.getImage_uf().equalsIgnoreCase("")) {
                                IMAGE_RUBY = new Image("file:/C:\\wamp\\www\\baskel\\images\\" + e.getImage_uf());
                            } else {
                                Membre l = mc.AfficherMembreById(e.getId_u());
                                if (l.getSexe_u().equals("Femme")) {
                                    File file = new File("src\\images\\femme.png");
                                    IMAGE_RUBY = new Image(file.toURI().toString());
                                }
                                if (l.getSexe_u().equals("Homme")) {
                                    File file = new File("src\\images\\homme.png");
                                    IMAGE_RUBY = new Image(file.toURI().toString());
                                }

                            }
                            /*System.out.println("image null");
                                File file = new File("src\\images\\2.jpg");
                                IMAGE_RUBY = new Image(file.toURI().toString());*/

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
                                        + "\n" + "Commentaire :  \n " + e.getText()
                                );
                                if (fs.getId_u() == m.getId_u()) {
                                    setStyle("-fx-control-inner-background: " + HIGHLIGHTED_CONTROL_INNER_BACKGROUND + ";");

                                }
                            }
                        }
                    }
                };

                return cell;
            });
            listeforum.setItems(list);

        }
    }

//Modifier un commentaire
    @FXML
    void modifierCom(MouseEvent event) throws NoSuchAlgorithmException, IOException {
        if (listeforum.getSelectionModel().getSelectedItem() != null) {
            Forum f = listeforum.getItems().get(listeforum.getSelectionModel().getSelectedIndex());
            if (f.getId_u() == m.getId_u()) {
                if (InputValidation.dat(f.getDate_f(), InputValidation.dateCalendar()) < 3) {
                    message.setText(f.getText());

                } else {
                    InputValidation.notificationError("Commentaire", "Vous pouvez modifier le commentaire que dans les 3 minutes qui suivent la publication");
                }
            } else {
                InputValidation.notificationError("Commentaire", "Vous pouvez ne modifier que vos commentaires ");

            }
        } else {
            InputValidation.notificationError("Commentaire", "Vous devez selectionner le commentaire a modifier");

        }
    }

    //Enregistrer les modfications
    @FXML
    void EngmodifierCom(MouseEvent event) throws NoSuchAlgorithmException, IOException {
        if (MessageVide() == true) {

            Forum f = listeforum.getItems().get(listeforum.getSelectionModel().getSelectedIndex());
            Forum f2 = new Forum(message.getText(), InputValidation.dateCalendar());

            if (f.getId_u() == m.getId_u()) {

                if (AutoCompleteAdresse.nb(message.getText()) == true) {

                    fs.updateForum(f2, f.getId_f());
                    actua();
                    message.clear();

                    InputValidation.notificationsucces("Commentaire", "Modifications enregistrés");
                } else {
                    InputValidation.notificationError("Commentaire", "Nous n'acceptons pas les gros mots, soyez responsable svp!");

                }
            } else {
                InputValidation.notificationError("Commentaire", "Vous pouvez ne modifier que vos commentaires ");

            }
        } else {
                InputValidation.notificationError("Commentaire", "Vous devez d'abord selectionner un commentaire");
        }

    }

    public boolean MessageVide() {
        if (message.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    @FXML
    void HomeRed(MouseEvent event) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.setTitle("Acceuil");
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
