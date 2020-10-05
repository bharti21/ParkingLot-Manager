package com.practice.slot;

public enum SlotTypeAndChargePerMinEnum {
	
	CAR("car",2), BIKE("bike",1), TRUCK("truck",4), AUTORICKSHAW("autorickshaw",2.5);
	
	private String slotType;
	private double chargePerMinInRS;
	
	SlotTypeAndChargePerMinEnum(String slotType, double chargePerMinInRS) {
		this.slotType = slotType;
		this.chargePerMinInRS = chargePerMinInRS;
	}
	public String getSlotType() {
		return slotType;
	}
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}
	public double getChargePerMinInRS() {
		return chargePerMinInRS;
	}
	public void setChargePerMinInRS(double chargePerMinInRS) {
		this.chargePerMinInRS = chargePerMinInRS;
	}
	
}
