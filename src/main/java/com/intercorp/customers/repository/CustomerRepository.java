package com.intercorp.customers.repository;

import com.intercorp.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findByCustEmailIgnoreCase(String email);
    List<Customer> findByCustNumDocAndCustEmailIgnoreCase(String id, String email);

    @Query(value = "SELECT COUNT(1) FROM CUSTOMER " +
            " WHERE EXTRACT(YEAR FROM cust_birthday) = EXTRACT(YEAR FROM :date) AND " +
            " EXTRACT(MONTH FROM cust_birthday) = EXTRACT(MONTH FROM :date) ",
           nativeQuery = true)
    int countNumNacidos(Date date);

    @Query(value = " SELECT COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) as NumNacidos,DATE_FORMAT(c.cust_birthday , \"%m/%Y\") as MonthYear " +
            "FROM customer c " +
            "group by DATE_FORMAT(c.cust_birthday , \"%m/%Y\") " +
            "having COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) = (select MAX(total.NumNacidos)  FROM " +
            "(SELECT COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) as NumNacidos,DATE_FORMAT(c.cust_birthday , \"%m/%Y\") as MonthYear " +
            "FROM customer c " +
            "group by DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) total) " , nativeQuery = true)
    List<Object[]> maxNumNacidos();

    @Query(value = " SELECT COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) as NumNacidos,DATE_FORMAT(c.cust_birthday , \"%m/%Y\") as MonthYear " +
            "FROM customer c " +
            "group by DATE_FORMAT(c.cust_birthday , \"%m/%Y\") " +
            "having COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) = (select MIN(total.NumNacidos)  FROM " +
            "(SELECT COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) as NumNacidos,DATE_FORMAT(c.cust_birthday , \"%m/%Y\") as MonthYear " +
            "FROM customer c " +
            "group by DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) total) " , nativeQuery = true)
    List<Object[]> minNumNacidos();

    @Query(value = " SELECT COUNT(DATE_FORMAT(c.cust_birthday , \"%m/%Y\")) as NumNacidos,DATE_FORMAT(c.cust_birthday , \"%m/%Y\") as MonthYear " +
            "FROM customer c " +
            "group by DATE_FORMAT(c.cust_birthday , \"%m/%Y\") " , nativeQuery = true)
    List<Object[]> allNumNacidos();
}
