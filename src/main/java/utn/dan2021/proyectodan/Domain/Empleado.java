package utn.dan2021.proyectodan.Domain;

import javax.persistence.*;

@Entity
public class Empleado {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String mail;

	@OneToOne
	private Usuario user;

	@Column
	private String nombre;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
