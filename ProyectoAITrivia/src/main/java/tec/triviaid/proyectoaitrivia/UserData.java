/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.triviaid.proyectoaitrivia;

public class UserData {

    public String Nombre = "";
    public String Correo = "";
    public int cantidad_preguntas = 0;
    public String idioma = "";
    public String tema = "";
    public int tiempoxpregunta = 0;

    public UserData(String Nombre, String Correo, int cantidad_preguntas, String idioma, String tema, int tiempoxpregunta) {
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.cantidad_preguntas = cantidad_preguntas;
        this.idioma = idioma;
        this.tema = tema;
        this.tiempoxpregunta = tiempoxpregunta;
    }

    public UserData() {
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public int getCantidad_preguntas() {
        return cantidad_preguntas;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTema() {
        return tema;
    }

    public int getTiempoxpregunta() {
        return tiempoxpregunta;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setCantidad_preguntas(int cantidad_preguntas) {
        this.cantidad_preguntas = cantidad_preguntas;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setTiempoxpregunta(int tiempoxpregunta) {
        this.tiempoxpregunta = tiempoxpregunta;
    }

}
