package com.test.crm.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class Product implements Serializable {

    Long id;

    String name;

    Date createdAt;

    String article;

    Group groupId;

}
