package task2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {

    @Before
    public void setUp() {
	customer = new Customer("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
	customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE),
		3));
	assertEquals(
		"Rental Record for Fred" + System.lineSeparator()
			+ "\tThe Cell\t9.0" + System.lineSeparator()
			+ "You owed 9.0" + System.lineSeparator()
			+ "You earned 2 frequent renter points"
			+ System.lineSeparator(), customer.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
	customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE),
		3));
	customer.addRental(new Rental(new Movie("The Tigger Movie",
		Movie.NEW_RELEASE), 3));
	assertEquals(
		"Rental Record for Fred" + System.lineSeparator()
			+ "\tThe Cell\t9.0" + System.lineSeparator()
			+ "\tThe Tigger Movie\t9.0" + System.lineSeparator()
			+ "You owed 18.0" + System.lineSeparator()
			+ "You earned 4 frequent renter points"
			+ System.lineSeparator(), customer.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
	customer.addRental(new Rental(new Movie("The Tigger Movie",
		Movie.CHILDRENS), 3));
	assertEquals(
		"Rental Record for Fred" + System.lineSeparator()
			+ "\tThe Tigger Movie\t1.5" + System.lineSeparator()
			+ "You owed 1.5" + System.lineSeparator()
			+ "You earned 1 frequent renter points"
			+ System.lineSeparator(), customer.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
	customer.addRental(new Rental(new Movie("Plan 9 from Outer Space",
		Movie.REGULAR), 1));
	customer.addRental(new Rental(new Movie("8 1/2", Movie.REGULAR), 2));
	customer.addRental(new Rental(new Movie("Eraserhead", Movie.REGULAR), 3));

	assertEquals(
		"Rental Record for Fred" + System.lineSeparator()
			+ "\tPlan 9 from Outer Space\t2.0"
			+ System.lineSeparator() + "\t8 1/2\t2.0"
			+ System.lineSeparator() + "\tEraserhead\t3.5"
			+ System.lineSeparator() + "You owed 7.5"
			+ System.lineSeparator()
			+ "You earned 3 frequent renter points"
			+ System.lineSeparator(), customer.statement());
    }

    private Customer customer;
}
