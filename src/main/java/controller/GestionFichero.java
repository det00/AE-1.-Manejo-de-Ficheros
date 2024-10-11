package controller;

import models.Coche;

import java.io.*;
import java.util.ArrayList;

public class GestionFichero {
    public ArrayList<Coche> comprobacionFichero(){

        /*
        * Si "coches.dat" existe, lo lee y añade los objeos a un ArrayList para luego manipularlo.
        * si el fichero no existe, inicia un ArrayList de Coches y lo deja listo para trabajar.
        * */

        ArrayList<Coche> listaCoches = new ArrayList<>();
        ObjectInputStream ois = null;
        File f = new File("src/main/resources/coches.dat");
        if (f.exists()){
            try {
                /*
                * Bloque try para capturar la excepcion "END OF FILE" y poder hacer un break dentro
                * del buble infinito
                */
                ois = new ObjectInputStream(new FileInputStream(f));
                while (true) {
                    try {
                        Coche car = (Coche) ois.readObject();
                        listaCoches.add(car);
                    } catch (EOFException e){ //END OF FILE
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error en la lectura del fichero");
            } finally {
                try {
                    if (ois!=null)
                        ois.close(); //Se cierra el flujo de datos entrante
                } catch (IOException e) {
                    System.out.println("Error en el cierre de ObjectInputStream");
                }
            }
        }
        return listaCoches; // Lista de coches que devuelve el metodo
    }
    public ArrayList<Coche> crearCoche(ArrayList<Coche> listaCoches, Coche coche){
        listaCoches.add(coche);
        return listaCoches;
    }
    public ArrayList<Coche> borrarId(ArrayList<Coche> listaCoches, long id){
        listaCoches.removeIf(coche -> coche.getId().equals(id));
        return listaCoches;
    }
    public Coche buscarCocheId(ArrayList<Coche> listaCoches, long id){
        for (Coche c : listaCoches){
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
    public void listarCoches(ArrayList<Coche> listaCoches){
        System.out.println(listaCoches);
    }
}
