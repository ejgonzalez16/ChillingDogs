package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public interface ClienteService{
    public Collection<Cliente> obtenerClientes();
  
    public Cliente buscarCliente(String cedula);
  
    public void registrarCliente(Cliente cliente);

    public void modificarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

    public Cliente buscarClientePorMascota(Long idMascota);
}
