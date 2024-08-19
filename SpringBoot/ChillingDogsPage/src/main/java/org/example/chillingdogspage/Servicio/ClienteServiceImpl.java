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
    public Cliente buscarCliente(String cedula){
        return repository.findByCedula(cedula);
    }

    @Override
    public void registrarCliente(Cliente cliente){
        repository.registrarCliente(cliente);
    }

    @Override
    public void modificarCliente(Integer cedula, Cliente cliente){
        repository.modificarCliente(cedula, cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente){
        repository.eliminarCliente(cliente);
    }
  
    @Override
    public Cliente buscarClientePorMascota(int idMascota) {
        return repository.buscarClientePorMascota(idMascota);
    }
}
