package org.tradewars.economy

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.PrintWriter
import java.io.StringWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Global instance to log stuff to the standard output stream.
 */
val LOGGER: Logger = LoggerJSONToStdOut

/**
 * Simple interface for logging, can easily be wrapped around the LOGGER of any library.
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
object LoggerJSONToStdOut : Logger {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    private val jsonMapper = jacksonObjectMapper()

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
        println(jsonMapper.writeValueAsString(
                generateLogMessage(
                        LocalDateTime.now().format(dateFormatter),
                        level,
                        message,
                        throwable)
        ))
    }

    /**
     * This method generates a JSON-[String] that is ready to be printed out as LOGGER output.
     *
     * Optionally allows passing a [Throwable] which will be converted to its stacktrace.
     *
     * @param[message] the message to be printed
     * @param[throwable] the [Throwable] which will be converted to a stacktrace
     */
    private fun generateLogMessage(time: String, level: String, message: String, throwable: Throwable?): LogMessage {
        val callerStackTraceElement = Thread.currentThread().stackTrace
                //Removing the first element, since it will be the call to Thread.currentThread()
                .drop(1)
                //Filtering out internal stack trace of the logging.
                .first {
                    it.className != javaClass.name && it.className.contains("Logger\$DefaultImpls").not()
                }

        return LogMessage(
                message = message,
                stackTrace = throwable?.toStackTraceString(),
                time = time,
                threadName = Thread.currentThread().name,
                className = callerStackTraceElement.className,
                methodName = callerStackTraceElement.methodName,
                lineNumber = callerStackTraceElement.lineNumber,
                logLevel = level)
    }

    private fun Throwable.toStackTraceString(): String {
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        printStackTrace(printWriter)
        return stringWriter.toString()
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class LogMessage(val message: String,
                 val stackTrace: String?,
                 val time: String,
                 val threadName: String,
                 val className: String,
                 val methodName: String,
                 val lineNumber: Int,
                 val logLevel: String)