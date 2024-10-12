package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository repository;

    @Override
    public List<Cliente> findAll(){
        return repository.findAll();
    }

    @Override
    public Cliente findByCedula(String cedula){
        return repository.findByCedula(cedula);
    }

    @Override
    public Cliente createCliente(Cliente cliente){
        return repository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente){
        cliente.setMascotas(repository.findById(cliente.getId()).get().getMascotas());
        return repository.save(cliente);
    }

    @Override
    public boolean deleteCliente(Long id){
        Cliente cliente = repository.findById(id).orElse(null);
        if (cliente == null) {
            return false;
        }
        repository.delete(cliente);
        return true;
    }

    @Override
    public List<Cliente> findBySimilarName(String nombre){
        return repository.findByNombreContaining(nombre.toLowerCase());
    }

    /*@Override
    public Cliente buscarClientePorMascota(Long idMascota) {
        return repository.findByMascotaId(idMascota);
    }*/
}