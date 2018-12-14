package tradingservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@Controller
public class QuotesController {

    private Flux<Quote> sharedQuotes;

    @GetMapping("/quotes")
    public String quotes() {
        return "quotes";
    }

    @GetMapping(path = "/quotes/feed", produces = TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Quote> quotesStream() {
        if(Objects.isNull(sharedQuotes)){
            sharedQuotes = getSharedQuotes();
        }
        return sharedQuotes;
    }

    private Flux<Quote> getSharedQuotes() {
        return WebClient.create("http://localhost:8081")
                .get()
                .uri("/quotes")
                .accept(APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class)
                .doOnSubscribe(s -> System.out.println("Subscribe!!!!!"))
                .share();
    }
}
