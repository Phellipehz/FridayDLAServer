package applications.reminder

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Reminder(
    val title: String,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val dateTime: LocalDateTime? = null,
    var diario: Boolean)