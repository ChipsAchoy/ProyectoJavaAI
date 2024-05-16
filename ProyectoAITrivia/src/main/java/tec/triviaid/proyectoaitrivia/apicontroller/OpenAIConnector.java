/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




package tec.triviaid.proyectoaitrivia.apicontroller;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenAIConnector {

    private String apiKey;
    private OkHttpClient client;

    public OpenAIConnector(String apiKey) {
        this.apiKey = apiKey;
        // Configura un nuevo OkHttpClient.Builder basado en el cliente existente
        
        this.client = new OkHttpClient();
        OkHttpClient.Builder builder = this.client.newBuilder();
        
        
        // Establece el tiempo de espera de conexión y lectura en el nuevo builder
        builder.connectTimeout(30, TimeUnit.SECONDS); // Ajusta el tiempo de espera de conexión
        builder.readTimeout(60, TimeUnit.SECONDS); // Ajusta el tiempo de espera de lectura

        // Crea un nuevo OkHttpClient con la configuración actualizada
        this.client = builder.build();
    }

    public String generateTextCompletion(String prompt) throws IOException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // Crea el cuerpo de la solicitud en formato JSON
        //String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": \"" + prompt + "\",";
        
        String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": ["
                + "{\"role\": \"system\", \"content\": \"Eres un generador de preguntas y respuestas (siempre das 4 respuestas posibles por pregunta) para una trivia que responde en este formato JSON (SOLO RESPONDE EL JSON) "
                + " [{pregunta1: {enunciado:x, respuestas:{numero:1, enunciado:y, correcta:0 (0 o 1 segun si es correcta o no)}, {numero:1, enunciado:y, correcta:1 (0 o 1 segun si es correcta o no)}, {numero:3...} }, pregunta2...}]\"},"
                + "{\"role\": \"user\", \"content\": \"" + prompt + "\"}"
                + "]}";
        
        // Crea una solicitud HTTP POST
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody))
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        // Ejecuta la solicitud y obtén la respuesta
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                System.err.println("Error en la solicitud: " + response.code() + " " + response.message());
                System.err.println("Body de la respuesta: " + response.body().string());
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error al ejecutar la solicitud: " + e.getMessage());
            throw e; // relanza la excepción para manejarla en un nivel superior si es necesario
        }
        
        
    }
}