package net.nextpulse.jfactuursturen

import net.nextpulse.jfactuursturen.models.Invoice
import net.nextpulse.jfactuursturen.models.InvoiceFilter
import net.nextpulse.jfactuursturen.models.NewInvoice
import net.nextpulse.jfactuursturen.util.BasicAuthApiClient
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.joda.time.DateTime


/**
 * API client wrapper for the FactuurSturen v1 API
 *
 * @author yholkamp
 */
open class FactuurSturenClient constructor(username: String, apiKey: String) : BasicAuthApiClient(username, apiKey, API_HOST, API_PATH) {

    companion object {
        val logger: Logger = LogManager.getLogger()
        val API_HOST = "www.factuursturen.nl"
        val API_PATH = "api/v1"
    }

    /**
     * Returns a list of invoices that match the optionally provided filters
     *
     * @param count     number of invoices to return
     * @param filter    optional filters to apply
     * @param offset    number of invoices to skip
     */
    @JvmOverloads fun getInvoices(count: Int = 20, filter: InvoiceFilter = InvoiceFilter(), offset: Int = 0): List<Invoice> {
        val urlBuilder = createUrlBuilder("invoices")
        if (filter.filter != null) {
            urlBuilder.addQueryParameter("filter", filter.filter.toString())
        }
        if (filter.since != null) {
            urlBuilder.addQueryParameter("since", DateTime(filter.since).toString("YYYY-mm-dd"))
        }
        if (filter.until != null) {
            urlBuilder.addQueryParameter("until", DateTime(filter.until).toString("YYYY-mm-dd"))
        }
        urlBuilder.addQueryParameter("count", count.toString())
        urlBuilder.addQueryParameter("offset", offset.toString())
        return deserialize(getRequest(urlBuilder.build()))
    }

    /**
     * Returns one invoice from the API
     *
     * @param invoiceId id of the invoice
     * @return the invoice
     */
    fun getInvoice(invoiceId: Long): Invoice {
        return deserialize(getRequest("invoices", invoiceId))
    }

    /**
     * Creates a new invoice
     *
     * @param invoice id of the invoice
     * @return the invoice id
     */
    fun createInvoice(invoice: NewInvoice): Int {
        return deserialize(postRequest("invoices", body = invoice))
    }

    /**
     * Deletes the invoice specified by invoiceId
     *
     * @param invoiceId id of the invoice
     */
    fun deleteInvoice(invoiceId: Long): Unit {
        deleteRequest("invoices", invoiceId)
    }

}