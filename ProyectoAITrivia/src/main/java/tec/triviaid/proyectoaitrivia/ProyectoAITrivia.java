/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tec.triviaid.proyectoaitrivia;

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



/**
 *
 * @author INTEL
 */
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
        try {
            respuesta = aiconn.generateTextCompletion("Dame "+String.valueOf(cantidad_preguntas)+" relacionadas al tema "+tema+" en el idioma "+idioma);
            respuesta_content = jext.jsonFromAIExtract(respuesta);
            
            System.out.println(respuesta_content);
            
            String enunciado1 = jext.getQuestionEnunciado(respuesta_content, "pregunta1");
            
            System.out.println("Enunciado: "+enunciado1);
            
            List<String> enunciadosPreguntas = jext.getQuestionRespuestasEnunciados(respuesta_content, "pregunta1");
            
            for (String item : enunciadosPreguntas) {
                System.out.println(item);
            }
            
            /*
            int respuestaState = jext.verifyAnswer(respuesta_content, "pregunta3", 2);
            
            if (respuestaState == 0){
                System.out.println("Incorrecta!!!!!");
            }else{
                System.out.println("Correcta!!!");
            }
            
            */

            
            //int valid = emval.emailValidator("antoca29@gmail.com");
            //emcont.emailSend("divad0907@gmail.com", "pruebas");
            //EmotionGetter emget = new EmotionGetter();
            //System.out.println(emget.getReaction("It was a neutral experience"));
            
  
        } catch (IOException ex) {
            Logger.getLogger(ProyectoAITrivia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
