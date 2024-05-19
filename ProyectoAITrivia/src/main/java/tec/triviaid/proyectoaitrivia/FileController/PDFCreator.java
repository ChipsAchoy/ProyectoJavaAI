/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.FileController;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;



import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tec.triviaid.proyectoaitrivia.ProyectoAITrivia;

public class PDFCreator {

    public void createPDF(String dest, List<String> preguntas, List<String> respuestas,List<String> respuestasJugador, List<String> respuestasCorrectas, String imagePath, int comodinesUsados, float avgTime) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Trivia")
                    .setFontSize(20)
                    .setFontColor(ColorConstants.BLUE));

            document.add(new Paragraph("Preguntas:")
                    .setFontSize(16)
                    .setBold());

            for (int i=0 ; i<preguntas.size(); i++) {
                document.add(new Paragraph("- " + preguntas.get(i))
                        .setFontSize(12));
                document.add(new Paragraph("- " + respuestas.get(i*4))
                        .setFontSize(12));
                document.add(new Paragraph("- " + respuestas.get(i*4+1))
                        .setFontSize(12));
                document.add(new Paragraph("- " + respuestas.get(i*4+2))
                        .setFontSize(12));
                document.add(new Paragraph("- " + respuestas.get(i*4+3))
                        .setFontSize(12));
            }

            document.add(new Paragraph("Respuestas del jugador:")
                    .setFontSize(16)
                    .setBold()
                    .setMarginTop(20));

            for (String respuestaJugador : respuestasJugador) {
                document.add(new Paragraph("- " + respuestaJugador)
                        .setFontSize(12));
            }
            
            document.add(new Paragraph("Respuestas correctas:")
                    .setFontSize(16)
                    .setBold()
                    .setMarginTop(20));

            for (String respuestaCorrecta : respuestasCorrectas) {
                document.add(new Paragraph("- " + respuestaCorrecta)
                        .setFontSize(12));
            }

            if (imagePath != null && !imagePath.isEmpty()) {
                ImageData imageData = null;
                try {
                    imageData = ImageDataFactory.create(imagePath);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(PDFCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image image = new Image(imageData);
                image.setAutoScale(true);
                document.add(new Paragraph("Imagen Relacionada:")
                        .setFontSize(16)
                        .setBold()
                        .setMarginTop(20));
                document.add(image);
            }

            document.add(new Paragraph("Resumen de Comodines y Tiempo Promedio:")
                    .setFontSize(16)
                    .setBold()
                    .setMarginTop(20));

            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();
            table.addCell("Comodines Usados");
            table.addCell(String.valueOf(comodinesUsados));
            table.addCell("Tiempo Promedio");
            table.addCell(String.valueOf(avgTime));

            document.add(table);

            document.close();
        } catch (Exception e) {
            Logger.getLogger(ProyectoAITrivia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}