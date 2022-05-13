package com.nttdata.buybitcoin.repository;

import com.nttdata.buybitcoin.model.BuySaleRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository of BuySaleRate
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IBuySaleRateRepository extends ReactiveMongoRepository<BuySaleRate, String> {
}
