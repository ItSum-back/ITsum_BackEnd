package itsum.study;

import itsum.study.posts.common.SliceResult;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsListResponseDto;
import itsum.study.posts.dto.PostsResponseDto;
import itsum.study.posts.repository.PostsRepository;
import itsum.study.posts.service.PagingResponseService;
import itsum.study.posts.service.PostsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ItsumApplicationTests {


	@Autowired
	PostsRepository postsRepository ;
	@Autowired
	PagingResponseService responseService;





	@Test
	void contextLoads() {
	}


	@Test
	void sliceTest(){

		//postsRepository.save(new Post(16, "모집합니다. 백엔드","itsum의 백엔드 2명을 모집합니다." , "java,Spring", "offline", "백엔드"));
		//postsRepository.save(new Post(17, "모집합니다. 프론트","hola 프론트 2명을 모집합니다." , "react", "offline", "프론트"));
		//postsRepository.save(new Post(18, "공모전 참여할 개발자 모집합니다. 프론트","공모전 프론트 2명을 모집합니다." , "react", "offline", "프론트"));


		Slice<PostsListResponseDto> slice= postsRepository.findAllPostsOrderByCreatedAtDesc("모집합니다","백엔드","","","",1L,PageRequest.of(0,1));

		for (PostsListResponseDto item : slice.getContent()) {
			System.out.println("제목:"+item.getTitle());
		}

		ResponseEntity<SliceResult<PostsListResponseDto>> response  = new ResponseEntity<>(responseService.getSliceResult(slice), HttpStatus.OK);

		System.out.println(response.getBody());





	}

}
