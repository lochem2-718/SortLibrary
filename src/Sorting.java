public final class Sorting
{
    private Sorting() { }

    public static void runMergeSort( int[] array, final int arraySize )
    {
        helpMergeSort( array, 0, arraySize - 1 );
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
        final int arraySegmentCapacity = highIndex - lowIndex + 1;

        final int rightLowIndex = lowIndex + ( arraySegmentCapacity / 2 );
        final int rightHighIndex = lowIndex + arraySegmentCapacity - 1;

        final int leftLowIndex = lowIndex;
        final int leftHighIndex = rightLowIndex - 1;

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
        final int sortedarraySize = highIndex - lowIndex + 1;

        int[] sortedArray = new int[ sortedarraySize ];
        int sortedCopyIndex = 0;
        int leftCopyIndex = lowIndex;
        int rightCopyIndex = middleIndex;
        final int offset = lowIndex;
        
        while( leftCopyIndex < middleIndex && rightCopyIndex <= highIndex )
        {
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
        sortedCopyIndex = 0;
        while( sortedCopyIndex < sortedarraySize )
        {
            array[ sortedCopyIndex + offset ] = sortedArray[ sortedCopyIndex ];
            sortedCopyIndex++;
        }
    }

    public static void runQuickSort( int[] array, final int arraySize )
    {
        helpQuickSort( array, 0, arraySize - 1 );
    }

    private static void helpQuickSort( int[] array, final int lowIndex,
                                      final int highIndex )
    {
        final int pivotIndex;

        // dont touch the actual pivot in this function
        if( lowIndex < highIndex )
        {
            pivotIndex = partition( array, lowIndex, highIndex );
            helpQuickSort( array, lowIndex, pivotIndex - 1 );
            helpQuickSort( array, pivotIndex + 1, highIndex );
        }
    }

    private static int partition( int[] array, final int lowIndex,
                                   final int highIndex )
    {
        // pivot will iterate before we touch it
        int pivot = lowIndex - 1;
        int testIndex = lowIndex;
        int testItem = array[ highIndex ];

        // at end of loop, everything left of the pivot is smaller than
        // everything at the right of the pivot
        while( testIndex < highIndex )
        {
            if( array[ testIndex ] <= testItem )
            {
                pivot++;
                swap( array, pivot, testIndex);
            }
            testIndex++;
        }

        pivot++;

        swap( array, highIndex, pivot );

        return pivot;
    }

    private static void swap( int[] array, final int firstIndex,
                              final int secondIndex )
    {
        int swapStorage;
        swapStorage = array[ firstIndex ];
        array[ firstIndex ] = array[ secondIndex ];
        array[ secondIndex ] = swapStorage;
    }

    public static void runBubbleSort( int[] array, final int arraySize )
    {
        int outerIndex;
        int innerIndex;
        int swapHolder;
        
        for( outerIndex = 0; outerIndex < arraySize; outerIndex++ )
        {
            for( innerIndex = 0; innerIndex < arraySize - 1; innerIndex++ )
            {
                if( array[ innerIndex ] > array[ innerIndex + 1 ] )
                {
                    swapHolder = array[ innerIndex ];
                    array[ innerIndex ] = array [ innerIndex + 1 ];
                    array[ innerIndex + 1 ] = swapHolder;
                }
            }
        }
    }

    public static void runSelectionSort( int[] array, final int arraySize )
    {
        int selectedIndex = 1;
        int insertIndex = 0;
        int searchIndex;
        int swapStorage;

        while( insertIndex < arraySize - 1 )
        {
            // find smallest item
            searchIndex = insertIndex + 1;
            while( searchIndex < arraySize )
            {
                if( array[ searchIndex ] < array[ selectedIndex ] )
                {
                    selectedIndex = searchIndex;
                }
                searchIndex++;
            }

            // swap smallest item with insertion test item
            if( array[ selectedIndex ] < array[ insertIndex ] )
            {
                swapStorage = array[ selectedIndex ];
                array[ selectedIndex ] = array[ insertIndex ];
                array[ insertIndex ] = swapStorage;
            }


            insertIndex++;
        }
    }

    public static void runInsertionSort( int[] array, int arraySize )
    {
        int testIndex = 1;
        int shiftIndex,
        tempStorage;

        // loop from second element to last element
        while( testIndex < arraySize )
        {
            // if the last item (and the biggest item) in the sorted array section is
            // larger than the test item then shift all elements down until an element
            // is found element that is smaller than the one at the search index

            shiftIndex = testIndex;
            tempStorage = array[ testIndex ];
            while( shiftIndex > 0 && array[ shiftIndex - 1 ] > tempStorage )
            {
                array[ shiftIndex ] = array[ shiftIndex - 1 ];
                shiftIndex--;
            }
            array[ shiftIndex ] = tempStorage;

            testIndex++;
        }

    }
}