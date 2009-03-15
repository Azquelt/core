/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
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

package org.jboss.webbeans.resources.spi;

import org.jboss.webbeans.bootstrap.api.Service;

/**
 * JNDI operations for Web Beans, by default a read-write spec compliant 
 * implementation will be used. If you wish to substitute, for example, a read-
 * only implementation, you may
 * 
 * @author Pete Muir
 *
 */
public interface NamingContext extends Service
{
   
   public static final String PROPERTY_NAME = NamingContext.class.getName();
   
   /**
    * Typed JNDI lookup
    * 
    * @param <T> The type
    * @param name The JNDI name
    * @param expectedType The expected type
    * @return The object
    */
   public <T> T lookup(String name, Class<? extends T> expectedType);

   /**
    * Binds an item to JNDI
    * 
    * @param key The key to bind under
    * @param value The item to bind
    */
   public void bind(String name, Object value);
   
}
