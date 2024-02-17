package pl.andrzejressel.pulumi;

import com.pulumi.core.Output;
import com.pulumi.core.internal.OutputInternal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.andrzejressel.pulumi.PulumiStringTemplate.PULUMI;

class PulumiStringTemplateTest {

    @Test
    void shouldInterpolate() throws Exception {
        // Given
        var one = Output.of("1");
        var two = "2";

        // When
        var combined = PULUMI."\{one} + \{one} = \{two}";
        var result = ((OutputInternal<String>)combined).getDataAsync().get().getValueNullable();

        // Then
        assertEquals("1 + 1 = 2", result);
    }

}