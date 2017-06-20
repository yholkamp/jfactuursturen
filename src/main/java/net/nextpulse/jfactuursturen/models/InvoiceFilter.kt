package net.nextpulse.jfactuursturen.models

import org.joda.time.DateTime

/**
 * Filtering object used when listing all invoices.
 * 
 * @author yholkamp
 */
data class InvoiceFilter(
        var filter: FilterType? = null,
        var since: DateTime? = null,
        var until: DateTime? = null
)
