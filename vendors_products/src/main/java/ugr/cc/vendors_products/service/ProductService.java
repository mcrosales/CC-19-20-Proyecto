package ugr.cc.vendors_products.service;


import org.springframework.stereotype.Service;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.repository.ProductRepository;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        //Run validations

        //Save product
        return productRepository.save(product);
    }

    public boolean deleteProduct(Integer productId) {
        return productRepository.deleteProductById(productId);
    }

    public Product findProduct(Integer id) {
        return productRepository.findFirstById(id);
    }

    public List<Product> retrieveAllProducts() {
        return productRepository.findAllBy();
    }
}
