package com.oriaxx77.micoserviceplay.payment.domain

import java.util.UUID

enum class OrderStatus {
    NEW, PAID, CANCELLED
}

data class Order (val id: UUID = UUID.randomUUID(),
                  val status: OrderStatus = OrderStatus.NEW) {

    fun paid() = copy(status = OrderStatus.PAID)

    fun cancel() = this.copy(status = OrderStatus.CANCELLED)

    fun isNew() = this.status == OrderStatus.NEW

}