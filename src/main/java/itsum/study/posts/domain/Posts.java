package itsum.study.posts.domain;

import itsum.study.members.domain.Members;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    private String title;
    private String contents;
    private int view;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private List<Position> positionList ;

    private int personnel;
    private String techSkill;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private List<MeetingWay> meetingWays ;


    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime addDate;//created

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime editDate;//modified

    @ManyToOne
    @JoinColumn(name="user_id")
    private Members members;



}
