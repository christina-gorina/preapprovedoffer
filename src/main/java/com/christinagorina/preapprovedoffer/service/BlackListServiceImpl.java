package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.model.AddressBlackList;
import com.christinagorina.preapprovedoffer.model.CheckResult;
import com.christinagorina.preapprovedoffer.model.PassportBlackList;
import com.christinagorina.preapprovedoffer.model.PhoneBlackList;
import com.christinagorina.preapprovedoffer.model.message.CheckMessage;
import com.christinagorina.preapprovedoffer.model.message.DecisionMessage;
import com.christinagorina.preapprovedoffer.repository.AddressBlackListRepository;
import com.christinagorina.preapprovedoffer.repository.CheckResultRepository;
import com.christinagorina.preapprovedoffer.repository.PassportBlackListRepository;
import com.christinagorina.preapprovedoffer.repository.PhoneBlackListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {

    private final CheckResultRepository checkResultRepository;
    private final AddressBlackListRepository addressBlackListRepository;
    private final PassportBlackListRepository passportBlackListRepository;
    private final PhoneBlackListRepository phoneBlackListRepository;

    @Override
    public DecisionMessage findPhone(CheckMessage checkMessage) {
        log.info("black list findPhone {} ", checkMessage);
        boolean approve = false;
        CheckResult checkResult = getCheckResultWithOfferId(checkMessage.getOfferId());
        PhoneBlackList phoneBlackList = phoneBlackListRepository.getByNumber(checkMessage.getPhone());

        if (Objects.isNull(phoneBlackList)) {
            approve = true;
        }
        checkResult.setPhoneApprove(approve);
        checkResultRepository.save(checkResult);
        return new DecisionMessage(checkMessage.getOfferId(), "phone", approve);
    }

    @Override
    public DecisionMessage findPassport(CheckMessage checkMessage) {
        log.info("black list findPassport {} ", checkMessage);
        boolean approve = false;
        CheckResult checkResult = getCheckResultWithOfferId(checkMessage.getOfferId());
        PassportBlackList passportBlackList = passportBlackListRepository.getByNumber(checkMessage.getPassport());
        if (Objects.isNull(passportBlackList)) {
            approve = true;
        }
        checkResult.setPassportApprove(approve);
        checkResultRepository.save(checkResult);
        return new DecisionMessage(checkMessage.getOfferId(), "passport", approve);
    }

    @Override
    public DecisionMessage findAddresses(CheckMessage checkMessage) {
        log.info("black list findAddresses {} ", checkMessage);
        boolean approve = false;
        CheckResult checkResult = getCheckResultWithOfferId(checkMessage.getOfferId());

        List<AddressBlackList> addressBlackList = addressBlackListRepository.getByNameIn(checkMessage.getAddresses());
        if (Objects.isNull(addressBlackList)) {
            approve = true;
        }
        checkResult.setAddressApprove(approve);
        checkResultRepository.save(checkResult);
        return new DecisionMessage(checkMessage.getOfferId(), "addresses", approve);
    }

    private CheckResult getCheckResultWithOfferId(Long offerId) {
        CheckResult checkResult = checkResultRepository.getByOfferId(offerId);

        if (Objects.nonNull(checkResult)) {
            checkResult.setReport(false);
            return checkResult;
        }

        checkResult = new CheckResult();
        checkResult.setOfferId(offerId);
        checkResult.setReport(false);
        return checkResult;
    }

}
