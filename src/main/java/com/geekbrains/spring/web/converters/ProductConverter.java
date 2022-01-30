package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.soap.ProductSOAP;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public ProductSOAP entityToSOAP(Product product) {
        ProductSOAP productSOAP = new ProductSOAP();

        productSOAP.setId(product.getId());
        productSOAP.setPrice(product.getPrice());
        productSOAP.setTitle(product.getTitle());

        return productSOAP;
    }
}
