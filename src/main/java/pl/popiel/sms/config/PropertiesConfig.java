package pl.popiel.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    private Environment env;

    @Autowired
    public PropertiesConfig(Environment env){
        this.env = env;
    }

    public String getConfigValue(String configKey) {
        return env.getProperty(configKey);
    }
}
