package core

import core.parses.SampleParser

class Samples {

    var samples = mutableListOf<String>()

    constructor(vararg list: String) {
        samples.addAll(list)
    }

    fun match(text: String): Boolean {
        return samples
            .map { sample -> SampleParser.match(text, sample) }
            .fold(true) { acc, b -> acc && b }
    }

    fun getFirstMatchIndex(text: String): Int {
        var index = -1;
        samples.forEach { sample ->
            if(SampleParser.match(text, sample)){
                index = samples.indexOf(sample)
                return@forEach
            }
        }
        return index;
    }

    fun getSampleByIndex(index: Int): String{
        return this.samples[index]
    }

    fun getMatchingPattern(text: String): String {
        var index = getFirstMatchIndex(text)
        return getSampleByIndex(index)
    }

}