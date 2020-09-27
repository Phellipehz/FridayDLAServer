package server

import com.google.gson.Gson
import core.Request
import core.RequestHandlerProcessor
import org.webbitserver.BaseWebSocketHandler
import org.webbitserver.WebSocketConnection
import java.util.concurrent.TimeUnit

class WebSocketConnectionHandler: BaseWebSocketHandler() {

    override fun onOpen(connection: WebSocketConnection) {
        println("Conexão com: " + connection.httpRequest().remoteAddress())
    }

    override fun onClose(connection: WebSocketConnection) {
        println("Conexão finalizada com: " + connection.httpRequest().remoteAddress())
    }

    override fun onMessage(connection: WebSocketConnection, message: String) {
        var gson = Gson()
        var request = gson.fromJson<Request>(message, Request::class.java)

        RequestHandlerProcessor.execute(request)
            .buffer(1, TimeUnit.MINUTES)
            .subscribe { response ->
                var jsonString = gson.toJson(response)
                connection.send(jsonString);
                println("Respostas: " + jsonString)
            }
    }
}