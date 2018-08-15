package com.xxy.ordersystem.constantConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author X
 * @package com.xxy.ordersystem.constantConfig
 * @date 8/15/2018 5:36 AM
 */
@Data
@ConfigurationProperties(prefix = "cookie-name")
@Component
public class CookieConfig {
    private String name;
}
