import java.time.LocalDateTime

/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

data class OpeningHours(val beginHour: LiteralHour, val endHour: LiteralHour) {

    fun operationStage(localDateTime: LocalDateTime): String {
        return if (beginHour.isOverlay(localDateTime, endHour)) {
            if (beginHour.isWithinAHour(localDateTime)) {
                beginHour.overlayOperationStage(localDateTime, "Open", "Open Soon")
            } else {
                endHour.overlayOperationStage(localDateTime, "Close", "Close Soon")
            }
        } else {
            if (beginHour.isBetween(localDateTime, endHour)) "Open" else "Close"
        }
    }

}

