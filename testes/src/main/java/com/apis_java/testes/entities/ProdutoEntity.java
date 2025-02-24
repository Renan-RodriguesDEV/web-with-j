package com.apis_java.testes.entities;

public class ProdutoEntity {
	String name;int stok;Double value;
	public ProdutoEntity(String name,int stok,Double value) {
		this.name = name;
		this.stok = stok;
		this.value = value;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
