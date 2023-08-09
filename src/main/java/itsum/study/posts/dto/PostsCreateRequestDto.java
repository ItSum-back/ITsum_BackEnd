package itsum.study.posts.dto;

import itsum.study.posts.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsCreateRequestDto {
    private String socialId;
    private String title;
    private String contents;
    private String positionList;
    private int personnel;
    private String techSkill;
    private String meetingWay;
    private String members;
    private String category;

    private String projectStartTime;
    private String projectEndTime;
    private String deadline;
    private String contact;

    public Post toEntity() {
        return Post.builder().title(title).contents(contents).positionList(positionList).personnel(personnel)
                .techSkill(techSkill).meetingWay(meetingWay).members(members)
                .category(category).projectStartTime(makeDate(projectStartTime))
                .projectEndTime(makeDate(projectEndTime)).deadline(makeDate(deadline))
                .contact(contact).socialId(socialId).build();
    }

    private LocalDateTime makeDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalTime localTime = LocalTime.of(0,0);

        return LocalDateTime.of(localDate,localTime);
    }

}
