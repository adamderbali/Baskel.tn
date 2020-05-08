/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

/**
 *
 * @author dell
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class verifEmail {

    private static final String ApiUrl = "http://api.emailverifyapi.com/api/a/v1";
    private static final String QueryFormatString = "%1$s?email=%2$s&key=%3$s";
    private static final String YourAPIKey = "35AE5C00DCE45C8F";

    public static boolean check(String email) {
        System.out.println(email);

        // Create a scanner to read in the requested email address
        Scanner in = new Scanner(email);
        String readLine = in.next();

        try {
            // Format the request url to the correct structure for the request
            URL requestUrl = new URL(String.format(QueryFormatString, ApiUrl, readLine, YourAPIKey));

            // Open a connection to the website
            HttpURLConnection myRequest = (HttpURLConnection) requestUrl.openConnection();

            // Set the type to HTTP GET
            myRequest.setRequestMethod("GET");

            // Create a new buffered reader to read the response back from the server
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(myRequest.getInputStream()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            // Read in the response line from the server
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            // Close the reader
            reader.close();

            // Output the result to console
            System.out.println(response.toString());
            if (response.toString().equals("{\"status\":\"Ok\",\"additionalStatus\":\"Success\",\"emailAddressProvided\":\""+email+"\",\"emailAddressChecked\":\""+email+"\",\"emailAddressSuggestion\":\"\"}")) {
                System.out.println("ok");
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            return false;
/*
    }
    public static void main(String[] args) {
        check("rfgchj@gmail.com");
    }*/
}
}
