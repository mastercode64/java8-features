import models.Account;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    public void predicateTest() {
        Predicate<Integer> biggerThan3 = x -> x > 3;

        assertFalse(biggerThan3.test(2));
        assertTrue(biggerThan3.negate().test(2));
    }

    @Test
    public void findFirstTest() {
        Integer result = Arrays.asList(1, 2, 3, 4).stream()
                .findFirst()
                .get();

        assertEquals(1, result);
    }

    @Test
    public void sortTest() {
        List<Integer> result = Arrays.asList(4, 1, 3, 2).stream()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    public void sortingListUsingCustomComparator() {
        List<Account> accounts = Arrays.asList(
                new Account("Bob", LocalDate.of(2001, 1, 1), 400L),
                new Account("Amanda", LocalDate.of(2002, 1, 1), 100L),
                new Account("Carlos", LocalDate.of(2000, 1, 1), 300L)
        );

        //Sorting by name
        List<Account> sortedAccounts = accounts
                .stream()
                .sorted(Comparator.comparing(Account::getName))
                .collect(Collectors.toList());

        assertEquals("Amanda", sortedAccounts.get(0).getName());
        assertEquals("Bob", sortedAccounts.get(1).getName());
        assertEquals("Carlos", sortedAccounts.get(2).getName());

        //Sorting by birthdate
        sortedAccounts = accounts
                .stream()
                .sorted(Comparator.comparing(Account::getBirthDate))
                .collect(Collectors.toList());

        assertEquals("Carlos", sortedAccounts.get(0).getName());
        assertEquals("Bob", sortedAccounts.get(1).getName());
        assertEquals("Amanda", sortedAccounts.get(2).getName());

        //Sorting by balance
        sortedAccounts = accounts
                .stream()
                .sorted(Comparator.comparing(Account::getCurrentBalance))
                .collect(Collectors.toList());

        assertEquals("Amanda", sortedAccounts.get(0).getName());
        assertEquals("Carlos", sortedAccounts.get(1).getName());
        assertEquals("Bob", sortedAccounts.get(2).getName());
    }

    @Test
    public void reverseSortTest() {
        List<Integer> result = Arrays.asList(4, 1, 3, 2).stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(4, 3, 2, 1), result);
    }

    @Test
    public void limitTest() {
        List<Integer> result = Arrays.asList(4, 1, 3, 2).stream()
                .limit(2)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(4, 1), result);
    }

    @Test
    public void skipTest() {
        List<Integer> result = Arrays.asList(4, 1, 3, 2).stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(1, 3), result);
    }

    @Test
    public void averageTest() {
        Double result = Arrays.asList(1, 2, 3).stream()
                .mapToDouble(x -> x.doubleValue())
                .average()
                .getAsDouble();

        assertEquals(2, result);
    }

    @Test
    public void intSummaryStatisticsTest() {
        IntSummaryStatistics summary = IntStream.of(1, 2, 3, 4).summaryStatistics();

        assertEquals(2.5, summary.getAverage());
        assertEquals(4, summary.getMax());
        assertEquals(1, summary.getMin());
        assertEquals(10, summary.getSum());
    }

}
