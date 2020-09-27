import core.Request
import core.RequestHandlerProcessor
import core.Response
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Test

class CommandHandlerTest {

    @Test
    fun whenComandNotIdentified() {
        var request = Request("ajhjahsjhams?")

        var observable = RequestHandlerProcessor.execute(request)

        observable
            .test()
            .assertNoErrors()
            .assertValues(Response("Desculpe, não entendi."))
    }

}