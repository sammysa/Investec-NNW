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
            System.out.println( "Reading File" );
            String line;
            while ((line = bufferedReader.readLine()) != null){
                jsonContentBuilder.append(line);
            }}catch(IOException e) //in case file can not be found/read
            {
                e.printStackTrace();
            }

        // Parse JSON
        System.out.println( "Parsing JSON Array" );
        JSONArray jsonArray = new JSONArray(jsonContentBuilder.toString());

        // Display JSON Contents
        int index = 0;
        String displayAddressDetail = "";
        System.out.println( "Displaying JSON Contents" );
        while (index < jsonArray.length())
        {
            JSONObject address = jsonArray.getJSONObject(index); //ref to be used when reading JSON file

            if(address.has("addressLineDetail"))
            {
                JSONObject addressLineDetail = address.getJSONObject("addressLineDetail");
                displayAddressDetail = addressLineDetail.getString("line1") + ", " + addressLineDetail.getString("line2")
                        + " - " + address.getString("cityOrTown");
            }
            if(address.has("provinceOrState"))
            {
                JSONObject province = address.getJSONObject("provinceOrState");
                displayAddressDetail = displayAddressDetail.concat(" - ").concat(province.getString("name"));

            }
            displayAddressDetail = displayAddressDetail.concat(" - ").concat(address.getString("postalCode"));

            System.out.println("Address: " + displayAddressDetail);
            System.out.println();
            index++;
        }
    }
}
