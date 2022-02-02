package com.christinagorina.preapprovedoffer.mapper;

import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Optional;

@Component
public class MapperHelper {

    public LocalDateTime toLocalDateTime(XMLGregorianCalendar xmlGregorianCalendar) {
        return Optional.ofNullable(xmlGregorianCalendar)
                .map(XMLGregorianCalendar::toGregorianCalendar)
                .map(GregorianCalendar::toZonedDateTime)
                .map(ZonedDateTime::toLocalDateTime)
                .orElse(null);
    }

    public Boolean toBoolean(Integer num) {
        return Optional.ofNullable(num)
                .map(n -> n == 1 ? Boolean.TRUE : Boolean.FALSE)
                .orElse(null);
    }
}
