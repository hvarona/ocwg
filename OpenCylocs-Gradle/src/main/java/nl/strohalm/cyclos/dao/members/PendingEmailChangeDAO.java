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
package nl.strohalm.cyclos.dao.members;

import java.util.Calendar;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.entities.members.PendingEmailChange;

/**
 * DAO interface for pending e-mail changes
 * @author luis
 */
public interface PendingEmailChangeDAO extends BaseDAO<PendingEmailChange>, InsertableDAO<PendingEmailChange>, UpdatableDAO<PendingEmailChange>, DeletableDAO<PendingEmailChange> {

    /**
     * Removes all {@link PendingEmailChange}s before the given time
     */
    int deleteBefore(Calendar limit);

    /**
     * Loads the {@link PendingEmailChange} for the given member, or null if none
     */
    PendingEmailChange getByMember(Member member, Relationship... fetch);

    /**
     * Removes all {@link PendingEmailChange}s for the given member
     */
    int removeAll(Member member);
}
