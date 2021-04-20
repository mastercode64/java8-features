import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StreamTest {

    @Test
    public void mapTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        numbers = numbers.stream()
                .map(n -> n + 1)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(2, 3, 4), numbers);
    }

    @Test
    public void allMatchTest() {
        List<String> letters = Arrays.asList("a", "a", "b");

        boolean result = letters.stream()
                .allMatch(l -> l == "a");

        assertFalse(result);
    }

    @Test
    public void anyMatchTest() {
        List<String> letters = Arrays.asList("a", "a", "b");

        boolean result = letters.stream()
                .anyMatch(l -> l == "b");

        assertTrue(result);
    }

    @Test
    public void noneMatchTest() {
        List<String> letters = Arrays.asList("a", "a", "b");

        boolean result = letters.stream()
                .noneMatch(l -> l == "c");

        assertTrue(result);
    }

    @Test
    public void distinctTest() {
        List<String> letters = Arrays.asList("a", "a", "b");

        List<String> result = letters.stream()
                .distinct()
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("a", "b"), result);
    }

    @Test
    public void inlineFilterTest() {
        List<Integer> result = Arrays.asList(1, 2, 3, 4).stream()
                .filter(x -> x > 1 && x < 3)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(2), result);
    }

    @Test
    public void createPredicateFilterTest() {
        Predicate<Integer> biggerThan3 = x -> x > 3;
        Predicate<Integer> lowerThan5 = x -> x < 5;

        List<Integer> result = Arrays.asList(1, 2, 3, 4).stream()
                .filter(biggerThan3.and(lowerThan5))
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(4), result);
    }


}
