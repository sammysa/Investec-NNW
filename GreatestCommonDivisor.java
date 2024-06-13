public class GreatestCommonDivisor {

    // Main Method that will run
    public static void main(String[] args)
    {
        int[] suppliedNumbers = {56, 67, 64, 14, 76, 9, 31};

        // System.out.println("Highest Common Factor Calculator Class"); Initialised to check that our class work!

        System.out.println("The Greatest Common Divisor is: " + commonDivisorCalc(suppliedNumbers));

    }

    // Static Method that will be used to determine our greatest common divisor - Assesment Scenario 1
    public static int commonDivisorCalc(int[] numberArray)
    {
        int commFact = 1; // Starting at 1 to avoid unnecessary iterarions
        boolean isCommonDivisor = false;
        int minNumber = Integer.MAX_VALUE; //maximum positive integer value that can be represented

        for(int number : numberArray) // Determine the minimum number in the array
        {
            if(number < minNumber)
            {
                minNumber = number;
            }
        }

        /* Step Process
        1.
         */

        return commFact;
    }
}
