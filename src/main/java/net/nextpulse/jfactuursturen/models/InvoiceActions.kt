package net.nextpulse.jfactuursturen.models

enum class InvoiceActions {

    none,
    // Send the invoice
    send,
    // Save the invoice as a draft
    save,
    // Plan a recurring invoice
    repeat
}