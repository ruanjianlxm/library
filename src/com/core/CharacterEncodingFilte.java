
package com.core;

import java.io.IOException;
import javax.servlet.*;

public class CharacterEncodingFilte
    implements Filter
{

    public CharacterEncodingFilte()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    public void destroy()
    {
    }
}