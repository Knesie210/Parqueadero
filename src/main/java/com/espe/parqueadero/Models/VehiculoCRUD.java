package com.espe.parqueadero.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehiculoCRUD {
    private final MongoCollection<Document> collection;

    public VehiculoCRUD(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void ingresarVehiculo(Vehiculo vehiculo) {
        Document doc = new Document("placa", vehiculo.getPlaca())
                .append("propietario", vehiculo.getPropietario())
                .append("horaEntrada", vehiculo.getHora())
                .append("tipoVehiculo", vehiculo.getTipoVehiculo())
                .append("horaSalida", null);
        collection.insertOne(doc);
    }

    public List<Vehiculo> listarVehiculos(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setPlaca(doc.getString("placa"));
                vehiculo.setPropietario(doc.getString("propietario"));
                vehiculo.setTipoVehiculo(doc.getString("tipoVehiculo"));
                vehiculo.setHora(doc.getString("horaEntrada"));

                vehiculos.add(vehiculo);
            }
        } finally {
            cursor.close();
        }
        return vehiculos;
    }

    public void actualizarHoraSalida(String placa) {
        Document filtro = new Document("placa", placa);
        Document actualizacion = new Document("$set", new Document("horaSalida", LocalDateTime.now().toString()));
        collection.updateOne(filtro, actualizacion);
    }

    public void actualizarDatosVehiculo(String placa, Vehiculo vehiculoActualizado) {
        Document query = new Document("placa", placa);
        Document update = new Document("$set", new Document("propietario", vehiculoActualizado.getPropietario())
                .append("tipoVehiculo", vehiculoActualizado.getTipoVehiculo())
                .append("placa", vehiculoActualizado.getPlaca()));
        collection.updateOne(query, update);
    }

    public void eliminarVehiculo(String placa) {
        Document query = new Document("placa", placa);
        collection.deleteOne(query);
    }

    public List<Vehiculo> buscarVehiculos(String placa) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        Document filtro = new Document("placa", placa);
        MongoCursor<Document> cursor = collection.find(filtro).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setTipoVehiculo(doc.getString("tipoVehiculo"));
                vehiculo.setPlaca(doc.getString("placa"));
                vehiculo.setPropietario(doc.getString("propietario"));
                vehiculo.setHora(doc.getString("horaEntrada"));
                vehiculo.setHora(doc.getString("horaSalida"));
                vehiculos.add(vehiculo);
            }
        } finally {
            cursor.close();
        }

        return vehiculos;
    }
}
