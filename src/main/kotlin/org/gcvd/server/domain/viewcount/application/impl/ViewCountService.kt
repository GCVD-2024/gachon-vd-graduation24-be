package org.gcvd.server.domain.viewcount.application.impl

import org.gcvd.server.domain.viewcount.application.converter.ViewCountConverter
import org.gcvd.server.domain.viewcount.model.entity.ViewCount
import org.gcvd.server.domain.viewcount.model.repository.ViewCountRepository
import org.gcvd.server.domain.viewcount.ui.dto.ViewCountResponse
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ViewCountService(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val viewCountRepository: ViewCountRepository,
) {
    @Transactional
    fun getViewCount(): ViewCountResponse.ViewCount {
        val count =
            redisTemplate
                .opsForValue()
                .get(
                    ViewCount.VIEW_COUNT_KEY,
                ).toString()
                .toInt()
                .plus(viewCountRepository.getReferenceByName(ViewCount.VIEW_COUNT_KEY).count)

        return ViewCountConverter.toViewCount(count)
    }

    @Transactional
    fun addViewCount() {
        redisTemplate.opsForValue().increment(ViewCount.VIEW_COUNT_KEY, 1)
    }
}
