package org.project_kessel.relations.client;

import static org.junit.jupiter.api.Assertions.fail;

import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.common.MapBackedConfigSource;
import java.util.HashMap;
import org.junit.jupiter.api.Test;


class RelationsConfigTest {

    @Test
    void canLoadBasicConfig() {
        /* Should always be able to build a RelationsConfig from a ConfigSource with just a target url (i.e. minimal
         * config for now). Also tests whether the mapping annotations in RelationsConfig are valid beyond static type
         * checking. */
        try {
            new SmallRyeConfigBuilder()
                    .withSources(new MapBackedConfigSource("test", new HashMap<>(), Integer.MAX_VALUE) {
                                @Override
                                public String getValue(String propertyName) {
                                    if ("relations-api.target-url".equals(propertyName)) {
                                        return "http://localhost:8080";
                                    }
                                    return null;
                                }
                            }
                    )
                    .withMapping(RelationsConfig.class)
                    .build().getConfigMapping(RelationsConfig.class);
        } catch (Exception e) {
            fail("Generating a config objective with minimal config should not fail.");
        }
    }

}
