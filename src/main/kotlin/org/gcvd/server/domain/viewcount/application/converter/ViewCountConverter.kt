package org.gcvd.server.domain.viewcount.application.converter

import org.gcvd.server.domain.viewcount.ui.dto.ViewCountResponse

class ViewCountConverter {
    companion object {
        fun toViewCount(count: Int) = ViewCountResponse.ViewCount(count)
    }
}
