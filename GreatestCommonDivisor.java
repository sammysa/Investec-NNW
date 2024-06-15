public class GreatestCommonDivisor {

    // Main Method that will run
    public static void main(String[] args)
    {
        //int[] suppliedNumbers = {56, 67, 64, 14, 76, 9, 31};
        int[] suppliedNumbers = {2, 8, 18, 44, 80};
        // System.out.println("Highest Common Factor Calculator Class"); Initialised to check that our class work!
        System.out.println("The Greatest Common Divisor is: " + commonDivisorCalc(suppliedNumbers));

    }

    // Static Method that will be used to determine our greatest common divisor - Assesment Scenario 1
    public static int commonDivisorCalc(int[] numberArray)
    {
        int highestCommFact = 1; // Starting at 1 to avoid unnecessary iterations
        boolean isCommonDivisor = true; //Initialise default
        int minNumber = Integer.MAX_VALUE; //maximum positive integer value that can be represented

        //1.
        for(int number : numberArray) // Determine the minimum number in the array
        {
            if(number < minNumber)
            {
                minNumber = number;
            }
        }

        //2.
        for(int i = 1; i <= minNumber; i++)
        {
            for(int numb : numberArray)
            {
                if(numb % i != 0)
                {
                    isCommonDivisor = false;
                    break;
                }
            }
            if(isCommonDivisor) // if isCommonDivisor is true
            {
                highestCommFact = i;
            }
        }
        return highestCommFact;
    }
}
