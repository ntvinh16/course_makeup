package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.authentication.AuthenticationRequest;
import com.ecommerce.courses.domain.model.request.authentication.IntrospectRequest;
import com.ecommerce.courses.domain.model.request.authentication.LogoutRequest;
import com.ecommerce.courses.domain.model.request.authentication.RefreshRequest;
import com.ecommerce.courses.domain.model.response.authentication.AuthenticationResponse;
import com.ecommerce.courses.domain.model.response.authentication.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
