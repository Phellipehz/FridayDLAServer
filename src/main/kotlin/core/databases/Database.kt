package core.databases

import org.dizitart.kno2.*
import org.dizitart.no2.Nitrite
import java.io.File

object Database{

    fun getInstance(): Nitrite{
        return nitrite {
            file = File("database")
            autoCommitBufferSize = 2048
            compress = true
            autoCompact = false
        }
    }

}

/*
class DatabaseHelper {

    fun tste(){
        db.getRepository(Reminder.class)

        // Initialize a Nitrite Collection
        val collection = db.getCollection("test") {
            insert(documentOf("a" to 1),
                documentOf("a" to 2),
                documentOf("a" to 3),
                documentOf("a" to 4),
                documentOf("a" to 5))

            val cursor = find(limit(0, 2))
        }

// Initialize an Object Repository
        val repository = db.getRepository<Employee> {
            insert(Employee(1, "red"), Employee(2, "yellow"))
        }

        val doc = emptyDocument()
        val doc = documentOf()

// create a document with one pair
        val doc = documentOf("a" to 1)

// create a document with more pairs
        val doc = documentOf("a" to 1, "b" to 2, "c" to 3)

    }

}

*/