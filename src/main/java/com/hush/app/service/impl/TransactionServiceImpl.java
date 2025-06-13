package com.hush.app.service.impl;

import com.hush.app.common.exception.IllegalParamsException;
import com.hush.app.common.exception.TransactionNotFoundException;
import com.hush.app.model.Transaction;
import com.hush.app.repository.TransactionRepository;
import com.hush.app.service.TransactionService;
import io.micrometer.common.util.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */
@Service
@CacheConfig(cacheNames = "transactions")
public class TransactionServiceImpl implements TransactionService {


    private static TransactionRepository repository = new TransactionRepository();


    @Cacheable(cacheNames = "transactionPages",  key = "#id")
    public Transaction getTransaction(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setId(UUID.randomUUID().toString());
        transaction.setCreateTime(LocalDateTime.now());
        return repository.save(transaction);
    }

    @Override
    @CacheEvict(cacheNames = "transactionPages", key = "#p0.id")
    public Transaction updateTransaction(Transaction updateTransaction) {
        if (StringUtils.isEmpty(updateTransaction.getId())) {
            throw new IllegalParamsException("id", "param is illegal");
        }

        Transaction transaction = getTransaction(updateTransaction.getId());
        if(updateTransaction.getType()!=null){
            transaction.setType(updateTransaction.getType());
        }
        if(updateTransaction.getAmount()!=null){
            transaction.setAmount(updateTransaction.getAmount());
        }
        if(StringUtils.isNotEmpty(updateTransaction.getDescription())){
            transaction.setDescription(updateTransaction.getDescription());
        }
        if(updateTransaction.getState() != null){
            transaction.setState(updateTransaction.getState());
        }
        transaction.setUpdateTime(LocalDateTime.now());
        return repository.save(transaction);
    }

    @Override
    @CacheEvict(cacheNames = "transactionPages", key = "#id")
    public void deleteTransaction(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalParamsException("id", "param is illegal");
        }
        if (!repository.existsById(id)) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Page<Transaction> listByPage(int page, int size) {
        if (page<0 || size<0) {
            throw new IllegalParamsException("page", "param is illegal");
        }
        List<Transaction> transactions = repository.findAll(page, size);
        return new PageImpl<>(transactions, PageRequest.of(page, size), transactions.size());
    }
}
