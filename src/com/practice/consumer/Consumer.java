package com.practice.consumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.practice.parkinglot.ParkingLot;
import com.practice.parkinglot.ParkingLotManager;
import com.practice.slot.Slot;

public class Consumer {

	private String name;
	private String phoneNo;
	private String address;
	private double age;
	private boolean isSpeciallyAbled;
	private int parkingToken;
	private Vehicle vehicle;
	private String personType;
	private String entryTime;
	private String exitTime;

	public Consumer(String name, String phoneNo, String address, Vehicle vehicle,double age,boolean isSpeciallyAbled) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
		this.vehicle = vehicle;
		this.age = age;
		this.isSpeciallyAbled = isSpeciallyAbled;
	}

	public boolean isSpeciallyAbled() {
		return isSpeciallyAbled;
	}

	public void setSpeciallyAbled(boolean isSpeciallyAbled) {
		this.isSpeciallyAbled = isSpeciallyAbled;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getParkingToken() {
		return parkingToken;
	}

	public void setParkingToken(int parkingToken) {
		this.parkingToken = parkingToken;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void parkVehicle(ParkingLot parkingLot) {
		this.personType = this.age > 45 ? PersonTypeEnum.ELDERLY.getPersonType() : PersonTypeEnum.YOUNG.getPersonType();
		if (ParkingLotManager.checkSlotAvailability(parkingLot, this).isPresent()) {
			Slot slot = ParkingLotManager.checkSlotAvailability(parkingLot, this).get();
			int slotNo = slot.getSlotNo();
			this.setParkingToken(slotNo);
			slot.setAvailable(false);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			this.setEntryTime(formatter.format(new Date()));
			ParkingLotManager.slotDetails.put(this.vehicle.getVehicleNo(), slotNo);
			System.out.println("Welcome: Your vehicle is parked at slot No:  " + slotNo);
			return;
		}
		System.out.println("We are sorry! Empty Slot is not available currently");
	}

	public void unParkVehicle(ParkingLot parkingLot) {
		Integer parkingToken = ParkingLotManager.slotDetails.get(this.vehicle.getVehicleNo());
		if (null != parkingToken && parkingToken == this.parkingToken) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			this.setExitTime(formatter.format(new Date()));
			Slot slot = parkingLot.getSlots().stream().filter(p -> p.getSlotNo() == parkingToken)
					.collect(Collectors.toList()).get(0);
			try {
				ParkingLotManager.generateReceipt(parkingLot,slot,this,payBill(slot));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			slot.setAvailable(true);
			System.out.println("Successfully unparked!");
			return;
		}
		System.out.println("We are sorry! Please verify your vehilce no and parking Token");
	}

	private double payBill(Slot slot) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date entryTime = formatter.parse(this.entryTime);
			Date exitTime = formatter.parse(this.exitTime);
			double totalDuration = TimeUnit.MILLISECONDS.toMinutes(exitTime.getTime()-entryTime.getTime());
			return slot.getType().getChargePerMinInRS() * totalDuration;
	}
}