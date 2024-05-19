package tec.triviaid.proyectoaitrivia;

/*
import java.util.Arrays;
import java.util.List;
import tec.triviaid.proyectoaitrivia.GraphGeneration.WordCloudGenerator;
import javax.swing.SwingUtilities;


public class ProyectoAITrivia {


    public static void main(String[] args) {
        List<String> categories = Arrays.asList(
                "art", "australia", "baby", "beach", "birthday", "blue", 
                "bw", "california", "canada", "canon", "cat", "chicago", 
                "china", "christmas", "city", "dog", "england", "europe", 
                "family", "festival", "flower", "flowers", "food", "france", 
                "friends", "fun", "germany", "holiday", "india", "italy", 
                "japan", "london", "me", "mexico", "music", "nature", 
                "new", "newyork", "night", "nikon", "nyc", "paris", "park", 
                "party", "people", "portrait", "sanfrancisco", "sky", "snow", 
                "spain", "summer", "sunset", "taiwan", "tokyo", "travel", 
                "trip", "uk", "usa", "vacation", "water", "wedding"
            );
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCloudGenerator().initUI(categories);
            }
        });
    }

}
*/



import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tec.triviaid.proyectoaitrivia.EmailOpController.EmailSender;
import tec.triviaid.proyectoaitrivia.EmailOpController.EmailValidation;
import tec.triviaid.proyectoaitrivia.EmotionAnalyzer.EmotionGetter;
import tec.triviaid.proyectoaitrivia.apicontroller.OpenAIConnector;
import tec.triviaid.proyectoaitrivia.JsonController.JsonExtractor;

import java.util.ArrayList;
import java.util.List;
import tec.triviaid.proyectoaitrivia.FileController.FileOperations;
import tec.triviaid.proyectoaitrivia.FileController.PDFCreator;
import tec.triviaid.proyectoaitrivia.GraphGeneration.WordCloudGenerator;


public class ProyectoAITrivia {

    public static void main(String[] args) {

        int cantidad_preguntas = 6;
        String idioma = "Español";
        String tema = "Capitales";
        String respuesta = "";
        String respuesta_content = "";
        OpenAIConnector aiconn = new OpenAIConnector("");
        EmailSender emcont = new EmailSender();
        EmailValidation emval = new EmailValidation();

        JsonExtractor jext = new JsonExtractor();
        
        FileOperations fp = new FileOperations("Feedback/feedback.json");
        try {
            List<String> listS = fp.readComments();
            for (String item : listS) {
                System.out.println(item);
            }
            fp.appendToJson("Inmejorable");
            listS = fp.readComments();
            for (String item : listS) {
                System.out.println(item);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProyectoAITrivia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //pdfCreate.createPDF(, , , , , , );
            
           
            
            try {
            respuesta = aiconn.generateTextCompletion("Dame " + String.valueOf(cantidad_preguntas) + " relacionadas al tema " + tema + " en el idioma " + idioma);
            respuesta_content = jext.jsonFromAIExtract(respuesta);

            System.out.println(respuesta_content);

            String enunciado1 = jext.getQuestionEnunciado(respuesta_content, "pregunta1");

            System.out.println("Enunciado: " + enunciado1);

            List<String> enunciadosPreguntas = jext.getQuestionRespuestasEnunciados(respuesta_content, "pregunta1");

            for (String item : enunciadosPreguntas) {
            System.out.println(item);
            }

            int respuestaState = jext.verifyAnswer(respuesta_content, "pregunta3", 2);
            
            if (respuestaState == 0){
            System.out.println("Incorrecta!!!!!");
            }else{
            System.out.println("Correcta!!!");
            }
            

            //int valid = emval.emailValidator("antoca29@gmail.com");
            //emcont.emailSend("divad0907@gmail.com", "pruebas");
            //EmotionGetter emget = new EmotionGetter();
            //System.out.println(emget.getReaction("It was a neutral experience"));
            } catch (IOException ex) {
            Logger.getLogger(ProyectoAITrivia.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Integer> numbers = List.of(3, 3, 3, 3, 3, 2);
            List<String> answersByNumbers = jext.getAnswersByNumbers(numbers, respuesta_content);
            
            PDFCreator pdfCreate = new PDFCreator();
            pdfCreate.createPDF("PDFSend/file.pdf", jext.getQuestionStatements(respuesta_content), 
                    jext.getAllAnswerStatements(respuesta_content), answersByNumbers,
                    jext.getCorrectAnswers(respuesta_content), "Graficos/piechart.png",1, (float)23.5 );
        
        

    }
}



/*
import javax.swing.JFrame;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import tec.triviaid.proyectoaitrivia.GraphGeneration.PieChartGenerator;

public class ProyectoAITrivia extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            List<String> categories = Arrays.asList("Categoría A", "Categoría B", "Categoría C", "Categoría D");
            List<Integer> values = Arrays.asList(45, 25, 20, 10);
            
            PieChartGenerator example = new PieChartGenerator("Ejemplo de Gráfico de Pastel", categories, values);
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}

*/
