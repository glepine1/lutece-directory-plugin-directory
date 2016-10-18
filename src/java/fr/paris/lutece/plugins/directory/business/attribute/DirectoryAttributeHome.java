/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.directory.business.attribute;

import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * DirectoryAttributeHome
 *
 */
public final class DirectoryAttributeHome
{
    private static final String BEAN_DIRECTORY_ATTRIBUTE_DAO = "directory.directoryAttributeDAO";
    private static Plugin _plugin = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );
    private static IDirectoryAttributeDAO _dao = SpringContextService.getBean( BEAN_DIRECTORY_ATTRIBUTE_DAO );

    /**
     * Private constructor
     */
    private DirectoryAttributeHome( )
    {
    }

    /**
     * Load the attributes of the directory
     * 
     * @param nIdDirectory
     *            the id directory
     * @return a map of key - value
     */
    public static Map<String, Object> findByPrimaryKey( int nIdDirectory )
    {
        return _dao.load( nIdDirectory, _plugin );
    }

    /**
     * Create an attribute of the directory
     * 
     * @param nIdDirectory
     *            The id of the directory
     * @param strAttributeKey
     *            The key of the attribute
     * @param attributeValue
     *            The attribute value
     */
    public static void create( int nIdDirectory, String strAttributeKey, Object attributeValue )
    {
        _dao.insert( nIdDirectory, strAttributeKey, attributeValue, _plugin );
    }

    /**
     * Create the attribute of the directory
     * 
     * @param nIdDirectory
     *            the id directory
     * @param mapAttributes
     *            the map of attributes
     */
    public static void create( int nIdDirectory, Map<String, Object> mapAttributes )
    {
        for ( Entry<String, Object> attribute : mapAttributes.entrySet( ) )
        {
            create( nIdDirectory, attribute.getKey( ), attribute.getValue( ) );
        }
    }

    /**
     * Remove the attributes of the directory
     * 
     * @param nIdDirectory
     *            the id directory
     */
    public static void remove( int nIdDirectory )
    {
        _dao.remove( nIdDirectory, _plugin );
    }
}
