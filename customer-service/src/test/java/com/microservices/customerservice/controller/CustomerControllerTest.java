package com.microservices.customerservice.controller;

import com.microservices.customerservice.models.Customer;
import com.microservices.customerservice.models.CustomerList;
import com.microservices.customerservice.repositories.CustomerRepository;
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
public class CustomerControllerTest {

    @Mock
     CustomerRepository mockRepository;

    @InjectMocks
     CustomerController mockController;

    @Before
    public void setUp() {
        for(int i = 1;i<=3;i++){
            given(mockRepository.findByCreditId(i)).willReturn(mockData(i));
        }
    }

    @Test
    public void getCustomersTest(){
        System.out.println();
        List<Integer> ids = Arrays.asList(1,2,3);

        CustomerList customerList = mockController.getCustomers(ids);

        Assert.assertEquals(customerList.getCustomers().size(), 3);
    }

    private Customer mockData(int creditId){
        var customer = new Customer();
        customer.setCreditId(creditId);
        customer.setPesel("987752" + creditId);
        customer.setFirstName("Imie" + creditId);
        customer.setSurName("Nazwisko" + creditId);
        return customer;
    }
}