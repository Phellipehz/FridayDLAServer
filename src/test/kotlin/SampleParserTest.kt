import core.Request
import core.Samples
import core.parses.SampleParser
import org.junit.Assert.assertTrue

import org.junit.Test

class SampleParserTest {

    var samples = Samples(
        "me lembra amanhã de (?<title>.+)",
        "me lembra todo dia de (?<title>.+)",
        "me lembra (?<time_frequency>.+) de (?<title>.+)",
        "me lembra de (?<title>.+) as (?<time>\\w+) horas",
        "me lembra de (?<title>.+) as (?<time>\\w+) horas de|da (?<title>.+)",
        "me lembra as (?<time>\\w+) horas de (?<title>.+)"
    )

    @Test
    fun whenPipeAnythingAfterPass() {
        var request = Request("me lembra todo dia de tomar agua")
        var index = samples.getFirstMatchIndex(request.speak)
        var pattern = samples.getSampleByIndex(index)
        var parameters = SampleParser.parse(request.speak, pattern)

        assertTrue("",
            (parameters["time_frequency"] == "todo dia" &&
            parameters["title"] == "tomar agua")
        )
    }

    @Test
    fun whenPipeAnythingAfterPass2() {
        var request = Request("me lembra amanhã de comprar ração pro doguinho")
        var index = samples.getFirstMatchIndex(request.speak)
        var pattern = samples.getSampleByIndex(index)
        var parameters = SampleParser.parse(request.speak, pattern)

        assertTrue("",
            (parameters["time_frequency"] == "amanhã" &&
             parameters["title"] == "comprar ração pro doguinho")
        )
    }

    @Test
    fun whenPipeAnythingAfterPass3() {
        var request = Request("me lembra sexta-feira na manhã de ir no médico")
        var index = samples.getFirstMatchIndex(request.speak)
        var pattern = samples.getSampleByIndex(index)
        var parameters = SampleParser.parse(request.speak, pattern)

        assertTrue("",
            (parameters["time_frequency"] == "sexta-feira na manhã" &&
                    parameters["title"] == "ir no médico")
        )
    }

}