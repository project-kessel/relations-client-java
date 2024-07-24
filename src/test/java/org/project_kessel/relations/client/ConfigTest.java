package org.project_kessel.relations.client;

import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;

import io.smallrye.config.common.MapBackedConfigSource;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ConfigTest {

    @Test
    public void canLoadBasicConfig() {
        /* Should always be able to build a Config from a ConfigSource with just a target url (i.e. minimal config for
         * now). Also tests whether the mapping annotations in Config are valid beyond static type checking. */
        SmallRyeConfig config = new SmallRyeConfigBuilder()
                .withSources(new MapBackedConfigSource("test", new HashMap<>(), Integer.MAX_VALUE) {
                                 @Override
                                 public String getValue(String propertyName) {
                                     if("relations-api.target-url".equals(propertyName)) {
                                         return "http://localhost:8080";
                                     }
                                     return null;
                                 }
                             }
                )
                .withMapping(Config.class)
                .build();
    }

}
