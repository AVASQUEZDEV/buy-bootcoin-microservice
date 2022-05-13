package com.nttdata.buybitcoin.controller;

import com.nttdata.buybitcoin.dto.mapper.BuySaleRateMapper;
import com.nttdata.buybitcoin.dto.request.BuySaleRateRequest;
import com.nttdata.buybitcoin.dto.response.BuySaleRateResponse;
import com.nttdata.buybitcoin.service.IBuySaleRateService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class defines to endpoints
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/buy-sale")
public class BuySaleRateRestController {

    private final IBuySaleRateService buySaleRateService;
    private final BuySaleRateMapper buySaleRateMapper;

    public BuySaleRateRestController(IBuySaleRateService buySaleRateService, BuySaleRateMapper buySaleRateMapper) {
        this.buySaleRateService = buySaleRateService;
        this.buySaleRateMapper = buySaleRateMapper;
    }

    @GetMapping
    public Flux<BuySaleRateResponse> getAll() {
        return buySaleRateMapper.toFluxResponse(buySaleRateService.findAll());
    }

    @PostMapping
    public Mono<BuySaleRateResponse> create(@RequestBody BuySaleRateRequest request) {
        return buySaleRateMapper.toMonoResponse(buySaleRateService.create(request));
    }

    @PutMapping("/{id}")
    public Mono<BuySaleRateResponse> create(@PathVariable(name = "id") String id, @RequestBody BuySaleRateRequest request) {
        return buySaleRateMapper.toMonoResponse(buySaleRateService.update(id, request));
    }

    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable(name = "id") String id) {
        return buySaleRateService.deleteById(id);
    }

}
