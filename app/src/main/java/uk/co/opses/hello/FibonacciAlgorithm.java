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
        if (count == 0)
        {
            return sequence;
        }
        else if (count == 1)
        {
            sequence.add(0);
        }
        else if (count == 2)
        {
            sequence.add(0);
            sequence.add(1);
        }
        else if (count > 2)
        {
            sequence.add(0);
            sequence.add(1);
            do
            {
                result = first+second;
                first = second;
                second = result;
                sequence.add(result);
            }while(sequence.size() < count);
        }
        first = 0;
        second= 1;
        return sequence;


    }

}
