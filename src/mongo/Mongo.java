package mongo;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Mongo {
    
    private static MongoDB conn;
    private final static Scanner SC = new Scanner(System.in);
    
    // Mostramos un menu con las diferentes acciones que podemos realizar
    private static void printMenu(){
        System.out.println("Selecciona el numero de la accion que quiere realizar:");
        System.out.println("1 Visualizar contactos");
        System.out.println("2 Visualizar contactos con mas edad");
        System.out.println("3 Visualizar un contacto");
        System.out.println("4 Insertar un contacto");
        System.out.println("5 Modificar un contacto");
        System.out.println("6 Eliminar un contacto");
        System.out.println("7 Tranpasar datos del los contactos a un fichero");
        System.out.println("0 Finalizar"); 
    }
    
    // Pedimos al usuario el correo electronico del contacto
    private static String writeCorreo(){
        System.out.print("Indique el correo electronico: ");
        return SC.next();
    }
    
     // Pedimos al usuario los demas datos del contacto
    private static Contacto writeDatosRestantes(Contacto ct){
        System.out.print("Indique el nombre: ");
        ct.setNombre(SC.next());
        System.out.print("Indique los apellidos: ");
        ct.setApellidos(SC.next());
        System.out.print("Indique el telefono: ");
        ct.setTelefono(SC.next());
        System.out.print("Indique la edad: ");
        ct.setEdad(SC.nextInt());
        return ct;
    }
    
    
    // Visualizamos los contactos
    private static void printContactos(){
        ArrayList<Contacto> cts = conn.getContactos();
        
        if(!cts.isEmpty()){
            System.out.println("Lista de contactos:");     
            for(Contacto ct : cts){
                printContacto(ct);
            }
        }else{
            System.out.println("Actualmente no hay conctacos insertados.");
        } 
    }
    
        // Visualizamos los contactos
    private static void printContactosMasEdad(){
        ArrayList<Contacto> cts = conn.getContactosMasEdad();
        
        if(!cts.isEmpty()){
            System.out.println("Lista de contactos con mas edad:");     
            for(Contacto ct : cts){
                printContacto(ct);
            }
        }else{
            System.out.println("Actualmente no hay conctacos insertados.");
        } 
    }
    
    // Visualizamos el contacto con el correo que ha indicado el usuario
    private static void printContacto(){
        Contacto ct = conn.getContacto(writeCorreo());
        
        if (ct.getCorreo() != null){
            printContacto();
        } else {
            System.out.println("Error: No existe un usuario con este correo.");
        }
    }
    
    // Visualizamos el contacto con el correo que ha indicado el usuario
    private static void printContacto(Contacto ct){
        System.out.println("Nombre: " + ct.getNombre());
        System.out.println("Apellidos: " + ct.getApellidos());
        System.out.println("Correo: " + ct.getCorreo());
        System.out.println("Telefono: " + ct.getTelefono());
        System.out.println("Edad: " + ct.getEdad() + "\n");
    }
    
    // Preguntamos los datos para insertar un nuevo contacto
    private static void insertContacto() {
        String correo = writeCorreo();
        Contacto ct = conn.getContacto(correo);
        
        if (ct.getCorreo() == null){
            ct.setCorreo(correo);
            conn.insertContacto(writeDatosRestantes(ct));
            System.out.println("Insertado correctamente.");
        } else {
            System.out.println("Error: Ya existe un usuario con este correo.");
        }
    }

    // Preguntamos el correo del contacto que quiere modificar y sus nuevos datos
    private static void updateContacto() {
        String correo = writeCorreo();
        Contacto ct = conn.getContacto(correo);
        
        if (ct.getCorreo() != null){
            ct.setCorreo(correo);
            conn.updateContacto(writeDatosRestantes(ct));
            System.out.println("Se ha actualizado el contacto correctamente.");
        } else {
            System.out.println("Error: No existe un usuario con este correo.");
        }
    }

    // Preguntamos el correo del contacto que quiere eliminar
    private static void deleteContacto() {
        Contacto ct = conn.getContacto(writeCorreo());
        
        if (ct.getCorreo() != null){
            conn.deleteContacto(ct.getCorreo());
            System.out.println("Se ha eliminado el contacto correctamente.");
        } else {
            System.out.println("Error: No existe un usuario con este correo.");
        }
    }
    
    // Guardaremos en la ruta que nos indique el usuario un fichero con los 
    // datos de todos los contactos
    private static void extractContactos() {

    }
    
    // Segun la opcion indicada por el usuario ejecutaremos un metodo o otro
    private static boolean setAction(int action){
        switch(action){
            // Finalizamos el bucle para finalizar el programa
            case 0: 
                return false;
            
            // Visualizamos los contactos
            case 1:
                printContactos(); break;
            
            // Visualizamos los contactos con mas edad
            case 2:
                printContactosMasEdad(); break;     
                
            // Visualizamos el contacto con el correo que ha indicado el usuario
            case 3:
                printContacto(); break;
                
            // Preguntamos los datos para insertar un nuevo contacto
            case 4:
                insertContacto(); break;
                
            // Preguntamos el correo del contacto que quiere modificar y sus
            // nuevos datos
            case 5:
                updateContacto(); break;
                
            // Preguntamos el correo del contacto que quiere eliminar
            case 6:
                deleteContacto(); break;
               
            // Guardaremos en la ruta que nos indique el usuario un fichero con
            // los datos de todos los contactos
            case 7:
                extractContactos(); break;
           
            // Mandamos un mensaje de error
            default:
                System.out.println("Opcion incorrecta.");
        }
        
        System.out.println("");
        return true;
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
