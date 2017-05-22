package net.nextpulse.jfactuursturen.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 *
 * @author yholkamp
 */
data class InvoiceFilter(
        var filter: FilterType? = null,
        var since: Date? = null,
        var until: Date? = null
)

enum class FilterType {
    open,
    overdue,
    sent,
    partly,
    toomuch,
    paid,
    uncollectible
}