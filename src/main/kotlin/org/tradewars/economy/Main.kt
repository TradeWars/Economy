package org.tradewars.economy

import org.tradewars.economy.basic.UncaughtExceptionHandler

/**
 * Main entry point of application.
 *
 * @param[arguments] Passed launch arguments, but those are unused for now
 */
fun main(arguments: Array<String>) {
    LOGGER.info("Starting Economy module.")
    initializeUnhandledExceptionHandler()
}

/**
 * Initializes an [java.lang.Thread.UncaughtExceptionHandler].
 *
 * It is expected that at this point the logging is already set up.
 */
private fun initializeUnhandledExceptionHandler() {
    LOGGER.info("Registering UncaughtExceptionHandler")
    Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler())
    LOGGER.info("Registration of UncaughtExceptionHandler successful.")
}
