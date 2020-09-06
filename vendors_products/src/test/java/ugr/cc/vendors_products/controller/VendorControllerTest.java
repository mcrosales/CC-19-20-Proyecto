package ugr.cc.vendors_products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
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
        vendorList.add(new Vendor("Jackie", "Chan", Date.valueOf(LocalDate.now())));
        vendorList.add(new Vendor("Alexandre", "Dumas", Date.valueOf(LocalDate.now())));
        Mockito.when(vendorService.retrieveAllVendors()).thenReturn(vendorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/vendors/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    /**
     * Test getting one vendor through GET method
     */
    @Test
    public void testGetOne() throws Exception {

        //Valid product
        Vendor vendor = new Vendor("Steve", "Jobs", Date.valueOf(LocalDate.now()));

        Mockito.when(vendorService.findVendor(2)).thenReturn(vendor);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/vendors/".concat(String.valueOf(2)))
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null");

        //Invalid product
        Mockito.when(vendorService.findVendor(-2)).thenReturn(null);

        resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/vendors/".concat(String.valueOf(-2)))
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print());
        mvcResult = resultActions.andReturn();

        status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("", responseBody, "Response body should be empty");

    }

    /**
     * Test creating a vendor through POST method
     */
    @Test
    public void testCreateVendor() throws Exception {
        Vendor vendor = new Vendor("Bill", "Gates", Date.valueOf(LocalDate.now()));
        vendor.setId(2);

        Mockito.when(vendorService.saveVendor(vendor)).thenReturn(vendor);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/vendors/create")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(
                        vendor
                )));
        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null either");
    }

    /**
     * Test updating a vendor through PUT method
     */
    @Test
    public void testUpdateProduct() throws Exception {

        Vendor vendor = new Vendor("Bill", "Gates", Date.valueOf(LocalDate.now()));
        vendor.setId(1);
        Mockito.when(vendorService.saveVendor(vendor)).thenReturn(vendor);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/vendors/update/".concat(String.valueOf(vendor.getId())))
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(
                        vendor
                )));
        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null either");
    }

    /**
     * Test deleting a vendor through PUT method
     */
    @Test
    public void testDeleteVendor() throws Exception {

        //Non existent vendor
        Mockito.when(vendorService.deleteVendor(-1)).thenReturn(false);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/vendors/delete/".concat(String.valueOf(-1)))
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null");

        //Existent vendor
        Mockito.when(vendorService.deleteVendor(1)).thenReturn(true);

        resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/vendors/delete/".concat(String.valueOf(1)))
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andDo(print());
        mvcResult = resultActions.andReturn();

        status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null");
    }

}