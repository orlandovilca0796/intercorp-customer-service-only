package com.intercorp.customers.service;

import com.intercorp.customers.bean.NumNacidos;
import com.intercorp.customers.entity.Customer;
import com.intercorp.customers.repository.CustomerRepository;
import com.intercorp.customers.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomersAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomersByNumDoc(String numDocument) {
        return customerRepository.findById(numDocument).orElse(null);
    }

    @Override
    public List<Customer> getCustomersByEmail(String email) {
        return customerRepository.findByCustEmailIgnoreCase(email);
    }

    @Override
    public List<Customer> getCustomersByIdAndEmail(String id,String email) {
        return customerRepository.findByCustNumDocAndCustEmailIgnoreCase(id,email);
    }

    @Override
    public Customer save(Customer customer) {
        if(customerRepository.existsById(customer.getCustNumDoc())){
           return null;
        }
        customer.setCustCreationDate(Calendar.getInstance());
        customer.setCustStatus(Constants.STATUS_ACTIVE.getValue());
        return customerRepository.save(customer);
    }

    @Override
    public int numNacidos(Date date) {
        return customerRepository.countNumNacidos(date);
    }

    @Override
    public List<NumNacidos> maxNacidos(){
        return customerRepository.maxNumNacidos().stream().map(x->{
            return NumNacidos.builder().cantidad(((BigInteger)x[0]).intValue()).mesAño(x[1].toString()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<NumNacidos> minNacidos(){
        return customerRepository.minNumNacidos().stream().map(x->{
            return NumNacidos.builder().cantidad(((BigInteger)x[0]).intValue()).mesAño(x[1].toString()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<NumNacidos> allNacidos(){
        return customerRepository.allNumNacidos().stream().map(x->{
            return NumNacidos.builder().cantidad(((BigInteger)x[0]).intValue()).mesAño(x[1].toString()).build();
        }).collect(Collectors.toList());
    }
}