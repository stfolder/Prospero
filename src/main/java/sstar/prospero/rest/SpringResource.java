package sstar.prospero.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * Created by Sergey.Tarasenko on 08.03.2015.
 */
public abstract class SpringResource {
    @Context
    public void setContext(ServletContext context) {
        autowire(context);
    }
    
    public void autowire(ServletContext context) {
        autowire(context, this);
    }

    public void autowire(ServletContext context, Object target) {
        XmlWebApplicationContext wcontext = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(context);
        wcontext.getBeanFactory().autowireBean(target);
    }
}
