package ru.softlab.zikova.efr.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Table(name = "products")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("product_id")
    private Long id;
    @JsonProperty("product_number")
    private String number;
    @JsonProperty("product_type")
    @Column(name = "product_type")
    private String type;
    @JsonProperty("client_id")
    @Column(name = "client_id")
    private Integer clientId;

}
