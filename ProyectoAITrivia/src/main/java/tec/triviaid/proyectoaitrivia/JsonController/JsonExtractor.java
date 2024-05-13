/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.JsonController;

/**
 *
 * @author INTEL
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class JsonExtractor {
    
    
    public String jsonFromAIExtract(String jsonString){
        
        String content_parsed = "";
        String content = "";
        try {
            // Convertir la cadena JSON en un objeto JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Obtener el array "choices" del objeto JSON
            JSONArray choicesArray = jsonObject.getJSONArray("choices");

            // Iterar sobre los elementos del array "choices"
            for (int i = 0; i < choicesArray.length(); i++) {
                JSONObject choiceObject = choicesArray.getJSONObject(i);

                // Obtener el objeto "message" dentro de cada elemento de "choices"
                JSONObject messageObject = choiceObject.getJSONObject("message");

                // Obtener el valor de "content" dentro del objeto "message"
                content = messageObject.getString("content");

                // Imprimir el contenido obtenido
                System.out.println("Contenido del mensaje: " + content);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return content;
    }
    
}
