package tec.triviaid.proyectoaitrivia.FileController;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFCreator {

    public void createPDF(String dest, List<String> preguntas, List<String> respuestas, List<String> respuestasJugador, List<String> respuestasCorrectas, String imagePath, int comodinesUsados, float avgTime, int correctas, int incorrectas) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Reporte de Trivia");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.newLineAtOffset(100, 650);
                contentStream.showText("Preguntas:");
                contentStream.endText();

                float y = 630;
                for (int i = 0; i < preguntas.size(); i++) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
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
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Respuestas del jugador:");
                contentStream.endText();

                y -= 40;
                for (String respuestaJugador : respuestasJugador) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- " + respuestaJugador);
                    contentStream.endText();
                    y -= 15;
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Respuestas correctas:");
                contentStream.endText();

                y -= 40;
                for (String respuestaCorrecta : respuestasCorrectas) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- " + respuestaCorrecta);
                    contentStream.endText();
                    y -= 15;
                }

                if (imagePath != null && !imagePath.isEmpty()) {
                    // No se puede agregar imágenes directamente con PDFBox en este ejemplo.
                    // Tendrías que investigar cómo agregar imágenes usando PDFBox si lo necesitas.
                    // Aquí simplemente omitimos este paso.
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Resumen de Estadísticas:");
                contentStream.endText();

                y -= 40;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Comodines Usados: " + comodinesUsados);
                contentStream.endText();

                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Tiempo Promedio: " + avgTime);
                contentStream.endText();
                
                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Respondidas correctamente: " + correctas);
                contentStream.endText();
                
                y -= 20;
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("Respondidas incorrectamente: " + incorrectas);
                contentStream.endText();
            }

            document.save(dest);
        } catch (IOException e) {
            Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
