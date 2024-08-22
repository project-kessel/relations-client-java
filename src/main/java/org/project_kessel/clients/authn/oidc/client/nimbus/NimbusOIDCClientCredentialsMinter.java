package org.project_kessel.clients.authn.oidc.client.nimbus;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.Issuer;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsAuthenticationConfig;
import org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsMinter;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation pulled in by reflection in Vanilla java and registered for reflection if Quarkus native is used.
 */
@RegisterForReflection
public class NimbusOIDCClientCredentialsMinter extends OIDCClientCredentialsMinter {
    @Override
    public BearerHeader authenticateAndRetrieveAuthorizationHeader(OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig config) throws OIDCClientCredentialsMinterException {
        Issuer issuer = new Issuer(config.issuer());
        ClientID clientID = new ClientID(config.clientId());
        Secret clientSecret = new Secret(config.clientSecret());
        Optional<Scope> scope = config.scope().map(Scope::new);
        AuthorizationGrant clientGrant = new ClientCredentialsGrant();

        try {
            OIDCProviderMetadata providerMetadata = OIDCProviderMetadata.resolve(issuer);
            URI tokenEndpoint = providerMetadata.getTokenEndpointURI();
            ClientAuthentication clientAuth = new ClientSecretBasic(clientID, clientSecret);
            // Make the token request
            TokenRequest request;
            if(scope.isPresent()) {
                request = new TokenRequest(tokenEndpoint, clientAuth, clientGrant, scope.get());
            } else {
                request = new TokenRequest(tokenEndpoint, clientAuth, clientGrant);
            }

            TokenResponse tokenResponse = OIDCTokenResponseParser.parse(request.toHTTPRequest().send());
            if (!tokenResponse.indicatesSuccess()) {
                TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
                String code = errorResponse.getErrorObject().getCode();
                String message = errorResponse.getErrorObject().getDescription();
                throw new OIDCClientCredentialsMinterException(
                        "Error requesting token from endpoint. TokenErrorResponse: code: " + code + ", message: " + message);
            }

            OIDCTokenResponse successResponse = (OIDCTokenResponse)tokenResponse.toSuccessResponse();
            BearerAccessToken bearerAccessToken = successResponse.getOIDCTokens().getBearerAccessToken();

            // Capture expiry if its exists in the token
            long lifetime = bearerAccessToken.getLifetime();
            Optional<LocalDateTime> expiryTime = getExpiryDateFromExpiresIn(lifetime);

            return new BearerHeader(bearerAccessToken.toAuthorizationHeader(), expiryTime);
        }
        catch(IOException | GeneralException e) {
            throw new OIDCClientCredentialsMinterException("Failed to retrieve and parse OIDC well-known configuration from provider.", e);
        }
    }
}
