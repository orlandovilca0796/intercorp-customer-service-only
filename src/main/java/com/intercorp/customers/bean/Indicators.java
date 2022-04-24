package com.intercorp.customers.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class Indicators {
    private Integer numNacidosXMonthYear;
    private List<NumNacidos> numNacidosAll;
    private List<NumNacidos> mayorCantidadNacidos;
    private List<NumNacidos> menorCantidadNacidos;
    private double tasaNatalidadXMonthYear;
    private List<TasaNatalidad> tasaNatalidadAll;
}
