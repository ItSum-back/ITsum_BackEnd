package itsum.study.posts.repository;

import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long>,PostRepositoryCustom {
    Post findPostById(Long post_id);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

}
