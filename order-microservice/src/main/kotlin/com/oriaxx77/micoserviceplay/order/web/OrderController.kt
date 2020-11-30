package com.oriaxx77.micoserviceplay.order.web

import com.oriaxx77.micoserviceplay.order.app.NewOrderUseCase
import com.oriaxx77.micoserviceplay.order.domain.Order
import com.oriaxx77.micoserviceplay.order.domain.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(private val newOrderUseCase: NewOrderUseCase,
                      private val orderRepository: OrderRepository)
{
    // TODO: this should be POST
    // TODO: rename the endpoint to /orders
    @GetMapping("/order")
    fun create(): String
    {
        val newOrderEvent = newOrderUseCase.exec()
        // TODO: remove return? It is useful for the demo.
        return "Message sent to kafka: $newOrderEvent!"
    }

    @GetMapping("/orders")
    fun all(): List<Order>
            = orderRepository.getAll()


}