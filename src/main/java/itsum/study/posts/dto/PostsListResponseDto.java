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
        private Long id;
        private String title;
        private String contents;
        private String positionList;
        private String techSkill;
        private String meetingWay;
        private int personnel;
        private String members;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String category;
        private LocalDateTime projectStartTime;
        private LocalDateTime projectEndTime;
        private LocalDateTime deadline;
        private String contact;
        private int view;
        private String socialId;


        public PostsListResponseDto(Post entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.contents = entity.getContents();
            this.view = entity.getView();
            this.deadline = entity.getDeadline();
            this.positionList = entity.getPositionList();
            this.personnel = entity.getPersonnel();
            this.techSkill = entity.getTechSkill();
            this.meetingWay = entity.getMeetingWay();
            this.members = entity.getMembers();
            this.createdAt = entity.getCreatedAt();
            this.modifiedAt = entity.getModifiedAt();
            this.category = entity.getCategory();
            this.projectStartTime = entity.getProjectStartTime();
            this.projectEndTime = entity.getProjectEndTime();
            this.contact = entity.getContact();
            this.socialId = entity.getSocialId();
        }

      public static ArrayList<PostsListResponseDto> toPostListResponse(List<Post> fetch) {

        ArrayList<PostsListResponseDto> responseDtoList = new ArrayList<>();

        for(Post p : fetch){
            responseDtoList.add(new PostsListResponseDto(p));
        }
        return responseDtoList;
    }
}
