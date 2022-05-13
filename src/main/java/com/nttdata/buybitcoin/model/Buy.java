package com.nttdata.buybitcoin.model;

import com.nttdata.buybitcoin.enums.CurrencyType;
import com.nttdata.buybitcoin.enums.PayMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * This class defines the model of buy
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buy")
public class Buy {

    @Id
    private String id;

    //@Field(name = "buy_user_id", write = Field.Write.NON_NULL)
    //private String buyUserId;

    @Field(name = "transaction_id")
    private String transactionId;

    @Field(name = "from_user_id")
    private String fromUserId;

    @Field(name = "amount", write = Field.Write.NON_NULL)
    private Float amount;

    @Field(name = "pay_mode", write = Field.Write.NON_NULL)
    private PayMode payMode;

    @Field(name = "created_at")
    private Date createdAt;

    @Field(name = "updated_at")
    private Date updatedAt;

    public Buy(String fromUserId, Float amount, PayMode payMode, Date createdAt, Date updatedAt) {
        this.fromUserId = fromUserId;
        this.amount = amount;
        this.payMode = payMode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
