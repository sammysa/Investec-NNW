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
        readAndJsonAddress(filePath);

    }

    public static void readAndJsonAddress(String fileName)
    {
        int index = 0;
        String displayAddressDetail = "";

        StringBuilder jsonContentBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            System.out.println( "Reading JSON File" );
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
        while (index < jsonArray.length())
        {
            JSONObject address = jsonArray.getJSONObject(index); //ref to be used when reading JSON file
            System.out.println( "---Checking Address---" );
            validateJsonAddress(address);
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
            //System.out.println();
            index++;
        }
    }

    public static void validateJsonAddress(JSONObject address)
    {
        /*
            a. A valid address must consist of a numeric postal code, a country, and at least one address line that is not blank or null.
            b. If the country is ZA, then a province is required as well.
         */
        boolean isValidAddress = true;
        StringBuilder validateAddressMessage = new StringBuilder("Invalid Address - Reason: ");

        String postalCode = address.getString("postalCode");
        if(!postalCode.matches("\\d+"))
        {
            isValidAddress = false;
            validateAddressMessage.append("The postal code is not numeric.");
        }

        if(isValidAddress)
        {
            System.out.println("Valid Address Examined");
        }else
        {
            System.out.println(validateAddressMessage.toString());
        }
    }
}
