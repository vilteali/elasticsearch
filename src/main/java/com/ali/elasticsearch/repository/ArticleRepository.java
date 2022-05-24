package com.ali.elasticsearch.repository;

import com.ali.elasticsearch.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends GenericElasticsearchRepository<Article> {

    List<Article> findByTitle(String title);

}
