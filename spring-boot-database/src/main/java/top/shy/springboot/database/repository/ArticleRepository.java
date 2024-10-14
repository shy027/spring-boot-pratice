package top.shy.springboot.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.shy.springboot.database.entity.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
