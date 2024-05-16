/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.EmailOpController;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author INTEL
 */
public class EmailValidation {
    
    
    private int validateStatus(String status){
        
        if ("ok".equals(status)){
            System.out.println("Validated");
            return 1;
        }else{
            System.out.println("Not Validated");
            return 0;
        }
        
    }
    
    
    public int emailValidator(String email){
        
        //String email = "test@example.com"; // Reemplaza con el email que deseas validar
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://apps.emaillistverify.com/api/verifyEmail").newBuilder();
        urlBuilder.addQueryParameter("secret", "");
        urlBuilder.addQueryParameter("email", email);

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
        
        return this.validateStatus(responseBody);
        
    }
}
