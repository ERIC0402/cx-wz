package com.cx.wz.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.wz.product.entity.ProductEntity;
import com.cx.wz.product.repo.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<ProductEntity> selectActiveProduct() {
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductEntity::getStatus, ProductEntity.Status.ACTIVE);
        return productMapper.selectList(queryWrapper);
    }

}
