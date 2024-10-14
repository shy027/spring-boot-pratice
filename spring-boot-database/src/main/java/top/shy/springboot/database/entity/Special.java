package top.shy.springboot.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Special {
    private Long id;
    private String title;
    private String introduction;
    private String banner;
    private Integer viewCount;
    private Integer followersCount;
    private Integer isFollowing;
    private Long updated;
    private List<Section> sections;
}
