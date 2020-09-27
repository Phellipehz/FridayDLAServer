package core

import io.reactivex.rxjava3.core.Observable

interface RequestHandler {

    fun canHandle(request: Request): Boolean
    fun handle(request: Request): Observable<Response>

}