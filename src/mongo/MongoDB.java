package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
        cliente.getDatabase(DATABASE);
        coleccion = db.getCollection(COLLECTION);
    }
    
    public String getContactos(){
        return null;
    }
}
