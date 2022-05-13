package com.nttdata.buybitcoin.service;

import com.nttdata.buybitcoin.dto.request.BuyRequest;
import com.nttdata.buybitcoin.dto.response.BuyResponse;
import com.nttdata.buybitcoin.generics.ICRUD;
import com.nttdata.buybitcoin.model.Buy;

/**
 * This interface extends the methods CRUD
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface IBuyService extends ICRUD<Buy, BuyRequest> {
}
