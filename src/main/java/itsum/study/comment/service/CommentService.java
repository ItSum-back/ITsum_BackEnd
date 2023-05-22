package itsum.study.comment.service;

import itsum.study.comment.domain.Comment;
import itsum.study.comment.dto.CommentsCreateRequestDto;
import itsum.study.comment.repository.CommentRepository;
import itsum.study.posts.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentsRepository;

    @Transactional
    public Long saveComments(Long post_id, CommentsCreateRequestDto commentsCreateRequestDto) {
        return commentsRepository.save(commentsCreateRequestDto.toEntity(post_id)).getId();
    }

    @Transactional
    public Long updateComments(Long commentId, String updateContents) {

        Comment comment = commentsRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id= "+commentId));;
        comment.setContents(updateContents);

        return commentsRepository.save(comment).getId();
    }

    @Transactional
    public Long delete(Long commentId) {
        commentsRepository.deleteById(commentId);
        return commentId;
    }
}
