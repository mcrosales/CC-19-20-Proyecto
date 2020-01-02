package ugr.cc.vendors_products.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void savedProductIsValid() {
        Product product = new Product();
        product.setName("TV");
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Product savedProduct = productService.saveProduct(product);

        assertTrue(savedProduct.getName().equalsIgnoreCase(product.getName()),"Names must match");
    }
}