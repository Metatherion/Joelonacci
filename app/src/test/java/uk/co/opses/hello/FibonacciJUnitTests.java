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
    @Test
    public void FibonacciCountZeroTest()
    {
        FibonacciAlgorithm.createSequence(0);
        assertEquals(0, FibonacciAlgorithm.sequence.size());
    }
    @Test
    public void FibonacciCountOneTest()
    {
        FibonacciAlgorithm.createSequence(1);
        assertEquals(1, FibonacciAlgorithm.sequence.size());
    }
    @Test
    public void FibonacciCountFiveTest()
    {
        FibonacciAlgorithm.createSequence(5);
        assertEquals(5, FibonacciAlgorithm.sequence.size());
    }
    @Test
    public void FibonacciSequenceZeroTest()
    {
        FibonacciAlgorithm.createSequence(0);
        ArrayList<Integer> testArraylist = new ArrayList<Integer>();
        assertEquals(testArraylist, FibonacciAlgorithm.sequence);
    }
    @Test
    public void FibonacciSequenceOneTest()
    {
        FibonacciAlgorithm.createSequence(1);
        ArrayList<Integer> testArraylist = new ArrayList<Integer>();
        testArraylist.add(0);
        assertEquals(testArraylist, FibonacciAlgorithm.sequence);
    }
    @Test
    public void FibonacciSequenceFiveTest()
    {
        FibonacciAlgorithm.createSequence(5);
        ArrayList<Integer> testArraylist = new ArrayList<Integer>();
        testArraylist.add(0);
        testArraylist.add(1);
        testArraylist.add(1);
        testArraylist.add(2);
        testArraylist.add(3);
        assertEquals(testArraylist, FibonacciAlgorithm.sequence);
    }
}