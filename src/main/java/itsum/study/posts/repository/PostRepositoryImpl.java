package itsum.study.posts.repository;

import static itsum.study.posts.domain.QPost.post;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public Slice<PostsResponseDto> findAllPostPageableByOrderByCreatedAtDesc( String keyword, Pageable pageable) {
        JPAQuery<Post> productQuery= queryFactory
                .selectFrom(post)
                .where(keywordContains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1); //limit보다 한 개 더 들고온다.
        for(Sort.Order o: pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(post.getType(),post.getMetadata());
            productQuery.orderBy(new OrderSpecifier(o.isAscending()? Order.ASC: Order.DESC,pathBuilder.get(o.getProperty())));
        }
        List<PostsResponseDto> content=new ArrayList<>(PostsResponseDto.toPostListResponse(productQuery.fetch()));
        boolean hasNext =false;
        //마지막 페이지는 사이즈가 항상 작다.
        if(content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }
    //동적 쿼리를 위한 BooleanExpression
    private BooleanExpression keywordContains(String keyword) {
        return ObjectUtils.isEmpty(keyword) ? null : post.title.contains(keyword);
    }
}
