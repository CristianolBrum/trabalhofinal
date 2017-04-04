package bean;

import com.itextpdf.text.Annotation;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import com.itextpdf.text.Image;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PDFModify {

    GenerateQRCode generatedQRCode = new GenerateQRCode();

    public void setGeneratedQRCode(GenerateQRCode generatedQRCode) {
        this.generatedQRCode = generatedQRCode;
    }

    public void modificaPDF(String url, String nome) {
        try {

            String qrCodeText = url+nome;
            System.out.println("valor contido na variavel qrFile: "+qrCodeText);
            File diretorio = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\qrcode"+nome);
            if (!diretorio.exists()){
                diretorio.mkdirs();
            }
            String filePath = diretorio+nome+".png";
            int size = 125;
            String fileType = "png";
            File qrFile = new File(filePath);
            
            generatedQRCode.createQRImage(qrFile, qrCodeText, size, fileType);
            System.out.println("DONE");
            //Create PdfReader instance.
            File diretorio2 = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\fotos");
            if (!diretorio2.exists()){
                diretorio2.mkdirs();
            }
            PdfReader pdfReader = new PdfReader(diretorio2+nome);

            //Create PdfStamper instance.
            File diretorio3 = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\pdf");
            if (!diretorio3.exists()){
                diretorio3.mkdirs();
            }
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream( diretorio3+nome));
            PdfContentByte content = pdfStamper.getOverContent(1);
            Image image = Image.getInstance(diretorio+nome+".png");

            //Get the number of pages in pdf.
            

            //Iterate the pdf through pages.
           
                image.scaleAbsolute(100, 100);
                image.setAbsolutePosition(0, 0);
                image.setBorder(0);
                image.setBorderWidth(0.0f);
                image.setAnnotation(new Annotation(0, 0, 0, 0, 3));
                content.addImage(image);
                
          

            //Close the pdfStamper.
            pdfStamper.close();

            System.out.println("PDF modificado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
