import com.google.gson.Gson
import core.Request
import core.Response
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.webbitserver.BaseWebSocketHandler
import org.webbitserver.WebSocketConnection
import org.webbitserver.netty.WebSocketClient
import server.main
import java.lang.Exception
import java.net.InetAddress
import java.net.URI
import java.util.concurrent.TimeUnit

class WebSocketTest {

    @Before
    fun before(){
        //main(arrayOf<String>())
    }

    @Test
    fun test(){
        Observable.create<Response> { emitter ->
            class TestWebSocketHandler: BaseWebSocketHandler() {
                var gson = Gson()

                override fun onOpen(connection: WebSocketConnection) {
                    println("Conectado a: " + connection.httpRequest().remoteAddress())
                    var request = Request("Oi")
                    connection.send(gson.toJson(request))
                }

                override fun onClose(connection: WebSocketConnection) {
                    println("Conexão finalizada com: " + connection.httpRequest().remoteAddress())
                    emitter.onComplete()
                }

                override fun onMessage(connection: WebSocketConnection, message: String) {
                    var response = gson.fromJson<Response>(message, Response::class.java)
                    emitter.onNext(response)
                    emitter.onComplete();
                }
            }

            var name = InetAddress.getLocalHost().hostName
            WebSocketClient(URI.create("ws://$name:8080/command"), TestWebSocketHandler()).start();

        }.delay(5, TimeUnit.SECONDS)
        .test()
       .assertNoErrors()
       .assertValues(Response("Olá, meu nome é Friday...."))
    }

}