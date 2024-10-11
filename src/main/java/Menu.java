import controller.GestionFichero;
import models.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        GestionFichero gf = new GestionFichero();
        ArrayList<Coche> listaCoches = gf.comprobacionFichero();
        boolean salir = false;
        while (!salir){
            mostrarMenu();
            salir = ejecutarOpcion(listaCoches, gf);
        }
    }
    private static void mostrarMenu() {
        System.out.println("""
                
                1. Agregar nuevo coche.
                2. Borrar coche por ID.
                3. Consultar coche por ID.
                4. Listado de coches.
                5. Salir.
                
                ELIGE UNA OPCION:
                """);
    }
    private static boolean ejecutarOpcion(ArrayList<Coche> listaCoches, GestionFichero gf) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion){
            case 1 -> {
                Coche c = new Coche();
                System.out.println("Agregar nuevo coche");
                System.out.println("Introduce marca:");
                c.setMarca(sc.nextLine());
                System.out.println("Introduce modelo:");
                c.setModelo(sc.nextLine());
                System.out.println("Introduce color:");
                c.setColor(sc.nextLine());
                gf.crearCoche(listaCoches, c);
            }
            case 2 -> {
                System.out.println("Borrar coche por ID");
                System.out.println("Introduce el ID del coche a borrar: ");
                long id = sc.nextLong();
                gf.borrarId(listaCoches, id);
            }
            case 3 -> {
                System.out.println("Consultar coche por ID");
                System.out.println("Introduce el ID del coche a buscar: ");
                long id = sc.nextLong();
                gf.buscarCocheId(listaCoches, id);
            }
            case 4 -> {
                System.out.println("Listado de coches");
                gf.listarCoches(listaCoches);
            }
            case 5 -> {
                System.out.println("Saliendo del programa...");
                salir = true;
            }
            default -> {
                System.out.println("Opcion incorrecta");
            }
        }
        return salir;
    }
}
