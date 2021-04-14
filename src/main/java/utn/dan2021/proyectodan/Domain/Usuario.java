package utn.dan2021.proyectodan.Domain;

public class Usuario {

	
	private Integer id;
	private String user;
	private String password;
	private utn.dan2021.proyectodan.Domain.TipoUsuario tipoUsuario;

	public utn.dan2021.proyectodan.Domain.TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(utn.dan2021.proyectodan.Domain.TipoUsuario tipoUsuario) {
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
