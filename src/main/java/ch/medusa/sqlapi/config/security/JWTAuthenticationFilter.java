package ch.medusa.sqlapi.config.security;

import ch.medusa.sqlapi.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private JWTProperties jwtProperties;

    public JWTAuthenticationFilter(RequestMatcher requestMatcher, AuthenticationManager authenticationManager, JWTProperties jwtProperties) {
        super(requestMatcher);
        setAuthenticationManager(authenticationManager);
        this.jwtProperties = jwtProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            ObjectMapper mapper = new ObjectMapper();

            User user = mapper.readValue(request.getInputStream(), User.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        String subject = user.getId();

        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+ jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getKey().getBytes())
                .compact();

        response.addHeader(jwtProperties.getHeader(), jwtProperties.getHeaderPrefix()+" "+token);
        response.addHeader("Access-Control-Expose-Headers", jwtProperties.getHeader());
    }
}
