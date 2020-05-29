package com.silvermedal.fraudetectcentral.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silvermedal.fraudetectcentral.models.FraudDetectionResult;
import com.silvermedal.fraudetectcentral.models.TransactionItem;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class CentralFraudDetectionResource {

    @Value("${my.rest: Restcontroller returns JSON}")
    private String greetingMessage;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    public List<TransactionItem> getTransaction() throws JsonProcessingException {

        // request url from eurika server
        String url = "http://flask-fraud-detection-service/fraudetection/single";

// create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

// create a transaction object
        TransactionItem transactionTest = new TransactionItem(1, "TRANSFER",1277212.77,"TestName",
                1277212.77, 0.0, "TestDesName",
                0.0,0.0,0,0);

        HttpEntity<String> request =
                new HttpEntity<String>(transactionTest.TransactionJson(), headers);

// send POST request

        FraudDetectionResult result = restTemplate.postForObject(url, request, FraudDetectionResult.class);
        transactionTest.setIsFlaggedFraud(result.getIsFlaggedFraud());

        return Collections.singletonList(transactionTest); //do the python api call for isFlaggedFraud
    }
}
