package com.tu.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by len on 2019/1/18.
 */
public class UserFilter implements Filter {


    private Logger logger = LoggerFactory.getLogger(UserFilter.class);

    //初始化
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("------------UserFilter is init -----------------");
    }

    //拦截逻辑
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("------------UserFilter is doFilter -----------------");
        logger.info("------------UserFilter info -----------------[{}]",servletRequest);
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("------------UserFilter is doFilter -----------------");
        logger.info("------------UserFilter info -----------------[{}]",servletRequest);

    }

    //销毁
    public void destroy() {
        logger.info("------------UserFilter is destroy -----------------");
    }
}
