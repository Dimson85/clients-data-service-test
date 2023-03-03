package ru.softlab.zikova.efr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.softlab.zikova.efr.controller.ProductsApi;
import ru.softlab.zikova.efr.entity.ProductEntity;
import ru.softlab.zikova.efr.exchange.model.Product;
import ru.softlab.zikova.efr.repository.ClientRepository;
import ru.softlab.zikova.efr.repository.ProductRepository;
import ru.softlab.zikova.efr.service.mapper.ProductMapper;

import java.util.NoSuchElementException;

/**
 * Сервис работы с Products
 *
 * @author vasilenko
 * @since 11.11.2022
 */
@Service
@RequiredArgsConstructor
public class ProductService implements ProductsApi.Delegate {

    /**
     * Конструктор
     *
     * @param productRepository Репозиторий работы с сущностью ProductEntity
     * @param productMapper Сервис преобразования сущностей Product, ProductEntity
     */
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ClientRepository clientRepository;

    /**
     * @param id идентификатор для получения сущности Product
     */
    @Override
    public ResponseEntity<Void> deleteProduct(Long id) throws Exception {
        return ResponseEntity.noContent().build();
    }

    /**
     * Метод поиска сущности Product
     *
     * @param id идентификатор для получения сущности Product
     * @return сущность Product
     */
    @Override
    public ResponseEntity<Product> findProductById(Long id) {
        ProductEntity productEntity = findProductEntityById(id);
        Product product = productMapper.parseProductEntityInProduct(productEntity);
        return ResponseEntity.ok(product);
    }

    /**
     * Метод сохранения сущности Product в БД
     * @param product сущность для сохранения в БД
     * @return сущность Product
     */
    @Override
    public ResponseEntity<Product> saveProduct(Product product) {
        ProductEntity productEntity = productMapper.parseProductInProductEntity(product);
        saveProductEntityInDb(productEntity);
        product.setId(productEntity.getId());
        return ResponseEntity.ok(product);
}

    /**
     * Метод обновления данных сущности Product в БД
     * @param product сущность с обновленными данными
     * @return сущность Product
     */
    @Override
    public ResponseEntity<Product> updateProduct(Product product) {
        setDataInProduct(product);
        return ResponseEntity.ok(product);
    }

    /**
     * Метод обновления данных сущности Product
     *
     * @param product сохраняемая сущность
     */
    private void setDataInProduct(Product product) {
        ProductEntity productEntity = findProductEntityById(product.getId());
        productEntity.setNumber(product.getNumber());
        productEntity.setType(product.getType());
        saveProductEntityInDb(productEntity);
    }

    /**
     * Метод поиска сущности ProductEntity по Id
     *
     * @param id идентификатор для получения сущности Product
     * @return сущность ProductEntity
     */
    private ProductEntity findProductEntityById(Long id) {
        return productRepository.findProductEntitiesById(id)
                .orElseThrow(() -> new NoSuchElementException("Product is empty in DB"));
    }

    /**
     * @param productEntity сущность для сохранения в БД
     */
    private void saveProductEntityInDb(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }
}
