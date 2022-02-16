package model;

import helper.utils;

public class produto {
	private static int contador = 1;
	
	
	private int codigo;
	private String nome;
	private Double preco;
	
	
	public produto(String nome, Double preco) {
		this.codigo = produto.contador;
		this.nome = nome;
		this.preco = preco;
		produto.contador += 1;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String toString() {
		return "Codigo: " + this.getCodigo() + 
				"\nNome: " + this.getNome() + 
				"\nPreço: " + utils.doubleParaString(this.getPreco());
	}
}
