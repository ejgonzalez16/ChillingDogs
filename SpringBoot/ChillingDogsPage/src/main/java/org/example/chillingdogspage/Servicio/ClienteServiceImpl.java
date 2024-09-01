package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
  
    @Autowired
    ClienteRepository repository;

    @Override
    public Collection<Cliente> obtenerClientes(){
        Collection<Cliente> clientes = repository.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarCliente(String cedula){
        Cliente cliente = repository.findByCedula(cedula);
        return cliente;
    }

    @Override
    public void registrarCliente(Cliente cliente){
        repository.save(cliente);
    }

    @Override
    public void modificarCliente(Cliente cliente){
        repository.save(cliente);
    }

    @Override
    public void eliminarCliente(String cedulaCliente){
        Cliente cliente = repository.findByCedula(cedulaCliente);
        repository.delete(cliente);
    }
  
    @Override
    public Cliente buscarClientePorMascota(Long idMascota) {
        return repository.findByMascotaId(idMascota);
    }
}
