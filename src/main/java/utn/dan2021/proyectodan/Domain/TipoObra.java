package utn.dan2021.proyectodan.Domain;

import javax.persistence.*;

@Entity
@Table(name = "USR_TIPO_OBRA", schema = "MS-USR")
public class TipoObra {

	@Id
	//@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String descripcion;


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
}
