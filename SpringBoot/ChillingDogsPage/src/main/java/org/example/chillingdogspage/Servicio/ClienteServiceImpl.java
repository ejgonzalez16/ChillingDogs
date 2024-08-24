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
        return repository.findAll();
    }

    @Override
    public Cliente buscarCliente(String cedula){
        return repository.findByCedula(cedula);
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
    public void eliminarCliente(Cliente cliente){
        repository.delete(cliente);
    }
  
    @Override
    public Cliente buscarClientePorMascota(Long idMascota) {
        return repository.findByMascotaId(idMascota);
    }
}
