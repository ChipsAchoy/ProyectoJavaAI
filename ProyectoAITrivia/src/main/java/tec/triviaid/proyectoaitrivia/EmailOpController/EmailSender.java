/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.EmailOpController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author INTEL
 */


public class EmailSender {
    
    public void emailSend(String emailString, String contentString){
        
        String apiKey = "";
        String apiSecret = "";
        String fromEmail = "antoca29@gmail.com";
        String fromName = "Celeste Proyecto";
        String toEmail = emailString;
        String toName = "Estimado usuario";
        String subject = "Trivia";
        String htmlContent = "<html><body><p>"+ contentString +"</p></body></html>";

        OkHttpClient client = new OkHttpClient();

        String json = "{"
                + "\"Messages\":[{"
                + "\"From\":{"
                + "\"Email\":\"" + fromEmail + "\","
                + "\"Name\":\"" + fromName + "\""
                + "},"
                + "\"To\":[{"
                + "\"Email\":\"" + toEmail + "\","
                + "\"Name\":\"" + toName + "\""
                + "}],"
                + "\"Subject\":\"" + subject + "\","
                + "\"HTMLPart\":\"" + htmlContent + "\""
                + "}]"
                + "}";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        String credential = Credentials.basic(apiKey, apiSecret);

        Request request = new Request.Builder()
                .url("https://api.mailjet.com/v3.1/send")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", credential)
                .build();
        
        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("Unexpected code " + response);
            }

            System.out.println(response.body().string());
        } catch (IOException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
