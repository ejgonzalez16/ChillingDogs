package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Usuario;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Repositorio.UsuarioRepository;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.example.chillingdogspage.Seguridad.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    VeterinarioRepository repository;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Veterinario> findAll(){
        return repository.findAll();
    }

    @Override
    public Veterinario findByCedula(String cedula){
        return repository.findByCedula(cedula);
    }

    @Override
    public Veterinario createVeterinario(Veterinario veterinario){
        if (repository.findByCedula(veterinario.getCedula()) != null) {
            return null;
        }
        // Crear usuario con rol VETERINARIO
        Usuario usuario = customUserDetailService.guardarUsuario(veterinario.getCedula(), veterinario.getContrasena(), "VETERINARIO");
        // Crear veterinario con la información recibida y asociando el usuario creado
        Veterinario veterinarioCrear = new Veterinario(veterinario.getCedula(), veterinario.getContrasena(), veterinario.getEspecialidad(), veterinario.getNombre(), veterinario.getEstado(), veterinario.getFoto());
        veterinarioCrear.setUsuario(usuario);
        return repository.save(veterinarioCrear);
    }

    @Override
    public Veterinario updateVeterinario(Veterinario veterinario){
        // Verificar si la cédula ya está registrada en otro veterinario, de ser así, no se puede actualizar
        Veterinario veterinarioActual = repository.findByCedula(veterinario.getCedula());
        if (veterinarioActual != null && !Objects.equals(veterinarioActual.getId(), veterinario.getId())) {
            return null;
        }
        // Si no encontramos el veterinario por la cédula (seguramente la actualizó), entonces lo buscamos por id
        if (veterinarioActual == null) {
            veterinarioActual = repository.findById(veterinario.getId()).orElse(null);
            // Si no encontramos el veterinario por id, entonces no se puede actualizar (no existe)
            if (veterinarioActual == null) {
                return null;
            }
        }
        // Modificar solo cedula, especialidad, nombre, estado, foto si no es nulo
        if (veterinario.getCedula() != null) {
            veterinarioActual.setCedula(veterinario.getCedula());
        }
        if (veterinario.getEspecialidad() != null) {
            veterinarioActual.setEspecialidad(veterinario.getEspecialidad());
        }
        if (veterinario.getNombre() != null) {
            veterinarioActual.setNombre(veterinario.getNombre());
        }
        if (veterinario.getEstado() != null) {
            veterinarioActual.setEstado(veterinario.getEstado());
        }
        if (veterinario.getFoto() != null) {
            veterinarioActual.setFoto(veterinario.getFoto());
        }
        return repository.save(veterinarioActual);
    }

    @Override
    public boolean deleteVeterinario(Long id){
        Veterinario veterinario = repository.findById(id).orElse(null);
        if (veterinario == null) {
            return false;
        }
        // Si encontramos el veterinario, desvinculamos los tratamientos asociados a ese veterinario y luego lo borramos
        veterinario.getTratamientos().forEach(tratamiento -> tratamiento.setVeterinario(null));
        // Hay que borrar el rol de veterinario en el usuario
        Usuario usuario = veterinario.getUsuario();
        veterinario.setUsuario(null);
        if (usuario != null) {
            // Transformar la lista de roles para que ya no contenga el rol de "CLIENTE"
            usuario.getRoles().removeIf(rol -> rol.getNombre().equals("VETERINARIO"));
            if (usuario.getRoles().isEmpty()) {
                // Si el usuario no tiene más roles, entonces lo borramos
                usuarioRepository.delete(usuario);
            } else {
                // Si el usuario tiene más roles, entonces lo actualizamos
                usuarioRepository.save(usuario);
            }
        }
        // Finalmente, borrar el veterinario
        repository.delete(veterinario);
        return true;
    }

    @Override
    public List<Veterinario> findBySimilarName(String nombre){
        return repository.findByNombreContaining(nombre.toLowerCase());
    }
}