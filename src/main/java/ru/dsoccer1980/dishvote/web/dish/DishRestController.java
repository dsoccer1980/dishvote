package ru.dsoccer1980.dishvote.web.dish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.service.DishService;
import ru.dsoccer1980.dishvote.service.RestaurantService;
import ru.dsoccer1980.dishvote.util.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;


@Controller
@RequestMapping(value = DishRestController.REST_URL)
public class DishRestController {

    static final String REST_URL = "/rest/dish/";

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/add")
    public String dishAdd(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Dish dish = getDish(null, request);
        dishService.create(dish);
        return "redirect:/rest/restaurant/listdish/id/"  + dish.getRestaurant().getId();
    }

    @GetMapping("/update/id/{id}")
    public String dishUpdate(@PathVariable(value = "id") String id, HttpServletRequest request) {
        request.setAttribute("dish", dishService.get(UserUtils.getId(id)));
        return "dishForm";
    }

    @PostMapping("/edit")
    public String dishEdit(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Integer dishId = Integer.parseInt(request.getParameter("id"));
        Dish dish = getDish(dishId, request);
        dishService.update(dish);

        return "redirect:/rest/restaurant/listdish/id/"  + dish.getRestaurant().getId();
    }

    @GetMapping("/delete/id/{id}")
    public String dishDelete(@PathVariable(value = "id") String id, HttpServletRequest request) {
        int dishId = UserUtils.getId(id);
        int restaurantId = dishService.get(dishId).getRestaurant().getId();
        dishService.delete(dishId);
        return "redirect:/rest/restaurant/listdish/id/" + restaurantId;
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
}