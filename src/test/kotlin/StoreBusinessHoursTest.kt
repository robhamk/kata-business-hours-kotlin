import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

class StoreBusinessHoursTest {
    private lateinit var storeBusinessHours: StoreBusinessHours

    @Before
    fun setup() {
        storeBusinessHours = StoreBusinessHours()
    }

    @Test
    fun testClose_MoreThanOneHoursBeforeOpen() {
        givenOneBusinessHours()

        shouldBe("Close", LocalDateTime.of(2019, 9, 2, 8, 59))
    }

    @Test
    fun testOpen_OneHourBeforeOpen() {
        givenOneBusinessHours()

        shouldBe("Open Soon", LocalDateTime.of(2019, 9, 2, 9, 0))
    }

    private fun givenOneBusinessHours() {
        val beginHour = LiteralHour(DayOfWeek.MONDAY, 10, 0)
        val endHour = LiteralHour(DayOfWeek.MONDAY, 12, 0)
        val openingHours = OpeningHours(beginHour, endHour)
        storeBusinessHours.setOpeningHours(listOf(openingHours))
    }

    private fun shouldBe(expectedResult: String, localDateTime: LocalDateTime) {
        assertEquals(expectedResult, storeBusinessHours.query(localDateTime))
    }
}
