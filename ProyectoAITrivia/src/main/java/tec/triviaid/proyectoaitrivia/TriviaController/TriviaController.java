/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.TriviaController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tec.triviaid.proyectoaitrivia.JsonController.JsonExtractor;
import tec.triviaid.proyectoaitrivia.apicontroller.OpenAIConnector;


public class TriviaController {
    
   OpenAIConnector conn;

   JsonExtractor jext;

   public TriviaController() {
       conn = new OpenAIConnector("");
       jext = new JsonExtractor();
   }


   public Trivia generateTrivia(String idioma, String tema, int cantidad_preguntas, int tiempo_pregunta) {
       String respuesta;
       respuesta = "";
       String respuesta_content = "";
       try {
            respuesta = conn.generateTextCompletion("Dame " + String.valueOf(cantidad_preguntas) + " relacionadas al tema " + tema + " en el idioma " + idioma);
            respuesta_content = jext.jsonFromAIExtract(respuesta);

            Trivia trivia = new Trivia(respuesta_content, tema, idioma, cantidad_preguntas, tiempo_pregunta);
            System.out.println("Trivia: "+trivia.toString());
            
            return trivia;


       } catch (IOException e) {
           System.out.println("Error en la generacion de trivia");
       }
       return null;
   }

   

    public boolean SerializeTrivia(Trivia trivia, String Nombre, String Tema, String Idioma, int CantidadPreguntas) {
        boolean flag = false;
        try {
            
            String fileName = "Trivias/"+Nombre + "_" + Tema + "_" + Idioma + "_" + String.valueOf(CantidadPreguntas) + ".bin";

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(trivia);
            out.close();
            fileOut.close();

            flag = true;

        } catch (IOException i) {
            System.err.println("Error al serializar la trivia");
            System.err.println(i.getMessage());
            flag = false;
        } 
       
        return flag;
    }


    //Deserialize all the trivia in the Trivias folder and return them in an array
    public Trivia[] getTrivias() {
        
        File folder = new File("Trivias");
        File[] listOfFiles = folder.listFiles();
        Trivia[] trivias = new Trivia[listOfFiles.length];
        try {
            for (int i = 0; i < listOfFiles.length; i++) {
                FileInputStream fileIn = new FileInputStream(listOfFiles[i]);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                trivias[i] = (Trivia) in.readObject();
                in.close();
                fileIn.close();
            }
        } catch (IOException i) {
            System.err.println("Error al deserializar la trivia");
            System.err.println(i.getMessage());
        } catch (ClassNotFoundException c) {
            System.err.println("Error al deserializar la trivia");
            System.err.println(c.getMessage());
        }
        return trivias;
    }
    
}
