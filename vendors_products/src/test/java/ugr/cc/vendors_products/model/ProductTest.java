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
    }
}