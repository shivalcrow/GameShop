package com.web.demo.config;
 /**
  * @author NguyenHuuSon
  */
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
 
public class WebUtils {
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
     
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append("( ");
            
            for (GrantedAuthority a : authorities) {
                if(a.getAuthority().equals("ROLE_ADMIN")||a.getAuthority().equals("ROLE_EMPLOYEE")) {
                    sb.append("MANAGER: ");
                }else {
                	 sb.append("CUSTOMER: ");
                }
                
            }
            
            sb.append(user.getUsername());
            sb.append(" )");
        }
        return sb.toString();
    }
     
}