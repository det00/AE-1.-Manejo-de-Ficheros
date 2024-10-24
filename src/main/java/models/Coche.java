package models;

import lombok.*;

import java.io.Serializable;
import java.util.Random;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Coche implements Serializable {

    private static final long serialVersionUID = 334455L;
    private Long id;
    private String matricula;

    @Setter
    private String marca, modelo, color;
    private static int contadorId;

    public Coche(String marca, String modelo, String color) {
        this.id = generarId();
        this.matricula = generadorMatricula();
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }
    /*Para generar la matricula use Random al cual le puedes pedir que te genere un numero
    *aleatorio, en el caso de numero matricula siempre sera de 1000 a 9999 y en el caso de letraAleatoria
    *de 0 a 20, he creado un array con letras para exluir Ñ y las letras vocales.
    *Finalmente mediante retornamos la mezcla como String de los numeros y letras.
     */
    public String generadorMatricula() {
        Random aleatorio = new Random();
        int numeroMatricula = aleatorio.nextInt(8999)+1000;
        StringBuilder trioDeLetras = new StringBuilder();

        char[] letrasSinVocales = new char[]{
                'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'
        };
        for(int i = 0 ; i < 3 ; i++){
            int letraAleatoria = aleatorio.nextInt(20);
            trioDeLetras.append(letrasSinVocales[letraAleatoria]);
        }
        return numeroMatricula +"-"+ trioDeLetras;
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
    //Contador es igual a MaxID que es el que verifica cual es el ultimo numero usado
    //en el id.
    public static void inicializarContadorId(int maxId){
        contadorId=maxId;
    }
}
