package com.nttdata.buybitcoin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.buybitcoin.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * This class defines the response of say sale rate
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BuySaleRateResponse {

    private String id;

    @JsonProperty(value = "originCurrency")
    private CurrencyType originCurrency;

    @JsonProperty(value = "targetCurrency")
    private CurrencyType targetCurrency;

    @JsonProperty(value = "buyPrice")
    private Float buyPrice;

    @JsonProperty(value = "salePrice")
    private Float salePrice;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updatedAt;

}
