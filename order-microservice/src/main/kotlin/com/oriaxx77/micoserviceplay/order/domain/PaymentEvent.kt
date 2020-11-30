package com.oriaxx77.micoserviceplay.order.domain

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.util.*

enum class PaymentEventCode {
    CreditExceeded, CreditReserved
}

typealias TransactionId = UUID
typealias OrderId = UUID

class PaymentEvent(val code: PaymentEventCode,
                   val transactionId: TransactionId,
                   val orderId: OrderId) {

    companion object {
        fun fromJsonString(json: String): PaymentEvent?
             = try { Gson().fromJson(json,PaymentEvent::class.java) }
               catch (ex: JsonSyntaxException){null}
    }

    fun isCreditExceeded() = code == PaymentEventCode.CreditExceeded

    fun isCreditReserved() = code == PaymentEventCode.CreditReserved

    fun fold(onCreditExceeded: (PaymentEvent) -> Void,
             onCreditReserved: (PaymentEvent) -> Void) {
        when (code) {
            PaymentEventCode.CreditExceeded -> onCreditExceeded(this)
            PaymentEventCode.CreditReserved -> onCreditReserved(this)
        }
    }
}