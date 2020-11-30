package com.oriaxx77.micoserviceplay.payment.domain

import com.google.gson.Gson
import com.oriaxx77.micoserviceplay.payment.app.TransactionId
import java.util.*

class NewOrderEvent(val transactionId: TransactionId, val order: Order)
{

    companion object
    {
        fun fromJsonString(json: String): NewOrderEvent?
        {
            val orderEvent = Gson().fromJson(json,OrderEvent::class.java)
            return  if (orderEvent.order.isNew()) { NewOrderEvent(orderEvent.transactionId,orderEvent.order) }
                    else { null }
        }
    }

}