package com.rehabilitationpro

import android.os.Build
import androidx.annotation.RequiresApi
import com.rehabilitationpro.screens.notice.Notice
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



@RequiresApi(Build.VERSION_CODES.O)
suspend fun getNoticeData(callback: (Result<List<Notice>>) -> Unit) {
    delay(1000) // Simulate network delay

    val mockNotices = listOf(
        Notice(
            "1",
            "New Hydrotherapy Pool Opening",
            "We're excited to announce the opening of our state-of-the-art hydrotherapy pool next month. This facility will greatly enhance our aquatic therapy programs.",
            LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "2",
            "Workshop: Ergonomics in the Workplace",
            "Join our certified physical therapists for a workshop on maintaining proper posture and ergonomics in office environments. Learn techniques to prevent work-related injuries.",
            LocalDateTime.now().minusDays(5).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "3",
            "New Stroke Rehabilitation Program",
            "We're introducing a comprehensive stroke rehabilitation program that combines physical therapy, occupational therapy, and speech therapy for optimal recovery.",
            LocalDateTime.now().minusWeeks(1).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "4",
            "Virtual Reality in Pain Management",
            "Our clinic now offers virtual reality sessions as part of our pain management program. This innovative approach has shown promising results in reducing chronic pain.",
            LocalDateTime.now().minusWeeks(2).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "5",
            "Sports Injury Prevention Seminar",
            "Attend our upcoming seminar on preventing common sports injuries. Our experts will discuss proper warm-up techniques, strength training, and injury prevention strategies.",
            LocalDateTime.now().minusWeeks(3).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "6",
            "New Equipment: Anti-Gravity Treadmill",
            "We've added an anti-gravity treadmill to our facility. This advanced equipment allows for early mobility training with reduced joint stress.",
            LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "7",
            "Pediatric Therapy Expansion",
            "Our pediatric therapy department is expanding. We now offer specialized programs for children with developmental delays, neurological disorders, and orthopedic conditions.",
            LocalDateTime.now().minusMonths(1).minusWeeks(1).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "8",
            "Introducing Dry Needling Therapy",
            "We're now offering dry needling therapy as part of our treatment options. This technique can be effective for myofascial pain and trigger points.",
            LocalDateTime.now().minusMonths(1).minusWeeks(2).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "9",
            "Balance and Fall Prevention Program",
            "Our new balance and fall prevention program is designed for older adults. It includes exercises to improve stability, strength, and confidence in daily activities.",
            LocalDateTime.now().minusMonths(2).format(DateTimeFormatter.ISO_LOCAL_DATE)
        ),
        Notice(
            "10",
            "Hand Therapy Specialist Joins Team",
            "We're pleased to welcome Dr. Jane Smith, a certified hand therapy specialist, to our team. She brings expertise in treating complex hand and upper extremity conditions.",
            LocalDateTime.now().minusMonths(2).minusWeeks(1).format(DateTimeFormatter.ISO_LOCAL_DATE)
        )
    )

    callback(Result.success(mockNotices))
}