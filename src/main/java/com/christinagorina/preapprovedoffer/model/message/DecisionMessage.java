package com.christinagorina.preapprovedoffer.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DecisionMessage {

    public Long offerId;

    public String type;

    public Boolean decision;
}
