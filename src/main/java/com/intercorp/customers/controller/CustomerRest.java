package com.intercorp.customers.controller;

import com.intercorp.customers.bean.Indicators;
import com.intercorp.customers.bean.NumNacidos;
import com.intercorp.customers.bean.TasaNatalidad;
import com.intercorp.customers.entity.Customer;
import com.intercorp.customers.util.Util;
import com.intercorp.customers.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerRest {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(required = false) String id,
                                                   @RequestParam(required = false) String email){
        List<Customer> customers = new ArrayList<>();
        if(id!=null && id!="" && (email==null || email=="")){
            Customer cust = customerService.getCustomersByNumDoc(id);
            if(cust!=null){
                customers.add(cust);
            }
        }else if (email!=null && email!="" && (id==null || id=="")){
            customers = customerService.getCustomersByEmail(email);
        }else if (id!= null && id!="" && email != null && email != "") {
            customers = customerService.getCustomersByIdAndEmail(id,email);
        }else{
            customers = customerService.getCustomersAll();
        }

        if(customers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity(Util.formatMessage(result), HttpStatus.BAD_REQUEST);
        }
        Customer customerNew = customerService.save(customer);
        if(customerNew == null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return ResponseEntity.status(HttpStatus.CREATED).body(customerNew);
        return new ResponseEntity(customerNew, HttpStatus.CREATED);
    }

    @GetMapping(value = "/indicators")
    public ResponseEntity<Indicators> calculateIndicators(@RequestParam @DateTimeFormat(pattern = "yyyy/MM") Date date){
        int numNacidos = customerService.numNacidos(date);
        List<NumNacidos> allNumNacidos = customerService.allNacidos();
        int totalNacidos = allNumNacidos.stream().map(x->x.getCantidad()).reduce(0,Integer::sum);
        if(totalNacidos == 0){
            return ResponseEntity.noContent().build();
        }
        List<TasaNatalidad> allTasaNatalidad = allNumNacidos.stream().map(x->{
            return TasaNatalidad.builder().mesAño(x.getMesAño()).tasaNatalidad(x.getCantidad()*1000/totalNacidos).build();
        }).collect(Collectors.toList());

        Indicators ind = Indicators.builder()
                .numNacidosXMonthYear(numNacidos)
                .numNacidosAll(allNumNacidos)
                .mayorCantidadNacidos(customerService.maxNacidos())
                .menorCantidadNacidos(customerService.minNacidos())
                .tasaNatalidadXMonthYear(numNacidos*1000/totalNacidos)
                .tasaNatalidadAll(allTasaNatalidad)
                .build();
        return ResponseEntity.ok(ind);
    }
}
