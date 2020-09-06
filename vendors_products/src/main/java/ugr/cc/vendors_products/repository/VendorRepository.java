package ugr.cc.vendors_products.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ugr.cc.vendors_products.model.Vendor;

import java.util.List;

public interface VendorRepository extends PagingAndSortingRepository<Vendor, Integer> {

    List<Vendor> findAllBy();
}
