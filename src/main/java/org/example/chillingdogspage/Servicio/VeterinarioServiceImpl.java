package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    VeterinarioRepository repository;

    @Override
    public List<Veterinario> findAll(){
        return repository.findAll();
    }

    @Override
    public Veterinario findByCedula(String cedula){
        return repository.findByCedula(cedula);
    }

    @Override
    public Veterinario findByCedulaAndContrasena(String cedula, String contrasena){
        return repository.findByCedulaAndContrasena(cedula, contrasena);
    }

    @Override
    public Veterinario createVeterinario(Veterinario veterinario){
        return repository.save(veterinario);
    }

    @Override
    public Veterinario updateVeterinario(Veterinario veterinario){
        veterinario.setTratamientos(repository.findById(veterinario.getId()).get().getTratamientos());
        return repository.save(veterinario);
    }

    @Override
    public boolean deleteVeterinario(Long id){
        Veterinario veterinario = repository.findById(id).orElse(null);
        if (veterinario == null) {
            return false;
        }
        // Si encontramos el veterinario, desvinculamos los tratamientos asociados a ese veterinario y luego lo borramos
        veterinario.getTratamientos().forEach(tratamiento -> tratamiento.setVeterinario(null));
        repository.delete(veterinario);
        return true;
    }

    @Override
    public List<Veterinario> findBySimilarName(String nombre){
        return repository.findByNombreContaining(nombre.toLowerCase());
    }
}