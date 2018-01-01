package net.nextpulse.jfactuursturen.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime
import java.util.HashMap

data class Invoice(
        var id: String,
        @JsonProperty("invoicenr_full")
        var invoiceNrFull: String,
        // Invoice number including layout. When posting an invoice, do not specify or define this attribute.
        // If you really want to define the invoice number, please include the invoice number as an Integer 
        // number without any prefix layout. So to create INVOICE00023, please send us 23 as Integer.
        var invoiceNr: String,
        // Contains reference lines on the invoice. 'line1', 'line2', 'line3'. All are Strings
        var reference: Reference,
        // All invoice lines on the invoice
        var lines: Map<String, InvoiceLine>? = HashMap(),
        // The ID of the used profile. Default is default profile
        var profile: Int?,
        // The type of discount. 'amount' or 'percentage'
        var discountType: String,
        // If 'DiscountType' is amount, then this is the amount of discount set on the invoice. 
        // If 'DiscountType' is set to 'percentage', this is the discount percentage set.
        var discount: Double?,
        // The payment condition set on the invoice. Default is the payment condition set in the application.
        var paymentCondition: String,
        // Term of payment in days.Default is the payment period set with the client.
        var paymentPeriod: Int,
        // The date the invoice was saved
        @JsonInclude(value= JsonInclude.Include.NON_EMPTY)
        var dateSaved: DateTime?,
        // If invoice is an automatic collection
        var collection: String,
        // Total of all taxes on this invoice
        var tax: Double,
        // Invoice total including taxes
        var totalIntax: Double,
        // Client number
        var clientNr: Int,
        var company: String,
        var contact: String,
        var address: String,
        var zipcode: String,
        var city: String,
        // Country id. You can get a list of country id's with the function api/v1/countrylist.
        // When creating or updating a client, you can supply a country id or a country name. 
        // We'll then try to find the id of the country you supplied
        var country: String,
        var phone: String,
        var mobile: String,
        var email: String,
        var taxNumber: String,
        // A note that is saved with the invoice
        var invoiceNote: String,
        // The date the invoice is sent
        var sent: DateTime?,
        // The date when the invoice is marked as uncollectible
        var uncollectible: DateTime?,
        // The date when the last reminder was sent
        
        var lastReminder: DateTime?,
        // The amount that is still open on the invoice. If this amount is 0, then the invoice is paid.If it is negative, 
        // there is paid more than the invoice amount.
        var open: Double,
        // The date of the last received payment
        var paidDate: DateTime?,
        // All taxes defined in the invoice
        var taxes: Map<String, TaxItem>,
        // The complete URL where the invoice can be paid
        @JsonProperty("payment_url")
        var paymentUrl: String,
        // The duedate of the invoice. This is the sent-date + the payment period in days
        var duedate: DateTime?,
        var history: Map<String, HistoryItem>?,

        //------------- Fields required when adding a new invoice

        // Define the action what to do when this is a new invoice request
        var action: InvoiceActions?,

        // How to send the invoice to the receiver. Required when you use the action 'send'
        var sendMethod: SendMethods?,

        // When the action is 'save' or 'repeat' you must supply a savename.We'll save the invoice under that name.
        var saveName: String?,

        // If a savename already exists, it will not be overwritten unless this attribute is set to 'true'. Default is 'false'.
        var overwriteIfExist: Boolean?,

        // When this option is set to 'true' we will convert all the given prices on the invoices to euro, 
        // based on the currency set in the selected client and the invoice date (to retrieve the current exchange rate)  
        var convertPricesToEuro: Boolean?,

        // region Needed when Action is Repeat
        // Date when the first invoice must be sent. Please use YYYY-MM-DD
        var initialDate: DateTime?,

        // The next date when the next invoice is going to be sent.
        var nextSendDate: DateTime?,

        // Date when the last invoice must be sent. After this date the recurring invoice entry is deleted.
        var finalSendDate: DateTime?,

        // The frequency when the invoice must be sent. Based on the initialdate.
        var frequency: Frequencies?,

        // Set if the recurring invoice is automatically sent by our system
        var repeatType: RepeatTypes?,
        
        // New undocumented field
        var category: String?
)