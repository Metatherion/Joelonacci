package uk.co.opses.hello;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FibonacciJUnitTests {
    int countZero = 0;
    int countOne = 1;
    int countFive = 5;
    int countMinusFive = -5;
    ArrayList<Integer> arrayListZero = new ArrayList<Integer>();
    ArrayList<Integer> arrayListOne = new ArrayList<Integer>();
    ArrayList<Integer> arrayListFive = new ArrayList<Integer>();
    ArrayList<Integer> arrayListMinusFive = new ArrayList<Integer>();
    @Test
    public void FibonacciOutputTests()
    {
        arrayListOne.add(0);
        arrayListFive.add(0);
        arrayListFive.add(1);
        arrayListFive.add(1);
        arrayListFive.add(2);
        arrayListFive.add(3);
        FibonacciSequenceTest(countZero, arrayListZero);
        FibonacciSequenceTest(countOne, arrayListOne);
        FibonacciSequenceTest(countFive, arrayListFive);
        FibonacciSequenceTest(countMinusFive, arrayListMinusFive);

    }

    public void FibonacciSequenceTest(Integer count, ArrayList<Integer> testArraylist)
    {
        FibonacciAlgorithm.createSequence(count);
        assertEquals(testArraylist, FibonacciAlgorithm.sequence);
    }

}