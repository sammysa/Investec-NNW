package za.co.sammysa;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddressJsonReader
{
    public static void main( String[] args )
    {
        System.out.println( "Address File Reader" );

        String filePath = "addresses.json";
        readJsonFile(filePath);

    }

    public static void readJsonFile(String fileName)
    {
        StringBuilder jsonContentBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                jsonContentBuilder.append(line);
            }}catch(IOException e) //in case file can not be found/read
            {
                e.printStackTrace();
            }

        // Parse JSON
        JSONArray jsonArray = new JSONArray(jsonContentBuilder.toString());

        // Display JSON Contents
        int index = 0;
        while (index < jsonArray.length())
        {
            JSONObject address = jsonArray.getJSONObject(index);
            System.out.println("Address: " + (index + 1) + ":");

            index++;
        }
    }
}
