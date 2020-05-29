package com.siovermedal.batchprocessing.processing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siovermedal.batchprocessing.models.FraudDetectionResult;
import com.siovermedal.batchprocessing.models.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

//could spit out a different object. doesn't have to be a person


public class TransactionRecordProcessor implements ItemProcessor<TransactionRecord, TransactionRecord>
{

    // request url from eurika server
    String url = "http://flask-fraud-detection-service/fraudetection/single";
    // create headers
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(TransactionRecordProcessor.class);

    @Override
    public TransactionRecord process(final TransactionRecord record) throws Exception {

// create headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request =
                new HttpEntity<String>(record.TransactionJson(), headers);

// send POST request

        FraudDetectionResult result = restTemplate.postForObject(url, request, FraudDetectionResult.class);
        record.setIsFlaggedFraud(result.getIsFlaggedFraud());

        return record;
    }
}

