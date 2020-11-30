package com.oriaxx77.micoserviceplay.payment.domain


import com.oriaxx77.micoserviceplay.payment.app.OrderId
import com.oriaxx77.micoserviceplay.payment.app.TransactionId

enum class PaymentEventCode {
    CreditExceeded, CreditReserved
}

class PaymentEvent(private val code: PaymentEventCode,
                   private val transactionId: TransactionId,
                   private val orderId: OrderId)
