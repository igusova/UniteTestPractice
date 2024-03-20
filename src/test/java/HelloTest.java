import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloTest {

    @Test
    void simpleJunitTest() {
        int a = 2;
        int b = 3;
        int sum = a + b;

        Assertions.assertEquals(5, sum);
    }
}
