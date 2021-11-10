package com.christinagorina.preapprovedoffer.mapper;

import com.christinagorina.preapprovedoffer.model.ClientStatus;
import com.christinagorina.preapprovedoffer.model.to.ClientStatusTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MapperHelper.class)
public abstract class ClientStatusMapper {

    public static final ClientStatusMapper INSTANCE = Mappers.getMapper(ClientStatusMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract ClientStatus map(ClientStatusTo clientStatusTo);

}
