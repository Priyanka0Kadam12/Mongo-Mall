package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class JavaPdfHelloWorld
{
   public JavaPdfHelloWorld()
   {}
   public static void createDocument(String customer){
      Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("f:/Download/SEM -VI/project/eclipse marse 1 workspace/MongoMall/reports/"+customer+".pdf"));
         document.open();
         document.add(new Paragraph(customer+"\n"+SelectedProductList.str+"\n\nTotal :"+ItemWindow.cost));
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