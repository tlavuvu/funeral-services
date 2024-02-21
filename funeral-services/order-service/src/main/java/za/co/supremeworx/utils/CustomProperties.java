package za.co.supremeworx.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "za.co.custom")
@Configuration("customProperties")
@Data
public class CustomProperties {

	private final String inventoryservicesuri;
}
