package model;

import java.util.List;
import java.util.Objects;

public class UserPosJava {
	
	private long id;
	private String nome;
	private String email;
	
	private List<Telefone> telefones;
	
	
	public UserPosJava(long i, String nome, String email) {
		this.id = i;
		this.nome = nome;
		this.email = email;
	}
	
		public UserPosJava(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public UserPosJava() {
		}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPosJava other = (UserPosJava) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "UserPosJava [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}	
	
}
