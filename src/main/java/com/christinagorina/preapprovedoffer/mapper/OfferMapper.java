package com.christinagorina.preapprovedoffer.mapper;

import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {MapperHelper.class, ClientMapper.class},
        imports = {LocalDateTime.class})

public abstract class OfferMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfCheck", expression = "java(LocalDateTime.now())")
    @Mapping(target = "client", source = "clientTo")
    public abstract Offer map(OfferTo offerTo);

}
