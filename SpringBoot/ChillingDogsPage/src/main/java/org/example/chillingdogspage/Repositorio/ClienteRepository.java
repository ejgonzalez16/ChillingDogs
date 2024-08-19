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
        data.put(1, new Cliente("1234567891", "Daniel Silva", "silva@silva.com", "3155784455", mascotas));
        data.put(2, new Cliente("3245789023", "Kurosaki Carlos", "carlos@carlos.com", "3234567789", mascotas));
        data.put(3, new Cliente("5234567890", "Julius Novachrono", "edgar@edgar.com", "3195678341", mascotas));
    }

    public Cliente findByCedula(String cedula){
        for (Map.Entry<Integer, Cliente> entry : data.entrySet()) {
            if (entry.getValue().getCedula().equals(cedula)) {
                return entry.getValue();
            }
        }
        return null;
    }
}