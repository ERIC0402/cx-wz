package com.cx.wz.product.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.wz.product.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper extends BaseMapper<ProductEntity> {

}
