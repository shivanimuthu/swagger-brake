package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import io.swagger.v3.oas.models.media.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaTypeTransformer implements Transformer<MediaType, Schema> {
    private final SchemaTransformer schemaTransformer;

    @Override
    public Schema transform(MediaType from) {
        return schemaTransformer.transform(from.getSchema());
    }
}
