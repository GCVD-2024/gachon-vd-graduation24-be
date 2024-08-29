package org.gcvd.server.domain.guestbook.model.repository

import org.gcvd.server.domain.guestbook.model.entity.Guestbook
import org.springframework.data.jpa.repository.JpaRepository

interface GuestbookRepository : JpaRepository<Guestbook, Long>
