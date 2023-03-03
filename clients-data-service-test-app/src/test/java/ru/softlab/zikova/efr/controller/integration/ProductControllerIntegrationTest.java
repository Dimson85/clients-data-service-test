package ru.softlab.zikova.efr.controller.integration;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.softlab.efr.test.infrastructure.transport.rest.MockRestRule;
import ru.softlab.zikova.efr.config.IntegrationTestApplicationConfig;
import ru.softlab.zikova.efr.controller.ClientApi;
import ru.softlab.zikova.efr.controller.ProductsApi;
import ru.softlab.zikova.efr.entity.ClientEntity;
import ru.softlab.zikova.efr.entity.ProductEntity;
import ru.softlab.zikova.efr.exchange.model.Product;
import ru.softlab.zikova.efr.repository.ProductRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.softlab.efr.test.infrastructure.transport.rest.matchers.MockRestResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = IntegrationTestApplicationConfig.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Rule
    public MockRestRule mockRestRule;

    @Test
    public void getProductById_success() throws Exception {
        ProductEntity productEntity =
                new ProductEntity(null, "108", "card", 1);
        productRepository.save(productEntity);
        mockRestRule.init()
                .path(ProductsApi.FIND_PRODUCT_BY_ID_PATH)
                .variable("id", productEntity.getId())
                .get(ProductEntity.class)
                .andExpect(status().isOk());
    }

    @Test
    public void saveProduct() throws Exception {
        Product product = new Product(null, "Bob", "Mag", 1);
        Product productSave = getProductSave(product);
        assertNotNull(productSave.getId());
        assertEquals(productSave.getNumber(), product.getNumber());
    }

    @Test
    public void updateProduct() throws Exception {
        Product product = new Product(null, "Bob", "Mag", 1);
        Product productSave = getProductSave(product);
        productSave.setNumber("2000");
        Product productUpdate = (Product) mockRestRule
                .init()
                .setTimeoutInSeconds(60L)
                .path(ProductsApi.UPDATE_PRODUCT_PATH)
                .put(productSave, Product.class)
                .andExpect(status().isOk())
                .andReturnBody();
        assertEquals(productUpdate.getNumber(), "2000");
    }

    @Test
    public void deleteProduct() throws Exception {
        Product product = new Product(null, "Bob", "Mag", 1);
        Product productSave = getProductSave(product);
        assertNotNull(productSave.getId());
        assertEquals(productSave.getNumber(), product.getNumber());

        mockRestRule.init()
                .setTimeoutInSeconds(60L)
                .path(ProductsApi.DELETE_PRODUCT_PATH)
                .variable("id", product.getId())
                .delete()
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindProductByIdNotFound() throws Exception {
        mockRestRule.init()
                .path(ClientApi.FIND_CLIENT_BY_ID_PATH)
                .variable("id", -1)
                .get(ClientEntity.class)
                .andExpect(status().isNotFound());
    }

    private Product getProductSave(Product product) throws Exception {
        return (Product) mockRestRule
                .init()
                .setTimeoutInSeconds(60L)
                .path(ProductsApi.SAVE_PRODUCT_PATH)
                .post(product, Product.class)
                .andExpect(status().isOk())
                .andReturnBody();
    }

    @After
    public void tearDown(){
        productRepository.deleteAll();
    }
}
