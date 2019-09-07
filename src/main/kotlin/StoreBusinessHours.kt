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
        return serviceHours.map { it.operationStage(localDateTime) }.reduce { first, second -> reduceOpening(first, second) }
    }
}
