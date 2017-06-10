package net.nextpulse.jfactuursturen.models

import org.joda.time.DateTime

/**
 * Object containing the required/available fields when creating a new invoice
 */
data class NewInvoice(
        // Define the action what to do when this is a new invoice request
        var action: InvoiceActions = InvoiceActions.save,

        // How to send the invoice to the receiver. Required when you use the action 'send'
        var sendMethod: SendMethods? = null,

        // When the action is 'save' or 'repeat' you must supply a savename. We'll save the invoice under that name.
        var saveName: String? = null,

        // If a savename already exists, it will not be overwritten unless this attribute is set to 'true'. Default is 'false'.
        var overwriteIfExist: Boolean = false,

        // When this option is set to 'true' we will convert all the given prices on the invoices to euro, 
        // based on the currency set in the selected client and the invoice date (to retrieve the current exchange rate)  
        var convertPricesToEuro: Boolean = false,

        // region Needed when Action is Repeat
        // Date when the first invoice must be sent. Please use YYYY-MM-DD
        var initialDate: DateTime? = null,

        // The next date when the next invoice is going to be sent.
        var nextSendDate: DateTime? = null,

        // Date when the last invoice must be sent. After this date the recurring invoice entry is deleted.
        var finalSendDate: DateTime? = null,

        // The frequency when the invoice must be sent. Based on the initialdate.
        var frequency: Frequencies? = null,

        // Set if the recurring invoice is automatically sent by our system
        var repeatType: RepeatTypes? = null,

        // Invoice number including layout. When posting an invoice, do not specify or define this attribute.
        // If you really want to define the invoice number, please include the invoice number as an Integer 
        // number without any prefix layout. So to create INVOICE00023, please send us 23 as Integer.
        var invoiceNr: String? = null,

        // Contains reference lines on the invoice. 'line1', 'line2', 'line3'. All are Strings
        var reference: Reference? = null,
        
        // All invoice lines on the invoice
        var lines: Map<String, InvoiceLine> = HashMap(),
        
        // The ID of the used profile. Default is default profile
        var profile: Int? = null,
        
        // The type of discount. 'amount' or 'percentage'
        var discountType: String = "amount",
        
        // If 'DiscountType' is amount, then this is the amount of discount set on the invoice. 
        // If 'DiscountType' is set to 'percentage', this is the discount percentage set.
        var discount: Double = 0.0,
        
        // The payment condition set on the invoice. Default is the payment condition set in the application.
        var paymentCondition: String? = null,
        
        // Term of payment in days.Default is the payment period set with the client.
        var paymentPeriod: Int? = null,

        // Client number
        var clientNr: Int? = null,

        // The date of the last received payment
        var paidDate: DateTime? = null
)