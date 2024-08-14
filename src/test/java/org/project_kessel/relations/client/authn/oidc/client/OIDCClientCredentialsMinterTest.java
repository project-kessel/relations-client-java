package org.project_kessel.relations.client.authn.oidc.client;

import org.junit.jupiter.api.Test;
import org.project_kessel.relations.client.Config;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.project_kessel.relations.client.authn.oidc.client.ClientCredentialsRefreshers.getExpiryDateFromExpiresIn;

class OIDCClientCredentialsMinterTest {

    @Test
    void testCreateDefaultMinter() {
        Class<?> defaultMinterClass = ClientCredentialsRefreshers.getDefaultMinterImplementation();
        try {
            var minter = ClientCredentialsRefreshers.forClass(defaultMinterClass);
            assertInstanceOf(defaultMinterClass, minter);
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            fail("Creating minter from default implementation name should not throw an OIDCClientCredentialsMinterException");
        }
    }

    @Test
    void testCreateMinterFromClass() {
        Class<?> testMinterClass = TestMinter.class;
        try {
            var minter = ClientCredentialsRefreshers.forClass(testMinterClass);
            assertInstanceOf(testMinterClass, minter);
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            fail("Creating minter from test implementation name should not throw an OIDCClientCredentialsMinterException");
        }
    }

    @Test
    void testCreateMinterFromName() {
        String testMinterName = TestMinter.class.getName();
        try {
            ClientCredentialsRefreshers.forName(testMinterName);
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            fail("Creating minter from test implementation name should not throw an OIDCClientCredentialsMinterException");
        }
    }

    @Test
    void testCreateMinterFromFakeImplNameThrowsException() {
        String defaultMinterName = "absolutely.not.a.valid.Implementation";
        try {
            ClientCredentialsRefreshers.forName(defaultMinterName);
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            return;
        }
        fail("Creating minter from not existent implementation name should throw an OIDCClientCredentialsMinterException");
    }

    @Test
    void testGetExpiryDateFromExpiresInLongLived() {
        LocalDateTime someTimeBefore = LocalDateTime.now().plusSeconds(9000);
        LocalDateTime someTimeAfter = LocalDateTime.now().plusSeconds(11000);
        Optional<LocalDateTime> expiryDate = getExpiryDateFromExpiresIn(10000);
        assertTrue(expiryDate.isPresent());
        assertTrue(someTimeBefore.isBefore(expiryDate.get()));
        assertTrue(someTimeAfter.isAfter(expiryDate.get()));
    }

    @Test
    void testGetAbsentExpiryDateFromExpiresInShortLived() {
        Optional<LocalDateTime> expiryDate = getExpiryDateFromExpiresIn(0);
        assertTrue(expiryDate.isEmpty());
    }

    @Test
    void bearerHeaderExpiryScenarios() {
        Optional<LocalDateTime> someTimeInTheFuture = Optional.of(LocalDateTime.now().plusSeconds(10000));
        var bearerHeader = new ClientCredentialsRefreshers.BearerHeader("header", someTimeInTheFuture);
        assertFalse(bearerHeader.isExpired());

        Optional<LocalDateTime> someTimeInThePast = Optional.of(LocalDateTime.now().minusSeconds(10000));
        bearerHeader = new ClientCredentialsRefreshers.BearerHeader("header", someTimeInThePast);
        assertTrue(bearerHeader.isExpired());

        Optional<LocalDateTime> noExpiryTime = Optional.empty();
        bearerHeader = new ClientCredentialsRefreshers.BearerHeader("header", noExpiryTime);
        assertTrue(bearerHeader.isExpired());
    }

    static class TestMinter extends ClientCredentialsRefreshers {
        public TestMinter() {
        }

        @Override
        public BearerHeader authenticateAndRetrieveAuthorizationHeader(Config.OIDCClientCredentialsConfig clientConfig)
                throws OIDCClientCredentialsMinterException {
            return null;
        }
    }
}
