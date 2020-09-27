package core.parses

import com.google.code.regexp.Pattern


object SampleParser {

    fun match(input: String, regexPattern: String): Boolean {
        val pattern = Pattern.compile(regexPattern);
        val matcher = pattern.matcher(input)
        return matcher.matches();
    }

    fun parse(input: String, regexPattern: String): Map<String, String> {
        val pattern = Pattern.compile(regexPattern);
        val matcher = pattern.matcher(input)

        if(!matcher.matches()){
            return mapOf()
        }

        matcher.find()

        var map = matcher.namedGroups()
        return if(map.firstOrNull() != null){
            map.firstOrNull().orEmpty()
        }else{
            mapOf()
        }
    }

}