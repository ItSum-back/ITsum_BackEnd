package itsum.study.comment.repository;

import itsum.study.comment.domain.Comment;
import itsum.study.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
