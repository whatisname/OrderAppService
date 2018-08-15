package com.xxy.ordersystem.constantConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author X
 * @package com.xxy.ordersystem.constantConfig
 * @date 8/15/2018 12:58 AM
 */
@Data
@ConfigurationProperties(prefix = "project-url")
@Component
public class ProjectUrlConfig {
    private String os;
}
