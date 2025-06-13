package com.hush.app.repository;

import com.hush.app.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */
@Repository
public class TransactionRepository {

    private final Map<String, Transaction> transactionsMap = new ConcurrentHashMap<>();


    public Transaction save(Transaction transaction) {
        transactionsMap.put(transaction.getId(), transaction);
        return transaction;
    }


    public void deleteById(String id) {
        transactionsMap.remove(id);
    }


    public void delete(String id) {
        if (transactionsMap.containsKey(id)) {
            Transaction transaction = transactionsMap.get(id);
            transaction.setIsDelete((byte)1);
        }
    }



    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(transactionsMap.get(id));
    }

    public List<Transaction> findAll(int page, int size) {
        return transactionsMap.values().stream()
                .skip((long) page * size)
                .limit(size)
                .toList();
    }


    public boolean existsById(String id) {
        return transactionsMap.containsKey(id);
    }
}
