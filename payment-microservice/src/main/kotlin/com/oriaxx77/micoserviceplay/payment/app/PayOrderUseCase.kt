package com.oriaxx77.micoserviceplay.payment.app


import com.oriaxx77.micoserviceplay.payment.domain.MessageGateway
import com.oriaxx77.micoserviceplay.payment.domain.NewOrderEvent
import com.oriaxx77.micoserviceplay.payment.domain.PaymentEvent
import com.oriaxx77.micoserviceplay.payment.domain.PaymentEventCode
import java.util.UUID
import kotlin.random.Random

typealias TransactionId = UUID
typealias OrderId = UUID

class PayOrderUseCase(private val messageGateway: MessageGateway) {
    //TODO: use either
    //TODO: use AOP for logging
    fun exec(orderEvent: NewOrderEvent) {
        val paymentEvent =  PaymentEvent(code=pay(),
                                         transactionId=orderEvent.transactionId,
                                         orderId=orderEvent.order.id)
        messageGateway.publish(paymentEvent)
    }

    private fun pay() = if (shouldReject()) { PaymentEventCode.CreditExceeded }
                        else { PaymentEventCode.CreditReserved }

    private fun shouldReject() = Random.nextBoolean()
}