package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.integration.Check;
import com.christinagorina.preapprovedoffer.mapper.CheckMessageMapper;
import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.message.CheckMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckServiceImpl implements CheckService {

    private final Check check;

    @Override
    @Transactional
    public void check(Offer offer) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        CheckMessage phoneCheckMessage = CheckMessageMapper.INSTANCE.phoneCheckMessageMap(offer);
        CheckMessage passportCheckMessage = CheckMessageMapper.INSTANCE.passportCheckMessageMap(offer);
        CheckMessage addressCheckMessage = CheckMessageMapper.INSTANCE.addressesCheckMessageMap(offer);
        List<CheckMessage> checkMessages = Arrays.asList(phoneCheckMessage, passportCheckMessage, addressCheckMessage);
        pool.execute(() -> {
            String decision = check.process(checkMessages);
            log.info("Check decision is: {}", decision);
        });
    }
}
