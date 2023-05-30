package itsum.study.comment.dto;

import itsum.study.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReCommentListResponseDto {
        private Long id;
        private String contents;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String creatorName;

        public ReCommentListResponseDto(Comment c) {
            this.id = c.getId();
            this.contents = c.getContents();
            this.createdAt = c.getCreatedAt();
            this.modifiedAt = c.getModifiedAt();
            this.creatorName = c.getCreatorName();
        }


        public static ArrayList<itsum.study.comment.dto.ReCommentListResponseDto> toReCommentListResponse(List<Comment> fetch) {

            ArrayList<itsum.study.comment.dto.ReCommentListResponseDto> responseDtoList = new ArrayList<>();

            for(Comment c : fetch){
                responseDtoList.add(new itsum.study.comment.dto.ReCommentListResponseDto(c));
            }
            return responseDtoList;
        }
    }
