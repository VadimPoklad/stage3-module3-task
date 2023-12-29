package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
@Repository
public class TagRepository implements BaseRepository<Tag, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> readAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = criteriaQuery.from(Tag.class);
        return entityManager.createQuery(criteriaQuery.select(root)).getResultList();
    }

    public List<Tag> readAllByTagIds(List<Long> ids) {
        TypedQuery<Tag> typedQuery = entityManager.createNamedQuery("findAllTagsIn", Tag.class);
        typedQuery.setParameter("tagIds", ids);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Tag> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Tag create(Tag entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Tag update(Tag entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        if(tag != null){
            entityManager.remove(tag);
            return true;
        }
        return false;
    }
}
