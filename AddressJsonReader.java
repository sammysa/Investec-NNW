import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

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
        StringBuilder validateAddressMessage = new StringBuilder("Address Is Invalid. Reason: ");
        boolean isValidAddress = true;
        /*
            a. A valid address must consist of a numeric postal code,
            a country,
            and at least one address line that is not blank or null.
            b. If the country is ZA, then a province is required as well.
         */
        Predicate<String> isNumericPostalCd = str -> str.matches("\\d+");
        Predicate<JSONObject> checkEmptyCountry = country -> country.getString("name").isEmpty() &&
                !country.getString("code").isEmpty();
        Predicate<JSONObject> hasValidAddressLine = addressLine -> addressLine.has("addressLineDetail") &&
                !addressLine.getJSONObject("addressLineDetail").getString("line1").isEmpty();
        Predicate<JSONObject> provinceZAChek = addressLine -> addressLine.getJSONObject("country").getString("code")
                .equals("ZA") || addressLine.has("provinceOrState");

        String postalCd = address.getString("postalCode");
        JSONObject country = address.getJSONObject("country");

        //If any of these conditions fail, the address is Invalid
        isValidAddress = isNumericPostalCd.test(postalCd)
                && checkEmptyCountry.test(country)
                && hasValidAddressLine.test(address)
                && provinceZAChek.test(address);

        //Validation Checks
        if(!isNumericPostalCd.test(postalCd))
        {
            validateAddressMessage.append("Postal Code Is Not Numeric.");
        }
        if(!checkEmptyCountry.test(country))
        {
            validateAddressMessage.append("Country is empty.");
        }
        if(!hasValidAddressLine.test(address))
        {
            validateAddressMessage.append("Address Line Is Invalid/Empty.");
        }
        if(!provinceZAChek.test(address))
        {
            validateAddressMessage.append("Province is required for country code ZA.");
        }

        // Display valid message if isValid fails
        if(isValidAddress)
        {
            System.out.println("Valid Address Examined");
        }else{
            System.out.println(validateAddressMessage.toString());
        }
    }
}
