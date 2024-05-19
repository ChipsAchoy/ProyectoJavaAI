/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.Estadisticas;


public class TriviaTracker {

    private int correctas = 0;
    private int incorrectas = 0;
    private int total_preguntas = 0;
    
    private int[] preguntas;

    private float tiempoxpregunta = 0;
    private int remainingSeconds = 0;
    
    private int comodinesUsados = 0;

    public TriviaTracker(int total_preguntas) {
        this.total_preguntas = total_preguntas;
        preguntas = new int[total_preguntas];
    }

    public void add_average() {
        this.tiempoxpregunta += remainingSeconds;
    }

    public void setRemainingSeconds(int seconds) {
        this.remainingSeconds = seconds;
    }
    public int getRemainingSeconds() {
        return this.remainingSeconds;
    }

    public void addCorrecta(int pregunta) {
        correctas++;
        preguntas[pregunta] = 1;
    }

    public void addIncorrecta(int pregunta) {
        incorrectas++;
        preguntas[pregunta] = -1;
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
        tiempoxpregunta  = remainingSeconds / total_preguntas;
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
}
