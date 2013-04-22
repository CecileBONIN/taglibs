/**
 * Copyright (C) 2000 - 2012 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.silverpeas.tags.pdc;

import java.util.Comparator;

import com.stratelia.silverpeas.contentManager.GlobalSilverContent;

public class GSCNameComparatorAsc implements Comparator {
  static public GSCNameComparatorAsc comparator = new GSCNameComparatorAsc();

  public int compare(Object o1, Object o2) {
    GlobalSilverContent gsc1 = (GlobalSilverContent) o1;
    GlobalSilverContent gsc2 = (GlobalSilverContent) o2;

    return gsc1.getName().compareTo(gsc2.getName());
  }

  /**
   * This comparator equals self only. Use the shared comparator GSCNameComparator.comparator if
   * multiples comparators are used.
   */
  public boolean equals(Object o) {
    return o == this;
  }
}