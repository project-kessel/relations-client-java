package org.project_kessel.relations.client.authn.oidc;

import static org.project_kessel.relations.client.authn.oidc.client.ClientCredentialsRefreshers.getExpiryDateFromExpiresIn;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.project_kessel.relations.client.Config;
import org.project_kessel.relations.client.Config.OIDCClientCredentialsConfig;
import org.project_kessel.relations.client.authn.oidc.client.ClientCredentialsRefreshers.BearerHeader;
import org.project_kessel.relations.client.authn.oidc.client.ClientCredentialsRefreshers.OIDCClientCredentialsMinterException;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.OAuth2CredentialsWithRefresh;
import com.nimbusds.oauth2.sdk.AuthorizationGrant;
import com.nimbusds.oauth2.sdk.ClientCredentialsGrant;
import com.nimbusds.oauth2.sdk.GeneralException;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenErrorResponse;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.Issuer;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;

public class NimbusClientCredentialsGrantRefresher implements OAuth2CredentialsWithRefresh.OAuth2RefreshHandler {
  final Config.OIDCClientCredentialsConfig config;

  public NimbusClientCredentialsGrantRefresher(OIDCClientCredentialsConfig config) {
    this.config = config;
  }

  @Override
  public AccessToken refreshAccessToken() throws IOException {
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
      if (scope.isPresent()) {
        request = new TokenRequest(tokenEndpoint, clientAuth, clientGrant, scope.get());
      } else {
        request = new TokenRequest(tokenEndpoint, clientAuth, clientGrant);
      }

      TokenResponse tokenResponse = OIDCTokenResponseParser.parse(request.toHTTPRequest().send());
      if (!tokenResponse.indicatesSuccess()) {
        TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
        String code = errorResponse.getErrorObject().getCode();
        String message = errorResponse.getErrorObject().getDescription();
        throw new RuntimeException(
            "Error requesting token from endpoint. TokenErrorResponse: code: " + code + ", message: " + message);
      }

      OIDCTokenResponse successResponse = (OIDCTokenResponse) tokenResponse.toSuccessResponse();
      BearerAccessToken bearerAccessToken = successResponse.getOIDCTokens().getBearerAccessToken();

      // Capture expiry if its exists in the token
      long lifetime = bearerAccessToken.getLifetime();
      Date expiryTime = Date.from(Instant.now().plusSeconds(lifetime));

      return new AccessToken(bearerAccessToken.toAuthorizationHeader(), expiryTime);
    } catch (IOException | GeneralException e) {
      throw new RuntimeException(
          "Failed to retrieve and parse OIDC well-known configuration from provider.", e);
    }
  }
}

class Test {
  static void main() {

  }
}