package io.michel.keygenerator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "generated_keys")
public class Key {
    @Id
    private String hash;

    @Column(columnDefinition = "boolean default false not null")
    private Boolean used = false;

    @CreatedDate
    private Date creationDate;
}
