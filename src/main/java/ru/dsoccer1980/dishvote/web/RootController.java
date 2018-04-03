package ru.dsoccer1980.dishvote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;;
import ru.dsoccer1980.dishvote.service.DishService;
import ru.dsoccer1980.dishvote.service.RestaurantService;
import ru.dsoccer1980.dishvote.service.UserService;
import ru.dsoccer1980.dishvote.service.VoteService;
import ru.dsoccer1980.dishvote.util.Exception.VoteException;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String root() {
        return "loginPage";
    }

    @PostMapping("/menu")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        User user = userService.get(userId);
        request.setAttribute("user", user);
        if (user.isAdmin()) {
            return "mainMenuAdmin";
        }
        else {
            return "mainMenuUser";
        }
    }

    @GetMapping("/userVote")
    public String userVoteGet(Model model) {
        LocalDate date = LocalDate.now();
        int userId = AuthorizedUser.getId();

        model.addAttribute("date", date);
        model.addAttribute("dishes", dishService.getDishOnDate(date));
        model.addAttribute("allVotesForUser", voteService.getAllVotesForUser(userId));
        return "voteForm";
    }

    @PostMapping("/userVote/date")
    public String userVoteChosenDate(Model model, HttpServletRequest request) {
        LocalDate localDate = LocalDate.parse(request.getParameter("date"));
        int userId = AuthorizedUser.getId();

        model.addAttribute("date", localDate);
        model.addAttribute("dishes", dishService.getDishOnDate(localDate));
        model.addAttribute("allVotesForUser", voteService.getAllVotesForUser(userId));

        return "voteForm";
    }

    @PostMapping("/userVote/vote")
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

    @GetMapping("/user/get")
    public String getUser(Model model, HttpServletRequest request) {
        int id = AuthorizedUser.getId();
        model.addAttribute("user", userService.get(id));
        return "userProfile";
    }

    @PostMapping("/user/update")
    public String updateUser(Model model, HttpServletRequest request) {
        int id = AuthorizedUser.getId();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(id, name, email, password, userService.get(id).getRegistered(), true, false);
        userService.update(user);
        model.addAttribute("user", user);
        return "mainMenuUser";
    }

    @GetMapping("/restaurant/show")
    public String showRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurant";
    }

    @PostMapping("/restaurant/add")
    public String addRestaurant(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String restaurantName = request.getParameter("name");
        String restaurantAddress = request.getParameter("address");
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);
        restaurantService.create(restaurant);
        return "redirect:/restaurant/show";
    }

    @GetMapping("/restaurant/update/id/{id}")
    public String updateRestaurant(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        model.addAttribute("restaurant", restaurantService.get(getId(id)));
        return "restaurantForm";
    }

    @GetMapping("/restaurant/delete/id/{id}")
    public String deleteRestaurant(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        restaurantService.delete(getId(id));
        return "redirect:/restaurant/show";
    }

    @GetMapping("/restaurant/listdish/id/{id}")
    public String dishRestaurant(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        List<Dish> allDishByRestaurant = dishService.getAllDishByRestaurant(getId(id));
        model.addAttribute("dishes", allDishByRestaurant);
        model.addAttribute("restaurant", getId(id));
        return "dish";
    }

    @PostMapping("/restaurant/edit")
    public String editRestaurant(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String restaurantName = request.getParameter("name");
        String restaurantAddress = request.getParameter("address");
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);

        int restaurantId = Integer.parseInt(request.getParameter("id"));

        restaurant.setId(restaurantId);
        restaurantService.update(restaurant);
        return "redirect:/restaurant/show";
    }

    @PostMapping("/dish/add")
    public String dishAdd(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Dish dish = getDish(null, request);
        dishService.create(dish);
        return "redirect:/restaurant/listdish/id/"  + dish.getRestaurant().getId();
    }

    @GetMapping("/dish/update/id/{id}")
    public String dishUpdate(@PathVariable(value = "id") String id, HttpServletRequest request) {
        request.setAttribute("dish", dishService.get(getId(id)));
        return "dishForm";
    }

    @PostMapping("/dish/edit")
    public String dishEdit(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Integer dishId = Integer.parseInt(request.getParameter("id"));
        Dish dish = getDish(dishId, request);
        dishService.update(dish);

        return "redirect:/restaurant/listdish/id/"  + dish.getRestaurant().getId();
    }

    @GetMapping("/dish/delete/id/{id}")
    public String dishDelete(@PathVariable(value = "id") String id, HttpServletRequest request) {
        int dishId = getId(id);
        int restaurantId = dishService.get(dishId).getRestaurant().getId();
        dishService.delete(dishId);
        return "redirect:/restaurant/listdish/id/" + restaurantId;
    }

    @GetMapping("/adminVote/show")
    public String adminVoteShow(HttpServletRequest request) {
        LocalDate date = LocalDate.now();
        String dateString = request.getParameter("date");
        if (dateString != null) {
            date = LocalDate.parse(request.getParameter("date"));
        }
        request.setAttribute("date", date);
        Map<Restaurant, Long> allVotesForDate = voteService.getVotesForRestaurantOnDate(date);
        if (allVotesForDate.size() == 0) {
            request.setAttribute("message", "There are not votes on this date");
        } else {
            request.setAttribute("allVotesForDate", allVotesForDate);
        }
        return "voteForDateForm";
    }

    @GetMapping("/adminVote/showUsersByRestaurantAndDate/restaurant_id/{restaurant_id}/date/{date}")
    public String adminVoteShowUsersByRestaurantAndDate(@PathVariable(value = "restaurant_id") String restaurant_id,
                                                        @PathVariable(value = "date") String date,
                                                        HttpServletRequest request)
    {
        LocalDate localDate = LocalDate.parse(date);
        int restaurantId = Integer.parseInt(restaurant_id);
        List<User> userList = voteService.getUsersVotedByRestaurantAndDate(restaurantId, localDate);
        request.setAttribute("userList", userList);
        return "voteUsersByRestaurantAndDate";
    }

    @GetMapping("/admin/showUsers")
    public String adminShowUsers(HttpServletRequest request) {
        int userId = AuthorizedUser.getId();

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/admin/userEditForm/id/{id}")
    public String adminUserEditForm(@PathVariable(value = "id") int id, HttpServletRequest request) {
        request.setAttribute("user", userService.get(id));
        return "userEditForm";
    }

    @GetMapping("/admin/userDelete/id/{id}")
    public String adminUserDelete(@PathVariable(value = "id") int id, HttpServletRequest request) {
        userService.delete(id);

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    @PostMapping("/admin/addUser")
    public String adminAddUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        userService.update(getUser(request, "addUser"));

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    @PostMapping("/admin/editUser")
    public String adminEditUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        userService.create(getUser(request, "editUser"));

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    private Dish getDish(Integer id, HttpServletRequest request) {
        String dishName = request.getParameter("name");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int restaurantId = Integer.parseInt(request.getParameter("restaurant_id"));
        Restaurant restaurant = restaurantService.get(restaurantId);
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        Dish dish = new Dish(id, dishName, price, restaurant, date);
        return dish;
    }

    private int getId(String id) {
        return Integer.parseInt(id);
    }

    private User getUser(HttpServletRequest request, String action) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean enabled = Boolean.valueOf(request.getParameter("enabled"));
        boolean isAdmin = Boolean.valueOf(request.getParameter("is_admin"));
        LocalDate registered = LocalDate.now();
        Integer id = null;

        if (action.equals("editUser")) {
            registered = LocalDate.parse(request.getParameter("registered"));
            id = getId(request.getParameter("id"));
        }
        User user = new User(id, name, email, password, registered, enabled, isAdmin);
        return user;
    }

}
