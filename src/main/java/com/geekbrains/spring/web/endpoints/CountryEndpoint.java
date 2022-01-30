package com.geekbrains.spring.web.endpoints;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductsService;
import com.geekbrains.spring.web.soap.GetProductRequest;
import com.geekbrains.spring.web.soap.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.geekbrains.com/market";

    private final ProductsService productsService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProductResponse(@RequestPayload GetProductRequest request) {

        Product product = productsService.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found by id: " + request.getId()));

        GetProductResponse response = new GetProductResponse();
        response.setProductSOAP(productConverter.entityToSOAP(product));

        return response;
    }
}