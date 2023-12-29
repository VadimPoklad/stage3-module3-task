package com.mjc.school.service.implementation;

import com.mjc.school.service.annatation.Size;
import com.mjc.school.service.implementation.Dto.request.Request;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Validator {
    public void validateInput(Request dtoRequest) {
        try {
            Field[] fields = dtoRequest.getClass().getDeclaredFields();
            for (Field field : fields) {
                Size annotation = field.getAnnotation(Size.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    String str = (String) field.get(dtoRequest);
                    if (str.length() < annotation.min() || str.length() > annotation.max()) {
                        throw new IllegalArgumentException("Field: \"" +
                                field.getName() +
                                "\" illegal size min = " + annotation.min() +
                                " max = " + annotation.max());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
