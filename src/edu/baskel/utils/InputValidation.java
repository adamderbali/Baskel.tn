
package edu.baskel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

    
}
