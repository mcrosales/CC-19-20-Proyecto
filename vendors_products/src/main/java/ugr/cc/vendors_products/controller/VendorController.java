package ugr.cc.vendors_products.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.model.Vendor;
import ugr.cc.vendors_products.service.VendorService;

import java.util.List;

@RestController
@RequestMapping(value = "/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * GET /all -> retrieves all vendors
     *
     * @return List<Vendor>
     */
    @GetMapping("/all")
    ResponseEntity<List<Vendor>> getAllVendors() {
        return new ResponseEntity<>(vendorService.retrieveAllVendors(), HttpStatus.OK);
    }
}
