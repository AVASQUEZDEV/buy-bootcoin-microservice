package com.nttdata.buybitcoin.controller;

import com.nttdata.buybitcoin.dto.mapper.BuyMapper;
import com.nttdata.buybitcoin.dto.request.BuyRequest;
import com.nttdata.buybitcoin.dto.response.BuyResponse;
import com.nttdata.buybitcoin.service.IBuyService;
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
@RequestMapping("/api/v1/buy")
public class BuyRestController {

    private final IBuyService buyService;
    private final BuyMapper buyMapper;

    public BuyRestController(IBuyService buyService, BuyMapper buyMapper) {
        this.buyService = buyService;
        this.buyMapper = buyMapper;
    }

    @GetMapping
    public Flux<BuyResponse> getAll() {
        return buyMapper.toFluxResponse(buyService.findAll());
    }

    @GetMapping("/{id}")
    public Mono<BuyResponse> getById(@PathVariable(name = "id") String id) {
        return buyMapper.toMonoResponse(buyService.findById(id));
    }

    @PostMapping
    public Mono<BuyResponse> create(@RequestBody BuyRequest request) {
        return buyMapper.toMonoResponse(buyService.create(request));
    }

    @PutMapping("/{id}")
    public Mono<BuyResponse> update(@PathVariable(name = "id") String id, @RequestBody BuyRequest request) {
        return buyMapper.toMonoResponse(buyService.update(id, request));
    }

    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable(name = "id") String id) {
        return buyService.deleteById(id);
    }

}
