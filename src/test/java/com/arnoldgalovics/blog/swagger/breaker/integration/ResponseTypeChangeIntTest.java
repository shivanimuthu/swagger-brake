package com.arnoldgalovics.blog.swagger.breaker.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.response.ResponseTypeAttributeRemovedBreakingChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResponseTypeChangeIntTest extends AbstractSwaggerBreakerTest {
    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenExistingAttributeRemoved() {
        // given
        String oldApiPath = "response/attributeremoved/petstore.yaml";
        String newApiPath = "response/attributeremoved/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange expected = new ResponseTypeAttributeRemovedBreakingChange("Pet", "category");
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next()).isEqualTo(expected);
    }

    @Test
    public void testResponseTypeChangeIsNotBreakingChangeWhenDifferentTypeIsUsedButSameAttributes() {
        // given
        String oldApiPath = "response/differenttypesameattributes/petstore.yaml";
        String newApiPath = "response/differenttypesameattributes/petstore_v2.yaml";
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(0);
    }

    @Test
    public void testResponseTypeChangeIsBreakingChangeWhenDifferentTypeIsUsedWithDifferentAttributes() {
        // given
        String oldApiPath = "response/differenttypesdifferentattributes/petstore.yaml";
        String newApiPath = "response/differenttypesdifferentattributes/petstore_v2.yaml";
        ResponseTypeAttributeRemovedBreakingChange expected = new ResponseTypeAttributeRemovedBreakingChange("Pet", "category");
        // when
        Collection<BreakingChange> result = underTest.execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next()).isEqualTo(expected);
    }
}