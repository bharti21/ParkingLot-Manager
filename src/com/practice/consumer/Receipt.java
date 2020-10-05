package com.practice.consumer;

public class Receipt {


	Consumer consumer;
	double chargePerMinInRS;
	
	public Receipt(Consumer consumer, double chargePerMinInRS) {
		this.consumer = consumer;
		this.chargePerMinInRS = chargePerMinInRS;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public double getChargePerMinInRS() {
		return chargePerMinInRS;
	}

	public void setChargePerMinInRS(double chargePerMinInRS) {
		this.chargePerMinInRS = chargePerMinInRS;
	}
	
}
