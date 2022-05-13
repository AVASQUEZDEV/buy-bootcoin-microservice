package com.nttdata.buybitcoin.service.impl;

import com.nttdata.buybitcoin.dto.mapper.BuyMapper;
import com.nttdata.buybitcoin.dto.request.BuyRequest;
import com.nttdata.buybitcoin.dto.response.BuyResponse;
import com.nttdata.buybitcoin.enums.EventType;
import com.nttdata.buybitcoin.exceptions.CustomException;
import com.nttdata.buybitcoin.model.Buy;
import com.nttdata.buybitcoin.producer.KafkaProducer;
import com.nttdata.buybitcoin.repository.IBuyRepository;
import com.nttdata.buybitcoin.service.IBuyService;
import com.nttdata.buybitcoin.util.Topic;
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
public class BuyServiceImpl implements IBuyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyServiceImpl.class);

    private final IBuyRepository buyRepository;
    private final BuyMapper buyMapper;
    private final KafkaProducer<BuyResponse> kafkaProducer;

    public BuyServiceImpl(IBuyRepository buyRepository, BuyMapper buyMapper, KafkaProducer<BuyResponse> kafkaProducer) {
        this.buyRepository = buyRepository;
        this.buyMapper = buyMapper;
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * @return Buy list
     */
    @Override
    public Flux<Buy> findAll() {
        return buyRepository.findAll().onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][findAll]" + e);
            return Mono.error(CustomException.internalServerError("Internal Server Error:" + e));
        });
    }

    /**
     * @param id request
     * @return Buy
     */
    @Override
    public Mono<Buy> findById(String id) {
        return buyRepository.findById(id)
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][findById]" + e.getMessage());
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Buy not found")));
    }

    /**
     * @param request request
     * @return created Buy
     */
    @Override
    public Mono<Buy> create(BuyRequest request) {
        return buyMapper.toPostModel(request)
                .flatMap(buyRepository::save)
                .doOnNext(m -> {
                    BuyResponse buyResponse = new BuyResponse();
                    buyResponse.setFromUserId(m.getFromUserId());
                    kafkaProducer.publish(Topic.REQUESTED_BUY, buyResponse, EventType.CREATED);
                    LOGGER.info("[BuyServiceImpl][create][Producer]" + m);
                })
                .onErrorResume(e -> {
                    LOGGER.error("[" + getClass().getName() + "][create]" + e);
                    return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                }).switchIfEmpty(Mono.error(CustomException.notFound("Buy not created")));
    }

    /**
     * @param id      request id
     * @param request request body
     * @return updated Buy
     */
    @Override
    public Mono<Buy> update(String id, BuyRequest request) {
        return findById(id)
                .flatMap(p -> buyMapper.toPutModel(p, request)
                        .flatMap(buyRepository::save)
                        .onErrorResume(e -> {
                            LOGGER.error("[" + getClass().getName() + "][create]" + e);
                            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
                        }).switchIfEmpty(Mono.error(CustomException.notFound("Buy not updated")))
                );
    }

    /**
     * @param id request id
     * @return void
     */
    @Override
    public Mono<Void> deleteById(String id) {
        return buyRepository.deleteById(id).onErrorResume(e -> {
            LOGGER.error("[" + getClass().getName() + "][deleteById]" + e);
            return Mono.error(CustomException.badRequest("The request is invalid:" + e));
        });
    }

}
