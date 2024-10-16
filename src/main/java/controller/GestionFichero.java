package controller;

import models.Coche;

import java.io.*;
import java.util.ArrayList;

public class GestionFichero {

    File f = new File("src/main/resources/coches.dat");
    ArrayList<Coche> listaCoches = new ArrayList<>();

    public ArrayList<Coche> comprobacionFichero() {

        /*
         * Si "coches.dat" existe, lo lee y añade los objeos a un ArrayList para luego manipularlo.
         * si el fichero no existe, inicia un ArrayList de Coches y lo deja listo para trabajar.
         * */

        ObjectInputStream ois = null;
        if (f.exists()) {
            try {
                /*
                 * Bloque try para capturar la excepcion "END OF FILE" y poder hacer un break dentro
                 * del buble infinito
                 */
                ois = new ObjectInputStream(new FileInputStream(f));
                while (true) {
                    try {
                        listaCoches = (ArrayList<Coche>) ois.readObject();
                    } catch (EOFException e) { //END OF FILE
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error en la lectura del fichero");
            } finally {
                try {
                    if (ois != null)
                        ois.close(); //Se cierra el flujo de datos entrante
                } catch (IOException e) {
                    System.out.println("Error en el cierre de ObjectInputStream");
                }
            }
        }
        return listaCoches; // Lista de coches que devuelve el metodo
    }

    public boolean crearCoche(ArrayList<Coche> listaCoches, Coche coche) {
        return listaCoches.add(coche);
    }

    public Coche buscarCocheId(ArrayList<Coche> listaCoches, long id) {
        for (Coche c : listaCoches) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public boolean borrarId(ArrayList<Coche> listaCoches, long id) {
        Coche c = this.buscarCocheId(listaCoches, id);
        if (c != null) {
            listaCoches.remove(c);
            return true;
        } else {
            System.out.println("No hay coche con el id " + id);
            return false;
        }


    }

    public void listarCoches(ArrayList<Coche> listaCoches) {
        for (Coche c : listaCoches) {
            System.out.println(c);
        }
    }

    public void escribirDatos(ArrayList<Coche> listaCoches){
        ObjectOutputStream ous = null;
        try {
            ous = new ObjectOutputStream(new FileOutputStream(f));
            ous.writeObject(listaCoches);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ous.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el flujo de datos.");
            }
        }
    }
}
