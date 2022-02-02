package com.christinagorina.preapprovedoffer;

import com.christinagorina.preapprovedoffer.model.to.AddressTo;
import com.christinagorina.preapprovedoffer.model.to.ClientStatusTo;
import com.christinagorina.preapprovedoffer.model.to.ClientTo;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class TestData {

    private static AddressTo getAddressTo() {
        AddressTo addressTo = new AddressTo();
        addressTo.setCode("114774");
        addressTo.setCountry("Россия");
        addressTo.setArea("г.Черный");
        addressTo.setStreet("ул.Цвета");
        addressTo.setHome("д.89");
        addressTo.setFrame("к.1");
        addressTo.setFlat("кв.76");
        return addressTo;
    }

    private static ClientStatusTo getClientStatusTo() {
        ClientStatusTo clientStatusTo = new ClientStatusTo();
        clientStatusTo.setLevel("gold");
        clientStatusTo.setRegular(1);
        return clientStatusTo;
    }

    private static ClientTo getClientTo() {
        ClientTo clientTo = new ClientTo();
        clientTo.setFio("Рогов Сергей Степанович");
        clientTo.setPassport("1846856109");
        clientTo.setPhone("+79151766187");

        Set<AddressTo> addressTos = new HashSet<>();
        addressTos.add(getAddressTo());
        clientTo.setAddressesTo(addressTos);

        clientTo.setClientStatusTo(getClientStatusTo());
        return clientTo;
    }

    public static OfferTo getOfferTo() throws DatatypeConfigurationException {
        OfferTo offerTo = new OfferTo();
        XMLGregorianCalendar dateOfOffer = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("2021-10-01T12:13:15.923");
        offerTo.setDateOfOffer(dateOfOffer);
        offerTo.setType("mortgage");
        offerTo.setAmount("6000000");
        offerTo.setClientTo(getClientTo());
        return offerTo;
    }

}
