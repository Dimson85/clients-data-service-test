/*
 * clients-data-service-test
 * Тестовый API
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ru.softlab.zikova.efr.exchange.model;

import com.fasterxml.jackson.annotation.*;
import java.util.Objects;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import ru.softlab.efr.infrastructure.logging.api.model.LoggableEntity;
import ru.softlab.zikova.efr.exchange.model.PublicModelFormatter;

/**
 * Данные о продукте
 */
@SuppressWarnings("all")
@Validated
public class Product implements LoggableEntity {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("number")
    private String number = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("client_id")
    private Integer clientId = null;


    /**
     * Создает пустой экземпляр класса
     */
    public Product() {}

    /**
     * Создает экземпляр класса
     * 
     * @param id Внутренний идентификатор продукта
     * @param number Номер продукта
     * @param type Тип продукта
     * @param clientId ID клиента
     */
    public Product(Long id, String number, String type, Integer clientId) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.clientId = clientId;
    }

    /**
     * Внутренний идентификатор продукта
     *
     * @return Внутренний идентификатор продукта
     */
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Номер продукта
     *
     * @return Номер продукта
     */
    


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * Тип продукта
     *
     * @return Тип продукта
     */
    


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    /**
     * ID клиента
     *
     * @return ID клиента
     */
    


    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String asLogDataString() {
        return PublicModelFormatter.format(this);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(this.id, product.id) &&
                Objects.equals(this.number, product.number) &&
                Objects.equals(this.type, product.type) &&
                Objects.equals(this.clientId, product.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, type, clientId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Product {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
