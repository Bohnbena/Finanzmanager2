package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class MongoDBUtils {

    public MongoDatabase connect() {
        var client = MongoClients.create("mongodb://admin:admin@localhost:27017");
        //Holen uns die datenbank Tranksaktion
        return client.getDatabase("Transaktionen");
    }

    public void schreibeindb(Transaktionen transaktionen, String kollektion) {
        //connecten zu unserere datenbank
        MongoDatabase database = connect();
        if (database.equals(null)) {
            System.out.println("Datenbank konnte nicht erreich werden");
        }
        //die tabelle / in mongodb fall die collection transactionen
        MongoCollection<Document> collection = database.getCollection(kollektion);

        Document transaktiondokument = new Document("name", transaktionen.getBezeichnung())
                .append("Betrag", transaktionen.getBetrag())
                .append("Angekommen", transaktionen.isAngekommen());

        collection.insertOne(transaktiondokument);
    }

    public ArrayList<Transaktionen> database(String kollektion) {
        MongoDatabase database = connect();
        if (database.equals(null)) {
            System.out.println("Datenbank konnte nicht erreich werden");
        }
        //die tabelle / in mongodb fall die collection transactionen
        MongoCollection<Document> collection = database.getCollection(kollektion);
        //Legen eine neue arrayliste an
        ArrayList<Transaktionen> transaktionenList = new ArrayList<>();
        //Gehen die ganze collection durch und schreiben sie in die arrayliste rein
        for (Document doc : collection.find()) {
            Transaktionen transaktion = new Transaktionen();
            transaktion.setId(doc.getObjectId("_id").toString());
            transaktion.setBezeichnung(doc.getString("name"));
            transaktion.setBetrag(doc.getInteger("Betrag"));
            transaktionenList.add(transaktion);
        }
        return transaktionenList;
    }
}
