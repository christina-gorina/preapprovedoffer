package com.christinagorina.preapprovedoffer.model.to;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;

@Data
public class OfferTo {

    public XMLGregorianCalendar dateOfOffer;

    public String type;

    public String amount;

    public ClientTo clientTo;
}
