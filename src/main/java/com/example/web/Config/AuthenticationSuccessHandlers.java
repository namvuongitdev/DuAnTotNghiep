package com.example.web.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthenticationSuccessHandlers implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
         authorities.forEach(authori -> {
          if(authori.getAuthority().equals("ADMIN")){
              try {
                  redirectStrategy.sendRedirect(request , response , "/admin/thong-ke");
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }else if(authori.getAuthority().equals("STAFF")){
              try {
                  redirectStrategy.sendRedirect(request , response , "/admin/thong-ke");
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          else if(authori.getAuthority().equals("CLIENT")){
              try {
                  redirectStrategy.sendRedirect(request , response , "/index/home");
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }else{
              throw new IllegalStateException();
          }
      });
    }
}
