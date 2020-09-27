package applications.handlers

import core.Request
import core.RequestHandler
import core.Response
import core.parses.SampleParser
import io.reactivex.rxjava3.core.Observable

class GreetingsHandler: RequestHandler {

    override fun canHandle(request: Request): Boolean {
        var input = request.speak.toLowerCase().replace("[^a-zA-Z]", "");
        input = input.replace("!", "");
        return SampleParser.match(input, "oi|ola|hello")
    }

    override fun handle(request: Request): Observable<Response> {
       return Observable.just(Response("Olá, meu nome é Friday...."))
    }

}