package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Response;
import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaRef;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiResponseTransformer implements Transformer<Pair<String, ApiResponse>, Response> {
    private final MediaTypeTransformer mediaTypeTransformer;

    @Override
    public Response transform(Pair<String, ApiResponse> from) {
        Set<Map.Entry<String, MediaType>> entries = from.getValue().getContent().entrySet();
        Collection<SchemaRef> schemaRefs = entries.stream().map(e -> new ImmutablePair<>(e.getKey(), e.getValue())).map(mediaTypeTransformer::transform).collect(toList());
        return new Response(from.getKey(), schemaRefs);
    }
}