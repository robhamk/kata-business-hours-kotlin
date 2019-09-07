import java.time.DayOfWeek

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class LiteralHour(val dayOfWeek: DayOfWeek, val hour: Int, val minute: Int) {
    fun toMinute(): Long {
        return (dayOfWeek.value * 24 * 60 + hour * 60 + minute).toLong()
    }
}
