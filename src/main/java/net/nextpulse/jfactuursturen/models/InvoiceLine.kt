package net.nextpulse.jfactuursturen.models

import com.fasterxml.jackson.annotation.JsonProperty

data class InvoiceLine(
        var amount: Double,
        @JsonProperty("amount_desc")
        var amountDesc: String,
        var description: String,
        @JsonProperty("tax_rate")
        var taxRate: Double,
        var price: Double,
        @JsonProperty("discount_pct")
        var discountPct: Double?,
        var lineTotal: Double
)