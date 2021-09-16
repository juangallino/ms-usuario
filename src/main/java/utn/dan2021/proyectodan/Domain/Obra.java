package utn.dan2021.proyectodan.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "USR_OBRA", schema = "MS-USR")
public class Obra {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String descripcion;

	@Column
	private Float latitud;

	@Column
	private Float longitud;

	@Column
	private String direccion;

	@Column
	private Integer superficie;

	@OneToOne
	@JoinColumn(name = "ID_TIPO_OBRA")
	private TipoObra tipo;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JsonBackReference	//  Anotación para marcar que esta propiedad se debe ignorar al serializar, evitando referencias recursivas en loop
	private Cliente cliente;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Float getLatitud() {
		return latitud;
	}
	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}
	public Float getLongitud() {
		return longitud;
	}
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}
	public TipoObra getTipo() {
		return tipo;
	}

	public void setTipo(TipoObra tipo1) {
		tipo = tipo1;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		if(this.cliente!=cliente){
		this.cliente = cliente;}
		if(!cliente.getObras().contains(this)){cliente.addObra(this);}
	}







}
