package itsum.study.comment.repository;

import itsum.study.comment.domain.Comment;
import itsum.study.comment.dto.CommentListResponseDto;
import itsum.study.comment.dto.CommentResponseDto;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
