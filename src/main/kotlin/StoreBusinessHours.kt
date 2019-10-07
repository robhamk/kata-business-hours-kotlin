import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

class StoreBusinessHours(private val serviceHours: List<OpeningHours>) {

    fun query(localDateTime: LocalDateTime): String {
        return serviceHours.map { it.operationStage(localDateTime) }.reduce { first, second -> reduceOpening(first, second) }
    }

    private fun reduceOpening(first: String, second: String): String {
        return if (first.equals("Close Soon", false) && second.equals("Open Soon", false)) {
            first
        } else {
            if (first == "Close") second else first
        }
    }
}
