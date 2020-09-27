package applications.reminder

import core.*
import core.parses.SampleParser
import io.reactivex.rxjava3.core.Observable

class ReminderApplicationHandler: RequestHandler {

    var samples = Samples(
        "me lembra ((amanhã)|(todo dia)|(?<time_frequency>.+)) de (?<title>.+)",
        "me lembra de (?<title>.+) as (?<time>\\w+) horas",
        "me lembra as (?<time>\\w+) horas de (?<title>.+)"
    )

    override fun canHandle(request: Request): Boolean {
        var input = request.speak.toLowerCase().replace("[^a-zA-Z]", "");
        return samples.match(input)
    }

    override fun handle(request: Request): Observable<Response> {
        var pattern = samples.getMatchingPattern(request.speak)
        var parameters = SampleParser.parse(request.speak, pattern)

        return if(parameters.containsKey("title") && parameters["title"] != null){
            var reminder = Reminder(parameters.getOrElse("title") { "" }, null, null, null, false)
            var response = "Pronto, lembrarei você de "+reminder.title

            if(parameters["time_frequency"] == "todo dia"){
                reminder.diario = true
                response += ", todos os dias. "
            }

            Observable.just(Response(response))
        }else{
            Observable.just(Response("Desculpe, mas não consegui identificar sua ordem."))
        }
    }

}