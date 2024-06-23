package com.hb.example.auth_grant.config.auth;

import com.hb.example.auth_grant.service.security.customgrant.CustomGrantService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.StringUtils;

public class CustomTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "custom_grant_type";
    private final CustomGrantService customGrantService;

    protected CustomTokenGranter(AuthorizationServerTokenServices tokenServices,
                                 ClientDetailsService clientDetailsService,
                                 OAuth2RequestFactory requestFactory,
                                 CustomGrantService customGrantService) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.customGrantService = customGrantService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        String code = tokenRequest.getRequestParameters().get("code");

        if (!StringUtils.hasText(code)) {
            throw new BadCredentialsException("code" + ":must be not blank");
        }

        UserDetails userDetails = customGrantService.loadUserCode(code);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(userDetails);

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);

        return new OAuth2Authentication(storedOAuth2Request, authentication);
    }
}
