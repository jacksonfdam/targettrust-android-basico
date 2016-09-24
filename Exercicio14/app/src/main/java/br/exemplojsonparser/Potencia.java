package br.exemplojsonparser;

public class Potencia {
	private double motor;
	private int cavalos;
	
	
	
	public Potencia(){
		super();
	}
	public Potencia(float motor, int cavalos){
		this.motor = motor;
		this.cavalos = cavalos;
	}
	
	
	public double getMotor() {
		return motor;
	}
	public void setMotor(double motor) {
		this.motor = motor;
	}
	
	
	public int getCavalos() {
		return cavalos;
	}
	public void setCavalos(int cavalos) {
		this.cavalos = cavalos;
	}
}
