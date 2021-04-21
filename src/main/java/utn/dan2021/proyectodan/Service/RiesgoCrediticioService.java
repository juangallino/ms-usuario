package utn.dan2021.proyectodan.Service;

public interface RiesgoCrediticioService {

    public Boolean reporteVerazPositivo(String cuit);
    public Boolean reporteBCRAPositivo(String cuit);
    public Boolean reporteAFIPPositivo(String cuit);

}
