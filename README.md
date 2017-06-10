# jFactuurSturen

Unofficial Java/Kotlin/JVM library to interact with the FactuurSturen.nl API, currently at v1.


## Usage

Include the dependency in your Maven pom file:

Call the code as follows:

    String username = "YOUR_FS_USER_NAME";
    String apiToken = "YOUR_API_TOKEN";
    FactuurSturenClient client = new FactuurSturenClient(username, apiToken);
    List<Invoice> invoices = client.getInvoices();

## Supported APIs

* Invoices: get all (with/without filters), get one, create, delete


## To be implemented

* All other API methods