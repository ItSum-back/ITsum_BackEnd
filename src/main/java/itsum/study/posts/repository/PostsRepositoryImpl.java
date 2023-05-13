package itsum.study.posts.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static itsum.study.posts.domain.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public Slice<PostsResponseDto> findAllPostsOrderByCreatedAtDesc(String title, String contents,
                                                                    String positionList, String techSkill,
                                                                    String meetingWay, Pageable pageable) {
        JPAQuery<Post> postQuery = queryFactory
                .selectFrom(post)
                .where(
                        containsTitle(title),
                        containsContents(contents),
                        containsPosition(positionList),
                        containsTechskill(techSkill),
                        containsMeetingWay(meetingWay)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1);

        for(Sort.Order o : pageable.getSort()){
            PathBuilder pathBuilder = new PathBuilder(post.getType(),post.getMetadata());
            postQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC:Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<PostsResponseDto> content = new ArrayList<>(PostsResponseDto.toPostListResponse(postQuery.fetch()));
        boolean hasNext = false;

        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content,pageable,hasNext);
    }

    private Predicate containsMeetingWay(String meetingWay) {
    }

    private Predicate containsTechskill(String techSkill) {
        return o;
    }

    private Predicate containsPosition(String positionList) {
        return null;
    }

    private Predicate containsContents(String contents) {
        return null;
    }

    private Predicate containsTitle(String title) {
        return null;
    }
}
