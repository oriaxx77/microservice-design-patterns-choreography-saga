package com.oriaxx77.micoserviceplay.order.app

import com.oriaxx77.micoserviceplay.order.domain.MessageGateway
import com.oriaxx77.micoserviceplay.order.domain.NewOrderEvent
import com.oriaxx77.micoserviceplay.order.domain.Order
import com.oriaxx77.micoserviceplay.order.domain.OrderRepository

class NewOrderUseCase (private val messageGateway: MessageGateway,
                       private val orderRepository: OrderRepository) {

    fun exec(): NewOrderEvent {
        val order = Order()
        orderRepository.add(order)
        val newOrderEvent = NewOrderEvent(order = order)
        messageGateway.publishNewOrderEvent(newOrderEvent)
        // TODO: remove the return? I may useful for the demo.
        return newOrderEvent
    }

}



