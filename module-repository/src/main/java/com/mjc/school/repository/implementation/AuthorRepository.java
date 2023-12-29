package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Author;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorRepository implements BaseRepository<Author, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> readAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);
        return entityManager.createQuery(criteriaQuery.select(root)).getResultList();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Author create(Author entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Author update(Author entity) {
        Author author = entityManager.find(Author.class, entity.getId());
        author.setName(entity.getName());
        author.setLastUpdateDate(entity.getLastUpdateDate());
        return author;
    }

    @Override
    public boolean deleteById(Long id) {
        Author author = entityManager.find(Author.class, id);
        if(author != null){
            entityManager.remove(author);
            return true;
        }
        return false;
    }
}
