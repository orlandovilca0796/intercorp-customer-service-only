package com.intercorp.customers.service;

import com.intercorp.customers.bean.NumNacidos;
import com.intercorp.customers.entity.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {
    List<Customer> getCustomersAll();
    Customer getCustomersByNumDoc(String numDocument);
    List<Customer> getCustomersByEmail(String email);
    List<Customer> getCustomersByIdAndEmail(String id,String email);
    Customer save(Customer customer);
    int numNacidos(Date date);
    List<NumNacidos> maxNacidos();
    List<NumNacidos> minNacidos();
    List<NumNacidos> allNacidos();
}
