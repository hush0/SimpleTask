package com.hush.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: huangshuai
 * @Date: 6/13/25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    //Long or UUID
    private String id;

    @NonNull
    private String fromAccount;

    private String toAccount;

    private BigDecimal amount;

    @NonNull
    private Integer type;

    private Integer state;

    private String userId;

    private String description;

    private Byte isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
