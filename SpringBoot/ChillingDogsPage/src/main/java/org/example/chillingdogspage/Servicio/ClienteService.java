package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ClienteService{
    public List<Cliente> obtenerClientes();
  
    public Cliente buscarCliente(String cedula);
  
    public void registrarCliente(Cliente cliente);

    public void modificarCliente(Cliente cliente);

    public void eliminarCliente(String cedulaCliente);

    public Cliente buscarClientePorMascota(Long idMascota);
}
