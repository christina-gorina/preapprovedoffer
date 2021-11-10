package com.christinagorina.preapprovedoffer.model.to;

import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class ClientTo {

    public String fio;

    public String passport;

    public String phone;

    public ClientStatusTo clientStatusTo;

    private Set<AddressTo> addressesTo;
}
