package com.nttdata.buybitcoin.dto.request;

import com.nttdata.buybitcoin.enums.CurrencyType;
import lombok.Data;

/**
 * This class defines to request of model say sale rate
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class BuySaleRateRequest {

    private CurrencyType originCurrency;

    private CurrencyType targetCurrency;

    private Float buyPrice;

    private Float salePrice;

}
