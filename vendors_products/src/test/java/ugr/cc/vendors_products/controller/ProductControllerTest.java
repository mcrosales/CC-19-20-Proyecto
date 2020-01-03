package ugr.cc.vendors_products.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
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

}