package itsum.study.posts.repository;

import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsResponseDto;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {


    Slice<PostsResponseDto> findByTitleOrderByCreatedAtDesc( String keyword, Pageable pageable);

}