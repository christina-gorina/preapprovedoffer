package com.christinagorina.preapprovedoffer.model.message;

import lombok.Data;

import java.util.Set;

@Data
public class CheckMessage {

    public Long offerId;

    public String type;

    public String phone;

    public String passport;

    public Set<String> addresses;

}
