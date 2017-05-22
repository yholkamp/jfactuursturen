package net.nextpulse.jfactuursturen.models

import com.fasterxml.jackson.annotation.JsonProperty

data class TaxItem(
        var rate: Int,
        var sum: String,
        @JsonProperty("sum_of")
        var sumOf: String
)