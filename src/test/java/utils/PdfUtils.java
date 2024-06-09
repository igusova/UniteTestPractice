package utils;

import io.restassured.response.Response;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PdfUtils {
    public void savePdf (Response response, String fileName) {
        if (response != null) {
            try (InputStream inputStream = response.getBody().asInputStream()) {
                OutputStream outputStream = new FileOutputStream(fileName);

                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer,0,bytesRead);
                }

                inputStream.close();
                outputStream.close();

                System.out.println("PDF успешно сохранен в файл: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String readPdf (String fileName) {
        String text = null;
        try {
            File file = new File(fileName);
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
