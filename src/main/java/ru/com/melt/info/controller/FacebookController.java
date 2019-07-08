package ru.com.melt.info.controller;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ExtendedPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import com.restfb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.service.SocialService;
import ru.com.melt.info.util.SecurityUtil;

import static com.restfb.FacebookClient.*;

@Controller
public class FacebookController {
    @Value("${social.facebook.idClient}")
    private String idClient;

    @Value("${social.facebook.secret}")
    private String secret;

    private String redirectUrl;

    @Value("${application.host}")
    public  void setRedirectUrl(String appHost) {
        this.redirectUrl = String.format("%s/fromFb", appHost);
    }
    @Autowired
    private SocialService<User> facebookSocialService;

    private String getAuthoriveUrl() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(ExtendedPermissions.EMAIL);
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_6);
        return client.getLoginDialogUrl(idClient, redirectUrl, scopeBuilder);
    }

    @RequestMapping(value = {"/fbLogin"}, method = RequestMethod.GET)
    public String gotoFacebook() {
        return String.format("redirect:%s", getAuthoriveUrl());
    }

    @RequestMapping(value = {"/fromFb"}, method = RequestMethod.GET)
    public String fromFb(@RequestParam(value = "code", required = false) String code) {
        if (StringUtils.isBlank(code)) {
            return "redirect:/sign-in";
        }
        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_6);
        AccessToken accessToken = client.obtainUserAccessToken(idClient, secret, redirectUrl, code);
        client = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_2_6);
        User user = client.fetchObject("me",User.class, Parameter.with("fields", "name,email,first_name,last_name"));
        Profile p = facebookSocialService.loginViaSocialNetwork(user);
        if (p != null) {
            SecurityUtil.authentificate(p);
            return "redirect:/" + p.getUid();
        } else {
            return "redirect:/sign-in";
        }
    }

}
