package com.christinagorina.preapprovedoffer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "check_result")
public class CheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long offerId;

    public Boolean phoneApprove;

    public Boolean passportApprove;

    public Boolean addressApprove;

    public Boolean report;

}
