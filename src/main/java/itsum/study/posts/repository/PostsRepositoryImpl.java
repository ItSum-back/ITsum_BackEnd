package itsum.study.posts.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.posts.domain.Post;
import itsum.study.posts.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static itsum.study.posts.domain.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Slice<PostsListResponseDto> findAllPostsOrderByCreatedAtDesc(String title, String contents,
                                                                        String positionList, String techSkill,
                                                                        String meetingWay, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();

        JPAQuery<Post> postQuery = queryFactory
                .selectFrom(post)
                .where( containsTitle(title)
                        ,containsContents(contents)
                        ,containsPosition(positionList)
                        ,containsTechskill(techSkill)
                        ,containsMeetingWay(meetingWay)
                         ,afterSth(post.deadline, now)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1);

        for(Sort.Order o : pageable.getSort()){
            PathBuilder pathBuilder = new PathBuilder(post.getType(),post.getMetadata());
            postQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC:Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<PostsListResponseDto> content = new ArrayList<>(PostsListResponseDto.toPostListResponse(postQuery.fetch()));
        boolean hasNext = false;

        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content,pageable,hasNext);
    }

    private  BooleanExpression containsMeetingWay(String meetingWay) {
        return ObjectUtils.isEmpty(meetingWay) ? null : post.meetingWay.contains(meetingWay);
    }

    private  BooleanExpression  containsTechskill(String techSkill) {
        return ObjectUtils.isEmpty(techSkill) ? null : post.techSkill.contains(techSkill);
    }

    private  BooleanExpression  containsPosition(String positionList) {
        return ObjectUtils.isEmpty(positionList) ? null : post.positionList.contains(positionList);

    }

    private  BooleanExpression  containsContents(String contents) {
        return ObjectUtils.isEmpty(contents) ? null : post.contents.contains(contents);

    }

    private  BooleanExpression  containsTitle(String title) {
        return ObjectUtils.isEmpty(title) ? null : post.title.contains(title);

    }

    private BooleanExpression afterSth(DateTimePath<LocalDateTime> deadline, LocalDateTime compare) {
        if (Objects.isNull(deadline) || Objects.isNull(compare)) {
            return null;
        }
        return deadline.after(compare);
    }
}
