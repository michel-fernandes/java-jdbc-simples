package model;

import java.util.Objects;

public class Telefone {
	private long id;
	private String numero;
	private String tipo;
	
	private long userId;

	public Telefone() {
	}

	public Telefone(String numero, String tipo, long userId) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numero, tipo, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return id == other.id && Objects.equals(numero, other.numero) && Objects.equals(tipo, other.tipo)
				&& userId == other.userId;
	}
}
