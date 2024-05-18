/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.Estadisticas;


public class TriviaTracker {

    public int correctas = 0;
    public int incorrectas = 0;
    public int total_preguntas = 0;
    
    public int[] preguntas;

    public float tiempoxpregunta = 0;
    public int remainingSeconds = 0;

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

    
    


}
