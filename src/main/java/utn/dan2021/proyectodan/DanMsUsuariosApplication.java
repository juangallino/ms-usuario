package utn.dan2021.proyectodan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Domain.Obra;
import utn.dan2021.proyectodan.Domain.TipoUsuario;
import utn.dan2021.proyectodan.Domain.Usuario;
import utn.dan2021.proyectodan.Service.Implementations.ClienteServiceImpl;
import utn.dan2021.proyectodan.repository.ClienteRepository;

@SpringBootApplication
public class DanMsUsuariosApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DanMsUsuariosApplication.class, args);

		System.out.println("VAMOS LAS CARAJO: BUILD --SUCCESS");


	}


}
