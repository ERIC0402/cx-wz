package com.cx.wz.event.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.wz.event.entity.EventEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EventMapper extends BaseMapper<EventEntity> {

}
