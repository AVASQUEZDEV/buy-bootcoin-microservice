package com.nttdata.buybitcoin.repository;

import com.nttdata.buybitcoin.model.Buy;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository of Buy
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IBuyRepository extends ReactiveMongoRepository<Buy, String> {
}
