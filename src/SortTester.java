import java.util.Random;
import java.util.concurrent.Callable;

public class SortTester
{
    public static void main( String[] arguments )
    {
        final int[] array = generateRandomIntArray();

        test( Test.BubbleSort, array );
        test( Test.SelectionSort, array );
        test( Test.InsertionSort, array );
        test( Test.MergeSort, array );
        test( Test.QuickSort, array );
        test( Test.ShellSort, array );
        test( Test.RadixSort, array );
    }

    private static int[] generateRandomIntArray()
    {
        final int capacity = 50;
        int[] array = new int[ capacity ];
        int copyIndex = 0;
        Random randomIntFactory = new Random();

        while( copyIndex < capacity )
        {
            array[ copyIndex ] = randomIntFactory.nextInt( 100 );
            copyIndex++;
        }

        return array;
    }

    private static String intArrayToString( int[] array )
    {
        String arrayString =
                "Array at memory address "
                + array.toString()
                + " contains:\n[ ";
        int index;

        for( index = 0; index < array.length - 1; index++ )
        {
            arrayString += "" + array[ index ] + ", ";
        }
        arrayString += "" + array[ index ] + " ]";
        return arrayString;
    }

    private enum Test
    {
        BubbleSort, SelectionSort, InsertionSort, ShellSort, MergeSort,
        QuickSort, RadixSort
    }

    private static void test( final Test test, final int[] array )
    {
        int[] arr = array.clone();
        final int size = arr.length;
        switch( test )
        {
            case BubbleSort:
                Sorting.runBubbleSort( arr, size );
                System.out.println( "Bubble Sort:" );
                break;
            case InsertionSort:
                Sorting.runInsertionSort( arr, size );
                System.out.println( "Insertion Sort:" );
                break;
            case SelectionSort:
                Sorting.runSelectionSort( arr, size );
                System.out.println( "Selection Sort:" );
                break;
            case MergeSort:
                Sorting.runMergeSort( arr, size );
                System.out.println( "Merge Sort:" );
                break;
            case QuickSort:
                Sorting.runQuickSort( arr, size );
                System.out.println( "Quick Sort:" );
                break;
            case ShellSort:

                System.out.println( "Shell Sort:" );
                break;
            case RadixSort:

                System.out.println( "Radix Sort:" );
                break;
            default:
                System.out.println( "No Valid Test Given" );
                return;
        }
        System.out.println( intArrayToString( arr ) );
        System.out.println();
    }

}