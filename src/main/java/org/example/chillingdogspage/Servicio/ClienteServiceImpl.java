package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Usuario;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.UsuarioRepository;
import org.example.chillingdogspage.Seguridad.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    ClienteRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Cliente createCliente(Cliente cliente){
        if (repository.findByCedula(cliente.getCedula()) != null) {
            return null;
        }
        // Crear usuario con rol CLIENTE
        Usuario usuario = customUserDetailService.guardarUsuario(cliente.getCedula(), "", "CLIENTE");
        // Crear cliente con la información recibida y asociando el usuario creado
        Cliente clienteCrear = new Cliente(cliente.getCedula(), cliente.getNombre(), cliente.getCorreo(), cliente.getCelular(), cliente.getFoto());
        clienteCrear.setUsuario(usuario);
        return repository.save(clienteCrear);
    }

    @Override
    public List<Cliente> findAll(){
        return repository.findAll();
    }

    @Override
    public Cliente findByCedula(String cedula){
        return repository.findByCedula(cedula);
    }

    @Override
    public List<Cliente> findBySimilarName(String nombre){
        return repository.findByNombreContaining(nombre.toLowerCase());
    }

    @Override
    public Cliente updateCliente(Cliente cliente){
        // Verificar si la cédula ya está registrada en otro cliente, de ser así, no se puede actualizar
        Cliente clienteActual = repository.findByCedula(cliente.getCedula());
        if (clienteActual != null && !Objects.equals(clienteActual.getId(), cliente.getId())) {
            return null;
        }
        // Si no encontramos el cliente por la cédula (seguramente la actualizó), entonces lo buscamos por id
        if (clienteActual == null) {
            clienteActual = repository.findById(cliente.getId()).orElse(null);
            // Si no encontramos el cliente por id, entonces no se puede actualizar (no existe)
            if (clienteActual == null) {
                return null;
            }
        }
        // Modificar solo cedula, nombre, correo, celular, foto si no es nulo
        if (cliente.getCedula() != null) {
            clienteActual.setCedula(cliente.getCedula());
        }
        if (cliente.getNombre() != null) {
            clienteActual.setNombre(cliente.getNombre());
        }
        if (cliente.getCorreo() != null) {
            clienteActual.setCorreo(cliente.getCorreo());
        }
        if (cliente.getCelular() != null) {
            clienteActual.setCelular(cliente.getCelular());
        }
        if (cliente.getFoto() != null) {
            clienteActual.setFoto(cliente.getFoto());
        }
        return repository.save(clienteActual);
    }

    @Override
    public boolean deleteCliente(Long id){
        Cliente cliente = repository.findById(id).orElse(null);
        if (cliente == null) {
            return false;
        }
        // Si encontramos el cliente, no es necesario desvincular las mascotas asociadas a ese cliente
        // Pero sí es necesario desvincular los tratamientos asociados a las mascotas de ese cliente
        cliente.getMascotas().forEach(mascota -> mascota.getTratamientos().forEach(tratamiento -> tratamiento.setMascota(null)));
        // Hay que borrar el rol de cliente en el usuario
        Usuario usuario = cliente.getUsuario();
        cliente.setUsuario(null);
        if (usuario != null) {
            // Transformar la lista de roles para que ya no contenga el rol de "CLIENTE"
            usuario.getRoles().removeIf(rol -> rol.getNombre().equals("CLIENTE"));
            if (usuario.getRoles().isEmpty()) {
                // Si el usuario no tiene más roles, entonces lo borramos
                usuarioRepository.delete(usuario);
            } else {
                // Si el usuario tiene más roles, entonces lo actualizamos
                usuarioRepository.save(usuario);
            }
        }

        // Luego borramos el cliente suavesito
        repository.delete(cliente);
        return true;
    }
}