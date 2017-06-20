package net.nextpulse.jfactuursturen.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class InvoiceLine(
        var amount: BigDecimal = BigDecimal.ONE,
        @JsonProperty("amount_desc")
        var amountDesc: String = "",
        var description: String = "",
        @JsonProperty("tax_rate")
        var taxRate: Any = 21,
        var price: BigDecimal? = null,
        @JsonProperty("discount_pct")
        var discountPct: BigDecimal? = null,
        var lineTotal: BigDecimal? = null
)