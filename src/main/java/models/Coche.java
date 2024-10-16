package models;

import lombok.*;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Coche implements Serializable {

    private static final long serialVersionUID = 334455L;
    private static long contadorId = 033L;
    private static long contadorMatricula = 0000L;

    private Long id;
    private String matricula;

    @Setter
    private String marca, modelo, color;

    public Coche(String marca, String modelo, String color) {
        this.id = generarId();
        this.matricula = generarMatricula();
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }


    private String generarMatricula(){
        return contadorMatricula++ + "GHN";
    }

    private long generarId(){
        return contadorId++;
    }

    @Override
    public String toString() {
        return "\n\tID " + id + ":" +
                "\n\tMARCA: " + marca +
                "\n\tMODELO: " + modelo +
                "\n\tCOLOR: " + color +
                "\n\tMATRICULA: " + matricula;
    }
}
