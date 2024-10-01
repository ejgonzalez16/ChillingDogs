package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public interface ClienteService{
    public List<Cliente> obtenerClientes();
  
    public Cliente buscarCliente(String cedula);

    public Cliente getCliente(int id);
  
    public void registrarCliente(Cliente cliente);

    public void modificarCliente(Cliente cliente);

    public void eliminarCliente(String cedulaCliente);

    public void deleteClienteById(int id);

    public Cliente buscarClientePorMascota(Long idMascota);
}
