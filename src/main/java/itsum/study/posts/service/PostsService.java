package itsum.study.posts.service;

import io.jsonwebtoken.Claims;
import itsum.study.auth.dto.AuthResponse;
import itsum.study.auth.jwt.AuthToken;
import itsum.study.auth.jwt.AuthTokenProvider;
import itsum.study.comment.domain.Comment;
import itsum.study.members.domain.Members;
import itsum.study.members.repository.MemberQuerydslRepository;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsCreateRequestDto;
import itsum.study.posts.dto.PostsResponseDto;
import itsum.study.posts.dto.PostsUpdateRequestDto;
import itsum.study.posts.repository.PostsRepository;
import itsum.study.utils.dto.DataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostsService {
    //@Autowired 사용 지양 -> @RequiredArgsConstructor 로 생성되는 생성자로 주입받기 위해 final 붙임.
    private final PostsRepository postsRepository;

    @Transactional
    public Long savePost(PostsCreateRequestDto postsCreateRequestDto) {
        return postsRepository.save(postsCreateRequestDto.toEntity()).getId();
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Post entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Post posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));

        //JPA 의 영속성 컨텍스트 덕분에 entity 객체의 값만 변경하면 자동으로 변경사항 반영
        //따라서 repository에서 update를 하지 않아도 된다.
        posts.update(requestDto.getTitle(), requestDto.getContents(), requestDto.getPositionList(), requestDto.getPersonnel(), requestDto.getTechSkill(),
                requestDto.getMeetingWay(), requestDto.getMembers());

        return id;
    }

   @Transactional
    public Long delete(Long id) {
        Post posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));
       posts.delete();
       return id;
    }

   /* search */
   @Transactional
    public Page<Post> search(Pageable pageable) {
        Page<Post> postsList = postsRepository.findAll(pageable);
        return postsList;
    }


    @Transactional
    public Slice<PostsResponseDto> findPostAllByCreatedAtDesc( String title, String contents,
                                                               String positionList, String techSkill,
                                                               String meetingWay, Pageable pageable) {


       Slice<PostsResponseDto> slice =  postsRepository.findByTitleContaining(title,pageable);

        for(PostsResponseDto dto : slice.getContent()){
            System.out.println("제목:"+dto.getTitle());
        }

        return postsRepository.findAllPostsOrderByCreatedAtDesc( title, contents,
                                                                 positionList,   techSkill,
                                                                 meetingWay, pageable);
    }
}
