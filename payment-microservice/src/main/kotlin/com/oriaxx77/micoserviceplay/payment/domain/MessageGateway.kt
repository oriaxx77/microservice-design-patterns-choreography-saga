package com.oriaxx77.micoserviceplay.payment.domain

import com.google.gson.Gson
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate


class MessageGateway(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun publish(event: PaymentEvent) {
        kafkaTemplate.send("payments", Gson().toJson(event))
    }
}