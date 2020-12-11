package com.example.httpverbs;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpVerbsModel implements Serializable {
    @Id
    private Long id;

    private String name;

}
