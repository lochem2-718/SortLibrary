import java.util.Random;

public class SortTester
{
    public static void main( String[] arguments )
    {
        final int[] array = generateRandomIntArray();
        testMergeSort( array.clone() );
    }

    private static int[] generateRandomIntArray()
    {
        final int capacity = 50;
        int[] array = new int[ capacity ];
        int copyIndex = 0;
        Random randomIntFactory = new Random();

        while( copyIndex < capacity )
        {
            array[ copyIndex ] = randomIntFactory.nextInt();
            copyIndex++;
        }

        return array;
    }

    private static void testMergeSort( int[] array )
    {
        Sorting.runMergeSort( array, array.length );
        System.out.println( array.toString() );
    }
}