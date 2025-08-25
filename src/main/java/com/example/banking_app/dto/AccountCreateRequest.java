package com.example.banking_app.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AccountCreateRequest {
    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @Min(value = 0, message = "Initial balance must be non-negative")
    private BigDecimal initialBalance;

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public BigDecimal getInitialBalance() { return initialBalance; }
    public void setInitialBalance(BigDecimal initialBalance) { this.initialBalance = initialBalance; }
}
