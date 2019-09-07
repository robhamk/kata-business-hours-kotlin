import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class OpeningHours(val beginHour: LiteralHour, val endHour: LiteralHour) {

    fun operationStage(localDateTime: LocalDateTime): String {
        val dateTime = toMinute(localDateTime)
        if (isOverlay(dateTime)) {
            if (isWithinAHour(beginHour.toMinute(), dateTime)) {
                return overlayOperationStage(dateTime, beginHour.toMinute(), "Open", "Open Soon")
            }
            return overlayOperationStage(dateTime, endHour.toMinute(), "Close", "Close Soon")
        }

        return when {
            !isBetween(dateTime) -> "Close"
            else -> "Open"
        }
    }

    private fun toMinute(localDateTime: LocalDateTime): Long {
        return (localDateTime.dayOfWeek.value * ONE_DAY_IN_MINUTE + localDateTime.hour * ONE_HOUR_IN_MINUTE + localDateTime.minute).toLong()
    }

    private fun isBetween(now: Long) = beginHour.toMinute() <= now && endHour.toMinute() >= now

    private fun overlayOperationStage(
        target: Long,
        now: Long,
        positive: String,
        negative: String
    ) = if (now == target) {
        positive
    } else {
        negative
    }

    private fun isWithinAHour(target: Long, now: Long) =
        target - ONE_HOUR_IN_MINUTE <= now && now <= target

    private fun isOverlay(now: Long): Boolean {
        return isWithinAHour(beginHour.toMinute(), now) || isWithinAHour(endHour.toMinute(), now)
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
        private const val ONE_DAY_IN_MINUTE = 24 * ONE_HOUR_IN_MINUTE
    }
}

fun reduceOpening(first: String, second: String): String {
    if (first.equals("Close Soon", false) && second.equals("Open Soon", false)) {
        return first
    }

    return when (first) {
        "Close" -> second
        else -> first
    }
}
