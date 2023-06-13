package itsum.study.comment.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import itsum.study.comment.domain.Comment;
import itsum.study.comment.dto.CommentListResponseDto;
import itsum.study.comment.dto.ReCommentListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;
import static itsum.study.comment.domain.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Slice<CommentListResponseDto> findAllCommentsOrderByCreatedAtDesc(Long Id, Pageable pageable) {

        JPAQuery<Comment> Query = queryFactory
                .selectFrom(comment)
                .where( containsPostId(Id))
                .where(comment.parent.id.isNull())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1);

        for(Sort.Order o : pageable.getSort()){
            PathBuilder pathBuilder = new PathBuilder(comment.getType(),comment.getMetadata());
            Query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC:Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<CommentListResponseDto> content = new ArrayList<>(CommentListResponseDto.toCommentListResponse(Query.fetch()));
        boolean hasNext = false;

        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content,pageable,hasNext);
    }

    private  BooleanExpression containsPostId(Long postId) {
        return ObjectUtils.isEmpty(postId) ? null : comment.post_id.eq(postId);
    }


    @Override
    public Slice<ReCommentListResponseDto> findAllReCommentsOrderByCreatedAtDesc(Long parentId, Pageable pageable) {

        if(parentId == null) {
            return null;
        }
        JPAQuery<Comment> Query = queryFactory
                .selectFrom(comment)
                .where(containsParentId(parentId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1);

        for(Sort.Order o : pageable.getSort()){
            PathBuilder pathBuilder = new PathBuilder(comment.getType(),comment.getMetadata());
            Query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC:Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<ReCommentListResponseDto> content = new ArrayList<>(ReCommentListResponseDto.toReCommentListResponse(Query.fetch()));
        boolean hasNext = false;

        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content,pageable,hasNext);
    }

    private  BooleanExpression containsParentId(Long parentId) {
        return ObjectUtils.isEmpty(parentId) ? null : comment.parent.id.eq(parentId);
    }
}
