/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class OpeningHours(val beginHour: LiteralHour, val endHour: LiteralHour) {
    fun operationStage(now: Long): String {
        if (beginHour.toMinute() - ONE_HOUR_IN_MINUTE <= now && beginHour.toMinute() > now) {
            return "Open Soon"
        }
        if (endHour.toMinute() - ONE_HOUR_IN_MINUTE <= now && endHour.toMinute() > now) {
            return "Close Soon"
        }
        if (now == beginHour.toMinute()) {
            return "Open"
        }
        return "Close"
    }

    fun overlay(now: Long): Boolean {
        if (beginHour.toMinute() < endHour.toMinute()) {
            return now >= beginHour.toMinute() - ONE_HOUR_IN_MINUTE && now <= endHour.toMinute()
        }
        return now <= beginHour.toMinute() - ONE_HOUR_IN_MINUTE && now <= endHour.toMinute()
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
    }
}
