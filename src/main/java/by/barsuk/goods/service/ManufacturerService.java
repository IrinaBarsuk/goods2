package by.barsuk.goods.service;

import by.barsuk.goods.model.Manufacturer;
import by.barsuk.goods.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * Вывод всех производителей из базы
     *
     * @return список {@link Manufacturer}
     */
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    /**
     * Поиск производителя по id
     *
     * @param id идентификатор искомого производителя
     * @return {@link Optional} производителя
     */
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    /**
     * Сохранение производителя
     *
     * @param manufacturer сохраняемый производитель
     * @return сохранённый производитель
     */
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    /**
     * Удаление производителя
     *
     * @param id идентификатор удаляемого производителя
     */
    public void deleteById(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
