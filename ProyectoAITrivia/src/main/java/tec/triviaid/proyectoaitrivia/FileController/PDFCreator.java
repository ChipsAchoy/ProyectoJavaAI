package tec.triviaid.proyectoaitrivia.FileController;

import java.awt.image.BufferedImage;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFCreator {

    public void createPDF(String dest, List<String> preguntas, List<String> respuestas, List<String> respuestasJugador, List<String> respuestasCorrectas, String imagePath, int comodinesUsados, float avgTime, int correctas, int incorrectas) {
        try (PDDocument document = new PDDocument()) {
            // Crear una página con una altura mucho mayor
            PDRectangle customPageSize = new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight() * 3); // 10 veces la altura de una página A4
            PDPage page = new PDPage(customPageSize);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(100, customPageSize.getHeight() - 50);
                contentStream.showText("Reporte de Trivia");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.newLineAtOffset(100, customPageSize.getHeight() - 100);
                contentStream.showText("Preguntas:");
                contentStream.endText();

                float y = customPageSize.getHeight() - 130;
                for (int i = 0; i < preguntas.size(); i++) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 8);
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- " + preguntas.get(i));
                    contentStream.newLineAtOffset(20, -15);
                    contentStream.showText("- " + respuestas.get(i * 4));
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("- " + respuestas.get(i * 4 + 1));
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("- " + respuestas.get(i * 4 + 2));
                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText("- " + respuestas.get(i * 4 + 3));
                    contentStream.endText();
                    y -= 90;
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Respuestas del jugador:");
                contentStream.endText();

                y -= 40;
                for (String respuestaJugador : respuestasJugador) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 8);
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- " + respuestaJugador);
                    contentStream.endText();
                    y -= 15;
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Respuestas correctas:");
                contentStream.endText();

                y -= 40;
                for (String respuestaCorrecta : respuestasCorrectas) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 8);
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- " + respuestaCorrecta);
                    contentStream.endText();
                    y -= 15;
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Resumen de Estadísticas:");
                contentStream.endText();

                y -= 40;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Comodines Usados: " + comodinesUsados);
                contentStream.endText();

                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Tiempo Promedio: " + avgTime);
                contentStream.endText();

                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Respondidas correctamente: " + correctas);
                contentStream.endText();

                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Respondidas incorrectamente: " + incorrectas);
                contentStream.endText();

                contentStream.close();

                // Crear una nueva página para la imagen
                if (imagePath != null && !imagePath.isEmpty()) {
                    PDPage imagePage = new PDPage();
                    document.addPage(imagePage);

                    try (PDPageContentStream imageContentStream = new PDPageContentStream(document, imagePage)) {
                        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
                        int imgWidth = bufferedImage.getWidth();
                        int imgHeight = bufferedImage.getHeight();
                        float maxWidth = imagePage.getMediaBox().getWidth() - 100;
                        float maxHeight = imagePage.getMediaBox().getHeight() - 100;
                        float widthScale = maxWidth / imgWidth;
                        float heightScale = maxHeight / imgHeight;
                        float scale = Math.min(widthScale, heightScale);

                        int newWidth = (int) (imgWidth * scale);
                        int newHeight = (int) (imgHeight * scale);

                        float x = (imagePage.getMediaBox().getWidth() - newWidth) / 2;
                        float yImage = (imagePage.getMediaBox().getHeight() - newHeight) / 2;
                        imageContentStream.drawImage(PDImageXObject.createFromFile(imagePath, document), x, yImage, newWidth, newHeight);
                    }
                }
            }

            document.save(dest);
        } catch (IOException e) {
            Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
