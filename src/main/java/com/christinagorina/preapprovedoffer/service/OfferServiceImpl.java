package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.mapper.OfferMapper;
import com.christinagorina.preapprovedoffer.model.Address;
import com.christinagorina.preapprovedoffer.model.Client;
import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;
import com.christinagorina.preapprovedoffer.repository.AddressRepository;
import com.christinagorina.preapprovedoffer.repository.ClientRepository;
import com.christinagorina.preapprovedoffer.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final CheckService checkService;

    @Transactional
    @Override
    public Offer create(OfferTo offerTo) {
        Offer offer = offerMapper.map(offerTo);
        log.info("Mapped offer object {}", offer);

        Set<Address> addressesOffer = offer.getClient().getAddresses().stream().map(address -> {
            Address addressExisting = addressRepository.getByName(address.getName());
            if (Objects.nonNull(addressExisting)) {
                return addressExisting;
            } else {
                return address;
            }
        }).collect(Collectors.toSet());

        Client clientExisting = clientRepository.getByPassport(offer.getClient().getPassport());
        if (Objects.nonNull(clientExisting)) {
            clientExisting.setPhone(offer.getClient().getPhone());
            Set<Address> addressesExistingClient = clientExisting.getAddresses();
            addressesExistingClient.addAll(addressesOffer);
            clientExisting.setAddresses(addressesExistingClient);
            offer.setClient(clientExisting);
        } else {
            offer.getClient().setAddresses(addressesOffer);
        }

        Offer offerSaved = offerRepository.save(offer);
        log.info("offerSaved object {}", offerSaved);
        checkService.check(offerSaved);
        return offerSaved;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Offer getById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        offerRepository.deleteById(id);
    }

}
