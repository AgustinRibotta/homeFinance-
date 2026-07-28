package com.homeFinance.homeFinance.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.homeFinance.homeFinance.dto.request.TransactionRequest;
import com.homeFinance.homeFinance.dto.response.TransactionResponse;
import com.homeFinance.homeFinance.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * TransactionController
 */
@RestController
@Tag(name = "Transaction", description = "Transaction management")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("balances/{id}/transactions")
  public ResponseEntity<List<TransactionResponse>> getByUserBalanceId(UUID userBalanceId) {
    return ResponseEntity.ok(transactionService.findByUserBalanceId(userBalanceId));
  }

  @PostMapping("/balances/{id}/transactions")
  public ResponseEntity<TransactionResponse> create(
      @PathVariable UUID id,
      @Valid @RequestBody TransactionRequest request,
      UriComponentsBuilder uriBuilder) {

    TransactionResponse response = transactionService.newTransaction(request, id);

    URI location = uriBuilder
        .path("/transactions/{transactionId}")
        .buildAndExpand(response.id())
        .toUri();

    return ResponseEntity.created(location).body(response);
  }

}
