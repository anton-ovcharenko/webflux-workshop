package stockquotes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuoteRouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> helloRoute(QuoteHandler quoteHandler) {
        return route(path("/hello"), serverRequest -> quoteHandler.hello())
                .andRoute(path("/echo"), quoteHandler::echo)
                .andRoute(path("/quotes"), quoteHandler::quotes)
                .andRoute(path("/quotesLimit"), quoteHandler::quotesLimit)
                ;
    }
}
