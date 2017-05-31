package mongo;

public class Contacto {
    
    public static final String KEYNOMBRE = "nombre";
    public static final String KEYAPELLIDOS = "apellidos";
    public static final String KEYTELEFONO = "telefono";
    public static final String KEYCORREO = "correo";
    
    private String nombre;
    private String apellidos;
    private double telefono;
    private String correo;

    public Contacto(){ }
    
    public Contacto(String nombre, String apellidos, double telefono, 
            String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
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

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
