package server

import applications.handlers.GreetingsHandler
import applications.reminder.ReminderApplicationHandler
import core.RequestHandlerProcessor
import org.webbitserver.WebServer
import org.webbitserver.WebServers

fun main(args: Array<String>) {
    RequestHandlerProcessor.attachHanders(
        listOf(
            ReminderApplicationHandler(),
            GreetingsHandler()
        )
    )

    val webServer: WebServer = WebServers.createWebServer(8080)
        .add("/command", WebSocketConnectionHandler())
    webServer.start()

    println("Servidor iniciado em: " + webServer.uri)
}


