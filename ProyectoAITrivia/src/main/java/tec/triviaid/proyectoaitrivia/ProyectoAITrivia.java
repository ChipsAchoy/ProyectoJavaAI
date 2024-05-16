/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tec.triviaid.proyectoaitrivia;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tec.triviaid.proyectoaitrivia.apicontroller.OpenAIConnector;
import tec.triviaid.proyectoaitrivia.JsonController.JsonExtractor;

public class ProyectoAITrivia {

    public static void main(String[] args) {

        
        
        int cantidad_preguntas = 6;
        String idioma = "Español";
        String tema = "Capitales";
        String respuesta = "";
        String respuesta_content = "";
        OpenAIConnector aiconn = new OpenAIConnector("sk-proj-rHXxO6XTcUSjjUs7lG9YT3BlbkFJeyVhYU1JzE0OWy5oIUX4");
        
        JsonExtractor jext = new JsonExtractor();
        try {
            respuesta = aiconn.generateTextCompletion("Dame "+String.valueOf(cantidad_preguntas)+" relacionadas al tema "+tema+" en el idioma "+idioma);
            respuesta_content = jext.jsonFromAIExtract(respuesta);
            
            //System.out.println(respuesta_content);
            int respuestaState = jext.verifyAnswer(respuesta_content, "pregunta3", 2);
            
            if (respuestaState == 0){
                System.out.println("Incorrecta!!!!!");
            }else{
                System.out.println("Correcta!!!");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ProyectoAITrivia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
