package itsum.study.posts.repository;

import itsum.study.posts.dto.PostsResponseDto;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    // Page<PostsResponseDto> getReadAllPost(Pageable pageable, String articleFlag);

    Slice<PostsResponseDto> findAllPostPageableByOrderByCreatedAtDesc(String keyword,Pageable pageable);

}