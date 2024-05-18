/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.FileController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileOperations {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final File file;
    private int current_size;
    
    public FileOperations(String filePath) {
        this.file = new File(filePath);
    }

    public List<String> readComments() throws IOException {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        Map<String, List<Map<String, Object>>> data = objectMapper.readValue(file, new TypeReference<Map<String, List<Map<String, Object>>>>() {});
        List<Map<String, Object>> feedbackList = data.getOrDefault("feedback", new ArrayList<>());
        this.current_size = 0;
        List<String> comments = new ArrayList<>();
        for (Map<String, Object> feedback : feedbackList) {
            comments.add((String) feedback.get("comentario"));
            this.current_size++;
        }

        return comments;
    }

    public void appendToJson(String comentario) throws IOException {
        
        int numero = this.current_size;
        Map<String, List<Map<String, Object>>> data = new HashMap<>();
        if (file.exists()) {
            data = objectMapper.readValue(file, new TypeReference<Map<String, List<Map<String, Object>>>>() {});
        }
        
        List<Map<String, Object>> feedbackList = data.getOrDefault("feedback", new ArrayList<>());

        Map<String, Object> newFeedback = new HashMap<>();
        newFeedback.put("numero", numero);
        newFeedback.put("comentario", comentario);

        feedbackList.add(newFeedback);
        data.put("feedback", feedbackList);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
    }
    
}