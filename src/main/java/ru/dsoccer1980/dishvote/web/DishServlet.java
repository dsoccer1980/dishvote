package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.web.dish.DishRestController;
import ru.dsoccer1980.dishvote.web.restaurant.RestaurantRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class DishServlet extends HttpServlet {
    private static final Logger log = getLogger(DishServlet.class);

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantController;
    private DishRestController dishController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        restaurantController = springContext.getBean(RestaurantRestController.class);
        dishController = springContext.getBean(DishRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            //TODO
            if (request.getParameter("id").isEmpty()) {

            }
        }
        else if (action.equals("addDish") || (action.equals("editDish"))) {
            Integer dishId = action.equals("addDish")? null: Integer.parseInt(request.getParameter("id"));
            String dishName = request.getParameter("name");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            int restaurantId = Integer.parseInt(request.getParameter("restaurant_id"));
            Restaurant restaurant = restaurantController.get(restaurantId);
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            Dish dish = new Dish(dishId, dishName, price, restaurant, date);

            if (action.equals("addDish")) {
                dishController.create(dish);
            }
            else {
                dishController.update(dish);
            }
            response.sendRedirect("/restaurant?action=dish&id=" + restaurantId);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to restaurant");  //TODO log
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "update":
                request.setAttribute("dish", dishController.get(getId(request)));
                request.getRequestDispatcher("/dishForm.jsp").forward(request, response);
                break;
            case "delete":
                int dishId = getId(request);
                int restaurantId = dishController.get(dishId).getRestaurant().getId();
                dishController.delete(dishId);
                request.getRequestDispatcher("/restaurant?action=dish&id=" + restaurantId).forward(request, response);
                break;
            case "all": //TODO
            default:
               // request.getRequestDispatcher("/restaurant?action=dish&id=" + getId(request)).forward(request, response);
        }
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
