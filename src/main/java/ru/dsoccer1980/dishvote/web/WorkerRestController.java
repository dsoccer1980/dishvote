package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.Worker;
import ru.dsoccer1980.dishvote.service.MealService;
import ru.dsoccer1980.dishvote.service.WorkerService;

@Controller
public class WorkerRestController {
    private static final Logger log = LoggerFactory.getLogger(WorkerRestController.class);

    private final WorkerService service;

    @Autowired
    public WorkerRestController(WorkerService service) {
        this.service = service;
    }

    public Worker get(int id) {
        return service.get(id);
    }

}