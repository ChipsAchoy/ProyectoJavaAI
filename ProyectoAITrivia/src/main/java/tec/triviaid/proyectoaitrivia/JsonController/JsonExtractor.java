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
    
    public String content;
    
    
    public int verifyAnswer(String jsonString, String pregunta, int numeroRespuesta) {
        // JSON en formato String
        
        // Convertir el JSON String a un JSONArray
        JSONArray jsonArray = new JSONArray(jsonString);
        
        // Buscar la pregunta especificada en el JSONArray
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject preguntaObj = jsonArray.getJSONObject(i);
            
            if (preguntaObj.has(pregunta)) {
                JSONObject preguntaData = preguntaObj.getJSONObject(pregunta);
                
                // Verificar las respuestas de la pregunta
                JSONArray respuestasArray = preguntaData.getJSONArray("respuestas");
                
                for (int j = 0; j < respuestasArray.length(); j++) {
                    JSONObject respuestaObj = respuestasArray.getJSONObject(j);
                    
                    if (respuestaObj.getInt("numero") == numeroRespuesta) {
                        return respuestaObj.getInt("correcta"); // Retorna 1 o 0
                    }
                }
            }
        }
        
        // Si no se encuentra la pregunta o la respuesta, retorna -1 (indicando no encontrado)
        return -1;
    }
    
    
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
                this.content = messageObject.getString("content");

                // Imprimir el contenido obtenido
                System.out.println("Contenido del mensaje: " + this.content);
            }
            
            

        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return this.content;
    }
    
}
