package ugr.cc.vendors_products.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.service.ProductService;

import javax.websocket.server.PathParam;
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
        List<Product> products = productService.retrieveAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * GET / -> retrieves one product by its id
     *
     * @return Product
     */
    @GetMapping("/{id}")
    ResponseEntity<Product> getOne(@PathVariable Integer id) {
        Product product = productService.findProduct(id);
        if (product == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
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

    /**
     * PUT  /update -> Updates a product.
     */
    @RequestMapping(value = "/update/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Product> update(@PathVariable int id,
                                          @RequestBody Product product) throws URISyntaxException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");
        product.setId(id);
        Product updated = productService.saveProduct(product);
        return ResponseEntity.ok().headers(responseHeaders).body(updated);
    }

    /**
     * DELETE  /delete -> Deletes a product.
     */
    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");

        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok().headers(responseHeaders).body("Product deleted successfully");
        } else {
            //Bad request response due to non existent product identifier
            return ResponseEntity.badRequest().headers(responseHeaders).body("Unable to delete product");
        }
    }
}
