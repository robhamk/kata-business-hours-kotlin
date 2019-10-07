
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

class StoreBusinessHoursTest {
    private lateinit var openingHoursList: List<OpeningHours>

    @Test
    fun testClose_MoreThanOneHoursBeforeOpen() {
        givenOpeningHours(
            openingHours(
                10, 0,
                12, 0
            )
        )

        shouldBe("Close", currentHourAndMinute(8, 59))
    }

    @Test
    fun testOpenSoon_OneHourBeforeOpen() {
        givenOpeningHours(
            openingHours(
                10, 0,
                12, 0
            )
        )

        shouldBe("Open Soon", currentHourAndMinute(9, 0))
        shouldBe("Open Soon", currentHourAndMinute(9, 59))
    }

    @Test
    fun testOpen_OnOpen() {
        givenOpeningHours(
            openingHours(
                10, 0,
                12, 0
            )
        )

        shouldBe("Open", currentHourAndMinute(10, 0))
        shouldBe("Open", currentHourAndMinute(10, 59))
    }

    @Test
    fun testCloseSoon_OneHourBeforeClose() {
        givenOpeningHours(
            openingHours(
                10, 0,
                12, 0
            )
        )

        shouldBe("Close Soon", currentHourAndMinute(11, 0))
        shouldBe("Close Soon", currentHourAndMinute(11, 59))
    }

    @Test
    fun testClose_OnClose() {
        givenOpeningHours(
            openingHours(
                10, 0,
                12, 0
            )
        )

        shouldBe("Close", currentHourAndMinute(12, 0))
    }

    @Test
    fun testOpenSoon_OneHoursBeforeSecondOpen_TwoBusinessHours() {
        givenOpeningHours(
            openingHours(10, 0, 12, 0),
            openingHours(14, 0, 16, 0)
        )

        shouldBe("Open Soon", currentHourAndMinute(13, 0))
        shouldBe("Open Soon", currentHourAndMinute(13, 59))
    }

    @Test
    fun testCloseSoon_OneHoursBeforeSecondClose_TwoBusinessHours() {
        givenOpeningHours(
            openingHours(10, 0, 12, 0),
            openingHours(14, 0, 16, 0)
        )

        shouldBe("Close Soon", currentHourAndMinute(15, 0))
        shouldBe("Close Soon", currentHourAndMinute(15, 59))
    }

    @Test
    fun testClose_OneHourAfterSecondClose_TwoBusinessHours() {
        givenOpeningHours(
            openingHours(10, 0, 12, 0),
            openingHours(14, 0, 16, 0)
        )

        shouldBe("Close", currentHourAndMinute(16, 0))
    }

    @Test
    fun testCloseSoon_HalfHourBeforeFirstCloseAndOneHourBeforeSecondOpen_TwoCloseBusinessHours() {
        givenOpeningHours(
            openingHours(10, 0, 12, 0),
            openingHours(12, 30, 14, 0)
        )

        shouldBe("Close Soon", currentHourAndMinute(11, 30))
        shouldBe("Close Soon", currentHourAndMinute(11, 59))
    }

    @Test
    fun testOpenSoon_OnFirstCloseAndHalfHourBeforeSecondOpen_TwoCloseBusinessHours() {
        givenOpeningHours(
            openingHours(10, 0, 12, 0),
            openingHours(12, 30, 14, 0)
        )

        shouldBe("Open Soon", currentHourAndMinute(12, 0))
        shouldBe("Open Soon", currentHourAndMinute(12, 29))
    }

    @Test
    fun testCloseSoon_OneHourBeforeClose_CloseHourIsSmallerThanOpenHour() {
        givenOpeningHours(
            openingHours(8, 0, 7, 0)
        )

        shouldBe("Close Soon", currentHourAndMinute(6, 0))
        shouldBe("Close Soon", currentHourAndMinute(6, 59))
        shouldBe("Open", currentHourAndMinute(8, 0))
    }

    @Test
    fun testOpenSoon_OneHourBeforeOpen_CloseHourIsSmallerThanOpenHour() {
        givenOpeningHours(
            openingHours(8, 0, 7, 0)
        )

        shouldBe("Open Soon", currentHourAndMinute(7, 0))
        shouldBe("Open Soon", currentHourAndMinute(7, 59))
    }

    @Test
    fun testOpen_OneHourBeforeOpen_CloseHourIsSmallerThanOpenHour() {
        givenOpeningHours(
            openingHours(8, 0, 7, 0)
        )

        shouldBe("Open", currentHourAndMinute(8, 0))
    }

    private fun givenOpeningHours(vararg openingHours: OpeningHours) {
        openingHoursList = openingHours.toList()
    }

    private fun shouldBe(expectedResult: String, localDateTime: LocalDateTime) {
        assertEquals(expectedResult, StoreBusinessHours(openingHoursList).query(localDateTime))
    }

    private fun openingHours(beginHour: Int, beginMinute: Int, endHour: Int, endMinute: Int): OpeningHours {
        return OpeningHours(
            LiteralHour(DayOfWeek.MONDAY, beginHour, beginMinute),
            LiteralHour(DayOfWeek.MONDAY, endHour, endMinute)
        )
    }

    private fun currentHourAndMinute(hour: Int, minute: Int) = LocalDateTime.of(2019, 9, 2, hour, minute)

}
