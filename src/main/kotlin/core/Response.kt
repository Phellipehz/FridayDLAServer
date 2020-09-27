package core

class Response {

    var speak = ""
    var confidence = 0.0

    constructor(speak: String) {
        this.speak = speak
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Response

        if (speak != other.speak) return false
        if (confidence != other.confidence) return false

        return true
    }

    override fun hashCode(): Int {
        var result = speak.hashCode()
        result = 31 * result + confidence.hashCode()
        return result
    }

}