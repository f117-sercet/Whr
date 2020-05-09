package Config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    public Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
     if (!request.getMethod().equals("POST")) {
         throw new AuthenticationServiceException(
                 "Authentication method not supported: " + request.getMethod());
     }
      String verify_code=(String)request.getSession().getAttribute("verify_code");
     if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)){
         Map<String,String> loginData=new HashMap<String, String>();
         try{
             loginData=new ObjectMapper().readValue(request.getInputStream(),Map.class);

         } catch (IOException e) {
         }finally {
             String code =loginData.get("code");
             checkCode(response,code,verify_code);
         }
         String username = loginData.get(getUsernameParameter());
         String password = loginData.get(getPasswordParameter());
         if (username==null) {
             username = "";
         }
         if (password==null){
             password="";
         }
        username=username.trim();
         UsernamePasswordAuthenticationToken authRequest=new UsernamePasswordAuthenticationToken( username, password);
     }

    }
}
