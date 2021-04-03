package uk.co.opses.hello;

import java.util.ArrayList;

public class FibonacciAlgorithm
{
    static Integer first = 0;
    static Integer second = 1;
    static Integer result;
    static ArrayList<Integer> sequence;

    public static ArrayList<Integer> createSequence(int count)
    {
        sequence = new ArrayList<Integer>();

        //If the input is below 1 the funtion returns an empty arraylist

        if (count == 1)
        {
            // Adds the first starting number to the arraylist
            sequence.add(0);
        }
        else if (count == 2)
        {
            // Adds the first two starting numbers to the arraylist
            sequence.add(0);
            sequence.add(1);
        }
        else if (count > 2)
        {
            // Adds the first two starting numbers to the arraylist
            sequence.add(0);
            sequence.add(1);
            // Continues to add subsiquent numbers to the arraylist according to count value
            do
            {
                result = first+second;
                first = second;
                second = result;
                sequence.add(result);
            }while(sequence.size() < count);
        }
        // Reset initial variables
        first = 0;
        second= 1;

        // Return arraylist
        return sequence;


    }

}
