package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

public class Mongo {

    public static void main(String[] args) {
        MongoClient cliente = new MongoClient("192.168.56.12");
        MongoDatabase db = cliente.getDatabase("mis datos");
        MongoCollection<Document> coleccion = db.getCollection("personas");

        /*Document amigo = new Document();
        amigo.put("nombre", "Pepito");
        amigo.put("teléfono", 925677);
        amigo.put("curso", "2DAM");
        amigo.put("fecha", new Date());
        coleccion.insertOne(amigo);*/
        
        MongoCursor <Document> cursor = coleccion.find().iterator();
        while (cursor.hasNext()) {
        Document doc = cursor.next();
            System.out.println (doc.toJson());
        }
        cursor.close();

        /*List<Document> consulta = coleccion.find().into(new ArrayList<Document>());
        for (int i = 0; i < consulta.size(); i++) {
            System.out.println(" - " + consulta.get(i).toString());
        }*/
    }

}
