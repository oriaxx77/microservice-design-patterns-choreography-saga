package com.oriaxx77.micoserviceplay.order


import com.oriaxx77.micoserviceplay.order.app.NewOrderUseCase
import com.oriaxx77.micoserviceplay.order.app.UpdateOrderStatusByPaymentUseCase
import com.oriaxx77.micoserviceplay.order.domain.MessageGateway
import com.oriaxx77.micoserviceplay.order.domain.OrderRepository
import com.oriaxx77.micoserviceplay.order.domain.PaymentEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class OrderMicroservice {

    @Autowired
    lateinit var updateOrderStatusByPaymentUseCase: UpdateOrderStatusByPaymentUseCase

    @Bean fun updateOrderStatusByPayment(orderRepository: OrderRepository) =
            UpdateOrderStatusByPaymentUseCase(orderRepository)

    @Bean fun createOrderUseCase(messageGateway: MessageGateway, orderRepository: OrderRepository) =
            NewOrderUseCase(messageGateway, orderRepository)

    @Bean fun messageGateway(kafkaTemplate: KafkaTemplate<String,String>) =
        MessageGateway(kafkaTemplate)

    @Bean fun orderRepository() = OrderRepository()

    // TODO: move it to it's own class and use @KafkaListener and @KafkaHandler, app level
    @KafkaListener(topics = ["payments"])
    fun processPaymentEvents(paymentEventJson: String){
        PaymentEvent
                .fromJsonString(paymentEventJson)
                ?.let { updateOrderStatusByPaymentUseCase.exec(it) }

    }

}


fun main(args: Array<String>) {
    SpringApplication.run(OrderMicroservice::class.java, *args)
}