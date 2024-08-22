package org.project_kessel.clients.authn.oidc.client;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsMinter.getExpiryDateFromExpiresIn;

class OIDCClientCredentialsMinterTest {

    @Test
    void testCreateDefaultMinter() {
        Class<?> defaultMinterClass = OIDCClientCredentialsMinter.getDefaultMinterImplementation();
        try {
            var minter = OIDCClientCredentialsMinter.forClass(defaultMinterClass);
            assertInstanceOf(defaultMinterClass, minter);
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
            fail("Creating minter from default implementation name should not throw an OIDCClientCredentialsMinterException");
        }
    }

    @Test
    void testCreateMinterFromClass() {
        Class<?> testMinterClass = TestMinter.class;
        try {
            var minter = OIDCClientCredentialsMinter.forClass(testMinterClass);
            assertInstanceOf(testMinterClass, minter);
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
            fail("Creating minter from test implementation name should not throw an OIDCClientCredentialsMinterException");
        }
    }

    @Test
    void testCreateMinterFromName() {
        String testMinterName = TestMinter.class.getName();
        try {
            OIDCClientCredentialsMinter.forName(testMinterName);
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
            fail("Creating minter from test implementation name should not throw an OIDCClientCredentialsMinterException");
        }
    }

    @Test
    void testCreateMinterFromFakeImplNameThrowsException() {
        String defaultMinterName = "absolutely.not.a.valid.Implementation";
        try {
            OIDCClientCredentialsMinter.forName(defaultMinterName);
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
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
        var bearerHeader = new OIDCClientCredentialsMinter.BearerHeader("header", someTimeInTheFuture);
        assertFalse(bearerHeader.isExpired());

        Optional<LocalDateTime> someTimeInThePast = Optional.of(LocalDateTime.now().minusSeconds(10000));
        bearerHeader = new OIDCClientCredentialsMinter.BearerHeader("header", someTimeInThePast);
        assertTrue(bearerHeader.isExpired());

        Optional<LocalDateTime> noExpiryTime = Optional.empty();
        bearerHeader = new OIDCClientCredentialsMinter.BearerHeader("header", noExpiryTime);
        assertTrue(bearerHeader.isExpired());
    }

    static class TestMinter extends OIDCClientCredentialsMinter {
        public TestMinter() {
        }

        @Override
        public BearerHeader authenticateAndRetrieveAuthorizationHeader(OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig clientConfig) throws OIDCClientCredentialsMinterException {
            return null;
        }
    }
}
