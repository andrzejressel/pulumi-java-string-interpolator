package pl.andrzejressel.pulumi;

import com.pulumi.core.Output;

public class PulumiStringTemplate {
    public static StringTemplate.Processor<Output<String>, RuntimeException> PULUMI = StringTemplate.Processor.of(
            (template) -> {
                var outputs = template
                        .values()
                        .stream()
                        .map(maybeOutput ->
                                switch (maybeOutput) {
                                    case Output<?> output -> (Output<Object>) output;
                                    default -> Output.of(maybeOutput);
                                }
                        )
                        .toList();

                return Output.all(outputs)
                        .applyValue((values) ->
                                StringTemplate.interpolate(template.fragments(), values)
                        );
            }
    );
}
