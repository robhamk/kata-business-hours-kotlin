import java.time.DayOfWeek

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class LiteralHour(val dayOfWeek: DayOfWeek, val hour: Int, val minute: Int) {
    fun toMinute(): Long {
        return (dayOfWeek.value * ONE_DAY_IN_MINUTE + hour * ONE_HOUR_IN_MINUTE + minute).toLong()
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
        private const val ONE_DAY_IN_MINUTE = 24 * ONE_HOUR_IN_MINUTE
    }
}
