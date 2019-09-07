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
        if (openingHours[0].beginHour.toMinute() == now) {
            return "Open Soon"
        }
        return "Close"
    }

    private fun toMinute(localDateTime: LocalDateTime): Long {
        return (localDateTime.dayOfWeek.value * 24 * 60 + localDateTime.hour * 60 + localDateTime.minute).toLong()
    }

}
