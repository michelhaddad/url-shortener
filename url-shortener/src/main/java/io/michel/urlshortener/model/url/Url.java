package io.michel.urlshortener.model.url;

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
public class Url {
    @Id
    private String hash;

    @Column(nullable = false)
    private String originalUrl;

    @CreatedDate
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;
}
