package net.nextpulse.jfactuursturen

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource

class TestResponseBody constructor(content: String, private val contentType: String = "application/json") : ResponseBody() {
    private val buffer: Buffer = Buffer().writeUtf8(content)

    override fun contentType(): MediaType? {
        return if (contentType == null) null else MediaType.parse(contentType)
    }

    override fun contentLength(): Long {
        return buffer.size()
    }

    override fun source(): BufferedSource {
        return buffer.clone()
    }
}