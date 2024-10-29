package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ClienteService{
    public List<Cliente> findAll();

    public Cliente findByCedula(String cedula);

    public Cliente createCliente(Cliente cliente);

    public Cliente updateCliente(Cliente cliente);

    public boolean deleteCliente(Long id);

    List<Cliente> findBySimilarName(String nombre);

    //public Cliente buscarClientePorMascota(Long idMascota);
}