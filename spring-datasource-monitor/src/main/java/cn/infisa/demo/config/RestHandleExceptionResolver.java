package cn.infisa.demo.config;


import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestHandleExceptionResolver implements HandlerExceptionResolver {
    public RestHandleExceptionResolver() {
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
        view.addObject("success", "ok");
        view.addObject("message", ex.getMessage());
        ex.printStackTrace();
        return view;
    }
}
