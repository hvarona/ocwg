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
package nl.strohalm.cyclos.controls.members;

import nl.strohalm.cyclos.controls.ActionContext;
import nl.strohalm.cyclos.controls.BaseAction;
import nl.strohalm.cyclos.entities.members.PendingEmailChange;
import nl.strohalm.cyclos.exceptions.MailSendingException;
import nl.strohalm.cyclos.utils.ActionHelper;

import org.apache.struts.action.ActionForward;

/**
 * Action used to re-send an e-mail to validate an e-mail change
 * @author luis
 */
public class ResendEmailChangeValidationAction extends BaseAction {

    @Override
    protected ActionForward executeAction(final ActionContext context) throws Exception {
        final ResendEmailChangeValidationForm form = context.getForm();
        try {
            final PendingEmailChange change = elementService.resendEmailChange(form.getMemberId());
            context.sendMessage("profile.pendingEmail.resent", change.getNewEmail());
            return ActionHelper.redirectWithParam(context.getRequest(), context.getSuccessForward(), "memberId", change.getMember().getId());
        } catch (final MailSendingException e) {
            return context.sendError("profile.error.changeEmailValidationFailed");
        }
    }

}
