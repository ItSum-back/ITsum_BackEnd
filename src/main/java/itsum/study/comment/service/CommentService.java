package itsum.study.comment.service;

import itsum.study.comment.dto.CommentsCreateRequestDto;
import itsum.study.comment.repository.CommentRepository;
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
}
