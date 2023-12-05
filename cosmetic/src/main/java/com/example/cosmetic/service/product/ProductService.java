package com.example.cosmetic.service.product;

import com.example.cosmetic.dto.IImageDto;
import com.example.cosmetic.dto.IMainDto;
import com.example.cosmetic.model.product.Product;
import com.example.cosmetic.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product findById(Integer idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Product findProductById(Integer idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public List<IImageDto> findImage(Integer idProduct) {
        return productRepository.findImage(idProduct);
    }

    @Override
    public List<IMainDto> findProductByCategory(Integer idProduct) {
        Product product = findProductById(idProduct);
        return productRepository.findProductByCategory(product.getCategory().getId());
    }
}
