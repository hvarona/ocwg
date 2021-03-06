/*
    This file is part of Cyclos (www.cyclos.org).
    A project of the Social Trade Organisation (www.socialtrade.org).

    Cyclos is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    Cyclos is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Cyclos; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 */
package nl.strohalm.cyclos.spring;

import java.io.IOException;
import java.util.Properties;

import nl.strohalm.cyclos.CyclosConfiguration;
import nl.strohalm.cyclos.utils.CurrentApplicationContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Custom web application context used to set the allowRawInjectionDespiteWrapping property on the BeanFactory. Also, some optional configuration
 * files depending on flags on <code>cyclos.properties</code>, like <code>cyclos.disableWebServices</code> and <code>cyclos.disableScheduling</code>
 * @author luis
 */
public class CustomWebApplicationContext extends XmlWebApplicationContext {

    @Override
    public String[] getConfigLocations() {
        Properties cyclosProperties;
        try {
            cyclosProperties = CyclosConfiguration.getCyclosProperties();
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
        final boolean disableScheduling = Boolean.valueOf(cyclosProperties.getProperty("cyclos.disableScheduling", "false"));
        final boolean disableWebServices = Boolean.valueOf(cyclosProperties.getProperty("cyclos.disableWebServices", "false"));
        final boolean disableRest = Boolean.valueOf(cyclosProperties.getProperty("cyclos.disableRestServices", "false"));

        String[] configLocations = super.getConfigLocations();
        if (!disableScheduling) {
            configLocations = appendConfigLocations(configLocations, "schedulingConfigLocation");
        }
        if (!disableWebServices) {
            configLocations = appendConfigLocations(configLocations, "webServicesConfigLocation");
        }
        if (!disableRest) {
            configLocations = appendConfigLocations(configLocations, "restConfigLocation");
        }
        return configLocations;
    }

    @Override
    public void refresh() throws BeansException, IllegalStateException {
        // Keep the current application context statically accessible during refresh
        CurrentApplicationContext.CURRENT.set(this);
        try {
            super.refresh();
        } finally {
            CurrentApplicationContext.CURRENT.remove();
        }
    }

    @Override
    protected DefaultListableBeanFactory createBeanFactory() {
        final DefaultListableBeanFactory beanFactory = super.createBeanFactory();
        beanFactory.setAllowRawInjectionDespiteWrapping(true);
        return beanFactory;
    }

    private String[] appendConfigLocations(String[] configLocations, final String paramName) {
        final String param = getServletContext().getInitParameter(paramName);
        final String[] locations = StringUtils.split(param, " ,\n\r");
        configLocations = (String[]) ArrayUtils.addAll(configLocations, locations);
        return configLocations;
    }

}
