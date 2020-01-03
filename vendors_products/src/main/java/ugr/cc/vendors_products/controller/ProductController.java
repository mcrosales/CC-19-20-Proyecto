package ugr.cc.vendors_products.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.service.ProductService;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /all -> retrieves all products
     *
     * @return List<Product>
     */
    @GetMapping("/all")
    ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.retrieveAllProducts(), HttpStatus.OK);
    }

    /**
     * POST  /create -> Creates a new product.
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Product> create(@RequestBody Product product) throws URISyntaxException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");
        Product created = productService.saveProduct(product);
        return ResponseEntity.created(new URI("/products/" + created.getId()))
                .headers(responseHeaders)
                .body(product);
    }
}
