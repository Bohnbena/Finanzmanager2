package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public void bearbeiteinDB(Transaktionen transaktionen, String kollektion, int art) {
        MongoDatabase database = connect();
        if (database.equals(null)) {
            System.out.println("Datenbank konnte nicht erreich werden");
        }
        MongoCollection<Document> collection = database.getCollection(kollektion);
        Document filter = new Document("_id", transaktionen.getId());
        switch (art) {
            case 1:
                Document update = new Document("$set", new Document("name", transaktionen.getBezeichnung()));
                collection.updateOne(filter, update);
                break;
            case 2:
                Document update1 = new Document("$set", new Document("Betrag", transaktionen.getBetrag()));
                collection.updateOne(filter, update1);
                break;
            case 3:
                Document update2 = new Document("$set", new Document("Angekommen", transaktionen.isAngekommen()));
                collection.updateOne(filter, update2);
                break;
        }
    }

    public void ErsetzteDatensatz(Transaktionen transaktionen, String kollektion) {
        MongoDatabase database = connect();
        if (database.equals(null)) {
            System.out.println("Datenbank konnte nicht erreich werden");
        }
        MongoCollection<Document> collection = database.getCollection(kollektion);

        ObjectId objectId = transaktionen.getId();
        Document filter = new Document("_id", objectId);

        System.out.println("Ungültige ObjectId: " + transaktionen.getId());

        Document transaktionsdokument = new Document("_id", objectId)
                .append("name", transaktionen.getBezeichnung())
                .append("Betrag", transaktionen.getBetrag())
                .append("Angekommen", transaktionen.isAngekommen());

        collection.replaceOne(filter, transaktionsdokument);
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
            transaktion.setId(doc.getObjectId("_id"));
            transaktion.setBezeichnung(doc.getString("name"));
            transaktion.setBetrag(doc.getInteger("Betrag"));
            transaktion.setAngekommen(doc.getBoolean("Angekommen"));
            transaktionenList.add(transaktion);
        }
        return transaktionenList;
    }
}
