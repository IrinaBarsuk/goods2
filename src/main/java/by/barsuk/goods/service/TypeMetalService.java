package by.barsuk.goods.service;

import by.barsuk.goods.model.TypeMetal;
import by.barsuk.goods.repository.TypeMetalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeMetalService {

    private final TypeMetalRepository typeMetalRepository;

    public TypeMetalService(TypeMetalRepository typeMetalRepository) {
        this.typeMetalRepository = typeMetalRepository;
    }

    public List<TypeMetal> findAll() {
        return typeMetalRepository.findAll();
    }

}
