package mongo;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Scanner;

public class Mongo {
    
    private static MongoDB conn;
    private final static Scanner SC = new Scanner(System.in);
    
    // Mostramos un menu con las diferentes acciones que podemos realizar
    private static void printMenu(){
        System.out.println("Selecciona el numero de la accion que quiere realizar:");
        System.out.println("1 Visualizar contactos");
        System.out.println("2 Visualizar un contacto");
        System.out.println("3 Insertar un contacto");
        System.out.println("4 Modificar un contacto");
        System.out.println("5 Eliminar un contacto");
        System.out.println("6 Tranpasar datos del los contactos a un fichero");
        System.out.println("0 Finalizar"); 
    }
    
    // Pedimos el usuario el correo electronico del contacto
    private static String writeCorreo(){
        System.out.println("Datos del contacto");
        System.out.print("Indique el correo electronico: ");
        return SC.nextLine();
    }
    
    // Visualizamos los contactos
    private static void printContactos(){
        ArrayList<Contacto> cts = conn.getContactos();
        System.out.println("Lista de contactos:");
        for(Contacto ct : cts){
            printContacto(ct);
        }
        System.out.println("");
    }
    
    // Visualizamos el contacto con el correo que ha indicado el usuario
    private static void printContacto(){
        printContacto(conn.getContacto(writeCorreo()));
        System.out.println("");
    }
    
    // Visualizamos el contacto con el correo que ha indicado el usuario
    private static void printContacto(Contacto ct){
        System.out.println("Nombre: " + ct.getNombre());
        System.out.println("Apellidos: " + ct.getApellidos());
        System.out.println("Correo: " + ct.getCorreo());
        System.out.println("Telefono: " + ct.getTelefono() + "\n");
    }
    
    // Preguntamos los datos para insertar un nuevo contacto
    private static void insertContacto() {
        Contacto ct = new Contacto();
        System.out.println("Datos del contacto");
        System.out.print("Indique el nombre: ");
        ct.setNombre(SC.nextLine());
        System.out.print("Indique los apellidos: ");
        ct.setApellidos(SC.nextLine());
        System.out.print("Indique el telefono: ");
        ct.setTelefono(SC.nextDouble());
        System.out.print("Indique el correo electronico: ");
        ct.setCorreo(SC.nextLine());
        conn.insertContacto(ct);
    }

    // Preguntamos el correo del contacto que quiere modificar y sus nuevos datos
    private static void updateContacto() {
        writeCorreo();
    }

    // Preguntamos el correo del contacto que quiere eliminar
    private static void deleteContacto() {
        writeCorreo();
    }
    
    // Guardaremos en la ruta que nos indique el usuario un fichero con los 
    // datos de todos los contactos
    private static void extractContactos() {

    }
    
    private static boolean setAction(int action){
        switch(action){
            // Finalizamos el bucle para finalizar el programa
            case 0: 
                return false;
            
            // Visualizamos los contactos
            case 1:
                printContactos();
                return true;
                
           // Visualizamos el contacto con el correo que ha indicado el usuario
            case 2:
                printContacto();
                return true;
                
            // Preguntamos los datos para insertar un nuevo contacto
            case 3:
                insertContacto();
                return true;
                
            // Preguntamos el correo del contacto que quiere modificar y 
            // sus nuevos datos
            case 4:
                updateContacto();
                return true;
                
            // Preguntamos el correo del contacto que quiere eliminar
            case 5:
                deleteContacto();
                return true;
               
            // Guardaremos en la ruta que nos indique el usuario un fichero con
            // los datos de todos los contactos
            case 6:
                extractContactos();
                return true;
           
            // Mandamos un mensaje de error
            default:
                System.out.println("Opcion incorrecta.");
                return true;
        }
    }
    
    // Inicializamos la base de datos y mostramos la interfaz en bucle
    public static void main(String[] args) throws InterruptedException {
        conn = new MongoDB();
        sleep(1000);
        do{
            printMenu();
        }while(setAction(SC.nextInt()));
    }

}
