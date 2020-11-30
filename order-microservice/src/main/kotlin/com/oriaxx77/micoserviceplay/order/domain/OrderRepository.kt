package com.oriaxx77.micoserviceplay.order.domain

import com.oriaxx77.kotlin.extensions.on
import java.util.*

class OrderRepository {
    private var orders = mutableListOf<Order>()
    fun add(order: Order) = orders.add(order)
    fun getById(id: UUID) = orders.filter { it.id == id }.firstOrNull()
    fun getAll() = orders //TODO return immutable list
    fun save(order: Order) {
        getById(order.id).on { orders.remove(it) }
        orders.add(order)
    }

}