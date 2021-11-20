package com.christinagorina.preapprovedoffer.utils;

import com.christinagorina.preapprovedoffer.model.to.AddressTo;

import java.time.LocalDateTime;

public class Util {

    public static String createAddress(AddressTo addressTo) {
        return addressTo.getCode()
                .concat(", ")
                .concat(addressTo.getCountry())
                .concat(", ")
                .concat(addressTo.getArea())
                .concat(", ")
                .concat(addressTo.getStreet())
                .concat(", ")
                .concat(addressTo.getHome())
                .concat(", ")
                .concat(addressTo.getFrame())
                .concat(", ")
                .concat(addressTo.getFlat());
    }

    public static String fileNameDate() {
        return  LocalDateTime.now().toString()
                .replace(":", "-")
                .replace(".", "-");
    }

}
