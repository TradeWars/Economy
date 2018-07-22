package org.tradewars.economy.basic

import org.tradewars.economy.logger

/**
 * Handles all exceptions that aren't caught by a try-catch block.
 *
 * All Exceptions will be logged using [org.pmw.tinylog.Logger].
 *
 * @author Marcel Schramm
 * @since 20/07/18
 */
class UncaughtExceptionHandler : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, exception: Throwable?) {
        logger.error("Uncaught Exception in thread ${thread.name}: ", exception)
        //TODO (See TradeWars/Economy/#2) Theoretically we could trigger sending an E-Mail or something like that.
    }
}