package ugr.cc.vendors_products.service;

import org.springframework.stereotype.Service;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.model.Vendor;
import ugr.cc.vendors_products.repository.VendorRepository;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    /** Returns all vendors
     * @return list of all vendors
     */
    public List<Vendor> retrieveAllVendors() {
        return vendorRepository.findAllBy();
    }
}
