package br.com.targettrust.aulaintegracao;

public class ProdutoVO {
	
	private int id;
	private String descricao;
	private String codigo;
	private double preco;
	
	public ProdutoVO() {
		
	}
	
	public ProdutoVO(String nome, double preco) {
		super();
		this.descricao = nome;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
	

}
