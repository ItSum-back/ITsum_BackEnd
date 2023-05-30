package itsum.study.comment.repository;

import itsum.study.comment.domain.Comment;
import itsum.study.comment.dto.CommentListResponseDto;
import itsum.study.comment.dto.ReCommentListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CommentRepositoryCustom {
    Slice<CommentListResponseDto> findAllCommentsOrderByCreatedAtDesc(Long postId, Pageable pageable);
    Slice<ReCommentListResponseDto> findAllReCommentsOrderByCreatedAtDesc(Long parentId, Pageable pageable);
}
