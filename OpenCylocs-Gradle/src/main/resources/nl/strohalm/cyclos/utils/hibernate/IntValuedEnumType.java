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
package nl.strohalm.cyclos.utils.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import nl.strohalm.cyclos.utils.IntValuedEnum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.Type;

/**
 * A Hibernate custom type to store enums with a int value
 * @author luis
 */
public class IntValuedEnumType<EnumType> extends AbstractEnumType<EnumType> {

    private static final long serialVersionUID = 2442354783470853656L;
    private static final Log  LOG              = LogFactory.getLog(IntValuedEnumType.class);

    public static <T extends Enum<?>> Type getType(final Class<T> enumClass) {
        return getType(IntValuedEnumType.class, enumClass);
    }

    public int[] sqlTypes() {
        return new int[] { Types.INTEGER };
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int i, SharedSessionContractImplementor ssci) throws HibernateException, SQLException {
        if (o == null) {
            ps.setNull(i, Types.INTEGER);
        } else {
            int val;
            if (o instanceof IntValuedEnum) {
                val = ((IntValuedEnum) o).getValue();
            } else {
                val = ((Number) o).intValue();
            }
            ps.setInt(i, val);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Binding " + o + " to parameter: " + i);
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor ssci, Object owner) throws HibernateException, SQLException {
        final int value = rs.getInt(names[0]);
        if (!rs.wasNull()) {
            for (final EnumType item : getEnumValues()) {
                final IntValuedEnum intValuedEnum = (IntValuedEnum) item;
                if (value == intValuedEnum.getValue()) {
                    return item;
                }
            }
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Returning " + value + " as column " + names[0]);
        }

        return null;
    }
}
