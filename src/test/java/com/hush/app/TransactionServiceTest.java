package com.hush.app;

import com.hush.app.model.Transaction;
import com.hush.app.repository.TransactionRepository;
import com.hush.app.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionServiceImpl transactionService;



    @Test
    public void testCreateTranscation() {
        Transaction transaction = new Transaction();
        transaction.setFromAccount("100000000000");
        transaction.setType(1);

        //when(repository.findById("1200000157890")).thenReturn(Optional.of(transaction));
        Transaction oldtransactionData = transactionService.createTransaction(transaction);
        Transaction transactionData = transactionService.getTransaction(oldtransactionData.getId());

        assertNotNull(transactionData);
        assertEquals("100000000000", transactionData.getFromAccount());
        assertEquals(1, transactionData.getType());
    }



    @Test
    public void testUpdateTranscation() {
        Transaction transaction = new Transaction();
        transaction.setFromAccount("100000000000");
        transaction.setType(1);

        Transaction oldtransactionData = transactionService.createTransaction(transaction);
        oldtransactionData.setDescription("desc");
        oldtransactionData.setFromAccount("100000000001");
        oldtransactionData.setType(2);
        transactionService.updateTransaction(oldtransactionData);
        Transaction transactionData = transactionService.getTransaction(oldtransactionData.getId());

        assertEquals("100000000001", transactionData.getFromAccount());
        assertEquals("desc", transactionData.getDescription());
        assertEquals(2, transactionData.getType());
    }


   @Test
    public void testDeleteTranscation() {
       Transaction transaction = new Transaction();
       transaction.setFromAccount("100000000000");
       transaction.setType(1);

       Transaction newTransactionData = transactionService.createTransaction(transaction);
       transactionService.deleteTransaction(newTransactionData.getId());
       Transaction transactionData = transactionService.getTransaction(newTransactionData.getId());
       assertNull(transactionData);

   }


    @Test
    public void testListByPage() {
        Transaction transaction = new Transaction();
        transaction.setFromAccount("100000000000");
        transaction.setType(1);

        Transaction transaction1 = new Transaction();
        transaction.setFromAccount("1000000000001");
        transaction.setType(2);


        Transaction transaction2 = new Transaction();
        transaction.setFromAccount("1000000000002");
        transaction.setDescription("desc");
        transaction.setType(1);

        Transaction transaction3 = new Transaction();
        transaction.setFromAccount("1000000000003");
        transaction.setDescription("desc");
        transaction.setType(2);

        Transaction transaction4 = new Transaction();
        transaction.setFromAccount("1000000000004");
        transaction.setDescription("desc0");
        transaction.setType(1);

        Transaction transaction5 = new Transaction();
        transaction.setFromAccount("1000000000005");
        transaction.setDescription("desc");
        transaction.setType(2);

        transactionService.createTransaction(transaction);
        transactionService.createTransaction(transaction1);
        transactionService.createTransaction(transaction2);
        transactionService.createTransaction(transaction3);
        transactionService.createTransaction(transaction4);
        transactionService.createTransaction(transaction5);
        transactionService.createTransaction(transaction1);

        Page<Transaction> page =  transactionService.listByPage(0,5);
        assertEquals(1, page.getTotalPages());
        assertEquals(5, page.getContent().size());
    }
}
