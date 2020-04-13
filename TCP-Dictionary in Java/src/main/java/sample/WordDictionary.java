//---------------------------------Lab-05-------------------------------//
//NAME: AHMAD AMJAD MUGHAL----------------------------------------------//
//REG NO: 12w1672-------------------------------------------------------//
//CLASS: BSCS_6C--------------------------------------------------------//
package sample;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordDictionary {
    private String filename;
    private static WordDictionary dictionary;
    private JSONObject words;
    static {
        try {
            dictionary = new WordDictionary("src\\main\\resources\\dictionary.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private WordDictionary(String filename) throws IOException, ParseException {
        this.filename = filename;
        this.loadDictionary();
    }

    public void loadDictionary() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(this.filename);
        words = (JSONObject) parser.parse(reader);
    }

    public JSONObject getWords(){
        return words;
    }

    public static  WordDictionary getDictionary(){
        return dictionary;
    }


}
