package ru.softlab.zikova.efr.service.mapper;

import org.springframework.stereotype.Service;
import ru.softlab.zikova.efr.entity.ProductEntity;
import ru.softlab.zikova.efr.exchange.model.Product;

/**
 * Сервис парсинга сущностей Product, ProductEntity
 *
 * @author vasilenko
 * @since 11.11.2022
 */
@Service
public class ProductMapper {
    /**
     * Метод парсинга ProductEntity в Product
     *
     * @param entity сущность ProductEntity
     * @return сущность Product
     */
    public Product parseProductEntityInProduct(ProductEntity entity) {
        return ((entity == null) ? new Product() : new Product(
                entity.getId(),
                entity.getNumber(),
                entity.getType(),
                entity.getClientId()
        ));
    }


    /**
     * Метод парсинга Product в ProductEntity
     *
     * @param product сущность Product
     * @return сущность ProductEntity
     */
    public ProductEntity parseProductInProductEntity(Product product) {
        return ((product == null) ? ProductEntity.builder().build() : ProductEntity.builder()
                .number(product.getNumber())
                .type(product.getType())
                .clientId(product.getClientId())
                .build());
    }
}
