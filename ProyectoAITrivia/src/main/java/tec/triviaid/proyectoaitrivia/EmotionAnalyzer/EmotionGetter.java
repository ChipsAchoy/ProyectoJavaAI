/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.EmotionAnalyzer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author INTEL
 */
public class EmotionGetter {
    
    
    
    private String getEmotionRange(int range){
        
        
        if (range > 3){
            return "Bueno";
        }else if (range > 2 && range <3) {
            return "Neutral";
        }else{
            return "Malo";
        }
        
    }
    
    public String getReaction(String text){
        
        //String email = "test@example.com"; // Reemplaza con el email que deseas validar
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://us-central1-proyectosoa-422123.cloudfunctions.net/get_emotions").newBuilder();
        urlBuilder.addQueryParameter("text", text);

        String requestUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .build();
        
        
        String responseBody = "";
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Unexpected code " + response);
            }

            responseBody = response.body().string();
            System.out.println("Response " + responseBody);
            //JSONObject jsonObject = new JSONObject(responseBody);
            //String status = jsonObject.getString("status");
            //System.out.println("Status: " + status);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JSONObject jsonObject = new JSONObject(responseBody);

        // Extract the value of "data" as an integer
        int dataValue = jsonObject.getInt("data");
        return this.getEmotionRange(dataValue);
    }
    
}
