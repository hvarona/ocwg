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
package nl.strohalm.cyclos.entities.accounts.cards;

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
import nl.strohalm.cyclos.entities.members.Element;

/**
 * Represents a card log
 *
 * @author jefferson
 */
@javax.persistence.Entity
@Table(name = "card_logs")
public class CardLog extends Entity {

    private static final long serialVersionUID = 2551581873638833476L;
    private Calendar date;
    private Card.Status status;
    private Card card;
    private Element by;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(targetEntity = Element.class)
    @JoinColumn(name = "by_id")
    public Element getBy() {
        return by;
    }

    @ManyToOne(targetEntity = Card.class)
    @JoinColumn(name = "card_id")
    public Card getCard() {
        return card;
    }

    @Column
    public Calendar getDate() {
        return date;
    }

    @Enumerated(EnumType.STRING)
    public Card.Status getStatus() {
        return status;
    }

    public void setBy(final Element by) {
        this.by = by;
    }

    public void setCard(final Card card) {
        this.card = card;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }

    public void setStatus(final Card.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getId().toString();
    }

}
