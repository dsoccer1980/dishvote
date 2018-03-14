package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.web.dish.DishRestController;
import ru.dsoccer1980.dishvote.web.restaurant.RestaurantRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);

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
        String action = request.getParameter("action");
        String submit = request.getParameter("submit");

        String restaurantName = request.getParameter("name");
        String restaurantAddress = request.getParameter("address");
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);

        if (submit != null) {
            restaurantController.create(restaurant);
            response.sendRedirect("restaurant");
        }
        else if (action == null) {
            if (request.getParameter("id").isEmpty()) {

            }
            else {
                int restaurantId = Integer.parseInt(request.getParameter("id"));
                restaurant.setId(restaurantId);
                restaurantController.update(restaurant);
                response.sendRedirect("restaurant");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to restaurant");
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                request.setAttribute("restaurant", restaurantController.get(getId(request)));
                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
                break;
            case "delete":
                restaurantController.delete(getId(request));
                response.sendRedirect("restaurant");
                break;
            case "all":
            default:
                request.setAttribute("restaurants", restaurantController.getAll());
                request.getRequestDispatcher("/restaurant.jsp").forward(request, response);
        }
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
