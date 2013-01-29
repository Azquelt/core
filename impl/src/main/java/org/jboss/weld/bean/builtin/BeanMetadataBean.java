/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.bean.builtin;

import java.io.Serializable;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.weld.manager.BeanManagerImpl;
import org.jboss.weld.util.bean.SerializableForwardingBean;
import org.jboss.weld.util.reflection.Reflections;

/**
 * Allows a bean to obtain information about itself.
 *
 * @author Jozef Hartinger
 * @see CDI-92
 */
public class BeanMetadataBean extends AbstractBuiltInMetadataBean<Bean<?>> {

    public BeanMetadataBean(BeanManagerImpl beanManager) {
        this(Bean.class.getSimpleName(), beanManager);
    }

    protected BeanMetadataBean(String idSuffix, BeanManagerImpl beanManager) {
        super(idSuffix, Reflections.<Class<Bean<?>>> cast(Bean.class), beanManager);
    }

    @Override
    protected Bean<?> newInstance(InjectionPoint ip, CreationalContext<Bean<?>> creationalContext) {
        Contextual<?> contextual = getParentCreationalContext(creationalContext).getContextual();
        if (contextual instanceof Bean<?>) {
            if (contextual instanceof Serializable) {
                return Reflections.cast(contextual);
            } else {
                return SerializableForwardingBean.of(getBeanManager().getContextId(), (Bean<?>) contextual);
            }
        } else {
            throw new IllegalArgumentException("Unable to determine Bean metadata for " + ip);
        }
    }
}