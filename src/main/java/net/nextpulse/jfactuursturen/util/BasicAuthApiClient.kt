package net.nextpulse.jfactuursturen.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import net.nextpulse.jfactuursturen.FactuurSturenClient
import net.nextpulse.jfactuursturen.exceptions.ApiException
import net.nextpulse.jfactuursturen.exceptions.responseCodeToException
import okhttp3.*
import java.io.IOException

open class BasicAuthApiClient(val username: String, val apiKey: String, val apiHost: String, val apiPath: String) {

    val JSON = MediaType.parse("application/json; charset=utf-8")

    companion object {

        // create a shared object mapper
        val mapper: ObjectMapper = ObjectMapper().registerKotlinModule().registerModule(JodaModule())
                .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)

        // create a shared http client
        @JvmField
        var httpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()
    }

    /**
     * Inline function that handles deserializing the server response
     */
    inline fun <reified T : kotlin.Any> deserialize(responseBody: String): T {
        try {
            return mapper.readValue<T>(responseBody)
        } catch(e: JsonProcessingException) {
            FactuurSturenClient.logger.error("Could not process the API response: {}", responseBody, e)
            throw ApiException("Could not process the API response", e)
        }
    }

    /**
     * Returns a new URL builder with the provided path appended to the host portion
     */
    fun createUrlBuilder(path: String, id: Long): HttpUrl.Builder {
        return createUrlBuilder(path, id.toString())
    }

    /**
     * Returns a new URL builder with the provided path appended to the host portion
     */
    fun createUrlBuilder(path: String, id: String? = null): HttpUrl.Builder {
        val builder = HttpUrl.Builder()
                .scheme("https")
                .host(apiHost)
                .addPathSegments(apiPath)
                .addPathSegments(path)
        if (id != null) {
            builder.addPathSegment(id)
        }
        return builder
    }

    /**
     * Convenience variation on the getRequest method, performs a GET request, returning the response body or an
     * exception if an error occurred
     */
    fun getRequest(path: String, id: Long): String {
        val url = createUrlBuilder(path, id).build()
        return getRequest(url)
    }

    /**
     * Performs a GET request, returning the response body or an exception if an error occurred
     */
    fun getRequest(url: HttpUrl): String {
        val request = buildGetRequest(url)
        return performJsonResponseRequest(request)
    }

    /**
     * Performs a POST request, returning a string or an exception
     */
    fun postRequest(path: String, body: Any): String {
        val url = createUrlBuilder(path).build()
        val request = buildPostRequest(url, body)
        return performJsonResponseRequest(request)
    }

    /**
     * Performs a PUT request, returning unit or an exception
     */
    fun putRequest(url: HttpUrl, body: Any): Unit {
        val request = buildPostRequest(url, body)
        performEmptyResponseRequest(request)
    }

    /**
     * Performs a DELETE request, returning unit or an exception
     */
    fun deleteRequest(path: String, id: Long): Unit {
        val url = createUrlBuilder(path, id).build()
        val request = buildDeleteRequest(url)
        performEmptyResponseRequest(request)
    }

    /**
     * Performs a request that returns a JSON response body
     */
    private fun performJsonResponseRequest(request: Request): String {
        try {
            httpClient.newCall(request).execute().use {
                val responseBody = it.body()
                if (it.isSuccessful && responseBody != null) {
                    return responseBody.string()
                } else {
                    throw responseCodeToException(it.code())
                }
            }
        } catch(e: IOException) {
            throw ApiException("Received IO exception while trying to reach the FactuurSturen API", e)
        }
    }

    /**
     * Performs an HTTP request that returns an empty body on success
     */
    private fun performEmptyResponseRequest(request: Request) {
        try {
            httpClient.newCall(request).execute().use {
                if (it.isSuccessful) {
                    return
                } else {
                    throw responseCodeToException(it.code())
                }
            }
        } catch(e: IOException) {
            throw ApiException("Received IO exception while trying to reach the FactuurSturen API", e)
        }
    }

    /**
     * Generates a new Request object
     */
    private fun buildGetRequest(url: HttpUrl): Request {
        return createRequestBuilder(url).get().build()
    }

    /**
     * Generates a new Request object
     */
    private fun buildPostRequest(url: HttpUrl, body: Any): Request {
        val requestBody = RequestBody.create(JSON, mapper.writeValueAsString(body))
        return createRequestBuilder(url)
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build()
    }

    /**
     * Generates a new Request object
     */
    private fun buildDeleteRequest(url: HttpUrl): Request {
        return createRequestBuilder(url).delete().build()
    }

    /**
     * Generates a new RequestBuilder with the basics set up
     */
    private fun createRequestBuilder(url: HttpUrl): Request.Builder {
        return Request.Builder().url(url)
                .header("Authorization", Credentials.basic(username, apiKey))
                .header("Accept", "application/json")
    }
}