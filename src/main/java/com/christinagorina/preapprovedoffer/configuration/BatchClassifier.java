package com.christinagorina.preapprovedoffer.configuration;

import com.christinagorina.preapprovedoffer.model.CheckResult;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@RequiredArgsConstructor
public class BatchClassifier implements Classifier<CheckResult, ItemWriter<? super CheckResult>> {

    private final ItemWriter<CheckResult> itemWriterHand;
    private final ItemWriter<CheckResult> itemWriterAuto;

    @Override
    public ItemWriter<? super CheckResult> classify(CheckResult checkResult) {
        return checkResult.getResult().equals("manual")? itemWriterHand : itemWriterAuto;
    }
}
