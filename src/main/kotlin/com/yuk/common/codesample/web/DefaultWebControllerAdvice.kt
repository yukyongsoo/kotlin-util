package com.yuk.common.codesample.web

// import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
// import org.slf4j.LoggerFactory
// import org.springframework.http.HttpStatus
// import org.springframework.http.converter.HttpMessageConversionException
// import org.springframework.validation.BindException
// import org.springframework.web.HttpMediaTypeNotSupportedException
// import org.springframework.web.HttpRequestMethodNotSupportedException
// import org.springframework.web.bind.MethodArgumentNotValidException
// import org.springframework.web.bind.MissingServletRequestParameterException
// import org.springframework.web.bind.annotation.ExceptionHandler
// import org.springframework.web.bind.annotation.ResponseBody
// import org.springframework.web.bind.annotation.ResponseStatus
// import org.springframework.web.bind.annotation.RestControllerAdvice
// import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
// import org.springframework.web.servlet.NoHandlerFoundException
// import javax.servlet.http.HttpServletRequest
// import javax.servlet.http.HttpServletResponse
// import javax.validation.ConstraintViolationException
//
// @RestControllerAdvice
// class DefaultWebControllerAdvice {
//    private val logger = LoggerFactory.getLogger("Server")
//
//    @ExceptionHandler(Exception::class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    fun handleException(
//        exception: Exception
//    ): CommonResponse<String> {
//        logger.error("INTERNAL_SERVER_ERROR", exception)
//        return CommonResponse.createFromMessage("INTERNAL_SERVER_ERROR", exception.message ?: "서버에서 문제가 발생했습니다")
//    }
//
//    @ExceptionHandler(AccessDeniedException::class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    fun handleAccessDeniedException(
//        exception: AccessDeniedException
//    ): CommonResponse<String> {
//        return CommonResponse.createFromMessage("METHOD_FORBIDDEN", "현재 요청에 대한 권한이 없습니다")
//    }
//
//    @ExceptionHandler(NoHandlerFoundException::class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    fun handleNoHandlerFoundException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: NoHandlerFoundException
//    ): CommonResponse<String> {
//        logger.debug(exception.message ?: "")
//        return CommonResponse.createFromMessage("METHOD_NOT_FOUNDED", "해당 요청은 잘못된 URL입니다")
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleHttpMediaTypeNotSupportedException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: HttpMediaTypeNotSupportedException
//    ): CommonResponse<String> {
//        logger.debug(exception.message ?: "")
//        return CommonResponse.createFromMessage("CONTENT_NOT_SUPPORTED", "잘못된 컨텐츠 타입 요청입니다")
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleMethodWrongException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: HttpRequestMethodNotSupportedException
//    ): CommonResponse<String> {
//        logger.debug(exception.message ?: "")
//        return CommonResponse.createFromMessage("METHOD_NOT_SUPPORTED", "요청에 대해 응답 할 수 없습니다")
//    }
//
//    @ExceptionHandler(HttpMessageConversionException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleMessageWrongException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: HttpMessageConversionException
//    ): CommonResponse<String> {
//        val cause = exception.cause!!
//
//        val (code, message) =
//            if (cause is MissingKotlinParameterException) "NOT_ALLOW_NULL" to "Null허용이 아닌 파라미터에 Null이 발생했습니다"
//            else "MESSAGE_NOT_READABLE" to "요청 객체를 해석할 수 없습니다"
//
//        logger.info(exception.message ?: "")
//        return CommonResponse.createFromMessage(code, message)
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleMethodArgumentTypeMismatchException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: MethodArgumentTypeMismatchException
//    ): CommonResponse<String> {
//        logger.info(exception.message ?: "")
//        return CommonResponse.createFromMessage("ARGUMENT_TYPE_MISMATCH", "입력 값 중 형식에 맞지 않는 값이 존재합니다")
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleMissingServletRequestParameterException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: MissingServletRequestParameterException
//    ): CommonResponse<String> {
//        logger.info(exception.message)
//        return CommonResponse.createFromMessage("MISSING_REQUEST_PARAMETER", "잘못된 파라미터가 존재합니다")
//    }
//
//    @ExceptionHandler(ConstraintViolationException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleConstraintViolationException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: ConstraintViolationException
//    ): CommonResponse<String> {
//        logger.info(exception.message ?: "")
//        return CommonResponse.createFromMessage("CONSTRAINT_VIOLATION", exception.message ?: "잘못된 파라미터가 존재합니다")
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    fun handleMethodArgumentNotValidException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: MethodArgumentNotValidException
//    ): CommonResponse<String> {
//        logger.info(exception.message)
//        val errorMessageList = exception.bindingResult.allErrors.mapNotNull { it.defaultMessage }
//        return CommonResponse.createFromMessage("VALIDATION_FAIL", errorMessageList.joinToString(","))
//    }
//
//    @ExceptionHandler(BindException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    fun handleBindException(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        exception: BindException
//    ): CommonResponse<String> {
//        logger.info(exception.message)
//        val errorMessageList = exception.bindingResult.allErrors.mapNotNull { it.defaultMessage }
//        return CommonResponse.createFromMessage("VALIDATION_FAIL", errorMessageList.joinToString(","))
//    }
// }
