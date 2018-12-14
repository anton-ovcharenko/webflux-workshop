package tradingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private TradingUserRepository tradingUserRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", this.tradingUserRepository.findAll());
        return "index";
    }
}
