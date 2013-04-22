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

package com.silverpeas.tags.searchEngine;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.silverpeas.tags.authentication.AuthenticationManager;
import com.stratelia.silverpeas.pdc.model.SearchContext;

public class getSearchEngineTag extends TagSupport {

  public static final String PAGE_ID = "page";
  public static final String REQUEST_ID = "request";
  public static final String SESSION_ID = "session";
  public static final String APPLICATION_ID = "application";

  private String scope = REQUEST_ID;
  private String name;
  private String query;
  private String spaceId;
  private String componentId;
  private String authorId;
  private String afterDate;
  private String beforeDate;
  private SearchContext pdcContext;
  private Hashtable xmlQuery;
  private String xmlTitle;
  private String xmlTemplate;
  private String publicationEnabled = "true";
  private String forumEnabled = "false";

  public getSearchEngineTag() {
    super();
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }

  public void setSpaceId(String spaceId) {
    this.spaceId = spaceId;
  }

  public String getSpaceId() {
    return spaceId;
  }

  public void setComponentId(String componentId) {
    this.componentId = componentId;
  }

  public String getComponentId() {
    return componentId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public String getAuthorId() {
    return authorId;
  }

  public String getAfterDate() {
    return afterDate;
  }

  public void setAfterDate(String afterDate) {
    this.afterDate = afterDate;
  }

  public String getBeforeDate() {
    return beforeDate;
  }

  public void setBeforeDate(String beforeDate) {
    this.beforeDate = beforeDate;
  }

  public SearchContext getPdcContext() {
    return pdcContext;
  }

  public void setPdcContext(SearchContext pdcContext) {
    this.pdcContext = pdcContext;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public int doStartTag() throws JspTagException {
    String userId = AuthenticationManager.getUserId((HttpServletRequest) pageContext.getRequest());

    SearchEngineTagUtil stu = new SearchEngineTagUtil(getQuery(), getSpaceId(),
        getComponentId(), getAuthorId(), getAfterDate(), getBeforeDate(),
        getPublicationEnabled(), getForumEnabled());
    stu.setUserId(userId);
    stu.setPdcContext(getPdcContext());
    stu.setXmlQuery(getXmlQuery());
    stu.setXmlTemplate(getXmlTemplate());
    stu.setXmlTitle(getXmlTitle());

    pageContext.setAttribute(getName(), stu, translateScope(scope));
    return EVAL_PAGE;
  }

  protected int translateScope(String scope) {
    if (scope.equalsIgnoreCase(PAGE_ID)) {
      return PageContext.PAGE_SCOPE;
    } else if (scope.equalsIgnoreCase(REQUEST_ID)) {
      return PageContext.REQUEST_SCOPE;
    } else if (scope.equalsIgnoreCase(SESSION_ID)) {
      return PageContext.SESSION_SCOPE;
    } else if (scope.equalsIgnoreCase(APPLICATION_ID)) {
      return PageContext.APPLICATION_SCOPE;
    } else
      return PageContext.REQUEST_SCOPE;
  }

  public Hashtable getXmlQuery() {
    return xmlQuery;
  }

  public void setXmlQuery(Hashtable xmlQuery) {
    this.xmlQuery = xmlQuery;
  }

  public String getXmlTemplate() {
    return xmlTemplate;
  }

  public void setXmlTemplate(String xmlTemplate) {
    this.xmlTemplate = xmlTemplate;
  }

  public String getXmlTitle() {
    return xmlTitle;
  }

  public void setXmlTitle(String xmlTitle) {
    this.xmlTitle = xmlTitle;
  }

  public String getPublicationEnabled() {
    return publicationEnabled;
  }

  public void setPublicationEnabled(String publicationEnabled) {
    this.publicationEnabled = publicationEnabled;
  }

  public String getForumEnabled() {
    return forumEnabled;
  }

  public void setForumEnabled(String forumEnabled) {
    this.forumEnabled = forumEnabled;
  }

}