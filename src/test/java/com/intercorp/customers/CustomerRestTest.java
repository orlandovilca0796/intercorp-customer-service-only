package com.intercorp.customers;

import com.intercorp.customers.bean.Indicators;
import com.intercorp.customers.bean.NumNacidos;
import com.intercorp.customers.controller.CustomerRest;
import com.intercorp.customers.entity.Customer;
import com.intercorp.customers.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class CustomerRestTest {
    @InjectMocks
    private CustomerRest customerRest;

    @Mock
    private CustomerService customerService;

    @MockBean
    private BindingResult bindingResult;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void searchCustomerXEmail(){
        String id = null;
        String email = "correo@hotmail.com";
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().custName("orlando").build());
        Mockito.when(customerService.getCustomersByEmail(Mockito.anyString())).thenReturn(customers);
        ResponseEntity<List<Customer>> respTest = customerRest.searchCustomer(id,email);
        Assert.assertEquals(respTest.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void searchCustomerXId(){
        String id = "222";
        String email = null;
        Mockito.when(customerService.getCustomersByNumDoc(Mockito.anyString())).thenReturn(Customer.builder().custName("orlando").build());
        ResponseEntity<List<Customer>> respTest = customerRest.searchCustomer(id,email);
        Assert.assertEquals(respTest.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void searchCustomerXIdAndEmail(){
        String id = "222";
        String email = "correo@hotmail.com";
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().custName("orlando").build());
        Mockito.when(customerService.getCustomersByIdAndEmail(Mockito.anyString(),Mockito.anyString())).thenReturn(customers);
        ResponseEntity<List<Customer>> respTest = customerRest.searchCustomer(id,email);
        Assert.assertEquals(respTest.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void searchCustomerAll(){
        String id = null;
        String email = null;
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().custName("orlando").build());
        Mockito.when(customerService.getCustomersAll()).thenReturn(customers);
        ResponseEntity<List<Customer>> respTest = customerRest.searchCustomer(id,email);
        Assert.assertEquals(respTest.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void searchCustomerNotFound(){
        String id = null;
        String email = null;
        List<Customer> customers = new ArrayList<>();
        Mockito.when(customerService.getCustomersAll()).thenReturn(customers);
        ResponseEntity<List<Customer>> respTest = customerRest.searchCustomer(id,email);
        Assert.assertEquals(respTest.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void saveCustomerOk(){
        Customer c1 = Customer.builder().custName("asd")
                .custNumDoc("111")
                .custTypeDoc("DNI")
                .custBirthday(Calendar.getInstance())
                .custEmail("correo@hotmail.com")
                .build();
        Mockito.when(customerService.save(Mockito.any())).thenReturn(Customer.builder().custName("orlando").build());
        ResponseEntity<Object> resp = customerRest.save(c1,bindingResult);
        Assert.assertEquals(resp.getStatusCode(),HttpStatus.CREATED);
    }

    @Test
    public void saveCustomerHasError(){
        Customer c1 = Customer.builder().custName("asd")
                .custNumDoc("111")
                .custTypeDoc("DNI")
                .custBirthday(Calendar.getInstance())
                .custEmail("correo@hotmail.com")
                .build();
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(customerService.save(Mockito.any())).thenReturn(Customer.builder().custName("orlando").build());
        ResponseEntity<Object> resp = customerRest.save(c1, bindingResult);
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.BAD_REQUEST);
    }


    @Test
    public void saveCustomerInternalError(){
        Customer c1 = Customer.builder().custName("asd")
                .custNumDoc("111")
                .custTypeDoc("DNI")
                .custBirthday(Calendar.getInstance())
                .custEmail("correo@hotmail.com")
                .build();
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(customerService.save(Mockito.any())).thenReturn(null);
        ResponseEntity<Object> resp = customerRest.save(c1, bindingResult);
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void calculateIndicatorsNoContent(){
        ResponseEntity<Indicators> resp = customerRest.calculateIndicators(new Date());
        Assert.assertEquals(resp.getStatusCode(),HttpStatus.NO_CONTENT);
    }

    @Test
    public void calculateIndicatorsOk(){
        Mockito.when(customerService.numNacidos(Mockito.any())).thenReturn(2);
        List<NumNacidos> lstNumNacidosMock = new ArrayList<>();
        lstNumNacidosMock.add(NumNacidos.builder().cantidad(1).mesAño("01/2022").build());
        Mockito.when(customerService.allNacidos()).thenReturn(lstNumNacidosMock);
        Mockito.when(customerService.maxNacidos()).thenReturn(lstNumNacidosMock);
        Mockito.when(customerService.minNacidos()).thenReturn(lstNumNacidosMock);
        ResponseEntity<Indicators> resp = customerRest.calculateIndicators(new Date());
        Assert.assertEquals(resp.getStatusCode(),HttpStatus.OK);
    }
}
