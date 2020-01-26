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

    /** Creates or updates a @{@link Product}
     * @param product: an object of type Product
     * @return Product
     */
    public Product saveProduct(Product product) {
        //Run validations

        //Save product
        return productRepository.save(product);
    }


    /** Deletes a @{@link Product} by id
     * @param productId: Product identifier
     * @return true if deleted, false otherwise
     */
    public boolean deleteProduct(Integer productId) {
        return productRepository.deleteProductById(productId);
    }

    /** Finds a @{@link Product} by id
     * @param id: Product id
     * @return Product
     */
    public Product findProduct(Integer id) {
        return productRepository.findFirstById(id);
    }

    /** Returns all products
     * @return list of all products
     */
    public List<Product> retrieveAllProducts() {
        return productRepository.findAllBy();
    }
}
