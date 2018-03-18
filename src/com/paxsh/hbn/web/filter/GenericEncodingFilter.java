package com.paxsh.hbn.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 通用编码解决方案
 */
public class GenericEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 转型为与协议相关对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 对request包装增强
        HttpServletRequest request = new GenericEncodingRequest(httpServletRequest);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }

    static class GenericEncodingRequest extends HttpServletRequestWrapper {

        private HttpServletRequest request;
        private boolean hasEncode;

        public GenericEncodingRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            String method = request.getMethod().toLowerCase();
            switch (method) {
                case "post":
                    try {
                        request.setCharacterEncoding("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return request.getParameterMap();

                case "get":
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    if (!hasEncode) {
                        for (String parameterName : parameterMap.keySet()) {
                            String[] values = parameterMap.get(parameterName);
                            if (values != null) {
                                for (int i = 0; i < values.length; i++) {
                                    try {
                                        // 处理get乱码
                                        values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        hasEncode = true;
                    }
                    return parameterMap;
                default:
                    return super.getParameterMap();
            }
        }

        @Override
        public String getParameter(String name) {
            Map<String, String[]> parameterMap = getParameterMap();
            String[] values = parameterMap.get(name);
            if (values == null) {
                return null;
            }
            return values[0]; // 取回参数的第一个值
        }

        @Override
        public String[] getParameterValues(String name) {
            Map<String, String[]> parameterMap = getParameterMap();
            String[] values = parameterMap.get(name);
            return values;
        }
    }
}
