package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.model.CheckResult;
import com.christinagorina.preapprovedoffer.repository.CheckResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final CheckResultRepository checkResultRepository;

    public CheckResult reportProcessing(CheckResult checkResult) {
        log.info("checkResult = " + checkResult);
        String checkOfferResult = "";
        if (!checkResult.getPassportApprove()) {
            checkOfferResult = "refusal";
        } else if (checkResult.getAddressApprove() && checkResult.getAddressApprove()) {
            checkOfferResult = "success";
        } else {
            checkOfferResult = "manual";
        }

        log.info("checkOfferResult = " + checkOfferResult);

        checkResult.setReport(true);
        checkResultRepository.save(checkResult);

        return checkResult;
    }

}
