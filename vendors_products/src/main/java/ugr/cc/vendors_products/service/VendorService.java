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

    /**
     * Returns all vendors
     *
     * @return list of all vendors
     */
    public List<Vendor> retrieveAllVendors() {
        return vendorRepository.findAllBy();
    }

    /**
     * Creates or updates a @{@link Vendor}
     *
     * @param vendor: an object of type Vendor
     * @return Vendor
     */
    public Vendor saveVendor(Vendor vendor) {
        //Run validations

        //Save vendor
        return vendorRepository.save(vendor);
    }


    /**
     * Deletes a @{@link Vendor} by id
     *
     * @param vendorId: Vendor identifier
     * @return true if deleted, false otherwise
     */
    public boolean deleteVendor(Integer vendorId) {
        return vendorRepository.deleteVendorById(vendorId);
    }

    /**
     * Finds a @{@link Vendor} by id
     *
     * @param id: Vendor id
     * @return Vendor
     */
    public Vendor findVendor(Integer id) {
        return vendorRepository.findFirstById(id);
    }
}
