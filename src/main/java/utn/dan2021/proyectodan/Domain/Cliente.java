package utn.dan2021.proyectodan.Domain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "USR_CLIENTE", schema = "MS-USR")
public class Cliente  {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String razonSocial;
	@Column
	private String cuit;
	@Column
	private String mail;
	@Column
	private Double maxCuentaCorriente;
	@Column
	private Boolean habilitadoOnline;

	@OneToOne
	private Usuario user;

	@OneToMany(mappedBy ="cliente")
	private List<Obra> obras;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public Double getMaxCuentaCorriente() {
		return maxCuentaCorriente;
	}
	public void setMaxCuentaCorriente(Double maxCuentaCorriente) {
		this.maxCuentaCorriente = maxCuentaCorriente;
	}

	public Boolean getHabilitadoOnline() {
		return habilitadoOnline;
	}
	public void setHabilitadoOnline(Boolean habilitadoOnline) {
		this.habilitadoOnline = habilitadoOnline;
	}

	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Obra> getObras() {
		return obras;
	}
	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}


}
