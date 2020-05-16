/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Hela
 */
public class PDFdoc {
   public static void PDFwriter(String Data)
   {
      Document document = new Document();
      try
      {
          //String file_name = "C:\\wamp\\www\\Baskel\\PDF\\HelloWorld.pdf";
         String file_name = "C:\\xampp\\htdocs\\Baskel\\PDF\\Liste_Des_Reservations.pdf";
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file_name));
         document.open();
         document.add(new Paragraph("La liste de mes reservations.\n"));
         document.add(new Paragraph(Data));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
}
