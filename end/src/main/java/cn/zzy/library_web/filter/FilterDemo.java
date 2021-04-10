package cn.zzy.library_web.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//@Order(1)
//@WebFilter(urlPatterns = "/api/*", filterName = "apiFilter")
public class FilterDemo implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(FilterDemo.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("过滤器FilterDemo初始化");
        logger.info("我是过滤器");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        System.err.println("1.经过过滤器");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();
        String method = request.getMethod();
        System.out.println(request.getContextPath());
        System.out.println("请求的接口=" + uri + " 请求的方法=" + method);
        //请求参数打印
        Map map = servletRequest.getParameterMap();
        if (map != null && !map.isEmpty()) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String[] values = (String[]) map.get(key);
                for (String value : values) {
                    System.out.println("> " + key + "=" + value);
                }
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}