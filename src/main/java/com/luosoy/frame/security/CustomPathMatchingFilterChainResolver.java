/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.frame.security;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (filterChainManager instanceof CustomDefaultFilterChainManager) {
            if (!filterChainManager.hasChains()) {
                return null;
            }

            String requestURI = getPathWithinApplication(request);
            List<String> chainNames = new ArrayList<String>();
            for (String pathPattern : filterChainManager.getChainNames()) {
                if (pathMatches(pathPattern, requestURI)) {
                    chainNames.add(pathPattern);
                }
            }
            if (chainNames.isEmpty()) {
                return null;
            }
            return ((CustomDefaultFilterChainManager) filterChainManager).proxy(originalChain, chainNames);
        } else {
            return super.getChain(request, response, originalChain);
        }

    }
}
