package itsum.study.posts.repository;

import itsum.study.members.domain.Members;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Class : PostsRepository
 * @Description : 게시글 도메인에 대한 레포지토리
 **/
@Repository
public interface PostsRepository extends JpaRepository<Post, Long> , PostsRepositoryCustom {

    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int updateView(@Param("id") Long id);
}
