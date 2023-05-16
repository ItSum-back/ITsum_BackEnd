package itsum.study.posts.dto;

import itsum.study.posts.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsListResponseDto {


        private String title;
        private String contents;
        private String positionList;
        private String techSkill;
        private String meetingWay;
        private int view;
        // 작성자
        private String members;
        //모집 마감일
        private LocalDateTime deadline;


        public PostsListResponseDto(Post entity) {

            this.title = entity.getTitle();
            this.contents = entity.getContents();
            this.view = entity.getView();
            this.positionList = entity.getPositionList();
            this.techSkill = entity.getTechSkill();
            this.meetingWay = entity.getMeetingWay();
            this.members = entity.getMembers();
            this.deadline = entity.getDeadline();
        }

      public static ArrayList<PostsListResponseDto> toPostListResponse(List<Post> fetch) {

        ArrayList<PostsListResponseDto> responseDtoList = new ArrayList<>();

        for(Post p : fetch){
            responseDtoList.add(new PostsListResponseDto(p));
        }
        return responseDtoList;
    }
}
