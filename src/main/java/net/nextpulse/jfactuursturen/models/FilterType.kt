package net.nextpulse.jfactuursturen.models

/**
 * Invoice status to use for filtering.
 */
enum class FilterType {
    open,
    overdue,
    sent,
    partly,
    toomuch,
    paid,
    uncollectible
}