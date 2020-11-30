package com.oriaxx77.micoserviceplay.payment.domain

import com.oriaxx77.micoserviceplay.payment.app.TransactionId
import com.oriaxx77.micoserviceplay.payment.domain.Order
import org.hibernate.Transaction
import java.util.*

data class OrderEvent(val transactionId: TransactionId, val order: Order)