package mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;

public class MongoDB {

    public static final String HOST = "192.168.2.155";
    public static final String DATABASE = "agenda";
    public static final String COLLECTION = "contactos";

    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection<Document> coleccion;

    // Configuramos la base de datos indicandole la ip, el nombre de la base de 
    // datos y el nombre de la coleccion
    public MongoDB() {
        cliente = new MongoClient(HOST);
        db = cliente.getDatabase(DATABASE);
        coleccion = db.getCollection(COLLECTION);
    }

    // Obtenemos todos los contactos
    public ArrayList<Contacto> getContactos() {
        ArrayList<Contacto> contactos = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        while (cursor.hasNext()) {
            contactos.add(parseDocumentToContact(cursor.next()));
        }

        cursor.close();
        return contactos;
    }

    // Obtenemos el contacto con mas edad
    public Contacto getContactoMasEdad() {
        Contacto c = new Contacto();
        AggregateIterable<Document> output = coleccion.aggregate(Arrays.asList(
                new Document("$sort", new Document("edad", -1)), 
                new Document("$limit", 1)));

        for (Document dbObject : output) {
            c = parseDocumentToContact(dbObject);
        }
        return c;
    }

    // Obtenemos un contacto especifico a partir del correo electronico
    public Contacto getContacto(String correo) {
        Document doc = coleccion.find(eq(Contacto.KEYCORREO, correo)).first();
        return parseDocumentToContact(doc);
    }

    // Actualizamos todos los datos del usuario menos el correo electronico    
    public void updateContacto(Contacto ct) {
        coleccion.updateOne(eq(Contacto.KEYCORREO, ct.getCorreo()), 
                new Document("$set", parseContactToDocument(ct)));
    }

    // Eliminamos un contacto especifico a partir del correo electronico
    public void deleteContacto(String correo) {
        coleccion.deleteOne(eq(Contacto.KEYCORREO, correo));
    }

    // Insertamos un nuevo contacto
    public void insertContacto(Contacto ct) {
        coleccion.insertOne(parseContactToDocument(ct));
    }

    // Desconectamos la base de datos
    public void desconectar() {
        cliente.close();
    }
    
    // Obtenemos los datos de un contacto y creamos un objeto de tipo document
    // y lo retornamos
    private Document parseContactToDocument(Contacto ct) {
        Document contact = new Document();
        contact.put(Contacto.KEYNOMBRE, ct.getNombre());
        contact.put(Contacto.KEYAPELLIDOS, ct.getApellidos());
        contact.put(Contacto.KEYCORREO, ct.getCorreo());
        contact.put(Contacto.KEYTELEFONO, ct.getTelefono());
        contact.put(Contacto.KEYEDAD, ct.getEdad());
        return contact;
    }

    // Obtenemos los datos de un documento se lo ponemos a un objeto Contacto
    // y lo retornamos si el documento es nulo retornamos el contacto vacio
    private Contacto parseDocumentToContact(Document doc) {
        Contacto contacto = new Contacto();
        if (doc == null) {
            return contacto;
        }

        contacto.setNombre(doc.getString(Contacto.KEYNOMBRE));
        contacto.setApellidos(doc.getString(Contacto.KEYAPELLIDOS));
        contacto.setCorreo(doc.getString(Contacto.KEYCORREO));
        contacto.setTelefono(doc.getString(Contacto.KEYTELEFONO));
        contacto.setEdad(doc.getInteger(Contacto.KEYEDAD));
        return contacto;
    }

}
