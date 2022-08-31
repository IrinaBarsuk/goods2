package by.barsuk.goods.repository;

import by.barsuk.goods.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findGoodsByCategoryId(Long id);

    List<Goods> findGoodsByMetalId(Long id);

    List<Goods> findGoodsByManufacturerId(Long id);

    @Query(value = "select g from Goods g where upper(g.name) like upper(concat('%', ?1, '%')) order by g.name")
    List<Goods> findByName(String search);
}
