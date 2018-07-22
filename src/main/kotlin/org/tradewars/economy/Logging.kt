package org.tradewars.economy

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Global instance to log stuff to the standard output stream.
 */
val logger: Logger = LoggerJSONToStdOut

/**
 * Simple interface for logging, can easily be wrapped around the logger of any library.
 */
interface Logger {

    /**
     * Logs the given message using the logLevel `info` and optionally allows passing a
     * [Throwable] which will be converted to its stacktrace.
     *
     * @param[message] the message to be printed
     * @param[throwable] the [Throwable] which will be converted to a stacktrace
     */
    fun info(message: String, throwable: Throwable? = null)

    /**
     * Logs the given message using the logLevel `warning` and optionally allows passing a
     * [Throwable] which will be converted to its stacktrace.
     *
     * @param[message] the message to be printed
     * @param[throwable] the [Throwable] which will be converted to a stacktrace
     */
    fun warning(message: String, throwable: Throwable? = null)

    /**
     * Logs the given message using the logLevel `error` and optionally allows passing a
     * [Throwable] which will be converted to its stacktrace.
     *
     * @param[message] the message to be printed
     * @param[throwable] the [Throwable] which will be converted to a stacktrace
     */
    fun error(message: String, throwable: Throwable? = null)
}

/**
 * Default implementation for logging.
 *
 * This [Logger] only prints to the standard output stream. All output is JSON.
 *
 * The data container that is serialized is the class [LogMessage].
 */
private object LoggerJSONToStdOut : Logger {

    private const val STACK_TRACE_DEPTH_TO_GET_CALLER = 4

    override fun info(message: String, throwable: Throwable?) {
        log("info", message, throwable)
    }

    override fun warning(message: String, throwable: Throwable?) {
        log("warning", message, throwable)
    }

    override fun error(message: String, throwable: Throwable?) {
        log("error", message, throwable)
    }

    private fun log(level: String, message: String, throwable: Throwable?) {
        val callerStackTraceElement = Thread.currentThread().stackTrace[STACK_TRACE_DEPTH_TO_GET_CALLER]

        val logMessage = LogMessage(
                message = message,
                stackTrace = throwable?.toStackTraceString(),
                threadName = Thread.currentThread().name,
                className = callerStackTraceElement.className,
                methodName = callerStackTraceElement.methodName,
                lineNumber = callerStackTraceElement.lineNumber,
                logLevel = level)

        val mapper = jacksonObjectMapper()

        println(mapper.writeValueAsString(logMessage))
    }

    private fun Throwable.toStackTraceString(): String {
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        printStackTrace(printWriter)
        return stringWriter.toString()
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
private class LogMessage(val message: String,
                         val stackTrace: String?,
                         val threadName: String,
                         val className: String,
                         val methodName: String,
                         val lineNumber: Int,
                         val logLevel: String)