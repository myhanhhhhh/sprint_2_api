package com.example.cosmetic.service.main;

import com.example.cosmetic.dto.IMainDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMainService {
    List<IMainDto> getListProduct();

    Page<IMainDto> findAllProductByName(Pageable pageable, String searchName, String category);
}
