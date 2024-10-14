package top.shy.springboot.database.mapper;

import top.shy.springboot.database.entity.Special;

import java.util.List;

public interface SpecialMapper {
    List<Special> findAll();
}
