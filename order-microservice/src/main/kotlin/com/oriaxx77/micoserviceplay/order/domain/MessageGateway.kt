package com.oriaxx77.micoserviceplay.order.domain

import com.google.gson.Gson
import org.springframework.kafka.core.KafkaTemplate

class MessageGateway(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun publishNewOrderEvent(event: NewOrderEvent) {
        kafkaTemplate.send("orders", Gson().toJson(event))
    }
}