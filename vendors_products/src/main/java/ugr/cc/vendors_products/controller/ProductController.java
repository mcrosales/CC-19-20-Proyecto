package ugr.cc.vendors_products.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.retrieveAllProducts(), HttpStatus.OK);
    }
}
