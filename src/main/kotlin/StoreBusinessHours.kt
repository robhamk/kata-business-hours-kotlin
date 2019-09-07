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
        return "Close"
    }

}
