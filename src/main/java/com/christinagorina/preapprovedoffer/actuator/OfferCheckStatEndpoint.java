package com.christinagorina.preapprovedoffer.actuator;

import com.christinagorina.preapprovedoffer.model.CheckResult;
import com.christinagorina.preapprovedoffer.repository.CheckResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
@Endpoint(id = "activity-stat")
public class OfferCheckStatEndpoint {

    private final CheckResultRepository checkResultRepository;

    @ReadOperation
    public List<CheckResult> getOfferCheckStat() {
        return checkResultRepository.findAll();
    }
}
