package ugr.cc.vendors_products.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
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

    /**
     * Here we test that we can get all the cars in the database
     * using the GET method
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productsList = new ArrayList<Product>(2);
        productsList.add(new Product(19.99,"The Lord of The Rings"));
        productsList.add(new Product(17.99,"The importance of being earnest"));
        Mockito.when(productService.retrieveAllProducts()).thenReturn(productsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

}