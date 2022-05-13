package com.nttdata.buybitcoin.generics;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface defines to methods to mapper request adn response
 *
 * @param <T> class response
 * @param <M> class model
 * @param <R> class request
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface ICustomMapper<T, M, R> {

    Mono<M> toPostModel(R request);

    Mono<M> toPutModel(M model, R request);

    Mono<T> toMonoResponse(Mono<M> model);

    Flux<T> toFluxResponse(Flux<M> models);

}
