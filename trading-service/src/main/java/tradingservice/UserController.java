package tradingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private TradingUserRepository repository;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TradingUser> getAllUsers() {
        return repository
                .findAll();
    }

    @GetMapping(path = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TradingUser> getUSerByName(@PathVariable String username) {
        return repository
                .findByUserName(username);
    }
}
