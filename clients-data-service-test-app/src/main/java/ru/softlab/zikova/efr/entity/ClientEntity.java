package ru.softlab.zikova.efr.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("client_id")
    private Long id;
    @JsonProperty("client_name")
    private String name;
    @JsonProperty("client_surname")
    private String surname;
    @JsonProperty("client_is_active")
    @Column(name = "is_active")
    private Boolean isActive;

}
