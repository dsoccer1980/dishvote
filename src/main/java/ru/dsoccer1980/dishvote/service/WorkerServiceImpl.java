package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.Worker;
import ru.dsoccer1980.dishvote.repository.WorkerRepository;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository repository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Worker get(int id) {
        return repository.get(id);
    }

}