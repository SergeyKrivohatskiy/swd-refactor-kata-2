package task2;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
	this.movie = movie;
	this.daysRented = daysRented;
    }

    public int getDaysRented() {
	return daysRented;
    }

    public Movie getMovie() {
	return movie;
    }

    public boolean earnAdditionalFrequentPoint() {
	return getMovie().getPriceCode() == Movie.PriceCode.NEW_RELEASE
		&& getDaysRented() > 1;
    }

    public double calculateAmount() {
	switch (getMovie().getPriceCode()) {
	case REGULAR: {
	    double rentalAmount = 2;
	    if (getDaysRented() > 2) {
		rentalAmount += (getDaysRented() - 2) * 1.5;
	    }
	    return rentalAmount;
	}
	case NEW_RELEASE: {
	    return getDaysRented() * 3;
	}
	case CHILDRENS: {
	    double rentalAmount = 1.5;
	    if (getDaysRented() > 3) {
		rentalAmount += (getDaysRented() - 3) * 1.5;
	    }
	    return rentalAmount;
	}
	default:
	    throw new RuntimeException(String.format(
		    "Unprocessed movie price code '%s'! ", getMovie()
			    .getPriceCode()));
	}
    }
}
