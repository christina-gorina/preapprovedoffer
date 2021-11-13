package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.model.message.CheckMessage;
import com.christinagorina.preapprovedoffer.model.message.DecisionMessage;

public interface BlackListService {

    DecisionMessage findPhone(CheckMessage checkMessage);

    DecisionMessage findPassport(CheckMessage checkMessage);

    DecisionMessage findAddresses(CheckMessage checkMessage);

}
