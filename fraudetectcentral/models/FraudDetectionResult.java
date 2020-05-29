package com.silvermedal.fraudetectcentral.models;

public class FraudDetectionResult {

    private int isFlaggedFraud;

    public FraudDetectionResult() {
    }

    public FraudDetectionResult(int isFlaggedFraud) {
        this.isFlaggedFraud = isFlaggedFraud;
    }

    public int getIsFlaggedFraud() {
        return isFlaggedFraud;
    }

    public void setIsFlaggedFraud(int isFlaggedFraud) {
        this.isFlaggedFraud = isFlaggedFraud;
    }
}
