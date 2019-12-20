package top.funning.app.filter;


import top.funning.app.config.C;

import javax.servlet.annotation.WebFilter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "GlobeFilter", urlPatterns = "/*")
public class GlobeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
 
        request.setAttribute("imageHost", C.App.imageHost);

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
