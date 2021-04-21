package utn.dan2021.proyectodan.Service.Implementations;

import org.springframework.stereotype.Service;
import utn.dan2021.proyectodan.Service.RiesgoCrediticioService;

@Service
public class RiesgoCrediticioServiceImpl implements RiesgoCrediticioService {
    @Override
    public Boolean reporteVerazPositivo(String cuit) {
        return true;
    }

    @Override
    public Boolean reporteBCRAPositivo(String cuit) {
        System.out.println("Paso por riesgo credit");
        return true;
    }

    @Override
    public Boolean reporteAFIPPositivo(String cuit) {
        return true;
    }
}
