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
        List<Mascota> mascotas = new ArrayList<>();
        List<Mascota> mascotas2 = new ArrayList<>();
        List<Mascota> mascotas3 = new ArrayList<>();
        mascotas.add(new Mascota( 1,"Bengie", "Shih Tzu", 10, 10.0f, "Ser la mascota de Silva", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN6Ul9KehKdD7lzzGS90l7nyXattlnZcrMug&s", "Activo"));
        mascotas.add(new Mascota( 2,"Max", "Husky Siberiano", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-08Siberian20Husky2.jpg?itok=d5njFuxB", "Inactivo"));
        data.put(1, new Cliente("1", "Daniel Silva", "silva@silva.com", "3155784455", "https://www.famousbirthdays.com/faces/vazquez-jovani-image.jpg", mascotas));
        mascotas2.add(new Mascota( 3,"Luna", "Golden Retriever", 10, 10.0f, "Ser la mascota de Silva", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-09golden20retriever.jpg?itok=48StbVfe", "Inactivo"));
        data.put(2, new Cliente("2", "Kurosaki Carlos", "carlos@carlos.com", "3234567789", "https://www.famousbirthdays.com/faces/vazquez-jovani-image.jpg", mascotas2));
        mascotas3.add(new Mascota( 4,"Goku", "Pastor Alemán", 10, 10.0f, "Ser la mascota de Silva", "https://es.mypet.com/wp-content/uploads/sites/23/2021/03/razas-de-perros-pastor-aleman-570x455-1.jpg", "Activo"));
        mascotas3.add(new Mascota( 5,"Milo", "Pug", 10, 10.0f, "Ser la mascota de Silva", "https://images2.minutemediacdn.com/image/upload/c_crop,h_1191,w_2120,x_0,y_57/v1554740850/shape/mentalfloss/64051-istock-685469924.jpg?itok=Vdq383wX", "Activo"));
        mascotas3.add(new Mascota( 6,"Luna", "Labrador", 10, 10.0f, "Ser la mascota de Silva", "https://th.bing.com/th/id/R.55bd233e072fced4cc99e68f73b50d9a?rik=gJW5cTX8VljaeA&riu=http%3a%2f%2fupload.wikimedia.org%2fwikipedia%2fcommons%2f8%2f86%2fLabrador_Retriever_chocolate_Hershey_sit.jpg&ehk=hWIhzc%2bbVPhPnvg%2bLB0IqNsvnBiuTCn02G0yrJtVsYU%3d&risl=1&pid=ImgRaw&r=0", "Inactivo"));
        data.put(3, new Cliente("3", "Julius Novachrono", "edgar@edgar.com", "3195678341", "https://www.famousbirthdays.com/faces/vazquez-jovani-image.jpg", mascotas3));
    }

    public Collection<Cliente> allClientes(){
        return data.values();
    }

    public Cliente findByCedula(String cedula){
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            if (entry.getValue().getCedula().equals(cedula)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void registrarCliente(Cliente cliente) {
        data.put(data.size() + 1, cliente);
    }

    public void modificarCliente(Integer cedulaAnterior, Cliente cliente) {
        int pos = -1;
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            if (entry.getValue().getCedula().equals(cedulaAnterior)) {
                pos = entry.getKey();
                break;
            }
        }
        cliente.setMascotas(data.get(pos).getMascotas());
        data.put(pos, cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        int pos = -1;
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            if (entry.getValue().getCedula().equals(cliente.getCedula())) {
                pos = entry.getKey();
                break;
            }
        }
        data.remove(pos);
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