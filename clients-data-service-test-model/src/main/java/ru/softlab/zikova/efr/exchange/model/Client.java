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
 * Данные о клиенте
 */
@SuppressWarnings("all")
@Validated
public class Client implements LoggableEntity {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("surname")
    private String surname = null;

    @JsonProperty("isActive")
    private Boolean isActive = null;


    /**
     * Создает пустой экземпляр класса
     */
    public Client() {}

    /**
     * Создает экземпляр класса
     * 
     * @param id Внутренний идентификатор клиента
     * @param name Имя клиента
     * @param surname Фамилия клиента
     * @param isActive Актуальность
     */
    public Client(Long id, String name, String surname, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
    }

    /**
     * Внутренний идентификатор клиента
     *
     * @return Внутренний идентификатор клиента
     */
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Имя клиента
     *
     * @return Имя клиента
     */
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Фамилия клиента
     *
     * @return Фамилия клиента
     */
    


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * Актуальность
     *
     * @return Актуальность
     */
    


    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
        Client client = (Client) o;
        return Objects.equals(this.id, client.id) &&
                Objects.equals(this.name, client.name) &&
                Objects.equals(this.surname, client.surname) &&
                Objects.equals(this.isActive, client.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, isActive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Client {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
        sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
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
