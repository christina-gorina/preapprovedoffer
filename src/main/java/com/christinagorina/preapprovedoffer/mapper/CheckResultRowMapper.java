package com.christinagorina.preapprovedoffer.mapper;

import com.christinagorina.preapprovedoffer.model.CheckResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckResultRowMapper implements RowMapper<CheckResult> {

    @Override
    public CheckResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CheckResult(
                rs.getLong("id"),
                rs.getLong("offerId"),
                rs.getBoolean("phoneApprove"),
                rs.getBoolean("passportApprove"),
                rs.getBoolean("addressApprove"),
                rs.getBoolean("report"),
                rs.getString("result")
        );
    }

}
