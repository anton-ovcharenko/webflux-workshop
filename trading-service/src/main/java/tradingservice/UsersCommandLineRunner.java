package tradingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Component
public class UsersCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TradingUserRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        List<TradingUser> users = Arrays.asList(
                new TradingUser("tfirst", "Tim First"),
                new TradingUser("bsecond", "Bob Second"),
                new TradingUser("athird", "Anna Trird")
                );
        this.repository.insert(users).blockLast(Duration.ofSeconds(3));
    }
}
