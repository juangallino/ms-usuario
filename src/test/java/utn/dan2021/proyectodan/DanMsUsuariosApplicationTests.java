package utn.dan2021.proyectodan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Service.Implementations.ClienteServiceImpl;

@SpringBootTest
class DanMsUsuariosApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Hola proyecto spring");

		ClienteServiceImpl clienteService = new ClienteServiceImpl();


		Cliente cl = new Cliente();
		cl.setId(1);
		cl.setMail("el juango @gmial.com");
		cl.setCuit("285489dfr");

		clienteService.guardarLazy(cl);

	}

}
