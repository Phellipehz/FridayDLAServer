import core.parses.SampleParser
import junit.framework.Assert.assertTrue
import org.junit.Test

class SampleMatcherTest {

    @Test
    fun whenPipeFail() {
        assertTrue("",
            SampleParser.match(
                "oi",
                "(oi|ola) meu consagrado quero (um|uma) cerveja"
            )
        )
    }

    @Test
    fun whenPipePass() {
        assertTrue("",
            SampleParser.match(
                "oi meu consagrado quero uma cerveja",
                "(oi|ola) meu consagrado quero (um|uma) cerveja"
            )
        )
    }

    @Test
    fun whenPipeAnythingAfterPass() {
        assertTrue("",
            SampleParser.match(
                "oi meu consagrado quero uma cerveja",
                "(oi|ola) meu consagrado quero (um|uma) (?<coisa>\\w+)"
            )
        )
    }

    @Test
    fun whenPipeNextWordAfter_() {
        var t = SampleParser.parse(
            "oi meu consagrado quero uma cerveja",
            "(oi|ola) meu consagrado quero (um|uma) (?<coisa>\\w+)"
        )

        assertTrue("", t["coisa"] == "cerveja")
    }

    @Test
    fun whenPipeAnythingAfter() {
        var t = SampleParser.parse(
            "oi meu consagrado quero uma cerveja topzeira",
            "(oi|ola) meu (?<tratamento>\\w+) quero (um|uma) (?<coisa>.+)"
        )

        assertTrue("",
            t["coisa"] == "cerveja topzeira" &&
                    t["tratamento"] == "consagrado"
        )
    }

    @Test
    fun whenPipeAnythingAfter_() {
        var t = SampleParser.parse(
            "oi meu consagrado quero uma cerveja topzeira gelada",
            "(oi|ola) meu (?<tratamento>\\w+) quero (um|uma) (?<coisa>.+) gelada"
        )

        assertTrue("",
            t["coisa"] == "cerveja topzeira" &&
                    t["tratamento"] == "consagrado"
        )
    }

    @Test
    fun whenExactlyPass() {
        assertTrue("texto a texto",
            SampleParser.match(
                "oi",
                "oi"
            )
        )
    }

    @Test
    fun whenC() {
        assertTrue("",
            SampleParser.match(
                "oi",
                "oi|ola"
            )
        )
    }

    @Test
    fun whenD() {
        assertTrue("",
            SampleParser.match(
                "oi",
                "fala|fiote"
            )
        )
    }

}