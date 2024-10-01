package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collection;
import java.util.List;

@Service
public interface ClienteService{
    public List<Cliente> findAll();
    public List<Cliente> obtenerClientes();
  
    public Cliente findByCedula(String cedula);
    public Cliente buscarCliente(String cedula);

    public Cliente getCliente(int id);
  
    public Cliente createCliente(Cliente cliente);

    public Cliente updateCliente(Cliente cliente);

    public boolean deleteCliente(Long id);

    List<Cliente> findBySimilarName(String nombre);
    public void eliminarCliente(String cedulaCliente);

    public void deleteClienteById(int id);

    //public Cliente buscarClientePorMascota(Long idMascota);
}
