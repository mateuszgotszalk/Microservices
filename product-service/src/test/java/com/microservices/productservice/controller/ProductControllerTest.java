package com.microservices.productservice.controller;

import com.microservices.productservice.models.Product;
import com.microservices.productservice.models.ProductList;
import com.microservices.productservice.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    ProductRepository mockRepository;

    @InjectMocks
    ProductController mockController;

    @Before
    public void setUp(){

        for(int i = 1; i <= 3; i++) {
            given(mockRepository.findByCreditId(i)).willReturn(mockData(i));
        }
    }


    @Test
    public void getProductsTest() {

        List<Integer> ids = Arrays.asList(1,2,3);

        ProductList productList = mockController.getProducts(ids);

        Assert.assertEquals(productList.getProducts().size(),3);
    }

    private Product mockData(int creditId){
        var product = new Product();
        product.setCreditId(creditId);
        product.setProductValue(300 + creditId);
        product.setProductName("test123" + creditId);
        return product;
    }
}