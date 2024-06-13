package za.co.sammysa;

/**
 * Find the highest common for a given array of integers
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int[] factorNumbers = {1, 2, 4, 8, 16};
        System.out.println("The Highest Common Factor is: " + findHighestCommonFactor(factorNumbers));
    }

    public static int findHighestCommonFactor(int[] factorNumbers)
    {
        int result = factorNumbers[0];
        for(int i = 1; i < factorNumbers.length; i++)
        {
            result = getCommonDivisor(result, factorNumbers[i]);
        }

        return result;
    }

    // Validate if the number is divisable
    public static int getCommonDivisor(int res, int number){
        while( number != 0 )
        {
            int tempNumber = number; //declare it in loop so it exists only here
            number = res % number;
            res = tempNumber;
        }
        return res;
    }
}
