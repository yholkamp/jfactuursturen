package net.nextpulse.jfactuursturen.util

import okhttp3.Interceptor
import okhttp3.Response
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * Simple logging interceptor that prints the response times at debug level
 */
class LoggingInterceptor : Interceptor {
    companion object {
        private val logger: Logger = LogManager.getLogger()
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (chain == null) {
            throw IllegalArgumentException("Received null chain parameter")
        }
        val request = chain.request()

        val t1 = System.nanoTime()
        logger.debug("Sending {} request to {}", request.method(), request.url())

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        logger.debug(String.format("Received response for %s in %.1fms%n", response.request().url(), (t2 - t1) / 1e6))
        return response
    }

}