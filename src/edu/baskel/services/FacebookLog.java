/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author dell
 */
public class FacebookLog {

    public void fb() {
        String domain = "http://localhost:3306/";
        String appId = "564610487520090";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri="
                + domain + "&scope=email,user_birthday";
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        //Boolean test = true;
        while (true) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                System.out.println(url);
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                driver.quit();
                

                FacebookClient fbClient = new DefaultFacebookClient(accessToken,Version.LATEST);
                User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name,gender"));
                // System.out.println(user.getEmail()+user.getGender());
                System.out.println(user.getGender());
                System.out.println(user.getEmail());
                System.out.println(user.getBirthday());
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getPicture());

            }

        }
    }
}
