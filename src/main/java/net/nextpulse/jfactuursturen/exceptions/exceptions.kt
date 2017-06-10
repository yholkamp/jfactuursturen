@file:JvmName("ExceptionUtils")

package net.nextpulse.jfactuursturen.exceptions

import java.io.IOException

/**
 * Parent class for all response related exceptions thrown by the api client.
 */
open class ApiException(apiResponse: String, cause: IOException? = null) : RuntimeException(apiResponse, cause)

/**
 * Exception indicating the requested object could not be found.
 */
class NotFoundException(apiResponse: String) : ApiException(apiResponse)

/**
 * Exception indicating an authentication failure occurred.
 */
class AuthenticationFailedException(message: String) : ApiException(message)

/**
 * Generates an API exception to match the received response code
 */
fun responseCodeToException(responseCode: Int): ApiException {
    return when (responseCode) {
        404 -> NotFoundException("Requested object could not be found")
        401 -> AuthenticationFailedException("Authentication failed")
        else -> {
            ApiException("Received failure response from the API, HTTP code $responseCode")
        }
    }
}