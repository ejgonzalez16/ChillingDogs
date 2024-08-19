package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClienteRepository {
    private Map<Integer, Cliente> data = new HashMap<>();

    public ClienteRepository(){
        List<Mascota> mascotas1 = new ArrayList<>();
        mascotas1.add( new Mascota( 1,"Bengie", "Shih Tzu", 10, 10.0f, "Ser la mascota de Silva", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN6Ul9KehKdD7lzzGS90l7nyXattlnZcrMug&s", "Activo"));
        data.put(1, new Cliente( "1234567891", "Daniel Silva", "silva@silva.com", "3155784455", mascotas1));
        List<Mascota> mascotas2 = new ArrayList<>();
        mascotas2.add(new Mascota( 2,"Max", "Husky Siberiano", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-08Siberian20Husky2.jpg?itok=d5njFuxB", "Inactivo"));
        data.put(2, new Cliente("3245789023", "Kurosaki Carlos", "carlos@carlos.com", "3234567789", mascotas2));
        List<Mascota> mascotas3 = new ArrayList<>();
        mascotas3.add(new Mascota( 3,"Luna", "Golden Retriever", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-09golden20retriever.jpg?itok=48StbVfe", "Inactivo"));
        mascotas3.add(new Mascota( 4,"Goku", "Pastor Alemán", 10, 10.0f, "Ser la mascota de Silva", "https://es.mypet.com/wp-content/uploads/sites/23/2021/03/razas-de-perros-pastor-aleman-570x455-1.jpg", "Activo"));
        data.put(3, new Cliente("5234567890", "Julius Novachrono", "edgar@edgar.com", "3195678341", mascotas3));
    }

    public Cliente findByCedula(String cedula){
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            if (entry.getValue().getCedula().equals(cedula)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Cliente buscarClientePorMascota(int idMascota){
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            for(Mascota mascota: entry.getValue().getMascotas()){
                if(mascota.getId() == idMascota){
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}