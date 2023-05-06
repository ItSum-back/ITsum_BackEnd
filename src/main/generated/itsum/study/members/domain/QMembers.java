package itsum.study.members.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMembers is a Querydsl query type for Members
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMembers extends EntityPathBase<Members> {

    private static final long serialVersionUID = -1354359049L;

    public static final QMembers members = new QMembers("members");

    public final itsum.study.utils.domain.QBaseEntity _super = new itsum.study.utils.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<itsum.study.members.enumerate.MemberProvider> memberProvider = createEnum("memberProvider", itsum.study.members.enumerate.MemberProvider.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final EnumPath<itsum.study.auth.enumerate.RoleType> roleType = createEnum("roleType", itsum.study.auth.enumerate.RoleType.class);

    public final StringPath socialId = createString("socialId");

    public QMembers(String variable) {
        super(Members.class, forVariable(variable));
    }

    public QMembers(Path<? extends Members> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMembers(PathMetadata metadata) {
        super(Members.class, metadata);
    }

}

