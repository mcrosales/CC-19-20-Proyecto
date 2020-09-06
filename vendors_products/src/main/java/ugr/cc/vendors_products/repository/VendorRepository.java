package ugr.cc.vendors_products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.model.Vendor;

import java.util.List;

public interface VendorRepository extends PagingAndSortingRepository<Vendor, Integer> {

    List<Vendor> findAllBy();

    boolean deleteVendorById(Integer vendorId);

    Page<Vendor> findBy(Pageable pageable);

    Vendor findFirstById(Integer id);
}
