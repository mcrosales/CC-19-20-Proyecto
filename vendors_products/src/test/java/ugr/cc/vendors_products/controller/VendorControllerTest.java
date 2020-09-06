package ugr.cc.vendors_products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.model.Vendor;
import ugr.cc.vendors_products.service.ProductService;
import ugr.cc.vendors_products.service.VendorService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(VendorController.class)
class VendorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private VendorService vendorService;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Test getting all vendors through GET method
     */
    @Test
    public void testGetAllVendors() throws Exception {
        List<Vendor> vendorList = new ArrayList<Vendor>(2);
        vendorList.add(new Vendor("Jackie","Chan",  Date.valueOf(LocalDate.now())));
        vendorList.add(new Vendor("Alexandre","Dumas",  Date.valueOf(LocalDate.now())));
        Mockito.when(vendorService.retrieveAllVendors()).thenReturn(vendorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/vendors/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

}