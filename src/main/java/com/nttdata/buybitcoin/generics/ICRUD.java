package com.nttdata.buybitcoin.generics;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines to methods CRUD
 *
 * @param <T> class type
 * @param <E> class request
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface ICRUD<T, R> {

    Flux<T> findAll();

    Mono<T> findById(String id);

    Mono<T> create(R request);

    Mono<T> update(String id, R request);

    Mono<Void> deleteById(String id);

}
