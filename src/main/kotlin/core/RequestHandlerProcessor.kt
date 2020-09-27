package core

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object RequestHandlerProcessor {

    var handlers = mutableListOf<RequestHandler>()

    fun attachHander(handler: RequestHandler){
        handlers.add(handler)
    }

    fun attachHanders(handlersToAdd: List<RequestHandler>){
        handlers.addAll(handlers)
    }

    fun execute(request: Request): Observable<Response>{
        var executables = mutableListOf<Observable<Response>>()
        handlers.forEach { handler ->
            if(handler.canHandle(request)){
                executables.add( handler.handle(request) )
            }
        }

        if(executables.isEmpty()){
            return Observable.just(Response("Desculpe, não entendi."))
        }

        return Observable.concat(executables)
            .timeout(1, TimeUnit.MINUTES)
            .doOnError { error -> println(error) }
            .onErrorResumeNext {
                Observable.just(Response("Desculpe, não entendi."))
            }
    }

}