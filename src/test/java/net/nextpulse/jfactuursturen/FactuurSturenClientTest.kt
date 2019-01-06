package net.nextpulse.jfactuursturen

import com.nhaarman.mockitokotlin2.*
import net.nextpulse.jfactuursturen.models.Invoice
import net.nextpulse.jfactuursturen.util.BasicAuthApiClient
import okhttp3.*
import org.apache.commons.io.IOUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers


/**
 * @author yholkamp
 */
class FactuurSturenClientTest {
    val client: FactuurSturenClient = FactuurSturenClient("foo", "key")
    var responseBody: String = ""

    @Before
    fun setUp() {
        // create a mock call that returns a new fake response with 'responseBody' as content
        val mockCall = mock<Call> {
                on { execute() } doAnswer { Response.Builder()
                        .code(200).message("OK")
                        .body(TestResponseBody(responseBody, "application/json"))
                        .addHeader("foo", "bar")
                        .addHeader("kit", "kat")
                        .protocol(Protocol.HTTP_1_1)
                        .request(Request.Builder()
                                .url("http://foo.com/bar")
                                .get()
                                .build())
                        .build() }
        }
        // return the mock call instead of placing an actual call
        BasicAuthApiClient.Companion.httpClient = mock<OkHttpClient> {
            on { newCall(Matchers.any()) } doReturn mockCall
        }
    }

    @Test
    fun testDeserialize_modern() {
        responseBody = IOUtils.toString(javaClass.classLoader.getResource("./listInvoiceResponse.json").openStream(), "UTF-8")
        
        val invoices: List<Invoice> = client.getInvoices()
        assertEquals(1, invoices.size)

        // check if at least the tricky subset of the fields are deserialized correctly
        val invoice: Invoice = invoices[0]
        assertEquals("20170132", invoice.id)
        assertEquals("percentage", invoice.discountType)
        assertEquals(1, invoice.taxes.size)
        assertEquals("10.92", invoice.taxes["tax1"]!!.sum)
    }

    @Test
    fun testDeserialize_legacy() {
        responseBody = IOUtils.toString(javaClass.classLoader.getResource("./legacyListInvoiceResponse.json").openStream(), "UTF-8")
        val invoices : List<Invoice> = client.getInvoices()
        assertEquals(1, invoices.size)
    }
}

