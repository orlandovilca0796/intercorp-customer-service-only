package com.intercorp.customers.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class NumNacidos {
    private int cantidad;
    private String mesAño;
}
