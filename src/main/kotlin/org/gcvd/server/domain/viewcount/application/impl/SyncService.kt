package org.gcvd.server.domain.viewcount.application.impl

import org.gcvd.server.domain.viewcount.model.entity.ViewCount
import org.gcvd.server.domain.viewcount.model.repository.ViewCountRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@EnableScheduling
class SyncService(
    private val redisTemplate: RedisTemplate<String, String>,
    private val viewCountRepository: ViewCountRepository,
) {
    @Transactional
    @Scheduled(fixedDelay = 60000)
    fun syncToDatabase() {
        if (!redisTemplate.hasKey(ViewCount.VIEW_COUNT_KEY)) {
            redisTemplate.opsForValue().set(ViewCount.VIEW_COUNT_KEY, "0")
        }
        if (!viewCountRepository.existsByName(ViewCount.VIEW_COUNT_KEY)) {
            viewCountRepository.save(
                ViewCount(
                    name = ViewCount.VIEW_COUNT_KEY,
                    count = 0,
                ),
            )
        }
        val count =
            redisTemplate
                .opsForValue()
                .get(ViewCount.VIEW_COUNT_KEY)
                .toString()
                .toInt()

        viewCountRepository.updateCount(ViewCount.VIEW_COUNT_KEY, count)
        redisTemplate.opsForValue().getAndSet(ViewCount.VIEW_COUNT_KEY, "0")
    }
}
