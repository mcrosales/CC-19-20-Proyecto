package ugr.cc.vendors_products.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.model.Vendor;
import ugr.cc.vendors_products.repository.ProductRepository;
import ugr.cc.vendors_products.repository.VendorRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendorServiceTest {

    private VendorRepository vendorRepository = Mockito.mock(VendorRepository.class);

    private VendorService vendorService;

    @BeforeEach
    void setUp() {
        vendorService = new VendorService(vendorRepository);
    }

    @Test
    void retrieveAllVendors() {
        Mockito.when(vendorRepository.findAllBy()).thenReturn(
                new ArrayList<Vendor>());
        List<Vendor> allVendors = vendorService.retrieveAllVendors();
        assertNotNull(allVendors, "Should not return null, at least empty list");
    }

    @Test
    void saveVendor() {
        Vendor vendor = new Vendor();
        vendor.setName("Steve");
        Mockito.when(vendorRepository.save(Mockito.any(Vendor.class))).thenReturn(vendor);
        Vendor savedVendor = vendorService.saveVendor(vendor);

        assertTrue(savedVendor.getName().equalsIgnoreCase(vendor.getName()), "Names must match");
    }

    @Test
    void deleteVendor() {
        Integer productId = 1;
        Mockito.when(vendorRepository.deleteVendorById(Mockito.any(Integer.class))).thenReturn(
                true
        );
        boolean successfullyDeleted = vendorService.deleteVendor(productId);

        assertTrue(successfullyDeleted, "Invalid Product ID");
    }

    @Test
    void findVendor() {
        Integer vendorId = 7;
        Mockito.when(vendorRepository.findFirstById(Mockito.any(Integer.class))).thenReturn(
                new Vendor(vendorId, "Alexandre", "Dumas", Date.valueOf(LocalDate.now()))
        );
        Vendor foundVendor = vendorService.findVendor(vendorId);

        assertNotNull(foundVendor, "Product must not be null");
        assertSame(foundVendor.getId(), vendorId, "Product Id must match");
    }
}