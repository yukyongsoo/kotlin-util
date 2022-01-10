package com.yuk.common.codesample.web

// import net.logstash.logback.argument.StructuredArguments
// import org.slf4j.LoggerFactory
// import org.slf4j.MDC
// import org.springframework.stereotype.Component
// import org.springframework.web.filter.OncePerRequestFilter
// import org.springframework.web.util.ContentCachingRequestWrapper
// import java.nio.charset.Charset
// import java.time.LocalDateTime
// import java.util.UUID
// import javax.servlet.FilterChain
// import javax.servlet.http.HttpServletRequest
// import javax.servlet.http.HttpServletResponse
//
// @Component
// class RequestLoggingFilter : OncePerRequestFilter() {
//    private val log = LoggerFactory.getLogger("Request")
//    private val charSet = Charset.forName("UTF-8")
//
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        if (request.requestURI == "/" || request.requestURI == "" || isAsyncDispatch(request)) {
//            filterChain.doFilter(request, response)
//            return
//        }
//        val wrappedRequest = ContentCachingRequestWrapper(request)
//        val startTime = LocalDateTime.now()
//
//        try {
//            MDC.put("traceId", UUID.randomUUID().toString())
//            filterChain.doFilter(wrappedRequest, response)
//        } finally {
//            logRequest(startTime, wrappedRequest, response.status)
//            MDC.clear()
//        }
//    }
//
//    private fun logRequest(
//        startTime: LocalDateTime,
//        wrappedRequest: ContentCachingRequestWrapper,
//        status: Int
//    ) {
//        val url = if (wrappedRequest.queryString == null)
//            wrappedRequest.requestURI
//        else
//            "${wrappedRequest.requestURI}?${wrappedRequest.queryString}"
//
//        log.info(
//            String(wrappedRequest.contentAsByteArray, charSet),
//            StructuredArguments.keyValue("startTime", startTime.toString()),
//            StructuredArguments.keyValue("method", wrappedRequest.method),
//            StructuredArguments.keyValue("url", url),
//            StructuredArguments.keyValue("status", status),
//        )
//    }
// }
