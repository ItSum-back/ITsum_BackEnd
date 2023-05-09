package itsum.study;

import itsum.study.posts.common.SliceResult;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsResponseDto;
import itsum.study.posts.repository.PostRepositoryImpl;
import itsum.study.posts.repository.PostsRepository;
import itsum.study.posts.service.PagingResponseService;
import itsum.study.posts.service.PostsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@SpringBootTest
class ItsumApplicationTests {


	@Autowired
	PostsRepository postsRepository ;
	PagingResponseService responseService;
	PostsService postsService;



	@Test
	void contextLoads() {
	}


	@Test
	void sliceTest(){

		postsRepository.save(new Post(5,"가"));
		postsRepository.save(new Post(6,"나"));
		postsRepository.save(new Post(7,"다"));
		postsRepository.save(new Post(8,"라"));
		postsRepository.save(new Post(9,"마가"));
		postsRepository.save(new Post(10,"가바"));
		postsRepository.save(new Post(11,"사"));
		postsRepository.save(new Post(12,"가"));
		postsRepository.save(new Post(13,"자"));
		postsRepository.save(new Post(14,"차"));
		postsRepository.save(new Post(15,"카"));


		SliceResult<PostsResponseDto> result = responseService.getSliceResult(
				postsService.findPostAllByCreatedAtDesc("",PageRequest.of(0, 2)));

		for(PostsResponseDto post : result.getData()){
			System.out.println(post);
		}



	}

}
