package com.nttdata.buybitcoin.model;

import com.nttdata.buybitcoin.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * This class defines the model of buy sell rate
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buy_sale_rate")
public class BuySaleRate {

    @Id
    private String id;

    @Field(name = "origin_currency", write = Field.Write.NON_NULL)
    private CurrencyType originCurrency;

    @Field(name = "target_currency", write = Field.Write.NON_NULL)
    private CurrencyType targetCurrency;

    @Field(name = "buy_price", write = Field.Write.NON_NULL)
    private Float buyPrice;

    @Field(name = "sale_price", write = Field.Write.NON_NULL)
    private Float salePrice;

    @Field(name = "created_at")
    private Date createdAt;

    @Field(name = "updated_at")
    private Date updatedAt;

    public BuySaleRate(CurrencyType originCurrency, CurrencyType targetCurrency, Float buyPrice,
                       Float salePrice, Date createdAt, Date updatedAt) {
        this.originCurrency = originCurrency;
        this.targetCurrency = targetCurrency;
        this.buyPrice = buyPrice;
        this.salePrice = salePrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
