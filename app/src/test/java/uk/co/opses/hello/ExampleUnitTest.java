package uk.co.opses.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test

    public void FibonnaciCountZeroTest()
    {
        FibonacciAlgorithm.createSequence(0);
        assertEquals(0, FibonacciAlgorithm.sequence.size());
    }
    @Test
    public void FibonnaciCountOneTest()
    {
        FibonacciAlgorithm.createSequence(1);
        assertEquals(1, FibonacciAlgorithm.sequence.size());
    }
    @Test
    public void FibonnaciCountFiveTest()
    {
        FibonacciAlgorithm.createSequence(5);
        assertEquals(5, FibonacciAlgorithm.sequence.size());
    }
}