package io.redskap.swagger.brake.integration.nobreakingchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RefRequestBodyNoBreakingIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testNoBreakingChangeWorksCorrectly() {
        // given
        String oldApiPath = "nobreakingchange/refrequestbody/schema.yaml";
        String newApiPath = "nobreakingchange/refrequestbody/schema_v2.yaml";
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).isEmpty();
    }
}
