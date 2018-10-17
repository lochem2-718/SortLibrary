public final class Sorting
{
    private Sorting() {}

    public static void runMergeSort( int[] array, final int arrayCapacity )
    {
        helpMergeSort( array, 0, arrayCapacity - 1 );
    }

    private static void helpMergeSort( int[] array
                              , final int lowIndex
                              , final int highIndex )
    {
        // work out all indexing; most likely to have bug
        //  0 1 2 3 4 5 6 7 8 9  capacity: 10
        // |a|b|c|d|e|f|g|h|i|j| ->
        //
        // left side    right side
        //  0 1 2 3 4    5 6 7 8 9 
        // |a|b|c|d|e|  |f|g|h|i|j|
        final int arraySegmentCapacity = highIndex - lowIndex;
        final int rightHighIndex = arraySegmentCapacity - 1;
        final int rightLowIndex = arraySegmentCapacity / 2;
        final int leftHighIndex = rightLowIndex - 1;
        final int leftLowIndex = lowIndex;

        if( arraySegmentCapacity > 2 )
        {
            helpMergeSort( array, leftLowIndex, leftHighIndex );
            helpMergeSort( array, rightLowIndex, rightHighIndex );
        }
        merge( array, leftLowIndex, rightLowIndex, rightHighIndex );
    }

    private static void merge( int[] array
                      , final int lowIndex
                      , final int middleIndex
                      , final int highIndex )
    {
        final int sortedArrayCapacity = lowIndex - highIndex;
        int[] sortedArray = new int[ sortedArrayCapacity ];
        int sortedCopyIndex = 0;
        int leftCopyIndex = lowIndex;
        int rightCopyIndex = middleIndex;
        final int offset = lowIndex;
        
        while( leftCopyIndex < middleIndex && rightCopyIndex <= highIndex )
        {
            // order from smallest to largest
            if( array[ leftCopyIndex ] < array[ rightCopyIndex ] )
            {
                sortedArray[ sortedCopyIndex ] = array[ leftCopyIndex ];
                leftCopyIndex++;
            }
            else
            {
                sortedArray[ sortedCopyIndex ] = array[ rightCopyIndex ];
                rightCopyIndex++;
            }
            sortedCopyIndex++;
        }

        // clean up leftover left
        while( leftCopyIndex < middleIndex )
        {
            sortedArray[ sortedCopyIndex ] = array[ leftCopyIndex ];
            leftCopyIndex++;
            sortedCopyIndex++;
        }

        // clean up leftover right
        while( rightCopyIndex <= highIndex )
        {
            sortedArray[ sortedCopyIndex ] = array[ rightCopyIndex ];
            rightCopyIndex++;
            sortedCopyIndex++;
        }

        // copy sorted array into main array
        while( sortedCopyIndex >= 0 )
        {
            array[ sortedCopyIndex + offset ] = sortedArray[ sortedCopyIndex ];
            sortedCopyIndex--;
        }
    }
}