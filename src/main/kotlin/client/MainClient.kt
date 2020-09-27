package client

import org.webbitserver.netty.WebSocketClient
import java.lang.Exception
import java.net.URI

fun main(args: Array<String>) {

    try {
        var client = WebSocketClient(URI.create("ws://localhost:8080/command"), WebSocketConnectionHandler())
        client.start();
    }catch (e: Exception){
        println(e)
    }

}