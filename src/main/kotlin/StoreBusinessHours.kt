import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

class StoreBusinessHours {
    private lateinit var openingHours: List<OpeningHours>

    fun setOpeningHours(openingHours: List<OpeningHours>) {
        this.openingHours = openingHours;
    }

    fun query(localDateTime: LocalDateTime): String {
        val now = toMinute(localDateTime)
        if (openingHours[0].beginHour.toMinute() - ONE_HOUR_IN_MINUTE == now) {
            return "Open Soon"
        }
        return "Close"
    }

    private fun toMinute(localDateTime: LocalDateTime): Long {
        return (localDateTime.dayOfWeek.value * ONE_DAY_IN_MINUTE + localDateTime.hour * ONEHOUR_IN_MINUTE + localDateTime.minute).toLong()
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
        private const val ONE_DAY_IN_MINUTE = 60 * ONE_HOUR_IN_MINUTE
    }
}
