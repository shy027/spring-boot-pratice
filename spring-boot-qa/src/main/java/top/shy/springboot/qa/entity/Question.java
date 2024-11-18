package top.shy.springboot.qa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    private Long qId;
    private Long userId;
    private String content;
    private String time;
    private Integer deleted;
    private String userName;
}
