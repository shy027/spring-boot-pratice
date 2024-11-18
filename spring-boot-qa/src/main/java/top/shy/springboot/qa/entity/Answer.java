package top.shy.springboot.qa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    private Long aId;
    private Long qId;
    private String content;
    private String time;
    private String userName;
}
