package itsum.study.utils.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PagingDto {

    @Data
    public class PagingDTO {

        private Long id;
        private String title;
        private String createdBy;
        private LocalDateTime createdTime;

        public PagingDTO(Long id, String title, String createdBy, LocalDateTime createdTime) {
            this.id = id;
            this.title = title;
            this.createdBy = createdBy;
            this.createdTime = createdTime;
        }
    }

}
