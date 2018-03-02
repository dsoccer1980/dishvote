package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.dishvote.model.Worker;
import ru.dsoccer1980.dishvote.repository.WorkerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class WorkerRepositoryImpl implements WorkerRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Worker get(int id) {
        Worker worker = em.find(Worker.class, id);
        return worker;
    }

}