package top.shy.springboot.database.mapper;

import top.shy.springboot.database.entity.Clazz;

public interface ClazzMapper {
    Clazz getClazzById(int clazzId);

    Clazz getClazz(int clazzId);
}
