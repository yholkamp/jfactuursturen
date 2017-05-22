package net.nextpulse.jfactuursturen.models

data class HistoryItem(
        var date: String,
        var time: String,
        var description: String,
        var amount: Double
)