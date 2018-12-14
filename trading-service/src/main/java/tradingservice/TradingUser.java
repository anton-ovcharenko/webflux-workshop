package tradingservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class TradingUser {

    @Id
    private String id;
    private String userName;
    private String fullName;

    public TradingUser(String id, String userName, String fullName) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
    }

    public TradingUser(String userName, String fullName) {
        this.userName = userName;
        this.fullName = fullName;
    }
}
