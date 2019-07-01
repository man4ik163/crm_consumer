package com.test.crm.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.Date;

@Data
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class Group implements Serializable {

    Long id;

    String name;

    Date createdAt;


}
