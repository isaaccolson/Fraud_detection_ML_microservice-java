package com.silvermedal.fraudetectcentral.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class TransactionItem {

    private int step;
    private String type;
    private Double amount;
    private String nameOrig;
    private Double oldbalanceOrg;
    private Double newbalanceOrig;
    private String nameDest;
    private Double oldbalanceDest;
    private Double newbalanceDest;
    private int isFraud;
    private int isFlaggedFraud;

    public TransactionItem() { }

    public TransactionItem(int step, String type, Double amount, String nameOrig, Double oldbalanceOrg, Double newbalanceOrig, String nameDest, Double oldbalanceDest, Double newbalanceDest, int isFraud, int isFlaggedFraud) {
        this.step = step;
        this.type = type;
        this.amount = amount;
        this.nameOrig = nameOrig;
        this.oldbalanceOrg = oldbalanceOrg;
        this.newbalanceOrig = newbalanceOrig;
        this.nameDest = nameDest;
        this.oldbalanceDest = oldbalanceDest;
        this.newbalanceDest = newbalanceDest;
        this.isFraud = isFraud;
        this.isFlaggedFraud = isFlaggedFraud;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNameOrig() {
        return nameOrig;
    }

    public void setNameOrig(String nameOrig) {
        this.nameOrig = nameOrig;
    }

    public Double getOldbalanceOrg() {
        return oldbalanceOrg;
    }

    public void setOldbalanceOrg(Double oldbalanceOrg) {
        this.oldbalanceOrg = oldbalanceOrg;
    }

    public Double getNewbalanceOrig() {
        return newbalanceOrig;
    }

    public void setNewbalanceOrig(Double newbalanceOrig) {
        this.newbalanceOrig = newbalanceOrig;
    }

    public String getNameDest() {
        return nameDest;
    }

    public void setNameDest(String nameDest) {
        this.nameDest = nameDest;
    }

    public Double getOldbalanceDest() {
        return oldbalanceDest;
    }

    public void setOldbalanceDest(Double oldbalanceDest) {
        this.oldbalanceDest = oldbalanceDest;
    }

    public Double getNewbalanceDest() {
        return newbalanceDest;
    }

    public void setNewbalanceDest(Double newbalanceDest) {
        this.newbalanceDest = newbalanceDest;
    }

    public int getIsFraud() {
        return isFraud;
    }

    public void setIsFraud(int isFraud) {
        this.isFraud = isFraud;
    }

    public int getIsFlaggedFraud() {
        return isFlaggedFraud;
    }

    public void setIsFlaggedFraud(int isFlaggedFraud) {
        this.isFlaggedFraud = isFlaggedFraud;
    }

    public String TransactionJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        return mapper.writeValueAsString(this);
    }

}
