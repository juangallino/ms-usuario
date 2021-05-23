package utn.dan2021.proyectodan.Domain;

import javax.persistence.*;

@Entity
@Table(name = "USR_TIPO_USUARIO", schema = "MS-USR")
public class TipoUsuario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String tipo;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
