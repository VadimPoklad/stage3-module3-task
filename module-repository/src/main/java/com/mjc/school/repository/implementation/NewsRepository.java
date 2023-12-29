package com.mjc.school.repository.implementation;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<News, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> readAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        return entityManager.createQuery(criteriaQuery.select(root)).getResultList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return Optional.ofNullable(entityManager.find(News.class, id));
    }

    public List<News> readByAuthorName(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        Join<News, Author> join = root.join("author");
        criteriaQuery.where(criteriaBuilder.equal(join.get("name"), name));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<News> readByTitle(String title){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("title"), "%"+title+"%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<News> readByContent(String content){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("content"), "%"+content+"%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    public List<News> readByTagIds(List<Long> tagIds){
        TypedQuery<News> typedQuery = entityManager.createNamedQuery("findAllNewsByTagIds", News.class);
        typedQuery.setParameter("ids", tagIds);
        typedQuery.setParameter("numberTags", (long) tagIds.size());
        return typedQuery.getResultList();
    }

    public List<News> readByTagNames(List<String> tagNames){
        TypedQuery<News> typedQuery = entityManager.createNamedQuery("findAllNewsByTagNames", News.class);
        typedQuery.setParameter("names", tagNames);
        typedQuery.setParameter("numberTags", (long) tagNames.size());
        return typedQuery.getResultList();
    }

    @Override
    public News create(News entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public News update(News entity) {
        News news = entityManager.find(News.class, entity.getId());
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setLastUpdateDate(entity.getLastUpdateDate());
        news.setAuthor(entity.getAuthor());
        news.setTags(entity.getTags());
        return news;
    }

    @Override
    public boolean deleteById(Long id) {
        News news = entityManager.find(News.class, id);
        if(news != null){
            entityManager.remove(news);
            return true;
        }
        return false;
    }
}