package Config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class CustomUrlDecisionManager  implements AccessDecisionManager {
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
         for (ConfigAttribute configAttribute:configAttributes){
             String needRole=configAttribute.getAttribute();
             if ("Role_LOGIN".equals(needRole)){
                 if (authentication instanceof AnonymousAuthenticationToken){
                     throw  new AccessDeniedException("尚未登陆，请登录");
                 }else{
                     return;
                 }
             }
          Collection<?extends GrantedAuthority>authorities=authentication.getAuthorities();
             for (GrantedAuthority authority:authorities){
                 if (authority.getAuthority().equals(needRole)){
                     return;
                 }
             }
         }
        throw new  AccessDeniedException("权限不足，请联系管理员");
    }

    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    public boolean supports(Class<?> aClass) {
        return true;
    }
}
