package examples;

import net.nextpulse.jfactuursturen.FactuurSturenClient;
import net.nextpulse.jfactuursturen.models.*;

import java.util.List;

/**
 * Example usage of the library.
 * 
 * @author yorick
 */
public class Example {
  
  public static void main(String[] args) {
    // retrieve the user and apiToken from your system environment or config file
    String username = System.getenv("API_USER");
    String apiToken = System.getenv("API_KEY");
    FactuurSturenClient client = new FactuurSturenClient(username, apiToken);
    
    // call the API
    List<Invoice> response = client.getInvoices(1, new InvoiceFilter(FilterType.open, null, null));
    System.out.println("Received " + response.size() + " invoices");
    for(Invoice invoice : response) {
      System.out.println("Invoice: " + invoice.getId());
      System.out.println(invoice.toString());
    }
  }
}
