package by.barsuk.goods.repository;

import by.barsuk.goods.model.TypeMetal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMetalRepository extends JpaRepository<TypeMetal, Long> {

}
