package top.shy.zhihu.api.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.shy.zhihu.api.entity.Special;
import top.shy.zhihu.api.mapper.SpecialMapper;
import top.shy.zhihu.api.service.SpecialService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 15331
 */
@Service
public class SpecialServiceImpl implements SpecialService {
    @Resource
    public SpecialMapper specialMapper;

    @Override
    public List<Special> getAll() {
        List<Special> specials = specialMapper.selectAll();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        specials.forEach(special -> {
            String format = df.format(new Date(Long.parseLong(special.getUpdated() + "000")));
            special.setUpdated(format);
        });
        return specials;
    }
    @Override
    public  List<Special> getByPage(int limit,int offset) {
        return specialMapper.selectByPage(limit, offset);
    }
}
