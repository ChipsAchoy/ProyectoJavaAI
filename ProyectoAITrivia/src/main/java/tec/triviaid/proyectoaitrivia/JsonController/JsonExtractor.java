/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.JsonController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class JsonExtractor {
    
    public String content;
    
    public String getQuestionEnunciado(String jsonString, String questionKey) {
        
        
        JSONArray questionsArray = new JSONArray(jsonString);
        for (int i = 0; i < questionsArray.length(); i++) {
            
            if(questionsArray.getJSONObject(i).has(questionKey)){
                JSONObject questionObject = questionsArray.getJSONObject(i).getJSONObject(questionKey);
                if (questionObject != null) {
                    return questionObject.getString("enunciado");
                }
            }
            
        }
        return null;
    }
    
    public List<String> getQuestionRespuestasEnunciados(String jsonString, String questionKey) {
        List<String> enunciados = new ArrayList<>();

        try {
            JSONArray questionsArray = new JSONArray(jsonString);

            for (int i = 0; i < questionsArray.length(); i++) {
                if (questionsArray.getJSONObject(i).has(questionKey)) {
                    JSONObject questionObject = questionsArray.getJSONObject(i).getJSONObject(questionKey);

                    if (questionObject != null && questionObject.has("respuestas")) {
                        JSONArray respuestasArray = questionObject.getJSONArray("respuestas");

                        for (int j = 0; j < respuestasArray.length(); j++) {
                            enunciados.add(respuestasArray.getJSONObject(j).getString("enunciado"));
                        }
                    } 
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error procesando el JSON: " + e.getMessage());
        }

        return enunciados;
    }

    public List<String> getCorrectAnswers(String content) {
        List<String> correctAnswers = new ArrayList<>();
        JSONArray rootArray = new JSONArray(content);

        for (int i = 0; i < rootArray.length(); i++) {
            JSONObject questionObject = rootArray.getJSONObject(i);
            String questionKey = questionObject.keys().next();
            JSONObject questionDetails = questionObject.getJSONObject(questionKey);
            JSONArray answers = questionDetails.getJSONArray("respuestas");

            for (int j = 0; j < answers.length(); j++) {
                JSONObject answer = answers.getJSONObject(j);
                if (answer.getInt("correcta") == 1) {
                    correctAnswers.add(answer.getString("enunciado"));
                }
            }
        }

        return correctAnswers;
    }

    public List<String> getAnswersByNumbers(List<Integer> numbers, String content) {
        List<String> answersByNumbers = new ArrayList<>();
        JSONArray  rootArray = new JSONArray(content);

        for (int i = 0; i < rootArray.length(); i++) {
            JSONObject questionObject = rootArray.getJSONObject(i);
            String questionKey = questionObject.keys().next();
            JSONObject questionDetails = questionObject.getJSONObject(questionKey);
            JSONArray answers = questionDetails.getJSONArray("respuestas");

            int requestedNumber = numbers.get(i);
            for (int j = 0; j < answers.length(); j++) {
                JSONObject answer = answers.getJSONObject(j);
                if (answer.getInt("numero") == requestedNumber) {
                    answersByNumbers.add(answer.getString("enunciado"));
                }
            }
        }

        return answersByNumbers;
    }
    
    public List<String> getQuestionStatements(String content) {
        List<String> questionStatements = new ArrayList<>();
        JSONArray rootArray = new JSONArray(content);
        for (int i = 0; i < rootArray.length(); i++) {
            JSONObject questionObject = rootArray.getJSONObject(i);
            String questionKey = questionObject.keys().next();
            JSONObject questionDetails = questionObject.getJSONObject(questionKey);
            questionStatements.add(questionDetails.getString("enunciado"));
        }

        return questionStatements;
    }

    public List<String> getAllAnswerStatements(String content) {
        List<String> answerStatements = new ArrayList<>();
        JSONArray rootArray = new JSONArray(content);
        for (int i = 0; i < rootArray.length(); i++) {
            JSONObject questionObject = rootArray.getJSONObject(i);
            String questionKey = questionObject.keys().next();
            JSONObject questionDetails = questionObject.getJSONObject(questionKey);
            JSONArray answers = questionDetails.getJSONArray("respuestas");

            for (int j = 0; j < answers.length(); j++) {
                JSONObject answer = answers.getJSONObject(j);
                answerStatements.add(answer.getString("enunciado"));
            }
        }

        return answerStatements;
    }
    
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
