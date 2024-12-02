package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSONUtils {
    ObjectMapper objectMapper = new ObjectMapper();
    public void listtojson(ArrayList<Transaktionen> list,String quelle)
    {
        File file = new File("temp");
        if (quelle.equals("Einahme"))
        {
            file = new File("Einahme" + ".json");
        }else
        {
            file = new File("Ausgabe" + ".json");
        }
        try {
            objectMapper.writeValue(file,list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Transaktionen> jsontolist(String quelle)
    {
        String pfadname = "";
        if(quelle.equals("Einahme"))
        {
            pfadname = "Einahme";
        }
        else{
            pfadname = "Ausgabe";
        }
        File file = new File(pfadname + ".json");
        try {
            return objectMapper.readValue(file, new TypeReference<ArrayList<Transaktionen>>() {});
        } catch (IOException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
