package com.nttdata.buybitcoin.dto.mapper;

import com.nttdata.buybitcoin.dto.request.BuySaleRateRequest;
import com.nttdata.buybitcoin.dto.response.BuySaleRateResponse;
import com.nttdata.buybitcoin.generics.ICustomMapper;
import com.nttdata.buybitcoin.model.BuySaleRate;
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
public class BuySaleRateMapper implements ICustomMapper<BuySaleRateResponse, BuySaleRate, BuySaleRateRequest> {

    /**
     * @param request request
     * @return BuySaleRate converted
     */
    @Override
    public Mono<BuySaleRate> toPostModel(BuySaleRateRequest request) {
        return Mono.just(
                new BuySaleRate(
                        request.getOriginCurrency(),
                        request.getTargetCurrency(),
                        request.getBuyPrice(),
                        request.getSalePrice(),
                        AppUtil.dateFormat(new Date()),
                        AppUtil.dateFormat(new Date())
                )
        );
    }

    /**
     * @param model   entity
     * @param request request
     * @return BuySaleRate converted
     */
    @Override
    public Mono<BuySaleRate> toPutModel(BuySaleRate model, BuySaleRateRequest request) {
        model.setBuyPrice(request.getBuyPrice());
        model.setSalePrice(request.getSalePrice());
        model.setCreatedAt(AppUtil.dateFormat(new Date()));
        return Mono.just(model);
    }

    /**
     * @param model model
     * @return BuySaleRateResponse
     */
    @Override
    public Mono<BuySaleRateResponse> toMonoResponse(Mono<BuySaleRate> model) {
        return model.flatMap(p2p -> Mono.just(
                new BuySaleRateResponse(
                        p2p.getId(),
                        p2p.getOriginCurrency(),
                        p2p.getTargetCurrency(),
                        p2p.getBuyPrice(),
                        p2p.getSalePrice(),
                        p2p.getCreatedAt(),
                        p2p.getUpdatedAt()
                )
        ));
    }

    /**
     * @param models models
     * @return BuySaleRateResponse list
     */
    @Override
    public Flux<BuySaleRateResponse> toFluxResponse(Flux<BuySaleRate> models) {
        return models.flatMap(p2p -> toMonoResponse(Mono.just(p2p)));
    }

}
