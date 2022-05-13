package com.nttdata.buybitcoin.dto.request;

import com.nttdata.buybitcoin.enums.PayMode;
import lombok.Data;

/**
 * This class defines to request of model buy
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
public class BuyRequest {

    private String transactionId;

    private String fromUserId;

    private Float amount;

    private PayMode payMode;

}
