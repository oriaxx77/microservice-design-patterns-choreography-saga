package com.oriaxx77.micoserviceplay.order.app

import com.oriaxx77.kotlin.extensions.on
import com.oriaxx77.micoserviceplay.order.domain.OrderRepository
import com.oriaxx77.micoserviceplay.order.domain.PaymentEvent

class UpdateOrderStatusByPaymentUseCase(private val orderRepositoty: OrderRepository)
{
    fun exec(paymentEvent: PaymentEvent)
    {
        //TODO: make it cleaner
        orderRepositoty
                .getById(paymentEvent.orderId)
                .on {
                    val newVersion =  if (paymentEvent.isCreditExceeded())
                                           it.cancel()
                                      else if (paymentEvent.isCreditReserved())
                                           it.pay()
                                      else
                                            throw RuntimeException("Unknown payment event")
                    orderRepositoty.save(newVersion)
                }
    }



}