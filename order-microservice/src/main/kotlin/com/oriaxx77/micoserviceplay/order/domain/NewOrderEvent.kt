package com.oriaxx77.micoserviceplay.order.domain

import java.util.*

data class NewOrderEvent(val transactionId: UUID = UUID.randomUUID(), val order: Order)