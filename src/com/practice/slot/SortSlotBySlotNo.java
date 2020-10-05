package com.practice.slot;
import java.util.Comparator;



public class SortSlotBySlotNo implements Comparator<Slot>{

	public int compare(Slot s1,Slot s2){
		return s1.getSlotNo() - s2.getSlotNo();
	}
	

}