package core.parses

object NumberParser {

    fun parse(text: String): Int{
        return try {
            text.toInt()
        } catch (nfe: NumberFormatException) {
            0;
        }
    }

}