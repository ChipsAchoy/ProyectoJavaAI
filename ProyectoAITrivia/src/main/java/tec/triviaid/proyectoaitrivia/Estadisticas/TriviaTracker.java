/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.Estadisticas;

import java.util.List;


public class TriviaTracker {

    private int correctas = 0;
    private int incorrectas = 0;
    private int total_preguntas = 0;
    
    private int[] preguntas;

    private int tiempoxpregunta = 0;
    private int remainingSeconds = 0;
    private float tiempoAvrg = 0;
    private int comodinesUsados = 0;
    private List<String> respuestas;

    public TriviaTracker(int total_preguntas) {
        this.total_preguntas = total_preguntas;
        preguntas = new int[total_preguntas];
        this.respuestas = new java.util.ArrayList<>();
    }

    public void add_average() {
        this.tiempoAvrg += (tiempoxpregunta*60) - remainingSeconds;
    }

    public void setRemainingSeconds(int seconds) {
        this.remainingSeconds = seconds;
    }
    public int getRemainingSeconds() {
        return this.remainingSeconds;
    }

    public void addCorrecta(int pregunta) {
        correctas++;
        preguntas[pregunta-1] = 1;
    }

    public void addIncorrecta(int pregunta) {
        incorrectas++;
        preguntas[pregunta-1] = -1;
    }

    public int getCorrectas() {
        return correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public int getTotal_preguntas() {
        return total_preguntas;
    }

    public int[] getPreguntas() {
        return preguntas;
    }

    public void calculateAverage() {
        this.tiempoAvrg  = tiempoAvrg / total_preguntas;
    }
    
    public float getAverage(){
        return this.tiempoAvrg;
    }
    
    public int getComodines(){
        
        return this.comodinesUsados;
    }    

    public void useComodin(){
        this.comodinesUsados++;
    }

    public void addTime(){
        this.remainingSeconds += 60;
    }

    public int get_tiempoxpregunta() {
        return tiempoxpregunta;
    }
    public void set_tiempoxpregunta(int tiempoxpregunta) {
        this.tiempoxpregunta = tiempoxpregunta;
    }

    public void addRespuestas(String respuesta) {
        //append
        this.respuestas.add(respuesta);
    }

    public List<String> getRespuestas() {
        return respuestas;
    }
}
