package ru.dsoccer1980.dishvote.web.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.service.DishService;
import ru.dsoccer1980.dishvote.service.RestaurantService;
import ru.dsoccer1980.dishvote.util.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = RestaurantRestController.REST_URL)
public class RestaurantRestController {

    static final String REST_URL = "/rest/restaurant/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping("/show")
    public String showRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll());
        return "restaurant";
    }

    @PostMapping("/add")
    public String addRestaurant(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String restaurantName = request.getParameter("name");
        String restaurantAddress = request.getParameter("address");
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);
        restaurantService.create(restaurant);
        return "redirect:/rest/restaurant/show";
    }

    @GetMapping("/update/id/{id}")
    public String updateRestaurant(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        model.addAttribute("restaurant", restaurantService.get(UserUtils.getId(id)));
        return "restaurantForm";
    }

    @GetMapping("/delete/id/{id}")
    public String deleteRestaurant(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        restaurantService.delete(UserUtils.getId(id));
        return "redirect:/rest/restaurant/show";
    }

    @GetMapping("/listdish/id/{id}")
    public String dishRestaurant(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        List<Dish> allDishByRestaurant = dishService.getAllDishByRestaurant(UserUtils.getId(id));
        model.addAttribute("dishes", allDishByRestaurant);
        model.addAttribute("restaurant", UserUtils.getId(id));
        return "dish";
    }

    @PostMapping("/edit")
    public String editRestaurant(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String restaurantName = request.getParameter("name");
        String restaurantAddress = request.getParameter("address");
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);

        int restaurantId = Integer.parseInt(request.getParameter("id"));

        restaurant.setId(restaurantId);
        restaurantService.update(restaurant);
        return "redirect:/rest/restaurant/show";
    }
}