@StoreAPI
Feature: Pet Store API

  Como automatizador principal de NTT en el proyecto PetStore
  Quiero probar el correcto funcionamiento del API del Store
  Para garantizar que los componentes estén funcionando adecuadamente

  @CrearOrder
  Scenario Outline: Crear una orden en la tienda de mascotas
    Given la url es "https://petstore.swagger.io/v2"
    When creo una orden con id "<id>", petId "<petId>", cantidad "<quantity>", fecha de envío "<shipDate>", estado "<status>" y complete "<complete>"
    Then valido el código de respuesta sea 200
    And valido que el ID de la orden sea "<id>"

    Examples:
      | id  | petId | quantity | shipDate             | status  | complete |
      | 202 | 345   | 3        | 2024-12-17T02:02:41Z | placed  | true     |
      | 203 | 346   | 2        | 2024-12-18T04:00:00Z | shipped | false    |
      | 204 | 347   | 2        | 2024-12-19T04:00:00Z | shipped | false    |

  @ConsultarOrder
  Scenario Outline: Consultar una orden en la tienda de mascotas
    Given la url es "https://petstore.swagger.io/v2"
    When consulto la orden con ID "<id>"
    Then valido el código de respuesta sea 200
    And valido que el ID de la orden sea "<id>"
    And valido que el petId sea "<petId>"
    And valido que la cantidad sea "<quantity>"
    And valido que la fecha sea "<shipDate>"
    And valido que el status de la orden sea "<status>"
    And valido que el campo complete sea "<complete>"

    Examples:
      | id  | petId | quantity | shipDate             | status  | complete |
      | 202 | 345   | 3        | 2024-12-17T02:02:41Z | placed  | true     |
      | 203 | 346   | 2        | 2024-12-18T04:00:00Z | shipped | false    |
      | 203 | 346   | 2        | 2024-12-18T04:00:00Z | shipped | false    |
