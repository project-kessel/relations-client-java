package org.project_kessel.clients;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ChannelManagerTest {
    @Test
    void shouldSelectSameManager() {
        ChannelManager channelManager = ChannelManager.getInstance("myChannelManangerKey");
        ChannelManager channelManager2 = ChannelManager.getInstance("myChannelManangerKey");

        assertEquals(channelManager, channelManager2);
    }

    @Test
    void shouldNotSelectSameManager() {
        ChannelManager channelManager = ChannelManager.getInstance("myChannelManangerKey");
        ChannelManager channelManager2 = ChannelManager.getInstance("anotherChannelManagerKey");

        assertNotEquals(channelManager, channelManager2);
    }
}
