package com.example.gardeningfrienddemo;

public class cultivosModels {
    // atributos
    String nombre;
    String temperatura;
    String estacion;
    String region;
    int img;

    // constructor
    public cultivosModels(String nombre, String temperatura, String estacion, String region, int img) {
        this.nombre = nombre;
        this.temperatura = temperatura;
        this.estacion = estacion;
        this.region = region;
        this.img = img;
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getEstacion() {
        return estacion;
    }

    public String getRegion() {
        return region;
    }

    public int getImg() {
        return img;
    }
}
