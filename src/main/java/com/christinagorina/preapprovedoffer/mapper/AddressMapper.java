package com.christinagorina.preapprovedoffer.mapper;

import com.christinagorina.preapprovedoffer.model.Address;
import com.christinagorina.preapprovedoffer.model.to.AddressTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static com.christinagorina.preapprovedoffer.utils.Util.createAddress;

@Mapper
public abstract class AddressMapper {

    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "addressTo", qualifiedByName = "nameConverter")
    public abstract Address map(AddressTo addressTo);

    @Named("nameConverter")
    public String nameConverter(AddressTo addressTo) {

        return createAddress(addressTo);
    }

}
