package com.christinagorina.preapprovedoffer.mapper;

import com.christinagorina.preapprovedoffer.model.Address;
import com.christinagorina.preapprovedoffer.model.Client;
import com.christinagorina.preapprovedoffer.model.ClientStatus;
import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.AddressTo;
import com.christinagorina.preapprovedoffer.model.to.ClientStatusTo;
import com.christinagorina.preapprovedoffer.model.to.ClientTo;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "fio", qualifiedByName = "firstNameConverter")
    @Mapping(target = "middleName", source = "fio", qualifiedByName = "middleNameConverter")
    @Mapping(target = "secondName", source = "fio", qualifiedByName = "secondNameConverter")
    @Mapping(target = "clientStatus", source = "clientStatusTo", qualifiedByName = "clientStatusConverter")
    @Mapping(target = "addresses", source = "addressesTo", qualifiedByName = "addressesConverter")
    public abstract Client map(ClientTo clientTo);

    @Named("secondNameConverter")
    public String secondNameConverter(String fio) {
        return Optional.ofNullable(fio).map(n -> n.split(" ")[0]).orElse(null);
    }

    @Named("firstNameConverter")
    public String firstNameConverter(String fio) {
        return Optional.ofNullable(fio).map(n -> n.split(" ")[1]).orElse(null);
    }

    @Named("middleNameConverter")
    public String middleNameConverter(String fio) {
        return Optional.ofNullable(fio).map(n -> n.split(" ")[2]).orElse(null);
    }

    @Named("clientStatusConverter")
    public ClientStatus clientStatusConverter(ClientStatusTo clientStatusTo) {
        return ClientStatusMapper.INSTANCE.map(clientStatusTo);
    }

    @Named("addressesConverter")
    public Set<Address> addressesConverter(Set<AddressTo> addressesTo) {
        return Optional.ofNullable(addressesTo)
                .orElse(Collections.emptySet())
                .stream()
                .map(AddressMapper.INSTANCE::map)
                .collect(Collectors.toSet());
    }

    @AfterMapping
    public void afterMapping(@MappingTarget Client client) {
        ClientStatus clientStatus = client.getClientStatus();
        Optional.ofNullable(clientStatus).ifPresent(c -> c.setClient(client));
    }

}
