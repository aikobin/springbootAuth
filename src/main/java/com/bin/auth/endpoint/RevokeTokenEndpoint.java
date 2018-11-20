package com.bin.auth.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class RevokeTokenEndpoint {
/*    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;*/

    @Autowired
    private TokenStore tokenStore;

/*    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public String revokeToken(@RequestParam("access_token") String access_token) {
        if (consumerTokenServices.revokeToken(access_token)){
            return "注销成功";
        }else{
            return "注销失败";
        }
    }*/


    @RequestMapping(value = "/test/testToken")
    @PreAuthorize("hasAnyAuthority('test3Role','test4Role')")
    @ResponseBody
    public String testToken() {
        return "success";
    }

    @RequestMapping(value = "/username")
    @ResponseBody
    public String currentUserName(String access_token) {
        UserDetails userDetails = (UserDetails)tokenStore.readAuthentication(access_token).getPrincipal();
        return userDetails.getUsername();
    }

   /* @RequestMapping("/user")
    public UserDetails user(String access_token){
        UserDetails userDetails = (UserDetails)tokenStore.readAuthentication(access_token).getPrincipal();
        return userDetails;
    }*/

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
