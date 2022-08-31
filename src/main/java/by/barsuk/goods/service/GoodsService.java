package by.barsuk.goods.service;

import by.barsuk.goods.model.Goods;
import by.barsuk.goods.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    /**
     * Вывод всех украшений из базы
     *
     * @return список {@link Goods}
     */
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    /**
     * Поиск украшения по id
     *
     * @param id идентификатор искомого украшения
     * @return {@link Optional} украшения
     */
    public Optional<Goods> findById(Long id) {
        return goodsRepository.findById(id);
    }

    /**
     * Вывод всех украшений определенной категории
     *
     * @param id идентификатор категории
     * @return список {@link Goods}
     */
    public List<Goods> findGoodsByCategoryId(Long id) {
        return goodsRepository.findGoodsByCategoryId(id);
    }

    /**
     * Вывод всех украшений из определенного материала
     *
     * @param id идентификатор материала
     * @return список {@link Goods}
     */
    public List<Goods> findGoodsByMetalId(Long id) {
        return goodsRepository.findGoodsByMetalId(id);
    }

    /**
     * Вывод всех товаров определенного производителя
     *
     * @param id идентификатор производителя
     * @return список {@link Goods}
     */
    public List<Goods> findGoodsByManufacturerId(Long id) {
        return goodsRepository.findGoodsByManufacturerId(id);
    }

    /**
     * Поиск украшений по названию
     *
     * @param search поисковая строка
     * @return список {@link Goods}
     */
    public List<Goods> searchByName(String search) {
        return goodsRepository.findByName(search);
    }

    /**
     * Сохранение украшения
     *
     * @param goods сохраняемое украшение
     * @return сохранённое украшение
     */
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    /**
     * Удаление украшения
     *
     * @param id идентификатор удаляемого украшения
     */
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }
}
