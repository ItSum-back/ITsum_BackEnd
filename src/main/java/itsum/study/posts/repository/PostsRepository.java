package itsum.study.posts.repository;

import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long post_id);
    void deleteById(Long post_id);
}
