package ugr.cc.vendors_products.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ugr.cc.vendors_products.model.Product;
import ugr.cc.vendors_products.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Test getting all products through GET method
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productsList = new ArrayList<Product>(2);
        productsList.add(new Product(19.99, "The Lord of The Rings"));
        productsList.add(new Product(17.99, "The importance of being earnest"));
        Mockito.when(productService.retrieveAllProducts()).thenReturn(productsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    /**
     * Test getting one product through GET method
     */
    @Test
    public void testGetOne() throws Exception {

        //Valid product
        Product product = new Product(19.99, "The Lord of The Rings");
        Mockito.when(productService.findProduct(2)).thenReturn(product);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/products/".concat(String.valueOf(2)))
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null");

        //Invalid product
        Mockito.when(productService.findProduct(-2)).thenReturn(null);

        resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/products/".concat(String.valueOf(-2)))
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andDo(print());
        mvcResult = resultActions.andReturn();

        status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("", responseBody,"Response body should be empty");

    }

    /**
     * Test creating a product through POST method
     */
    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product(5000.00, "Hyundai");
        product.setId(2);

        Mockito.when(productService.saveProduct(product)).thenReturn(product);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/products/create")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(
                        product
                )));
        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null either");
    }

    /**
     * Test updating a product through PUT method
     */
    @Test
    public void testUpdateProduct() throws Exception {

        Product product = new Product(5000.00, "Test product");
        product.setId(1);
        Mockito.when(productService.saveProduct(product)).thenReturn(product);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/products/update/".concat(String.valueOf(product.getId())))
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(
                        product
                )));
        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null either");
    }

    /**
     * Test updating a product through PUT method
     */
    @Test
    public void testDeleteProduct() throws Exception {

        //Non existent product
        Mockito.when(productService.deleteProduct(-1)).thenReturn(false);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/delete/".concat(String.valueOf(-1)))
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andDo(print());
        MvcResult mvcResult = resultActions.andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null");

        //Existent product
        Mockito.when(productService.deleteProduct(1)).thenReturn(true);

        resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/delete/".concat(String.valueOf(1)))
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andDo(print());
        mvcResult = resultActions.andReturn();

        status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        responseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertNotNull(responseBody, "Response body should not be null");
    }
}