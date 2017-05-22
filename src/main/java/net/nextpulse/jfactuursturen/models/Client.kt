package net.nextpulse.jfactuursturen.models

import org.joda.time.DateTime

data class Client(
        var clientNr: Int,
        var contact: String,
        // Show the contact name on the invoice
        var showContact: Boolean,
        var company: String,
        var address: String,
        var zipcode: String,
        var city: String,
        var country: String,
        var phone: String,
        var mobile: String,
        // Invoice is sent to this e-mail address, if the sendmethod is e-mail
        var email: String,
        // The IBAN number of the client
        var bankCode: String,
        var bicCode: String,
        var taxNumber: String,
        // If the taxes on the invoice is shifted to the receiver
        var taxShifted: Boolean,
        // When last invoice to this client was sent
        var lastInvoice: DateTime?,
        // How to send the invoice to the receiver.
        var sendMethod: SendMethods,
        // How the invoice is going to be paid
        var paymentMethod: PaymentMethods,
        // The term of payment in days. Defines when the invoice has to be paid by the recipient
        var top: Int,
        // Standard discount percentage for this client. Every invoice defined for this client will automatically get this discount percentage
        var stdDiscount: Int,
        // The first line used in the e-mail to address the recipient. Example: "Dear sir/madam,"
        var mailIntro: String,
        // Three lines that will be prInted on the invoice. Can be used for references to other documents or something else
        var reference: Reference,
        // Notes saved for this client
        var notes: String,
        // PrInt the field 'notes' on every invoice for the client
        var notesOnInvoice: Boolean,
        // Non-active clients are hidden in the web application.
        var active: Boolean,
        // In what language the invoice will be generated for this client.
        var defaultDocLang: Languages,
        // ID of used e-mail text
        var defaultEmail: Int,
        // Used currency in invoice. Like 'EUR', 'USD', etc.
        var currency: String,
        // The mandate identification
        var mandateId: String,
        // The date of the signature
        var mandateDate: String,
        // The collection type
        var collectType: CollectTypes,
        // Will show if the products on the invoice for this client will be handled as excluding or including tax
        var taxType: TaxTypes,
        var defaultCategory: String,
        // Date and time when record was updated
        var timeStamp: DateTime
)

