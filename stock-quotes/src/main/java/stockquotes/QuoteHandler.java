package stockquotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class QuoteHandler {

    private QuoteGenerator quoteGenerator;
    private Flux<Quote> quoteFlux;

    @Autowired
    public QuoteHandler(QuoteGenerator quoteGenerator) {
        this.quoteGenerator = quoteGenerator;
        quoteFlux = quoteGenerator
                .fetchQuoteStream(Duration.ofMillis(200))
                .publish()
                .autoConnect();
    }

    public Mono<ServerResponse> hello() {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(fromObject("Flux Stream from String"));
    }

    public Mono<ServerResponse> echo(ServerRequest request) {
        Mono<String> body = request.bodyToMono(String.class);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(fromPublisher(body, String.class));
    }


    public Mono<ServerResponse> quotes(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(fromPublisher(quoteFlux, Quote.class))
                .doOnSubscribe(s -> System.out.println("SUBSCRIBE!!!"));
    }

    public Mono<ServerResponse> quotesLimit(ServerRequest request) {
        int size = Integer.parseInt(request
                .queryParam("size")
                .orElse("10"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(quoteFlux.take(size), Quote.class));
    }
}
