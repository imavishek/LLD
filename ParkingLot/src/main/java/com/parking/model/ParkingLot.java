package main.java.com.parking.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

enum AccountStatus {
	ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}

enum VehicleType {
	BIKE, CAR, TRUCK
}

enum ParkingSlotType {
	BIKE_PARKING, CAR_PARKING, TRUCK_PARKING
}

enum ParkingTicketStatus {
	PAID, ACTIVE
}

enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED
}

enum PaymentType {
	CASH, CEDIT_CARD, DEBIT_CARD, UPI
}

class Address {
	private Integer addressId;
	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String pinCode;
}

class User {
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
	private User user;
}

class Admin extends Account {
	public Boolean addParkingFloor(ParkingLot parkingLot, ParkingFloor floor);

	public Boolean addParkingSlot(ParkingFloor floor, ParkingSlot parkingSlot);

	public Boolean addParkingDisplayBoard(ParkingFloor floor, ParkingDisplayBoard parkingDisplayBoard);

	public Boolean addEntrancePanel(Entrance entrancePanel);

	public Boolean addExitPanel(Exit exitPanel);
}

class ParkingAttendant extends Account {
	public Boolean processVehicleEntry(Vehicle vehicle);

	public Boolean processPayment(ParkingTicket parkingTicket, PaymentType paymentType);
}

public class ParkingLot {
	private List<ParkingFloor> parkingFloors;
	private List<Entrance> entrances;
	private List<Exit> exits;
	private String parkingLotName;
	private Address address;

	public Boolean isParkingSpaceAvailableForVehicle(Vehicle vehicle);
}

class ParkingFloor {
	private Integer floorId;
	private List<ParkingSlot> parkingSlots;
	private ParkingDisplayBoard parkingDisplayBoard;
}

class ParkingSlot {
	private Integer slotId;
	private Boolean isFree;
	private Double costPerHour;
	private Vehicle vehicle;
	private ParkingSlotType parkingSlotType;
}

class ParkingDisplayBoard {
	private Map<ParkingSlotType, Integer> freeSpotsAvailableMap;
}

abstract class Vehicle {
	private final VehicleType vehicleType;
	private String licenseNumber;
	private ParkingTicket parkingTicket;

	public Vehicle(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}

class Bike extends Vehicle {
	public Bike() {
		super(VehicleType.BIKE);
	}
}

class Car extends Vehicle {
	public Car() {
		super(VehicleType.CAR);
	}
}

class Truck extends Vehicle {
	public Truck() {
		super(VehicleType.TRUCK);
	}
}

class ParkingTicket {
	private Integer ticketId;
	private Integer floorId;
	private Integer slotId;
	private LocalDateTime vehicleEntryTime;
	private LocalDateTime vehicleExitTime;
	private ParkingTicketStatus parkingTicketStatus;
	private Double totalCost;
}

abstract class Gate {
	private Integer gateId;
	private ParkingAttendant parkingAttendant;
}

class Entrance extends Gate {
	public ParkingTicket getParkingTicket(Vehicle vehicle);
}

class Exit extends Gate {
	public ParkingTicket payForParking(ParkingTicket parkingTicket, PaymentType paymentType);
}

class Payment {
	public PaymentInfo makePayment(ParkingTicket parkingTicket, PaymentType paymentType);
}

class PaymentInfo {
	private Double amount;
	private LocalDateTime paymentDate;
	private Integer transactionId;
	private ParkingTicket parkingTicket;
	private PaymentStatus paymentStatus;
}

