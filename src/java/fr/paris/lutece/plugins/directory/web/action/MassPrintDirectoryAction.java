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
package fr.paris.lutece.plugins.directory.web.action;

import fr.paris.lutece.plugins.directory.utils.DirectoryUtils;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.web.pluginaction.AbstractPluginAction;
import fr.paris.lutece.portal.web.pluginaction.DefaultPluginActionResult;
import fr.paris.lutece.portal.web.pluginaction.IPluginActionResult;
import fr.paris.lutece.util.url.UrlItem;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirects to jsp/admin/plugins/directory/MassPrint.jsp
 *
 */
public class MassPrintDirectoryAction extends AbstractPluginAction<DirectoryAdminSearchFields> implements IDirectoryAction
{
    private static final String ACTION_NAME = "Mass Print Directory";
    private static final String TEMPLATE_BUTTON = "actions/massprint.html";

    /** the button is an image so the name is .x or .y */
    private static final String PARAMETER_BUTTON_MASS_PRINT = "massprint";
    private static final String JSP_DIRECTORY_MASS_PRINT = "jsp/admin/plugins/directory/MassPrint.jsp";

    /**
     * {@inheritDoc}
     */
    public void fillModel( HttpServletRequest request, AdminUser adminUser, Map<String, Object> model )
    {
        // no additionnal data
    }

    /**
     * {@inheritDoc}
     */
    public String getButtonTemplate( )
    {
        return TEMPLATE_BUTTON;
    }

    /**
     * {@inheritDoc}
     */
    public String getName( )
    {
        return ACTION_NAME;
    }

    /**
     * @see #PARAMETER_BUTTON_MASS_PRINT
     */
    public boolean isInvoked( HttpServletRequest request )
    {
        return request.getParameter( PARAMETER_BUTTON_MASS_PRINT ) != null;
    }

    /**
     * Redirects to {@link #JSP_DIRECTORY_MASS_PRINT}
     */
    public IPluginActionResult process( HttpServletRequest request, HttpServletResponse response, AdminUser adminUser, DirectoryAdminSearchFields sessionFields )
            throws AccessDeniedException
    {
        DefaultPluginActionResult result = new DefaultPluginActionResult( );

        UrlItem urlItem = new UrlItem( AppPathService.getBaseUrl( request ) + JSP_DIRECTORY_MASS_PRINT );
        String strIdDirectory = request.getParameter( DirectoryUtils.PARAMETER_ID_DIRECTORY );
        urlItem.addParameter( DirectoryUtils.PARAMETER_ID_DIRECTORY, strIdDirectory );

        result.setRedirect( urlItem.getUrl( ) );

        return result;
    }
}
