package itsum.study.posts.repository;

import itsum.study.posts.dto.PostsResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostsRepositoryCustom {

    //private String title;
   // private String contents;
   // private String positionList ;
    // 인원수는 제거
    //private String techSkill;
    //private String meetingWay ;


    Slice<PostsResponseDto> findAllPostsOrderByCreatedAtDesc(final String title,final String contents, final String positionList,
                                                             final String techSkill, final String meetingWay, Pageable pageable);
}
