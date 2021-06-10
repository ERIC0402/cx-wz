package com.cx.wz.product;

import com.cx.wz.product.entity.ProductEntity;
import com.cx.wz.product.service.ProductService;
import com.cx.wz.uitls.CustomizedHttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    protected static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/product-list")
    public List<ProductEntity> login() {
        return productService.selectActiveProduct();
    }

}
