package com.christinagorina.preapprovedoffer;

import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.service.OfferServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeConfigurationException;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест для OfferServiceImplTest")
@SpringBootTest
@Transactional
public class OfferServiceImplTest {

    @Autowired
    private OfferServiceImpl offerServiceImpl;

    @Test
    void create() throws DatatypeConfigurationException {
        Offer offer = offerServiceImpl.create(TestData.getOfferTo());

        assertThat(offer).isNotNull()
            .matches(s -> s.getType().equals("mortgage"))
            .matches(s -> s.getAmount().equals(new BigDecimal(6000000)))
            .matches(s -> s.getClient().getPassport().equals("1846856109"))
            .matches(s -> s.getClient().getPhone().equals("+79151766187"))
            .matches(s -> s.getClient().getClientStatus().getLevel().equals("gold"));
    }

    @Test
    void getAll() {
        List<Offer> offers = offerServiceImpl.getAll();

        assertThat(offers).isNotNull()
            .matches(s -> s.stream().anyMatch(g -> g.getId().equals(1002L)))
            .matches(s -> s.stream().anyMatch(g -> g.getId().equals(1003L)));

    }

    @Test
    void getById() {
        Offer offer = offerServiceImpl.getById(1002L);

        assertThat(offer).isNotNull()
            .matches(s -> s.getId().equals(1002L));

    }

}
