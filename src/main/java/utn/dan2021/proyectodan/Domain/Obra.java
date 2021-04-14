package utn.dan2021.proyectodan.Domain;

public class Obra {
	
	private Integer id;
	private String descripcion;
	private Float latitud;
	private Float longitud;
	private String direccion;
	private Integer superficie;
	private utn.dan2021.proyectodan.Domain.TipoObra tipo;
	private utn.dan2021.proyectodan.Domain.Cliente cliente;
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
	public utn.dan2021.proyectodan.Domain.TipoObra getTipo() {
		return tipo;
	}
	public void setTipo(utn.dan2021.proyectodan.Domain.TipoObra tipo) {
		this.tipo = tipo;
	}
	public utn.dan2021.proyectodan.Domain.Cliente getCliente() {
		return cliente;
	}
	public void setCliente(utn.dan2021.proyectodan.Domain.Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
