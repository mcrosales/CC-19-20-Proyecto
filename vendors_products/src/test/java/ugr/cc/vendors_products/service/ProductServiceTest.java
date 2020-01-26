package ugr.cc.vendors_products.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    /**
     * Test that a new saved @{@link Product} retains its information
     */
    @Test
    void savedProductIsValid() {
        Product product = new Product();
        product.setName("TV");
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Product savedProduct = productService.saveProduct(product);

        assertTrue(savedProduct.getName().equalsIgnoreCase(product.getName()), "Names must match");
    }

    /**
     * Test a given Id returns a @{@link Product}
     */
    @Test
    void existentProductById() {
        Integer productId = 7;
        Mockito.when(productRepository.findFirstById(Mockito.any(Integer.class))).thenReturn(
                new Product(productId, "USB 3.0 cable")
        );
        Product foundProduct = productService.findProduct(productId);

        assertNotNull(foundProduct, "Product must not be null");
        assertSame(foundProduct.getId(), productId, "Product Id must match");
    }

    /**
     * Test a given Id deletes a @{@link Product}
     */
    @Test
    void deleteExistentProductById() {
        Integer productId = 1;
        Mockito.when(productRepository.deleteProductById(Mockito.any(Integer.class))).thenReturn(
                true
        );
        boolean successfullyDeleted = productService.deleteProduct(productId);

        assertTrue(successfullyDeleted, "Invalid Product ID");
    }

    /**
     * Test retrieving all products
     */
    @Test
    void allProducts() {

        Mockito.when(productRepository.findAllBy()).thenReturn(
                new ArrayList<Product>());
        List<Product> allProducts = productService.retrieveAllProducts();
        assertNotNull(allProducts, "Should not return null, at least empty list");
    }
}