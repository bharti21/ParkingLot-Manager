package com.practice.slot;

public class Slot  {
	private int slotNo;
	private SlotTypeAndChargePerMinEnum slotType;
	boolean isAvailable;
	boolean isSpecialSlot;
	
	public Slot(int slotNo, SlotTypeAndChargePerMinEnum slotType, boolean isAvailable, boolean isSpecialSlot) {
		this.slotNo = slotNo;
		this.slotType = slotType;
		this.isAvailable = isAvailable;
		this.isSpecialSlot = isSpecialSlot;
	}


	public boolean isSpecialSlot() {
		return isSpecialSlot;
	}


	public void setSpecialSlot(boolean isSpecialSlot) {
		this.isSpecialSlot = isSpecialSlot;
	}


	public int getSlotNo() {
		return slotNo;
	}


	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}


	public SlotTypeAndChargePerMinEnum getType() {
		return slotType;
	}


	public void setType(SlotTypeAndChargePerMinEnum type) {
		this.slotType = type;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}
