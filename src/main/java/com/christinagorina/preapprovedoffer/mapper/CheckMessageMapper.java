package com.christinagorina.preapprovedoffer.mapper;

import com.christinagorina.preapprovedoffer.model.Address;
import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.message.CheckMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public abstract class CheckMessageMapper {
    public static final CheckMessageMapper INSTANCE = Mappers.getMapper(CheckMessageMapper.class);

    @Mapping(target = "passport", source = "client.passport")
    @Mapping(target = "type", expression = "java(\"passport\")")
    @Mapping(target = "offerId", source = "id")
    public abstract CheckMessage passportCheckMessageMap(Offer offer);


    @Mapping(target = "phone", source = "client.phone")
    @Mapping(target = "type", expression = "java(\"phone\")")
    @Mapping(target = "offerId", source = "id")
    public abstract CheckMessage phoneCheckMessageMap(Offer offer);


    @Mapping(target = "addresses", source = "client.addresses", qualifiedByName = "addressesConverter")
    @Mapping(target = "type", expression = "java(\"addresses\")")
    @Mapping(target = "offerId", source = "id")
    public abstract CheckMessage addressesCheckMessageMap(Offer offer);

    @Named("addressesConverter")
    public Set<String> addressesConverter(Set<Address> addresses) {
        return addresses.stream().map(Address::getName).collect(Collectors.toSet());
    }
}
