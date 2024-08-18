package com.espe.parqueadero.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class VehiculoCRUD {
    private final MongoCollection<Document> collection;

    //Conection MongoDB
    public VehiculoCRUD(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    //Create
    public void ingresarVehiculo(Vehiculo vehiculo) {
        Document doc = crearDocumentoVehiculo(vehiculo);
        collection.insertOne(doc);
    }

    //Read
    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                vehiculos.add(convertirDocumentoAVehiculo(cursor.next()));
            }
        }
        return vehiculos;
    }

    //Update
    public void actualizarHoraSalida(String placa) {
        Document filtro = new Document("placa", placa);
        Document actualizacion = new Document("$set", new Document("horaSalida", new Vehiculo().getHoraSalida()));
        collection.updateOne(filtro, actualizacion);
    }
    public void actualizarDatosVehiculo(String placa, Vehiculo vehiculoActualizado) {
        Document query = new Document("placa", placa);
        Document update = new Document("$set", crearDocumentoVehiculo(vehiculoActualizado));
        collection.updateOne(query, update);
    }

    //Delete
    public void eliminarVehiculo(String placa) {
        Document query = new Document("placa", placa);
        collection.deleteOne(query);
    }

    //Search
    public List<Vehiculo> buscarVehiculos(String placa) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        Document filtro = new Document("placa", placa);
        try (MongoCursor<Document> cursor = collection.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                vehiculos.add(convertirDocumentoAVehiculo(cursor.next()));
            }
        }
        return vehiculos;
    }

    //Methods_Create
    private Document crearDocumentoVehiculo(Vehiculo vehiculo) {
        return new Document("placa", vehiculo.getPlaca())
                .append("propietario", vehiculo.getPropietario())
                .append("horaEntrada", vehiculo.getHoraEntrada())
                .append("tipoVehiculo", vehiculo.getTipoVehiculo())
                .append("horaSalida", vehiculo.getHoraSalida());
    }
    private Vehiculo convertirDocumentoAVehiculo(Document doc) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(doc.getString("placa"));
        vehiculo.setPropietario(doc.getString("propietario"));
        vehiculo.setTipoVehiculo(doc.getString("tipoVehiculo"));
        vehiculo.setHoraEntrada(doc.getString("horaEntrada"));
        vehiculo.setHoraSalida(doc.getString("horaSalida"));
        return vehiculo;
    }
}
