package com.verifycard.entrypoint.controller;

import com.verifycard.core.service.CardStatService;
import com.verifycard.core.service.CardVerificationService;
import com.verifycard.entrypoint.models.ApiResponse;
import com.verifycard.entrypoint.models.BankCardStatResponse;
import com.verifycard.entrypoint.models.BankCardVerificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/card-scheme/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankCardController {

    private final CardVerificationService cardVerificationService;
    private final CardStatService cardStatService;

    @GetMapping(value = "verify/{cardNumber}")
    public ResponseEntity<ApiResponse<BankCardVerificationResponse>> verifyCard(@PathVariable("cardNumber") String cardNumber){

        BankCardVerificationResponse response = cardVerificationService.verifyCard(cardNumber);

        ApiResponse<BankCardVerificationResponse> apiResponse = new ApiResponse<>(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "stats")
    public ResponseEntity<ApiResponse<BankCardStatResponse>> getBankCardStatistics(@RequestParam(name = "start") int start,
                                                                      @RequestParam(name = "limit") int limit
    ){

        BankCardStatResponse response = cardStatService.getBankCardStatistics(start, limit);

        ApiResponse<BankCardStatResponse> apiResponse = new ApiResponse<>(response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
