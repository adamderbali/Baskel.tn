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
public class AutoCompleteAdresse {

    public static String[] adrGov = {
    "Ariana",
    "Béja ",
    "Ben Arous", 
    "Bizerte",
    "Gabès ",
    "Gafsa",
    "Jendouba", 
    "Kairouan",
    "Kasserine ",
    "Kebili",
    "Kef ",
    "Mahdia",
    "Manouba",
    "Medenine",
    "Monastir",
    "Nabeul",
    "Sfax",
    "Sidi Bouzid",
    "Siliana",
    "Sousse",
    "Tataouine",
    "Tozeur",
    "Tunis",
    "Zaghouan",
};

    public static String[] getAdrGov() {
        return adrGov;
    }

    public static void setAdrGov(String[] adrGov) {
        AutoCompleteAdresse.adrGov = adrGov;
    }

}
