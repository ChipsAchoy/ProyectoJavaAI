/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia.TriviaController;
import java.io.Serializable;

public class Trivia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public String triviaString = "";
    public String Tema = "";
    public String Idioma = "";
    public int CantidadPreguntas = 0;
    public int TiempoXPregunta = 0;
    public boolean fiftyFifty = true;
    public boolean extraMin = true;
    


    public Trivia(String triviaString, String Tema, String Idioma, int CantidadPreguntas, int TiempoXPregunta) {
        this.triviaString = triviaString;
        this.Tema = Tema;
        this.Idioma = Idioma;
        this.CantidadPreguntas = CantidadPreguntas;
        this.TiempoXPregunta = TiempoXPregunta;
    }


    public String getTriviaString() {
        return triviaString;
    }

    public void setTriviaString(String triviaString) {
        this.triviaString = triviaString;
    }

    
    public String getTema() {
        return Tema;
    }

    public void setTema(String Tema) {
        this.Tema = Tema;
    }

    public String getIdioma() {
        return Idioma;
    }

    public void setIdioma(String Idioma) {
        this.Idioma = Idioma;
    }

    public int getCantidadPreguntas() {
        return CantidadPreguntas;
    }

    public void setCantidadPreguntas(int CantidadPreguntas) {
        this.CantidadPreguntas = CantidadPreguntas;
    }

    public int getTiempoXPregunta() {
        return TiempoXPregunta;
    }

    public void setTiempoXPregunta(int TiempoXPregunta) {
        this.TiempoXPregunta = TiempoXPregunta;
    }

    public String toString(){
        return "Tema: "+Tema+" Idioma: "+Idioma+" Cantidad de Preguntas: "+ String.valueOf(CantidadPreguntas)+" Tiempo por Pregunta: "+String.valueOf(TiempoXPregunta);
    }

    
}
