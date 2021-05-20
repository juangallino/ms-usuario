package utn.dan2021.proyectodan.Domain;

import javax.persistence.*;


@Entity
public class Usuario {


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String user;
	private String password;

	@OneToOne
	private TipoUsuario tipoUsuario;

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
