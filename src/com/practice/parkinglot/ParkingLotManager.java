package com.practice.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.practice.consumer.Consumer;
import com.practice.consumer.PersonTypeEnum;
import com.practice.consumer.Receipt;
import com.practice.slot.Slot;
import com.practice.slot.SortSlotBySlotNo;

public class ParkingLotManager {

	private String name;
	public static Map<String, Integer> slotDetails = new HashMap<>();

	public static void generateReceipt(ParkingLot parkingLot,Slot slot, Consumer consumer, double totalChargeInRS) {
		Receipt receipt = new Receipt(consumer, totalChargeInRS);
		System.out.println("Thank You! " + consumer.getName() + " for using our service.Your total bill is: "
				+ totalChargeInRS + " Rupees only" + "Please find below details:\n\n" + "Consumer Name:  "
				+ consumer.getName() + "\nVehicle Type:  " + consumer.getVehicle().getVehicleType() + "\nEntry Time:  "
				+ consumer.getEntryTime() + "\nExit Time:  " + consumer.getExitTime() + "\nParking Slot Floor: "
				+ parkingLot.getFloorNo()+"\nSlot No: "+slot.getSlotNo());
	}

	public Map<String, Integer> getSlotDetails() {
		return slotDetails;
	}

	public String getName() {
		return name;
	}

	public static Optional<Slot> checkSlotAvailability(ParkingLot parkingLot, Consumer consumer) {
		if (isSlotAvailable(parkingLot, consumer).isPresent()) {
			return Optional.ofNullable(isSlotAvailable(parkingLot, consumer).get().get(0));
		}
		return Optional.empty();
	}

	private static Optional<List<Slot>> isSlotAvailable(ParkingLot parkingLot, Consumer consumer) {
		String vehicleType = consumer.getVehicle().getVehicleType().getVehicleType();
		List<Slot> slots = parkingLot.getSlots();
		List<Slot> availableSlots = new ArrayList<>();
		if (PersonTypeEnum.ELDERLY.getPersonType().equalsIgnoreCase(consumer.getPersonType())
				|| consumer.isSpeciallyAbled()) {
			availableSlots = slots.stream()
					.filter(s -> s.isAvailable() == true && s.getType().getSlotType().equalsIgnoreCase(vehicleType))
					.collect(Collectors.toList());
		} else {
			availableSlots = slots
					.stream().filter(s -> s.isAvailable() == true
							&& s.getType().getSlotType().equalsIgnoreCase(vehicleType) && !s.isSpecialSlot())
					.collect(Collectors.toList());
		}
		if (!availableSlots.isEmpty()) {
			Collections.sort(availableSlots, new SortSlotBySlotNo());
			return Optional.ofNullable(availableSlots);
		}
		return Optional.empty();
	}

	public static void provideSerivce(ParkingLot parkingLot, Consumer consumer, int option) {

		switch (option) {
		case 1:
			consumer.parkVehicle(parkingLot);
			break;
		case 2:
			// TODO: valet parking
		case 3:
			consumer.unParkVehicle(parkingLot);
			break;
		}

	}
}