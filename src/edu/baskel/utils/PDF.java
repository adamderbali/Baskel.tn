/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class PDF {

    public static void PDFwriter() throws DocumentException {
        try {
            String file_name = "C:\\wamp\\www\\Baskel\\PDF\\BaseklTn.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();

            Paragraph par = new Paragraph("salut");
            document.add(par);

            document.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static void pdfRead() {
        try {
            Desktop.getDesktop().open(new File("C:\\wamp\\www\\Baskel\\PDF\\BaseklTn.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
