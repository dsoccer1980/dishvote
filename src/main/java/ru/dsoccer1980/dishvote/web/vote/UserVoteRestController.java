package ru.dsoccer1980.dishvote.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dsoccer1980.dishvote.service.DishService;
import ru.dsoccer1980.dishvote.service.VoteService;
import ru.dsoccer1980.dishvote.util.Exception.VoteException;
import ru.dsoccer1980.dishvote.web.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@Controller
@RequestMapping(value = UserVoteRestController.REST_URL)
public class UserVoteRestController {

    static final String REST_URL = "/rest/userVote/";

    @Autowired
    private VoteService voteService;

    @Autowired
    private DishService dishService;

    @GetMapping("/show")
    public String userVoteGet(Model model) {
        LocalDate date = LocalDate.now();
        int userId = AuthorizedUser.getId();

        model.addAttribute("date", date);
        model.addAttribute("dishes", dishService.getDishOnDate(date));
        model.addAttribute("allVotesForUser", voteService.getAllVotesForUser(userId));
        return "voteForm";
    }

    @PostMapping("/date")
    public String userVoteChosenDate(Model model, HttpServletRequest request) {
        LocalDate localDate = LocalDate.parse(request.getParameter("date"));
        int userId = AuthorizedUser.getId();

        model.addAttribute("date", localDate);
        model.addAttribute("dishes", dishService.getDishOnDate(localDate));
        model.addAttribute("allVotesForUser", voteService.getAllVotesForUser(userId));

        return "voteForm";
    }

    @PostMapping("/vote")
    public String userVoteGiveVote(Model model, HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        int userId = AuthorizedUser.getId();

        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        try {
            voteService.save(userId, restaurantId, date);
        } catch (VoteException e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("dishes", dishService.getDishOnDate(date));
        model.addAttribute("allVotesForUser", voteService.getAllVotesForUser(userId));
        model.addAttribute("date", date);
        return "voteForm";
    }
}