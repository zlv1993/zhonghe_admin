package com.admin.zhonghe.config.security;



import com.admin.zhonghe.domain.authority.SpPermission;
import com.admin.zhonghe.domain.authority.SpRole;
import com.admin.zhonghe.service.authority.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class SpSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    PermissionService permissionService;

    AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi=(FilterInvocation) object;
        String url=fi.getRequestUrl();
        List<SpPermission> permissions=permissionService.getPermissionRole();
        for (SpPermission spPermission :permissions){
            if(antPathMatcher.match(spPermission.getUrl(),url)){
                List<SpRole> roles=spPermission.getSpRoles();
                String[] roleList=new String[roles.size()];
                for (int i=0;i<roles.size();i++){
                    roleList[i]=roles.get(i).getRole();
                }
                return SecurityConfig.createList(roleList);
            }

        }

        return SecurityConfig.createList("ROLE_UNPERMISSION");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
