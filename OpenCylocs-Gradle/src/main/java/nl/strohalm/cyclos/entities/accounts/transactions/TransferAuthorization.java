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
package nl.strohalm.cyclos.entities.accounts.transactions;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.members.Element;
import nl.strohalm.cyclos.utils.FormatObject;
import nl.strohalm.cyclos.utils.StringValuedEnum;

/**
 * An authorization given to a pending authorized transfer
 *
 * @author luis, Jefferson Magno
 */
@javax.persistence.Entity
@Table(name = "transfer_authorizations")
public class TransferAuthorization extends Entity {

    public static enum Action implements StringValuedEnum {

        AUTHORIZE("A"), DENY("D"), CANCEL("C");
        private final String value;

        private Action(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum Relationships implements Relationship {

        TRANSFER("transfer"), LEVEL("level"), BY("by");
        private final String name;

        private Relationships(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static final long serialVersionUID = 8935717859614210717L;

    private Element by;
    private Calendar date;
    private Action action;
    private Transfer transfer;
    private AuthorizationLevel level;
    private String comments;
    private boolean showToMember;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    public Action getAction() {
        return action;
    }

    @ManyToOne(targetEntity = Element.class)
    @JoinColumn(name = "by_id", updatable = false)
    public Element getBy() {
        return by;
    }

    @Column(updatable = false)
    public String getComments() {
        return comments;
    }

    @Column(nullable = false, updatable = false)
    public Calendar getDate() {
        return date;
    }

    @ManyToOne(targetEntity = AuthorizationLevel.class)
    @JoinColumn(name = "level_id", nullable = false, updatable = false)
    public AuthorizationLevel getLevel() {
        return level;
    }

    @ManyToOne(targetEntity = Transfer.class)
    @JoinColumn(name = "transfer_id", nullable = false, updatable = false)
    public Transfer getTransfer() {
        return transfer;
    }

    @Column(nullable = false, updatable = false)
    public boolean isShowToMember() {
        return showToMember;
    }

    public void setAction(final Action action) {
        this.action = action;
    }

    public void setBy(final Element by) {
        this.by = by;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }

    public void setLevel(final AuthorizationLevel level) {
        this.level = level;
    }

    public void setShowToMember(final boolean showToMember) {
        this.showToMember = showToMember;
    }

    public void setTransfer(final Transfer transfer) {
        this.transfer = transfer;
    }

    @Override
    public String toString() {
        return getId() + " - " + action + " by " + by + " at " + FormatObject.formatObject(date);
    }

}
