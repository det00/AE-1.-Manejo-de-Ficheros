import controller.GestionFichero;
import models.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        GestionFichero gf = new GestionFichero();
        ArrayList<Coche> listaCoches = gf.comprobacionFichero();
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            salir = ejecutarOpcion(listaCoches, gf);
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
               \s
                *******************************
                *       CONCESIONARIO         *\s
                *******************************
                * 1. Agregar nuevo coche      *
                * 2. Borrar coche por ID      *
                * 3. Consultar coche por ID   *
                * 4. Listado de coches        *
                * 5. Exportar CSV             *
                * 6. Salir                    *
                *******************************
                *     ESCRIBE UNA OPCION:     *
                *******************************\s
               \s""");
    }

    private static boolean ejecutarOpcion(ArrayList<Coche> listaCoches, GestionFichero gf) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        try { /*
         * Bloque try para manejar las execpciones si se
         * introduce un caracter diferente a un int en el menú
         */
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> {
                    /*
                     * Se crean las variables a parte para poder usar el constructor
                     * que genera la matricula y el id automaticamente
                     */

                    System.out.println("AGREGAR NUEVO COCHE");
                    System.out.println("Introduce marca:");
                    String marca = sc.nextLine();
                    System.out.println("Introduce modelo:");
                    String modelo = sc.nextLine();
                    System.out.println("Introduce color:");
                    String color = sc.nextLine();
                    Coche c = new Coche(marca, modelo, color);
                    if (gf.crearCoche(listaCoches, c))
                        System.out.println("Se ha añadido a la lista: \n" + c);
                    else System.out.println("No se ha podido añadir el coche a la lista.");
                }
                case 2 -> {
                    System.out.println("BORRAR COCHE POR ID");
                    System.out.println("Introduce el ID del coche a borrar: ");
                    long id = sc.nextLong();
                    if (gf.borrarId(listaCoches, id))
                        System.out.println("Se ha borrado el coche con ID: " + id + " correctamente.");
                    else System.out.println("No se ha podido borrar el coche con id: " + id);
                }
                case 3 -> {
                    System.out.println("CONSULTAR COCHE POR ID");
                    System.out.println("Introduce el ID del coche a buscar: ");
                    long id = sc.nextLong();
                    Coche c = gf.buscarCocheId(listaCoches, id);
                    if (c != null) {
                        System.out.println(c);
                    } else System.out.println("No existe cocohe con id " + id);
                }
                case 4 -> {
                    System.out.println("LISTADO DE COCHES" + " (" + listaCoches.size() + ')');
                    if (listaCoches.isEmpty())
                        System.out.println("NO HAY COCHES EN LA LISTA");
                    gf.listarCoches(listaCoches);
                }
                case 5 -> {
                    //Csv
                    gf.exportarCSV(listaCoches);
                    System.out.println("Lista de coches exportada a CSV");

                }
                case 6 ->{
                    System.out.println("GUARDANDO DATOS...");
                    gf.escribirDatos(listaCoches);
                    System.out.println("SALIENDO DEL PROGRAMA...");
                    salir = true;
                }
                default -> {
                    System.out.println("Opcion incorrecta");
                }
            }
        } catch (Exception e) {
            System.out.println("Introduce un número");
        }

        return salir;
    }
}

