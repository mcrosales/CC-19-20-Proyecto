package ugr.cc.vendors_products.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.model.Vendor;
import ugr.cc.vendors_products.service.VendorService;

import java.net.URI;
import java.net.URISyntaxException;
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

    /**
     * GET / -> retrieves one product by its id
     *
     * @return Product
     */
    @GetMapping("/{id}")
    ResponseEntity<Vendor> getOne(@PathVariable Integer id) {
        Vendor vendor = vendorService.findVendor(id);
        if (vendor == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    /**
     * POST  /create -> Creates a new product.
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) throws URISyntaxException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");
        Vendor created = vendorService.saveVendor(vendor);
        return ResponseEntity.created(new URI("/vendors/" + created.getId()))
                .headers(responseHeaders)
                .body(vendor);
    }

    /**
     * PUT  /update -> Updates a product.
     */
    @RequestMapping(value = "/update/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Vendor> update(@PathVariable int id,
                                          @RequestBody Vendor vendor) throws URISyntaxException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");
        vendor.setId(id);
        Vendor updated = vendorService.saveVendor(vendor);
        return ResponseEntity.ok().headers(responseHeaders).body(updated);
    }

    /**
     * DELETE  /delete -> Deletes a product.
     */
    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");

        boolean deleted = vendorService.deleteVendor(id);
        if (deleted) {
            return ResponseEntity.ok().headers(responseHeaders).body("Vendor deleted successfully");
        } else {
            //Bad request response due to non existent product identifier
            return ResponseEntity.badRequest().headers(responseHeaders).body("Unable to delete vendor");
        }
    }
}
