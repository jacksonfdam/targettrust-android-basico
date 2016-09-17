package br.exemplobaseadapter;

public class Carro {
	private String modelo;
	private String marca;
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public int getCarroImagem(int position){
		switch(position){
			case 0:
				return(R.drawable.carro_1);
			case 1:
				return(R.drawable.carro_2);
			case 2:
				return(R.drawable.carro_3);
			case 3:
				return(R.drawable.carro_4);
			default:
				return(R.drawable.carro_5);
		}
	}
}
