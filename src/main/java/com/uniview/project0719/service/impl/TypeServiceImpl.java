package com.uniview.project0719.service.impl;

import com.uniview.project0719.repository.TypeRepository;
import com.uniview.project0719.service.TypeService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public ResponseData<?> findAllType() {
        return new ResponseData<>().success(typeRepository.findAll());
    }
}
