package com.oriaxx77.micoserviceplay.payment

import com.oriaxx77.micoserviceplay.payment.app.PayOrderUseCase
import com.oriaxx77.micoserviceplay.payment.domain.MessageGateway
import com.oriaxx77.micoserviceplay.payment.domain.NewOrderEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class PaymentMicroservice {

    @Autowired lateinit var payOrderUseCase: PayOrderUseCase

    // TODO: move to separate class and use @KafkaHandler, @KafkaListener
    @KafkaListener(topics = ["orders"])
    fun processOrderEvent(orderEventJson: String) {
        NewOrderEvent
                .fromJsonString(orderEventJson)
                ?.let { payOrderUseCase.exec(it) }
    }

    @Bean
    fun messageGateway(kafkaTemplate: KafkaTemplate<String, String>)
            = MessageGateway(kafkaTemplate = kafkaTemplate)

    @Bean
    fun payOrderUseCase(messageGateway: MessageGateway)
            = PayOrderUseCase(messageGateway=messageGateway)
}


fun main(args: Array<String>) {
    SpringApplication.run(PaymentMicroservice::class.java, *args)
}