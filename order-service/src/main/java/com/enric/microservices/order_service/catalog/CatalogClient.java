package com.enric.microservices.order_service.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.ResourceAccessException;

@Component
public class CatalogClient {

    //el restclient es un cliente HTTP que se utiliza para realizar solicitudes a servicios RESTful, en este caso, se esta utilizando para comunicarse con el servicio de catatologo
    private final RestClient restClient;

    public CatalogClient(RestClient.Builder restClientBuilder,@Value("${clients.catalog.base-url}") String catalogBaseUrl) {
        this.restClient = restClientBuilder
                .baseUrl(catalogBaseUrl)
                .build();
    }

    public CatalogProductResponse findProductById(Long productId) {
        try {
            return restClient
                    .get()
                    .uri("/api/products/{id}", productId)
                    //retrieve() es un metodo que se utiliza para enviar la solicitud HTTP y obtener la respuesta del servicio de catalogo
                    .retrieve()
                    //onStatus() es un metodo que se utiliza para manejar los errores de la respuesta HTTP, en este caso, si el servicio de catalogo devuelve un error 404 (no encontrado), se lanza una excepcion CatalogProductNotFoundException
                    .onStatus(
                            status -> status.value() == 404,
                            (request, response) -> {
                                throw new CatalogProductNotFoundException(productId);
                            }
                    )
                    .body(CatalogProductResponse.class);
        //el catch es por s la app se cae y no se puede acceder al servicio de catalogo, se lanza una excepcion CatalogUnavailableException
        } catch (ResourceAccessException exception) {
            throw new CatalogUnavailableException(exception);
        }
    }
}