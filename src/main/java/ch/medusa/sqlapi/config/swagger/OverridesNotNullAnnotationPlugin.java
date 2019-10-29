package ch.medusa.sqlapi.config.swagger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.schema.NotNullAnnotationPlugin;

@Component
@Order
public class OverridesNotNullAnnotationPlugin extends NotNullAnnotationPlugin {
}
