package com.nttdata.glue;

import com.nttdata.steps.PetStoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStoreStepDef {

    @Steps
    PetStoreStep tienda;

    @Given("la url es {string}")
    public void laUrlEs(String url) {
        tienda.definirURL(url);
    }

    @When("creo una orden con id {string}, petId {string}, cantidad {string}, fecha de envío {string}, estado {string} y complete {string}")
    public void creoUnaOrdenConIdPetIdCantidadFechaDeEnvioEstadoYComplete(String id, String petId, String quantity, String shipDate, String status, String complete) {
        tienda.crearOrden(id, petId, quantity, shipDate, status, Boolean.parseBoolean(complete));
    }

    @Then("valido el código de respuesta sea {int}")
    public void validoElCodigoDeRespuestaSea(int statusCode) {
        tienda.validarCodigoRespuesta(statusCode);
    }

    @And("valido que el ID de la orden sea {string}")
    public void validoQueElIDDeLaOrdenSea(String id) {
        tienda.validarIdOrden(id);
    }

    @When("consulto la orden con ID {string}")
    public void consultoLaOrdenConID(String id) {
        tienda.consultarOrden(id);
    }

    @And("valido que el petId sea {string}")
    public void validoQueElPetIdSea(String petId) {
        tienda.validarPetId(petId);
    }

    @And("valido que la cantidad sea {string}")
    public void validoQueLaCantidadSea(String quantity) {
        tienda.validarCantidad(quantity);
    }

    @And("valido que el status de la orden sea {string}")
    public void validoQueElStatusDeLaOrdenSea(String status) {
        tienda.validarStatus(status);
    }

    @And("valido que el campo complete sea {string}")
    public void validoQueElCampoCompleteSea(String complete) {
        tienda.validarCampoComplete(Boolean.parseBoolean(complete));
    }

    @And("valido que la fecha sea {string}")
    public void validoQueLaFechaSea(String fecha) {
        tienda.validarFecha(fecha);
    }
}
