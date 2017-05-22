package net.nextpulse.jfactuursturen.models

enum class SendMethods {

    None,
    // Print the invoices yourself. We'll send you the invoice number so you can execute a command to retrieve the PDF if you need so 
    Mail,
    //  Send invoices through e-mail. It will be sent immediately
    Email,
    // Send invoice through the prIntcenter.
    Printcenter
}