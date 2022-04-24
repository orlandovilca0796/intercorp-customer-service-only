package com.intercorp.customers.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum Constants {
    STATUS_ACTIVE("ACTIVO"),
    STATUS_INACTIVE("INACTIVO");

    private String value;
}
