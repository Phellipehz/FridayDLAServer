package client

import com.google.gson.Gson
import core.Request
import org.webbitserver.BaseWebSocketHandler
import org.webbitserver.WebSocketConnection

class WebSocketConnectionHandler: BaseWebSocketHandler() {

    var gson = Gson()

    override fun onOpen(connection: WebSocketConnection) {
        println("Conectado a: " + connection.httpRequest().remoteAddress())
        var request = Request("Oi!")
        connection.send(gson.toJson(request))
    }

    override fun onClose(connection: WebSocketConnection) {
        println("Conexão finalizada com: " + connection.httpRequest().remoteAddress())
    }

    override fun onMessage(connection: WebSocketConnection, message: String) {
        var request = gson.fromJson<Request>(message, Request::class.java)
        print(request.speak)
    }
}