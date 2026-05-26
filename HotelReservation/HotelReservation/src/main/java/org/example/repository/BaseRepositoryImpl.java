package org.example.repository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {
    protected EntityManager em;
    private Class<T> entityClass;

    public BaseRepositoryImpl(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {

    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(ID id) {

    }

    private void executeInTransaction(Runnable action) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            action.run();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Lỗi khi thao tác với Database: " + e.getMessage(), e);
        }
    }
}
