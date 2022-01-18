package bms;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

enum AccountStatus {
	ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}

enum SeatType {
	DELUX, VIP, ECONOMY, OTHER
}

enum SeatStatus {
	BOOKED, AVAILABLE, RESERVED, NOT_AVAILABLE
}

enum BookingStatus {
	REQUESTED, PENDING, CONFIRMED, CANCELLED
}

enum PaymentMethod {
	UPI, CASH, CARD, PAYPAL
}

enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED
}

class Address {
	private Integer addressId;
	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String pinCode;
}

class Person {
	private Integer id;
	private String name;
	private String email;
	private String mobNo;
	private Address address;
}

abstract class Account {
	private String username;
	private String password;
	private AccountStatus status;
	private Person person;
}

class Admin extends Account {
	public Boolean addMovie(Movie movie);

	public Boolean addShow(Show show);
}

class User extends Account {
	public Booking bookShow(Integer showId);

	public List<Booking> getBooking();

	public List<Movie> searchMoviesByNames(String name);

	public List<Movie> searchMoviesByGenre(Genre genre);

	public List<Movie> searchMoviesByLanguage(String language);

	public List<Movie> searchMoviesByDate(LocalDate releaseDate);
}

class City {
	private Integer cityId;
	private String cityName;
	private String state;
	private String country;
	private String pinCode;
}

class CinemaHall {
	private Integer cinemaHallId;
	private String cinemaHallName;
	private String streetAddress;
	private City city;
	private List<Audi> audiList;
}

class Audi {
	private Integer audiId;
	private String audiName;
	private Integer totalSeats;
	private List<Show> shows;
}

class Genre {
	private Integer genreId;
	private String genreName;
}

class Movie {
	private Integer movieId;
	private String movieName;
	private String title;
	private String descreption;
	private Duration duration;
	private LocalDate releaseDate;
	private List<String> languages;
	private List<Genre> genres;
	private Byte active;
}

class Show {
	private Integer showId;
	private Movie movie;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private CinemaHall cinemaPlayedAt;
	private List<Seat> seats;
}

class Seat {
	private Integer seatId;
	private SeatType seatType;
	private String seatNo;
	private Integer rowNo;
	private Integer colNo;
	private Audi audi;
}

class ShowSeat {
	private Integer showSeatId;
	private Integer showId;
	private Integer seatId;
	private Double price;
	private Integer bookingId;
	private SeatStatus seatStatus;
}

class Booking {
	private Integer bookingId;
	private Integer userId;
	private Integer noOfSeats;
	private Integer showId;
	private Double totalPrice;
	private BookingStatus bookingStatus;
	private LocalDateTime bookingTime;
}

class Payment {
	private Integer paymentId;
	private Integer bookingId;
	private Double amount;
	private Integer couponId;
	private PaymentStatus paymentStatus;
	private PaymentMethod paymentMethod;
	private LocalDateTime paymentTime;
}

class Comment {
}

class Rating {
}

public class Model {
}
