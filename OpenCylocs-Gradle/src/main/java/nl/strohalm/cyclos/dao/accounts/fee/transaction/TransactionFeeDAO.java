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
package nl.strohalm.cyclos.dao.accounts.fee.transaction;

import java.util.List;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.TransactionFee;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.TransactionFeeQuery;
import nl.strohalm.cyclos.entities.exceptions.DaoException;

/**
 * Data access object interface for transaction fees
 * @author rafael
 */
public interface TransactionFeeDAO extends BaseDAO<TransactionFee>, InsertableDAO<TransactionFee>, UpdatableDAO<TransactionFee>, DeletableDAO<TransactionFee> {

    /**
     * Searches for transaction fees, ordering by name. If no entity can be found, returns an empty list. If any exception is thrown by the underlying
     * implementation, it should be wrapped by a DaoException.
     * 
     * @throws DaoException
     */
    public List<TransactionFee> search(TransactionFeeQuery query) throws DaoException;

}
