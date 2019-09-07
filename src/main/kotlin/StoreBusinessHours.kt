import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

class StoreBusinessHours {
    private lateinit var serviceHours: List<OpeningHours>

    fun setOpeningHours(openingHours: List<OpeningHours>) {
        this.serviceHours = openingHours;
    }

    fun query(localDateTime: LocalDateTime): String {
        val now = toMinute(localDateTime)
        var overlayBusinessHours = serviceHours.filter { it.overlay(now) }
        if (overlayBusinessHours.isNotEmpty()) {
            return overlayBusinessHours[0].operationStage(now)
        }
        return "Close"
    }

    private fun toMinute(localDateTime: LocalDateTime): Long {
        return (localDateTime.dayOfWeek.value * ONE_DAY_IN_MINUTE + localDateTime.hour * ONE_HOUR_IN_MINUTE + localDateTime.minute).toLong()
    }

    companion object {
        private const val ONE_HOUR_IN_MINUTE = 60L
        private const val ONE_DAY_IN_MINUTE = 24 * ONE_HOUR_IN_MINUTE
    }
}
