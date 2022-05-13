package com.nttdata.buybitcoin.dto.mapper;

import com.nttdata.buybitcoin.dto.request.BuyRequest;
import com.nttdata.buybitcoin.dto.response.BuyResponse;
import com.nttdata.buybitcoin.enums.BuySaleStatus;
import com.nttdata.buybitcoin.generics.ICustomMapper;
import com.nttdata.buybitcoin.model.Buy;
import com.nttdata.buybitcoin.util.AppUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * This class convert request and response
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Service
public class BuyMapper implements ICustomMapper<BuyResponse, Buy, BuyRequest> {

    /**
     * @param request request
     * @return Buy converted
     */
    @Override
    public Mono<Buy> toPostModel(BuyRequest request) {
        return Mono.just(
                new Buy(
                        request.getFromUserId(),
                        request.getAmount(),
                        request.getPayMode(),
                        AppUtil.dateFormat(new Date()),
                        AppUtil.dateFormat(new Date())
                )
        );
    }

    /**
     * @param model   entity
     * @param request request
     * @return Buy converted
     */
    @Override
    public Mono<Buy> toPutModel(Buy model, BuyRequest request) {
        model.setTransactionId(request.getTransactionId());
        model.setCreatedAt(AppUtil.dateFormat(new Date()));
        return Mono.just(model);
    }

    /**
     * @param model model
     * @return BuyResponse
     */
    @Override
    public Mono<BuyResponse> toMonoResponse(Mono<Buy> model) {
        return model.flatMap(p2p -> Mono.just(
                new BuyResponse(
                        p2p.getId(),
                        BuySaleStatus.PENDING.toString(),
                        p2p.getFromUserId(),
                        p2p.getAmount(),
                        p2p.getPayMode(),
                        p2p.getCreatedAt(),
                        p2p.getUpdatedAt()
                )
        ));
    }

    /**
     * @param models models
     * @return BuyResponse list
     */
    @Override
    public Flux<BuyResponse> toFluxResponse(Flux<Buy> models) {
        return models.flatMap(p2p -> toMonoResponse(Mono.just(p2p)));
    }

}
