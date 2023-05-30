package itsum.study.comment.service;

import itsum.study.comment.domain.Comment;
import itsum.study.comment.dto.CommentListResponseDto;
import itsum.study.comment.dto.CommentResponseDto;
import itsum.study.comment.dto.CommentsCreateRequestDto;
import itsum.study.comment.dto.ReCommentListResponseDto;
import itsum.study.comment.repository.CommentRepository;
import itsum.study.comment.repository.CommentRepositoryCustom;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsListResponseDto;
import itsum.study.posts.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentsRepository;
    private final CommentRepositoryCustom commentsListRepository;


    @Transactional
    public Slice<CommentListResponseDto> findAllCommentsOrderByCreatedAtDesc(Long id, Pageable pageable) {
        Slice<CommentListResponseDto> slice =  commentsListRepository.findAllCommentsOrderByCreatedAtDesc(id,pageable);
        return slice;
    }

    @Transactional
    public Slice<ReCommentListResponseDto> findAllReCommentsOrderByCreatedAtDesc(Long id, Pageable pageable) {
        Slice<ReCommentListResponseDto> slice =  commentsListRepository.findAllReCommentsOrderByCreatedAtDesc(id,pageable);
        return slice;
    }

    @Transactional
    public Long saveComments(Long post_id, CommentsCreateRequestDto commentsCreateRequestDto) {
        return commentsRepository.save(commentsCreateRequestDto.toEntity(post_id)).getId();
    }

    @Transactional
    public Long updateComments(Long commentId, String updateContents) {

        Comment comment = commentsRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id= "+commentId));
        comment.setContents(updateContents);

        return commentsRepository.save(comment).getId();
    }

    @Transactional
    public Long delete(Long commentId) {
        Comment comment = commentsRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id= "+commentId));
        comment.delete();
        return commentId;
    }
}
