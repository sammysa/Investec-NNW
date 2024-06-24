# Investec-NNW
Investec Assessment

Assesment Scenarios Detailed Below
1. Find the highest common for a given array of integers
2. Given the attached addresses.json file which contains an array of address, do the following:
   Write a function to return a pretty print version of an address in the format: 
         - Type: Line details - city - province/state - postal code – country
         - Write a function to pretty print all the addresses in the attached file
         - Write a function to print an address of a certain type (postal, physical, business)
         - Write a function to validate an address
         - a. A valid address must consist of a numeric postal code, a country, and at least one address line that is not blank or null.
         - b. If the country is ZA, then a province is required as well.
         - For each address in the attached file, determine if it is valid or not, and if not give an indication of what is invalid in a message format of your choice.

Solutions Breakdown

Scenario 1
Step Process
1. Find the minimum number in the array
2. Check the common factor
3. Return the common factor

Scenario 2
NOTE: Import JSON JAR into project dependency if it does not update when doing a `mvn clean package`
Link: https://repo1.maven.org/maven2/org/json/json/20240303/json-20240303.jar
