package com.example.cosmetic.service.product;

import com.example.cosmetic.dto.IImageDto;
import com.example.cosmetic.dto.IMainDto;
import com.example.cosmetic.model.product.Product;

import java.util.List;

public interface IProductService {
    Product findById(Integer idProduct);

    Product findProductById(Integer idProduct);

    List<IImageDto> findImage(Integer idProduct);

    List<IMainDto> findProductByCategory(Integer idProduct);
}
