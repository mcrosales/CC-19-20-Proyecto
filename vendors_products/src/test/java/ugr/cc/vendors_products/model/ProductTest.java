package ugr.cc.vendors_products.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ugr.cc.vendors_products.model.Product;

import java.sql.Date;
import java.time.LocalDate;

class ProductTest {

    @Test
    void expiredProduct() {

        //New product
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(899.99);
        product.setExpirationDate(
                Date.valueOf(LocalDate.now().minusDays(1))
        );
        Assertions.assertTrue(product.isExpired());

        //Other product without expiration date
        Product otherProduct = new Product();
        otherProduct.setName("Desktop computer");
        otherProduct.setPrice(542.99);
        Assertions.assertFalse(otherProduct.isExpired());
    }

    @Test
    void requiresInventoryAnalysis() {
        Product fewStockProduct = new Product();
        fewStockProduct.setName("Samsung tablet");
        fewStockProduct.setPrice(542.99);
        fewStockProduct.setAmountAvailable(1);
        Assertions.assertTrue(fewStockProduct.requiresInventoryAnalysis());
    }

    @Test
    void productsAreUniquelyIdentifiable() {
        //New product
        Product product = new Product();
        product.setId(1);
        product.setName("Nike Sneakers");
        product.setPrice(99.99);


        //Other product with same
        Product otherProduct = new Product();
        product.setId(2);
        otherProduct.setName("Nike sneakers");
        otherProduct.setPrice(99.99);
        Assertions.assertNotEquals(product, otherProduct, "Products should be differentiable through ID");
        Assertions.assertNotEquals(product.hashCode(), otherProduct.hashCode(), "Two products hash codes should be different");
    }
}