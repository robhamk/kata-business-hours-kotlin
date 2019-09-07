/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class OpeningHours(val beginHour: LiteralHour, val endHour: LiteralHour) {
    fun operationStage(now: Long): String {
        if (now > beginHour.toMinute() && now <= endHour.toMinute() - ONE_HOUR_IN_MINUTE) {
            return "Close Soon"
        }
        if (beginHour.toMinute() == now) {
            return "Open"
        }
        if (beginHour.toMinute() - ONE_HOUR_IN_MINUTE == now) {
            return "Open Soon"
        }
        return "Close"
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
    }
}
