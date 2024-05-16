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
       conn = new OpenAIConnector("sk-proj-rHXxO6XTcUSjjUs7lG9YT3BlbkFJeyVhYU1JzE0OWy5oIUX4");
       jext = new JsonExtractor();
   }


   public Trivia generateTrivia(String idioma, String tema, int cantidad_preguntas) {
       String respuesta;
       respuesta = "";
       String respuesta_content = "";
       try {
            respuesta = conn.generateTextCompletion("Dame " + String.valueOf(cantidad_preguntas) + " relacionadas al tema " + tema + " en el idioma " + idioma);
            respuesta_content = jext.jsonFromAIExtract(respuesta);

            Trivia trivia = new Trivia(respuesta_content);
            
            return trivia;


       } catch (IOException e) {
           System.out.println("Error en la generacion de trivia");
       }
       return null;
   }

   public int countFilesInFolder(String folderPath) {
        // Create a File object representing the folder
        System.out.println("Provided folder path: " + folderPath);
        File folder = new File(folderPath);

        // Ensure that the specified path is a directory
        if (!folder.isDirectory()) {
            System.out.println("The specified path is not a directory.");
            return -1; // Return -1 to indicate an error
        }

        // List the files in the directory
        File[] files = folder.listFiles();

        // Count the number of files
        int fileCount = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileCount++;
                }
            }
        }

        return fileCount;
    }

    public boolean SerializeTrivia(Trivia trivia, String Nombre, String Tema, String Idioma, int CantidadPreguntas) {

        try {
            // Get the number of trivia files in the folder
            int triviaCount = countFilesInFolder("Trivias");
            
            String fileName = "Trivias/" + Nombre + "_" + Tema + "_" + Idioma + "_" + CantidadPreguntas + "_" + triviaCount + ".ser";

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(trivia);
            out.close();
            fileOut.close();


        } catch (IOException i) {
            return false;
        } 
       return false;
            
    }

    
}
