package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class PetStoreStep {
    private Response response;
    private String URL_BASE;

    public void definirURL(String url) {
        URL_BASE = url;
    }

    public void crearOrden(String id, String petId, String quantity, String shipDate, String status, boolean complete) {
        String body = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"petId\": " + petId + ",\n" +
                "  \"quantity\": " + quantity + ",\n" +
                "  \"shipDate\": \"" + shipDate + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"complete\": " + complete + "\n" +
                "}";
        response = RestAssured
                .given()
                .baseUri(URL_BASE)
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .post("/store/order")
                .then()
                .log().all()
                .extract().response();
    }

    public void validarIdOrden(String id) {
        Assert.assertEquals("Validación de la orden:", id, response.jsonPath().getString("id"));
    }

    public void consultarOrden(String id) {
        response = RestAssured
                .given()
                .baseUri(URL_BASE)
                .log().all()
                .get("/store/order/" + id)
                .then()
                .log().all()
                .extract().response();
    }

    public void validarCodigoRespuesta(int statusCode) {
        Assert.assertEquals("Validación del código de respuesta:", statusCode, response.statusCode());
        System.out.printf("Código de respuesta " + response.statusCode());
    }

    public void validarPetId(String petId) {
        Assert.assertEquals("Validación del petId:", petId, response.jsonPath().getString("petId"));
    }

    public void validarCantidad(String quantity) {
        Assert.assertEquals("Validación de la cantidad:", quantity, response.jsonPath().getString("quantity"));
    }

    public void validarStatus(String status) {
        Assert.assertEquals("Validación del estado de la orden:", status, response.jsonPath().getString("status"));
    }

    public void validarCampoComplete(boolean complete) {
        Assert.assertEquals("Validación del campo complete:", complete, response.jsonPath().getBoolean("complete"));
    }

    public void validarFecha(String fecha) {
        DateTimeFormatter formatoPersonalizado = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String shipDateObtenida = response.jsonPath().getString("shipDate");
        OffsetDateTime fechaEsperada = OffsetDateTime.parse(fecha, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        OffsetDateTime fechaObtenida = OffsetDateTime.parse(shipDateObtenida, formatoPersonalizado);

        Assert.assertEquals("Validación de la fecha de envío:", fechaEsperada.toInstant(), fechaObtenida.toInstant());
    }
}
