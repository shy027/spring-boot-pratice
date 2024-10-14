package top.shy.zhihu.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.shy.zhihu.api.entity.Special;

import java.util.List;

/**
 * @author 15331
 */

public interface SpecialMapper {
    List<Special> selectAll();
    List<Special> selectByPage(int limit,int offset);
}
