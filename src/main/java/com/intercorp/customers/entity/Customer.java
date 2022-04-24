package com.intercorp.customers.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cust_number_doc")
    @NotNull(message = "Ingrese el numero de documento del cliente.")
    private String custNumDoc;

    @Column(name = "cust_type_doc")
    @NotNull(message = "Ingrese el tipo de documento del cliente.")
    private String custTypeDoc;

    @Column(name = "cust_name")
    @NotNull(message = "Ingrese el nombre del cliente.")
    private String custName;

    @Column(name = "cust_last_name")
    @NotNull(message = "Ingrese el apellido del cliente.")
    private String custLastName;

    @Column(name = "cust_email")
    @NotNull(message = "Ingrese el correo del cliente.")
    private String custEmail;

    @Column(name = "cust_birthday")
    @NotNull(message = "Ingrese la fecha de nacimiento del cliente.")
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "UTC")
    private Calendar custBirthday;

    @Column(name = "cust_status")
    private String custStatus;

    @Column(name = "cust_creation_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Calendar custCreationDate;
}
