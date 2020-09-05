package ugr.cc.vendors_products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ugr.cc.vendors_products.model.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    boolean deleteProductById(Integer productId);

    Page<Product> findBy(Pageable pageable);

    List<Product> findAllBy();

    List<Product> findAllByVendorId(Integer vendorId);

    Product findFirstById(Integer id);
}
