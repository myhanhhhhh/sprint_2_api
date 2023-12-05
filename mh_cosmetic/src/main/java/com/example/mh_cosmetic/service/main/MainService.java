package com.example.mh_cosmetic.service.main;

import com.example.mh_cosmetic.dto.IMainDto;
import com.example.mh_cosmetic.repository.IMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService implements IMainService {
    @Autowired
    private IMainRepository mainRepository;

    @Override
    public List<IMainDto> getListProduct() {
        return mainRepository.getOutstandingProduct();
    }

    @Override
    public Page<IMainDto> findAllProductByName(Pageable pageable, String searchName, String category) {
        return mainRepository.getAllProductByCategory(pageable,searchName,category );
    }
}
