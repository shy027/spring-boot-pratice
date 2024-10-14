package top.shy.springboot.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {
    private Long sectionId;
    private Long specialId;
    private String sectionTitle;

}
