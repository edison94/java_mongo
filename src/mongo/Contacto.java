package mongo;

public class Contacto {
    
    public static final String KEYNOMBRE = "nombre";
    public static final String KEYAPELLIDOS = "apellidos";
    public static final String KEYTELEFONO = "telefono";
    public static final String KEYCORREO = "correo";
    public static final String KEYEDAD = "edad";
    
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private int edad;

    public Contacto(){ }
    
    public Contacto(String nombre, String apellidos, String telefono, 
            String correo, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
        
}
