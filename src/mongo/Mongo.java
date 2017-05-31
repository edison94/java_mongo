package mongo;

import java.util.Scanner;

public class Mongo {

    private static MongoDB conn;
    private final static Scanner SC = new Scanner(System.in);

    // Mostramos un menu con las diferentes acciones que podemos realizar
    private static void printMenu() {
        System.out.println("Selecciona el numero de la accion que quiere realizar:");
        System.out.println("1 Visualizar contactos");
        System.out.println("2 Insertar contacto");
        System.out.println("3 Modificar contacto");
        System.out.println("4 Eliminar contacto");
        System.out.println("5 Tranpasar datos a un fichero");
        System.out.println("0 Finalizar");
    }

    private static void printContactos() {

    }

    private static boolean setAction(int action) {
        switch (action) {
            // Finalizamos el bucle para finalizar el programa
            case 0:
                return false;

            // Visualizamos los contactos
            case 1:
                printContactos();

            // Preguntamos los datos para insertar un nuevo contacto
            case 2:

            // Mandamos un mensaje de error
            default:
                System.out.println("Opcion incorrecta.");
                return true;
        }
    }

    // 
    public static void main(String[] args) {
        conn = new MongoDB();
        do {
            printMenu();
        } while (setAction(SC.nextInt()));
    }

}
