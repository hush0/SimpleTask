package com.hush.app.service;

import com.hush.app.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */
public interface TransactionService {

    public Transaction getTransaction(String id);

    public Transaction createTransaction(Transaction transAction);

    public Transaction updateTransaction(Transaction transAction);

    public void deleteTransaction(String id);

    public Page<Transaction> listByPage(int page, int size);

}
