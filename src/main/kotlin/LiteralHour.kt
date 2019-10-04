import java.time.DayOfWeek
import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class LiteralHour(val dayOfWeek: DayOfWeek, val hour: Int, val minute: Int) {
    private fun toMinute(): Long {
        return toMinuteWithPrimitives(dayOfWeek.value, hour, minute)
    }

    fun isBetween(now: LocalDateTime, another: LiteralHour) =
        toMinute() <= toMinute(now) && another.toMinute() >= toMinute(now)

    private fun toMinute(localDateTime: LocalDateTime): Long {
        return toMinuteWithPrimitives(localDateTime.dayOfWeek.value, localDateTime.hour, localDateTime.minute)
    }

    private fun toMinuteWithPrimitives(dayOfWeek: Int, hour: Int, minute: Int): Long {
        return (dayOfWeek * ONE_DAY_IN_MINUTE + hour * ONE_HOUR_IN_MINUTE + minute)
    }

    fun isWithinAHour(now: LocalDateTime) =
        toMinute() - ONE_HOUR_IN_MINUTE <= toMinute(now) && toMinute(now) <= toMinute()

    fun isOverlay(now: LocalDateTime, another: LiteralHour): Boolean {
        return isWithinAHour(now) || another.isWithinAHour(now)
    }

    fun overlayOperationStage(
        now: LocalDateTime,
        positive: String,
        negative: String
    ) = if (toMinute(now) == toMinute()) {
        positive
    } else {
        negative
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
        private const val ONE_DAY_IN_MINUTE = 24 * ONE_HOUR_IN_MINUTE
    }
}
