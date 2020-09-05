package ugr.cc.vendors_products.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ugr.cc.vendors_products.model.Vendor;

public interface VendorRepository extends PagingAndSortingRepository<Vendor,Integer> {
}
