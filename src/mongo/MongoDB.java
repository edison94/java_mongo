package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import org.bson.Document;

public class MongoDB {
    public static final String HOST = "192.168.2.155";
    public static final String DATABASE = "agenda";
    public static final String COLLECTION = "contactos";
    
    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection<Document> coleccion;

    public MongoDB() {
        cliente = new MongoClient(HOST);
        db = cliente.getDatabase(DATABASE);
        coleccion = db.getCollection(COLLECTION);
    }
    
    public ArrayList<Contacto> getContactos(){
        ArrayList<Contacto> contactos = new ArrayList<>();
        MongoCursor <Document> cursor = coleccion.find().iterator();
        
        while (cursor.hasNext())
            contactos.add(parseDocumentToContact(cursor.next()));
        
        cursor.close();
        return contactos;
    }
    
    public Contacto getContacto(String correo){
        Contacto contacto = new Contacto();
        Document doc = coleccion.find(eq(Contacto.KEYCORREO, correo)).first();
        return parseDocumentToContact(doc);
    }
    
    public void updateContacto(Contacto ct){
        coleccion.updateOne(eq(Contacto.KEYID, ct.getId()), parseContactToDocument(ct));
    }
    
    public void deleteContacto(Contacto ct){
        coleccion.deleteOne(eq(Contacto.KEYID,ct.getId()));
    }
    
    public void insertContacto(Contacto ct){
        coleccion.insertOne(parseContactToDocument(ct));
    }
    
    public void desconectar(){
        cliente.close();
    }
    
    private Document parseContactToDocument(Contacto ct){
        Document contact = new Document();
        contact.put(Contacto.KEYNOMBRE, ct.getNombre());
        contact.put(Contacto.KEYAPELLIDOS, ct.getApellidos());
        contact.put(Contacto.KEYCORREO, ct.getCorreo());
        contact.put(Contacto.KEYTELEFONO, ct.getTelefono());
        return contact;
    }
    
    private Contacto parseDocumentToContact(Document doc){
        Contacto contacto = new Contacto();
        contacto.setId(doc.getString(Contacto.KEYID));
        contacto.setNombre(doc.getString(Contacto.KEYNOMBRE));
        contacto.setApellidos(doc.getString(Contacto.KEYAPELLIDOS));
        contacto.setCorreo(doc.getString(Contacto.KEYCORREO));
        contacto.setTelefono(doc.getDouble(Contacto.KEYTELEFONO));
        return contacto;
    }
}
