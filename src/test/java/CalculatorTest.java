import org.example.Calculator;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(5, 10);
        assertEquals(15, result);
    }
    @Test
    public void testAddWithNegativeNumbers() {
        Calculator calculator = new Calculator();
        int result = calculator.add(-5, 10);
        assertEquals(5, result);
    }

    @Test
    public void testAddWithZero() {
        Calculator calculator = new Calculator();
        int result = calculator.add(0, 0);
        assertEquals(0, result);
    }
}