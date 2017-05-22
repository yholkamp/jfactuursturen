package net.nextpulse.jfactuursturen.models

data class Taxes(
        var percentage: Int,
        var type: TaxRates,
        var default: Boolean,
        var country: String
)


    