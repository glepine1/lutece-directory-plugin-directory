/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.directory.business;

import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.plugins.directory.utils.DirectoryErrorException;
import fr.paris.lutece.plugins.directory.utils.DirectoryUtils;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * class EntryTypeInternalLink
 *
 */
public class EntryTypeInternalLink extends Entry
{
    private final String _template_create = "admin/plugins/directory/entrytypeinternallink/create_entry_type_internallink.html";
    private final String _template_modify = "admin/plugins/directory/entrytypeinternallink/modify_entry_type_internallink.html";
    private final String _template_html_code_form_entry = "admin/plugins/directory/entrytypeinternallink/html_code_form_entry_type_internallink.html";
    private final String _template_html_code_form_search_entry = "admin/plugins/directory/entrytypeinternallink/html_code_form_search_entry_type_internallink.html";
    private final String _template_html_code_entry_value = "admin/plugins/directory/entrytypeinternallink/html_code_entry_value_type_internallink.html";
    private final String _template_html_front_code_form_entry = "skin/plugins/directory/entrytypeinternallink/html_code_form_entry_type_internallink.html";
    private final String _template_html_front_code_form_search_entry = "skin/plugins/directory/entrytypeinternallink/html_code_form_search_entry_type_internallink.html";
    private final String _template_html_front_code_entry_value = "skin/plugins/directory/entrytypeinternallink/html_code_entry_value_type_internallink.html";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateHtmlFormEntry( boolean isDisplayFront )
    {
        if ( isDisplayFront )
        {
            return _template_html_front_code_form_entry;
        }

        return _template_html_code_form_entry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateHtmlRecordFieldValue( boolean isDisplayFront )
    {
        if ( isDisplayFront )
        {
            return _template_html_front_code_entry_value;
        }

        return _template_html_code_entry_value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateHtmlFormSearchEntry( boolean isDisplayFront )
    {
        if ( isDisplayFront )
        {
            return _template_html_front_code_form_search_entry;
        }

        return _template_html_code_form_search_entry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEntryData( HttpServletRequest request, Locale locale )
    {
        String strTitle = request.getParameter( PARAMETER_TITLE );
        String strHelpMessage = ( request.getParameter( PARAMETER_HELP_MESSAGE ) != null ) ? request.getParameter( PARAMETER_HELP_MESSAGE ).trim( ) : null;
        String strHelpMessageSearch = ( request.getParameter( PARAMETER_HELP_MESSAGE_SEARCH ) != null ) ? request.getParameter( PARAMETER_HELP_MESSAGE_SEARCH )
                .trim( ) : null;
        String strComment = request.getParameter( PARAMETER_COMMENT );
        String strValue = request.getParameter( PARAMETER_VALUE );
        String strMandatory = request.getParameter( PARAMETER_MANDATORY );
        String strIndexed = request.getParameter( PARAMETER_INDEXED );
        String strIndexedAsTitle = request.getParameter( PARAMETER_INDEXED_AS_TITLE );
        String strIndexedAsSummary = request.getParameter( PARAMETER_INDEXED_AS_SUMMARY );
        String strShowInAdvancedSearch = request.getParameter( PARAMETER_SHOWN_IN_ADVANCED_SEARCH );
        String strShowInResultList = request.getParameter( PARAMETER_SHOWN_IN_RESULT_LIST );
        String strShowInResultRecord = request.getParameter( PARAMETER_SHOWN_IN_RESULT_RECORD );
        String strShowInHistory = request.getParameter( PARAMETER_SHOWN_IN_HISTORY );
        String strShowInExport = request.getParameter( PARAMETER_SHOWN_IN_EXPORT );
        String strShowInCompleteness = request.getParameter( PARAMETER_SHOWN_IN_COMPLETENESS );

        String strFieldError = DirectoryUtils.EMPTY_STRING;

        if ( ( strTitle == null ) || strTitle.trim( ).equals( DirectoryUtils.EMPTY_STRING ) )
        {
            strFieldError = FIELD_TITLE;
        }

        if ( !strFieldError.equals( DirectoryUtils.EMPTY_STRING ) )
        {
            Object [ ] tabRequiredFields = {
                I18nService.getLocalizedString( strFieldError, locale )
            };

            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELD, tabRequiredFields, AdminMessage.TYPE_STOP );
        }

        if ( !strFieldError.equals( DirectoryUtils.EMPTY_STRING ) )
        {
            Object [ ] tabRequiredFields = {
                I18nService.getLocalizedString( strFieldError, locale )
            };

            return AdminMessageService.getMessageUrl( request, MESSAGE_NUMERIC_FIELD, tabRequiredFields, AdminMessage.TYPE_STOP );
        }

        this.setTitle( strTitle );
        this.setHelpMessage( strHelpMessage );
        this.setHelpMessageSearch( strHelpMessageSearch );
        this.setComment( strComment );

        if ( this.getFields( ) == null )
        {
            ArrayList<Field> listFields = new ArrayList<Field>( );
            Field field = new Field( );
            listFields.add( field );
            this.setFields( listFields );
        }

        this.getFields( ).get( 0 ).setValue( strValue );
        this.setMandatory( strMandatory != null );
        this.setIndexed( strIndexed != null );
        this.setIndexedAsTitle( strIndexedAsTitle != null );
        this.setIndexedAsSummary( strIndexedAsSummary != null );
        this.setShownInAdvancedSearch( strShowInAdvancedSearch != null );
        this.setShownInResultList( strShowInResultList != null );
        this.setShownInResultRecord( strShowInResultRecord != null );
        this.setShownInHistory( strShowInHistory != null );
        this.setShownInExport( strShowInExport != null );
        this.setShownInCompleteness( strShowInCompleteness != null );

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateCreate( )
    {
        return _template_create;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateModify( )
    {
        return _template_modify;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getRecordFieldData( Record record, List<String> lstValue, boolean bTestDirectoryError, boolean bAddNewValue, List<RecordField> listRecordField,
            Locale locale ) throws DirectoryErrorException
    {
        Plugin plugin = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );

        String strValueEntry = ( ( lstValue != null ) && ( lstValue.size( ) > 0 ) ) ? lstValue.get( 0 ) : null;
        RecordField recordField = new RecordField( );
        recordField.setEntry( this );

        if ( ( record != null ) && bAddNewValue )
        {
            RecordFieldFilter recordFieldFilter = new RecordFieldFilter( );
            recordFieldFilter.setIdDirectory( record.getDirectory( ).getIdDirectory( ) );
            recordFieldFilter.setIdEntry( this.getIdEntry( ) );
            recordFieldFilter.setIdRecord( record.getIdRecord( ) );

            List<RecordField> recordFieldList = RecordFieldHome.getRecordFieldList( recordFieldFilter, plugin );

            if ( ( recordFieldList != null ) && !recordFieldList.isEmpty( ) && StringUtils.isNotBlank( recordFieldList.get( 0 ).getValue( ) ) )
            {
                strValueEntry = recordFieldList.get( 0 ).getValue( ) + ", " + strValueEntry;
            }
        }

        if ( strValueEntry != null )
        {
            if ( bTestDirectoryError && this.isMandatory( ) && strValueEntry.equals( DirectoryUtils.EMPTY_STRING ) )
            {
                throw new DirectoryErrorException( this.getTitle( ) );
            }

            recordField.setValue( strValueEntry );
        }

        listRecordField.add( recordField );
    }
}
