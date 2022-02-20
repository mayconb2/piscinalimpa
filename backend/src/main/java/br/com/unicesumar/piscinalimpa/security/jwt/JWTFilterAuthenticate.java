package br.com.unicesumar.piscinalimpa.security.jwt;

import br.com.unicesumar.piscinalimpa.entity.UserBackoffice;
import br.com.unicesumar.piscinalimpa.security.data.UserDetailData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTFilterAuthenticate extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // 8 horas
    public static final int EXPIRATION_TOKEN = 60000 * 60 * 8;
    public static final String TOKEN_GUID = "b427eae2-2f9d-47b8-8bad-e8b67e9a39c8";

    public JWTFilterAuthenticate(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UserBackoffice user = new ObjectMapper()
                    .readValue(request.getInputStream(), UserBackoffice.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getLogin(),
                    user.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Erro ao autenticar usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        UserDetailData userDetailData = (UserDetailData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(userDetailData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .sign(Algorithm.HMAC512(TOKEN_GUID));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
