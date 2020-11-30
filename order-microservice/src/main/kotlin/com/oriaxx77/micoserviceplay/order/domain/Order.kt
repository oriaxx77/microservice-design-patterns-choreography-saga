package com.oriaxx77.micoserviceplay.order.domain

import java.util.UUID

enum class OrderStatus {
    NEW, PAID, CANCELLED
}
// TODO: make attributes read only
data class Order (val id: UUID = UUID.randomUUID(),
                  val status: OrderStatus = OrderStatus.NEW) {

    fun pay(): Order = copy(status = OrderStatus.PAID)

    fun cancel(): Order = this.copy(status = OrderStatus.CANCELLED)

}