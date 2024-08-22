package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MascotaRepository {
    private Map<Integer, Mascota> data = new HashMap<>();

    public MascotaRepository(){
        data.put(1, new Mascota( 1,"Bengie", "Shih Tzu", 10, 10.0f, "Ser la mascota de Silva", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN6Ul9KehKdD7lzzGS90l7nyXattlnZcrMug&s", "Activo"));
        data.put(2, new Mascota( 2,"Max", "Husky Siberiano", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-08Siberian20Husky2.jpg?itok=d5njFuxB", "Inactivo"));
        data.put(3, new Mascota( 3,"Luna", "Golden Retriever", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-09golden20retriever.jpg?itok=48StbVfe", "Inactivo"));
        data.put(4, new Mascota( 4,"Goku", "Pastor Alemán", 10, 10.0f, "Ser la mascota de Silva", "https://es.mypet.com/wp-content/uploads/sites/23/2021/03/razas-de-perros-pastor-aleman-570x455-1.jpg", "Activo"));
        data.put(5, new Mascota( 5,"Milo", "Pug", 10, 10.0f, "Ser la mascota de Silva", "https://images2.minutemediacdn.com/image/upload/c_crop,h_1191,w_2120,x_0,y_57/v1554740850/shape/mentalfloss/64051-istock-685469924.jpg?itok=Vdq383wX", "Activo"));
        data.put(6, new Mascota( 6,"Luna", "Labrador", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-09labrador.jpg?itok=3Z9Z9Z9Z", "Inactivo"));
    }

    public void save(Mascota mascota) {
        data.put(data.size(), mascota);
    }

    public Mascota findById(int id){
        return data.get(id);
    }

    public Collection<Mascota> findAll(){
        return data.values();
    }

    public void deleteById(int id) {
        data.remove(id);
    }

    public void update(Mascota mascota) {
        data.put(mascota.getId(), mascota);
    }
}
