package task2;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
	this.name = name;
    }

    public void addRental(Rental rental) {
	rentals.add(rental);
    }

    public String getName() {
	return name;
    }

    public String statement() {
	double rentalsAmount = 0;
	int frequentRenterPoints = 0;
	StringBuilder result = new StringBuilder();

	appendTitle(result);

	for (Rental rental : rentals) {
	    double rentalAmount = calculateRentalAmount(rental);

	    appentRentalInfo(result, rental, rentalAmount);

	    rentalsAmount += rentalAmount;
	    frequentRenterPoints += earnAdditionalFrequentPoint(rental) ? 2 : 1;
	}

	appendResume(result, rentalsAmount, frequentRenterPoints);

	return result.toString();
    }

    private void appendResume(StringBuilder result, double rentalsAmount,
	    int frequentRenterPoints) {
	result.append(String.format("You owed %s",
		String.valueOf(rentalsAmount)));
	result.append(System.lineSeparator());
	result.append(String.format("You earned %s frequent renter points",
		String.valueOf(frequentRenterPoints)));
	result.append(System.lineSeparator());
    }

    private void appentRentalInfo(StringBuilder result, Rental rental,
	    double rentalAmount) {
	result.append(String.format("\t%s\t%s", rental.getMovie().getTitle(),
		String.valueOf(rentalAmount)));
	result.append(System.lineSeparator());
    }

    private void appendTitle(StringBuilder result) {
	result.append(String.format("Rental Record for %s", getName()));
	result.append(System.lineSeparator());
    }

    // TODO move that logic to rental
    private boolean earnAdditionalFrequentPoint(Rental rental) {
	return rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
		&& rental.getDaysRented() > 1;
    }

    // TODO move that logic to rental
    private double calculateRentalAmount(Rental rental) {
	switch (rental.getMovie().getPriceCode()) {
	case Movie.REGULAR: {
	    double rentalAmount = 2;
	    if (rental.getDaysRented() > 2) {
		rentalAmount += (rental.getDaysRented() - 2) * 1.5;
	    }
	    return rentalAmount;
	}
	case Movie.NEW_RELEASE: {
	    return rental.getDaysRented() * 3;
	}
	case Movie.CHILDRENS: {
	    double rentalAmount = 1.5;
	    if (rental.getDaysRented() > 3) {
		rentalAmount += (rental.getDaysRented() - 3) * 1.5;
	    }
	    return rentalAmount;
	}
	}
	throw new RuntimeException(String.format(
		"Unprocessed movie price code '%s'! ", rental.getMovie()
			.getPriceCode()));
    }
}
