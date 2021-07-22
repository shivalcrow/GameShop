package com.web.demo.config;
 /**
  * @author An Nguyen thanks to Son
  */
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
 
public class WebUtilsAn {
 
    public static String toStringManager(User user) {
        StringBuilder sb = new StringBuilder();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {        
            sb.append(user.getUsername());
        }
        return sb.toString();
    }
     
}