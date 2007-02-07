/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/workplace/tools/accounts/CmsEditGroupDialog.java,v $
 * Date   : $Date: 2007/02/07 17:06:11 $
 * Version: $Revision: 1.18.4.3 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (c) 2005 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.workplace.tools.accounts;

import org.opencms.file.CmsGroup;
import org.opencms.jsp.CmsJspActionElement;
import org.opencms.main.CmsException;
import org.opencms.main.OpenCms;
import org.opencms.security.CmsOrganizationalUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * Dialog to create a new group or edit an existing group in the administration view.<p>
 * 
 * @author Michael Moossen 
 * 
 * @version $Revision: 1.18.4.3 $ 
 * 
 * @since 6.0.0 
 */
public class CmsEditGroupDialog extends A_CmsEditGroupDialog {

    /**
     * Public constructor with JSP action element.<p>
     * 
     * @param jsp an initialized JSP action element
     */
    public CmsEditGroupDialog(CmsJspActionElement jsp) {

        super(jsp);
    }

    /**
     * Public constructor with JSP variables.<p>
     * 
     * @param context the JSP page context
     * @param req the JSP request
     * @param res the JSP response
     */
    public CmsEditGroupDialog(PageContext context, HttpServletRequest req, HttpServletResponse res) {

        this(new CmsJspActionElement(context, req, res));
    }

    /**
     * Returns the description of the parent ou.<p>
     * 
     * @return the description of the parent ou
     */
    public String getAssignedOu() {

        try {
            return OpenCms.getOrgUnitManager().readOrganizationalUnit(getCms(), getParamOufqn()).getDescription()
                + " ("
                + CmsOrganizationalUnit.SEPARATOR
                + getParamOufqn()
                + ")";
        } catch (CmsException e) {
            return null;
        }
    }

    /**
     * Returns the simple name of the group object.<p>
     * 
     * @return the simple name of the group object
     */
    public String getName() {

        return m_group.getSimpleName();
    }

    /**
     * The method is just needed for displaying reasons.<p>
     * 
     * @param assignedOu nothing to do with this parameter
     */
    public void setAssignedOu(String assignedOu) {

        // nothing will be done here, just to avoid warnings
        assignedOu.length();
    }

    /**
     * Sets the name of the group object.<p>
     * 
     * @param name the name of the group object
     */
    public void setName(String name) {

        m_group.setName(name);
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditGroupDialog#getListClass()
     */
    protected String getListClass() {

        return CmsGroupsList.class.getName();
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditGroupDialog#getListRootPath()
     */
    protected String getListRootPath() {

        return "/accounts/orgunit/groups";
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditGroupDialog#isEditable(org.opencms.file.CmsGroup)
     */
    protected boolean isEditable(CmsGroup group) {

        return true;
    }
}