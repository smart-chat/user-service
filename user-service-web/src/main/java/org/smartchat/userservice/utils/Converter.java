package org.smartchat.userservice.utils;


import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Converter {
    private static final ModelMapper modelMapper = new ModelMapper();

    private Converter() { }

    public static <F, T> T convert(F from, Class<T> convertTo) {
        return modelMapper.map(from, convertTo);
    }

    public static <F, T> List<T> convertFrom(Collection<F> src, Class<T> convertTo) {
        return StreamSupport.stream(src.spliterator(), false)
                .map(m -> convert(m, convertTo))
                .collect(Collectors.toList());
    }
}
