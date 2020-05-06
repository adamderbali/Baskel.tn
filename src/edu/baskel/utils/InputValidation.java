package edu.baskel.utils;

import java.awt.TrayIcon;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author dell
 */
public class InputValidation {

    public static boolean validTextField(String test) {
        return test.equals("");
    }

    public static int validPwd(String mdp) {
        int i = 0;
        if ((!mdp.equals(""))) {
            i = 1;
        }
        return i;
    }

    public static int StrengthPwd(String mdp) {
        int i = 0;
        if (mdp.length() < 6) {
            i = 1;
        }
        if (mdp.length() < 10) {
            i = 2;
        }
        if (mdp.length() > 10) {
            i = 3;
        }
        return i;
    }

    public static boolean validEmail(String email) {
        boolean status = false;

        String emailPattern = "^[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(CharSequence.class.cast(email));
        if (matcher.matches()) {
            status = true;
        }
        return status;
    }

    public static int PhoneNumber(String ch) {
        int i = 0;
        if (ch.length() == 8) {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }
    
    

    public Alert getAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    public void CopyImage(String url, String imageDestination) throws IOException {
        //URL l'emplacement de fichier image sous wamp exemple (http://localhost/image/product)
        InputStream inputStream = new FileInputStream(imageDestination);//upload l'image
        System.out.println("Start uploading second file");

        OutputStream output = new FileOutputStream(url);
        byte[] bytesIn = new byte[4096];
        int read = 1;
        while ((read = inputStream.read(bytesIn)) != -1) {//copier l'image au serveur
            output.write(bytesIn, 0, read);
        }
        System.out.println("uploaded");
        output.close();
        inputStream.close();
    }

    public static String HshPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(), 0, password.length());
        return password;
    }

    public static void notificationError(String title, String message) {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(3000));

    }

    public static void notificationsucces(String title, String message) {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }
}
