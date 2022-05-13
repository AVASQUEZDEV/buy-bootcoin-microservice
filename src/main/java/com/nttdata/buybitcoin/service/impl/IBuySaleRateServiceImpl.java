package com.nttdata.buybitcoin.service.impl;

import com.nttdata.buybitcoin.dto.mapper.BuySaleRateMapper;
import com.nttdata.buybitcoin.dto.request.BuySaleRateRequest;
import com.nttdata.buybitcoin.exceptions.CustomException;
import com.nttdata.buybitcoin.model.BuySaleRate;
import com.nttdata.buybitcoin.repository.IBuySaleRateRepository;
import com.nttdata.buybitcoin.service.IBuySaleRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines to service
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Service
public class IBuySaleRateServiceImpl implements IBuySaleRateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IBuySaleRateServiceImpl.class);

    private final IBuySaleRateRepository buySaleRateRepository;
    private final BuySaleRateMapper buySaleRateMapper;

    public IBuySaleRateServiceImpl(IBuySaleRateRepository buySaleRateRepository, BuySaleRateMapper buySaleRateMapper) {
        this.buySaleRateRepository = buySaleRateRepository;
        this.buySaleRateMapper = buySaleRateMapper;
    }

    /**
     * @return BuySaleRate list
     */
    @Override
    public Flux<BuySaleRate> findAll() {
        return buySaleRateRepository.findAll().onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][findAll]" + e);
            return Mono.error(CustomException.internalServerError("Internal Server Error:" + e));
        });
    }

    /**
     * @param id request
     * @return BuySaleRate
     */
    @Override
    public Mono<BuySaleRate> findById(String id) {
        return buySaleRateRepository.findById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findById]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("BuySaleRate not found")));
    }

    /**
     * @param request request
     * @return created BuySaleRate
     */
    @Override
    public Mono<BuySaleRate> create(BuySaleRateRequest request) {
        return buySaleRateMapper.toPostModel(request)
                .flatMap(buySaleRateRepository::save)
                /*.doOnNext(m -> {
                    kafkaProducer.publish(Topic.WALLET_CREATED, m, EventType.CREATED);
                    LOGGER.info("[YankiServiceImpl][save][Producer]" + m);
                })*/
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e);
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("BuySaleRate not created")));
    }

    /**
     * @param id      request id
     * @param request request body
     * @return updated BuySaleRate
     */
    @Override
    public Mono<BuySaleRate> update(String id, BuySaleRateRequest request) {
        return findById(id)
                .flatMap(p -> buySaleRateMapper.toPutModel(p, request)
                        .flatMap(buySaleRateRepository::save)
                        .onErrorResume(e -> {
                            LOGGER.error("[" + getClass().getName() + "][create]" + e);
                            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                        }).switchIfEmpty(Mono.error(CustomException.notFound("BuySaleRate not updated")))
                );
    }

    /**
     * @param id request id
     * @return void
     */
    @Override
    public Mono<Void> deleteById(String id) {
        return buySaleRateRepository.deleteById(id).onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][deleteById]" + e);
            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
        });
    }

}
