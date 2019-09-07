import junit.framework.Assert.assertEquals
import org.junit.Test
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

class StoreBusinessHoursTest {
    @Test
    fun testClose_MoreThanOneHoursBeforeOpen() {
        var storeBusinessHours = StoreBusinessHours()
        val beginHour = LiteralHour(DayOfWeek.MONDAY, 10, 0)
        val endHour = LiteralHour(DayOfWeek.MONDAY, 12, 0)
        var openingHours = OpeningHours(beginHour, endHour)
        storeBusinessHours.setOpeningHours(listOf(openingHours))

        assertEquals("Close", storeBusinessHours.query(LocalDateTime.of(2019, 9, 2, 8, 59)))
    }

    @Test
    fun testOpen_OneHourBeforeOpen() {
        var storeBusinessHours = StoreBusinessHours()
        val beginHour = LiteralHour(DayOfWeek.MONDAY, 10, 0)
        val endHour = LiteralHour(DayOfWeek.MONDAY, 12, 0)
        var openingHours = OpeningHours(beginHour, endHour)
        storeBusinessHours.setOpeningHours(listOf(openingHours))

        assertEquals("Open Soon", storeBusinessHours.query(LocalDateTime.of(2019, 9, 2, 9, 0)))
    }
}
