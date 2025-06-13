package com.hush.app.controller;

import com.hush.app.model.Transaction;
import com.hush.app.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {


    @Autowired
    private TransactionServiceImpl transactionService;



    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") String id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }


    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transAction) {
        return ResponseEntity.ok(transactionService.createTransaction(transAction));
    }


    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transAction) {
        return  ResponseEntity.ok(transactionService.updateTransaction(transAction));
    }


    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteTransaction(@RequestParam String id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok(true);
    }


    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<Page<Transaction>> send(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(transactionService.listByPage(page, size));
    }
}
