package net.nextpulse.jfactuursturen.models

data class Product(
        var id: Int,
        var code: String,
        var name: String,
        var price: Double,
        var taxes: Int,
        var priceIntax: Double
)