/Users/rem/metalift/txl/qbs/allbench//EditProjectFormActionUtil.java
while (it.hasNext())
{
  org.itracker.web.ptos.ProjectScriptPTO projectScript = new org.itracker.web.ptos.ProjectScriptPTO(it.next(), locale);
  scriptPTOs.add(projectScript);
}{
  org.itracker.services.ITrackerServices itrackerServices = org.itracker.web.util.ServletContextUtils.getItrackerServices();
  org.itracker.services.ProjectService projectService = itrackerServices.getProjectService();
  org.itracker.services.UserService userService = itrackerServices.getUserService();
  javax.servlet.http.HttpSession session = request.getSession(true);
  org.itracker.model.User user = (org.itracker.model.User) session.getAttribute(org.itracker.web.util.Constants.USER_KEY);
  java.lang.Boolean allowPermissionUpdate = userService.allowPermissionUpdates(user, null, org.itracker.services.util.UserUtilities.AUTH_TYPE_UNKNOWN, org.itracker.services.util.UserUtilities.REQ_SOURCE_WEB);
  final java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> permissions = org.itracker.web.util.RequestHelper.getUserPermissions(session);
  org.itracker.model.Project project = (org.itracker.model.Project) session.getAttribute(org.itracker.web.util.Constants.PROJECT_KEY);
  boolean isUpdate;
  if (project == null)
  {
    log.info("EditProjectAction: Forward: unauthorized");
    return mapping.findForward("unauthorized");
  }
  else
  {
    isUpdate = false;
    if (!project.isNew())
    {
      isUpdate = true;
    }
  }
  request.setAttribute("isUpdate", isUpdate);
  setupTitle(request, form, projectService);
  java.util.List<org.itracker.model.NameValuePair> statuses = new java.util.ArrayList<org.itracker.model.NameValuePair>();
  statuses.add(new org.itracker.model.NameValuePair(org.itracker.services.util.ProjectUtilities.getStatusName(org.itracker.model.Status.ACTIVE, org.itracker.web.util.LoginUtilities.getCurrentLocale(request)), java.lang.Integer.toString(org.itracker.model.Status.ACTIVE.getCode())));
  statuses.add(new org.itracker.model.NameValuePair(org.itracker.services.util.ProjectUtilities.getStatusName(org.itracker.model.Status.VIEWABLE, org.itracker.web.util.LoginUtilities.getCurrentLocale(request)), java.lang.Integer.toString(org.itracker.model.Status.VIEWABLE.getCode())));
  statuses.add(new org.itracker.model.NameValuePair(org.itracker.services.util.ProjectUtilities.getStatusName(org.itracker.model.Status.LOCKED, org.itracker.web.util.LoginUtilities.getCurrentLocale(request)), java.lang.Integer.toString(org.itracker.model.Status.LOCKED.getCode())));
  request.setAttribute("statuses", statuses);
  java.util.Set<org.itracker.model.User> owners = new java.util.TreeSet<org.itracker.model.User>(org.itracker.model.User.NAME_COMPARATOR);
  if (!project.isNew())
  {
    owners.addAll(userService.getUsersWithProjectPermission(project.getId(), org.itracker.services.util.UserUtilities.PERMISSION_VIEW_ALL));
  }
  else
  {
    owners.addAll(userService.getSuperUsers());
  }
  owners.addAll(project.getOwners());
  request.setAttribute("owners", owners);
  boolean allowPermissionUpdateOption = allowPermissionUpdate == null ? false : allowPermissionUpdate && org.itracker.services.util.UserUtilities.hasPermission(permissions, new java.lang.Integer((-1)), org.itracker.services.util.UserUtilities.PERMISSION_USER_ADMIN);
  request.setAttribute("allowPermissionUpdateOption", allowPermissionUpdateOption);
  if (project.isNew())
  {
    labeled_1: {
                 java.util.List<org.itracker.model.User> users = new java.util.ArrayList<org.itracker.model.User>();
                 java.util.List<org.itracker.model.User> activeUsers = userService.getActiveUsers();
                 java.util.Collections.sort(activeUsers, org.itracker.model.User.NAME_COMPARATOR);
                 int i = 0;
                 boolean skip_0 = false;
                 while (i < activeUsers.size())
                 {
                   skip_0 = false;
                   if (!skip_0)
                     if (owners.contains(activeUsers.get(i)))
                     {
                       skip_0 = true;
                     }
                   if (!skip_0)
                     users.add(activeUsers.get(i));
                   i++;
                 }
               }
    request.setAttribute("users", users);
  }
  java.util.List<org.itracker.model.NameValuePair> permissionNames = org.itracker.services.util.UserUtilities.getPermissionNames(org.itracker.web.util.LoginUtilities.getCurrentLocale(request));
  request.setAttribute("permissions", permissionNames);
  request.setAttribute("optionSupressHistoryHtml", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_SURPRESS_HISTORY_HTML));
  request.setAttribute("optionPredefinedResolutions", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_PREDEFINED_RESOLUTIONS));
  request.setAttribute("optionAllowAssignToClose", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_ALLOW_ASSIGN_TO_CLOSE));
  request.setAttribute("optionAllowSefRegisteredCreate", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_ALLOW_SELF_REGISTERED_CREATE));
  request.setAttribute("optionLiteralHistoryHtml", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_LITERAL_HISTORY_HTML));
  request.setAttribute("optionNoAttachments", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_NO_ATTACHMENTS));
  request.setAttribute("optionAllowSelfRegisteredViewAll", java.lang.Integer.toString(org.itracker.services.util.ProjectUtilities.OPTION_ALLOW_SELF_REGISTERED_VIEW_ALL));
  java.util.List<org.itracker.model.CustomField> customFields = org.itracker.services.util.IssueUtilities.getCustomFields();
  java.util.List<org.itracker.web.actions.admin.project.EditProjectFormActionUtil.CustomFieldInfo> fieldInfos = new java.util.ArrayList<org.itracker.web.actions.admin.project.EditProjectFormActionUtil.CustomFieldInfo>(customFields.size());
  java.util.Iterator<org.itracker.model.CustomField> fieldsIt = customFields.iterator();
  org.itracker.model.CustomField ci;
  while (fieldsIt.hasNext())
  {
    ci = (org.itracker.model.CustomField) fieldsIt.next();
    fieldInfos.add(new org.itracker.web.actions.admin.project.EditProjectFormActionUtil.CustomFieldInfo(ci.getId(), org.itracker.services.util.CustomFieldUtilities.getCustomFieldName(ci.getId(), org.itracker.web.util.LoginUtilities.getCurrentLocale(request)), org.itracker.services.util.CustomFieldUtilities.getTypeString(ci.getFieldType(), org.itracker.web.util.LoginUtilities.getCurrentLocale(request))));
  }
  request.setAttribute("customFields", fieldInfos);
  java.util.List<org.itracker.model.ProjectScript> scripts = project.getScripts();
  java.util.Collections.sort(scripts, org.itracker.model.ProjectScript.FIELD_PRIORITY_COMPARATOR);
  java.util.Locale locale = org.itracker.web.util.LoginUtilities.getCurrentLocale(request);
  java.util.Iterator<org.itracker.model.ProjectScript> it = scripts.iterator();
  java.util.List<org.itracker.web.ptos.ProjectScriptPTO> scriptPTOs = new java.util.ArrayList<org.itracker.web.ptos.ProjectScriptPTO>(scripts.size());
  slothmark: ;
  request.setAttribute("projectScripts", scriptPTOs);
  java.util.List<org.itracker.model.Version> versions = project.getVersions();
  java.util.Collections.sort(versions, new org.itracker.model.Version.VersionComparator());
  java.util.List<org.itracker.web.actions.admin.project.EditProjectFormActionUtil.VersionInfo> vis = new java.util.ArrayList<org.itracker.web.actions.admin.project.EditProjectFormActionUtil.VersionInfo>();
  for (org.itracker.model.Version v : versions) vis.add(new org.itracker.web.actions.admin.project.EditProjectFormActionUtil.VersionInfo(v.getId(), v.getNumber(), v.getDescription(), v.getLastModifiedDate(), projectService.countIssuesByVersion(v.getId())));
  request.setAttribute("versions", vis);
  java.util.List<org.itracker.model.Component> components = project.getComponents();
  java.util.Collections.sort(components);
  java.util.List<org.itracker.web.actions.admin.project.EditProjectFormActionUtil.ComponentInfo> cis = new java.util.ArrayList<org.itracker.web.actions.admin.project.EditProjectFormActionUtil.ComponentInfo>();
  for (org.itracker.model.Component c : components) cis.add(new org.itracker.web.actions.admin.project.EditProjectFormActionUtil.ComponentInfo(c.getId(), c.getName(), c.getDescription(), c.getLastModifiedDate(), projectService.countIssuesByComponent(c.getId())));
  request.setAttribute("components", cis);
  return null;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//NotificationServiceImpl.java
while (it.hasNext())
{
  currentUser = it.next().getUser();
  if (null != currentUser && null != currentUser.getEmailAddress() && null != currentUser.getEmail() && !recList.contains(currentUser.getEmailAddress()) && currentUser.getEmail().indexOf('@') >= 0)
  {
    recList.add(currentUser.getEmailAddress());
  }
}{
  try
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("handleIssueNotificationhandleIssueNotification: called with " + "issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", lastModifiedDays: " + lastModifiedDays));
    }
    java.util.List<org.itracker.model.Notification> notifications;
    if (issue == null)
    {
      logger.warn(("handleIssueNotification: issue was null. Notification will n" + "ot be handled"));
      return;
    }
    if (lastModifiedDays == null || lastModifiedDays.intValue() < 0)
    {
      lastModifiedDays = java.lang.Integer.valueOf(org.itracker.web.scheduler.tasks.ReminderNotification.DEFAULT_ISSUE_AGE);
    }
    if (receipients == null)
    {
      java.util.ArrayList<javax.mail.internet.InternetAddress> recList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
      notifications = this.getIssueNotifications(issue);
      java.util.Iterator<org.itracker.model.Notification> it = notifications.iterator();
      org.itracker.model.User currentUser;
      slothmark: ;
      receipients = recList.toArray(new javax.mail.internet.InternetAddress[] {
                                                                              });
    }
    labeled_1: {
                 java.util.List<org.itracker.model.IssueActivity> activity = getIssueService().getIssueActivity(issue.getId(), false);
                 issue.getActivities();
                 java.util.List<org.itracker.model.IssueHistory> histories = issue.getHistory();
                 java.util.Iterator<org.itracker.model.IssueHistory> it = histories.iterator();
                 org.itracker.model.IssueHistory history = null;
                 org.itracker.model.IssueHistory currentHistory;
                 history = getIssueService().getLastIssueHistory(issue.getId());
                 java.lang.Integer historyId = 0;
                 while (it.hasNext())
                 {
                   currentHistory = (org.itracker.model.IssueHistory) it.next();
                   if (logger.isDebugEnabled())
                   {
                     logger.debug(("handleIssueNotification: found history: " + currentHistory.getDescription() + " (time: " + currentHistory.getCreateDate()));
                   }
                   if (currentHistory.getId() > historyId)
                   {
                     historyId = currentHistory.getId();
                     history = currentHistory;
                   }
                 }
               }
    if (logger.isDebugEnabled() && null != history)
    {
      logger.debug(("handleIssueNotification: got most recent history: " + history + " (" + history.getDescription() + ")"));
    }
    java.util.List<org.itracker.model.Component> components = issue.getComponents();
    java.util.List<org.itracker.model.Version> versions = issue.getVersions();
    if (receipients.length > 0)
    {
      java.lang.String subject = "";
      if (type == org.itracker.model.Notification.Type.CREATED)
      {
        subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.created", new java.lang.Object[] {
                                                                                                                                           issue.getId(),
                                                                                                                                           issue.getProject().getName(),
                                                                                                                                           lastModifiedDays,
                                                                                                                                         });
      }
      else
        if (type == org.itracker.model.Notification.Type.ASSIGNED)
        {
          subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.assigned", new java.lang.Object[] {
                                                                                                                                              issue.getId(),
                                                                                                                                              issue.getProject().getName(),
                                                                                                                                              lastModifiedDays,
                                                                                                                                            });
        }
        else
          if (type == org.itracker.model.Notification.Type.CLOSED)
          {
            subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.closed", new java.lang.Object[] {
                                                                                                                                              issue.getId(),
                                                                                                                                              issue.getProject().getName(),
                                                                                                                                              lastModifiedDays,
                                                                                                                                            });
          }
          else
            if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.reminder", new java.lang.Object[] {
                                                                                                                                                  issue.getId(),
                                                                                                                                                  issue.getProject().getName(),
                                                                                                                                                  lastModifiedDays,
                                                                                                                                                });
            }
            else
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.updated", new java.lang.Object[] {
                                                                                                                                                 issue.getId(),
                                                                                                                                                 issue.getProject().getName(),
                                                                                                                                                 lastModifiedDays,
                                                                                                                                               });
            }
      java.lang.String activityString;
      java.lang.String componentString = "";
      java.lang.String versionString = "";
      java.lang.StringBuffer sb = new java.lang.StringBuffer();
      int i = 0;
      while (i < activity.size())
      {
        sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType())).append(": ").append(activity.get(i).getDescription()).append("\n");
        i++;
      }
      activityString = sb.toString();
      i = 0;
      while (i < components.size())
      {
        componentString += (i != 0 ? ", " : "") + components.get(i).getName();
        i++;
      }
      i = 0;
      while (i < versions.size())
      {
        versionString += (i != 0 ? ", " : "") + versions.get(i).getNumber();
        i++;
      }
      java.lang.String msgText = "";
      if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
      {
        msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.reminder", new java.lang.Object[] {
                                                                                                                                         (url + "/module-projects/view_issue.do?id=" + issue.getId()),
                                                                                                                                         issue.getProject().getName(),
                                                                                                                                         issue.getDescription(),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                         ((issue.getOwner().getFirstName() != null ? issue.getOwner().getFirstName() : "") + " " + (issue.getOwner().getLastName() != null ? issue.getOwner().getLastName() : "")),
                                                                                                                                         componentString,
                                                                                                                                         (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                         (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                         lastModifiedDays,
                                                                                                                                         activityString,
                                                                                                                                       });
      }
      else
      {
        java.lang.String resolution = issue.getResolution() == null ? "" : issue.getResolution();
        if (!resolution.equals("") && org.itracker.services.util.ProjectUtilities.hasOption(org.itracker.services.util.ProjectUtilities.OPTION_PREDEFINED_RESOLUTIONS, issue.getProject().getOptions()))
        {
          resolution = org.itracker.services.util.IssueUtilities.getResolutionName(resolution, org.itracker.core.resources.ITrackerResources.getLocale());
        }
        msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.standard", new java.lang.Object[] {
                                                                                                                                         new java.lang.StringBuffer(url).append("/module-projects/view_issue.do?id=").append(issue.getId()).toString(),
                                                                                                                                         issue.getProject().getName(),
                                                                                                                                         issue.getDescription(),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                         resolution,
                                                                                                                                         org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                         ((null != issue.getOwner() && null != issue.getOwner().getFirstName() ? issue.getOwner().getFirstName() : "") + " " + (null != issue.getOwner() && null != issue.getOwner().getLastName() ? issue.getOwner().getLastName() : "")),
                                                                                                                                         componentString,
                                                                                                                                         (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                         (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                         activityString,
                                                                                                                                       });
      }
      emailService.sendEmail(receipients, subject, msgText);
      updateIssueActivityNotification(issue, true);
    }
  }
  catch (java.lang.Exception e)
  {
    logger.error(("handleIssueNotification: unexpected exception caught, throwi" + "ng runtime exception"), e);
    throw new java.lang.RuntimeException(e);
  }
}haha 
while (i < components.size())
{
  componentString += (i != 0 ? ", " : "") + components.get(i).getName();
  i++;
}{
  try
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("handleIssueNotificationhandleIssueNotification: called with " + "issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", lastModifiedDays: " + lastModifiedDays));
    }
    java.util.List<org.itracker.model.Notification> notifications;
    if (issue == null)
    {
      logger.warn(("handleIssueNotification: issue was null. Notification will n" + "ot be handled"));
      return;
    }
    if (lastModifiedDays == null || lastModifiedDays.intValue() < 0)
    {
      lastModifiedDays = java.lang.Integer.valueOf(org.itracker.web.scheduler.tasks.ReminderNotification.DEFAULT_ISSUE_AGE);
    }
    if (receipients == null)
    {
      java.util.ArrayList<javax.mail.internet.InternetAddress> recList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
      notifications = this.getIssueNotifications(issue);
      java.util.Iterator<org.itracker.model.Notification> it = notifications.iterator();
      org.itracker.model.User currentUser;
      while (it.hasNext())
      {
        currentUser = it.next().getUser();
        if (null != currentUser && null != currentUser.getEmailAddress() && null != currentUser.getEmail() && !recList.contains(currentUser.getEmailAddress()) && currentUser.getEmail().indexOf('@') >= 0)
        {
          recList.add(currentUser.getEmailAddress());
        }
      }
      receipients = recList.toArray(new javax.mail.internet.InternetAddress[] {
                                                                              });
    }
    labeled_1: {
                 java.util.List<org.itracker.model.IssueActivity> activity = getIssueService().getIssueActivity(issue.getId(), false);
                 issue.getActivities();
                 java.util.List<org.itracker.model.IssueHistory> histories = issue.getHistory();
                 java.util.Iterator<org.itracker.model.IssueHistory> it = histories.iterator();
                 org.itracker.model.IssueHistory history = null;
                 org.itracker.model.IssueHistory currentHistory;
                 history = getIssueService().getLastIssueHistory(issue.getId());
                 java.lang.Integer historyId = 0;
                 while (it.hasNext())
                 {
                   currentHistory = (org.itracker.model.IssueHistory) it.next();
                   if (logger.isDebugEnabled())
                   {
                     logger.debug(("handleIssueNotification: found history: " + currentHistory.getDescription() + " (time: " + currentHistory.getCreateDate()));
                   }
                   if (currentHistory.getId() > historyId)
                   {
                     historyId = currentHistory.getId();
                     history = currentHistory;
                   }
                 }
               }
    if (logger.isDebugEnabled() && null != history)
    {
      logger.debug(("handleIssueNotification: got most recent history: " + history + " (" + history.getDescription() + ")"));
    }
    java.util.List<org.itracker.model.Component> components = issue.getComponents();
    java.util.List<org.itracker.model.Version> versions = issue.getVersions();
    if (receipients.length > 0)
    {
      java.lang.String subject = "";
      if (type == org.itracker.model.Notification.Type.CREATED)
      {
        subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.created", new java.lang.Object[] {
                                                                                                                                           issue.getId(),
                                                                                                                                           issue.getProject().getName(),
                                                                                                                                           lastModifiedDays,
                                                                                                                                         });
      }
      else
        if (type == org.itracker.model.Notification.Type.ASSIGNED)
        {
          subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.assigned", new java.lang.Object[] {
                                                                                                                                              issue.getId(),
                                                                                                                                              issue.getProject().getName(),
                                                                                                                                              lastModifiedDays,
                                                                                                                                            });
        }
        else
          if (type == org.itracker.model.Notification.Type.CLOSED)
          {
            subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.closed", new java.lang.Object[] {
                                                                                                                                              issue.getId(),
                                                                                                                                              issue.getProject().getName(),
                                                                                                                                              lastModifiedDays,
                                                                                                                                            });
          }
          else
            if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.reminder", new java.lang.Object[] {
                                                                                                                                                  issue.getId(),
                                                                                                                                                  issue.getProject().getName(),
                                                                                                                                                  lastModifiedDays,
                                                                                                                                                });
            }
            else
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.updated", new java.lang.Object[] {
                                                                                                                                                 issue.getId(),
                                                                                                                                                 issue.getProject().getName(),
                                                                                                                                                 lastModifiedDays,
                                                                                                                                               });
            }
      java.lang.String activityString;
      java.lang.String componentString = "";
      java.lang.String versionString = "";
      java.lang.StringBuffer sb = new java.lang.StringBuffer();
      int i = 0;
      while (i < activity.size())
      {
        sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType())).append(": ").append(activity.get(i).getDescription()).append("\n");
        i++;
      }
      activityString = sb.toString();
      i = 0;
      slothmark: ;
      i = 0;
      while (i < versions.size())
      {
        versionString += (i != 0 ? ", " : "") + versions.get(i).getNumber();
        i++;
      }
      java.lang.String msgText = "";
      if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
      {
        msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.reminder", new java.lang.Object[] {
                                                                                                                                         (url + "/module-projects/view_issue.do?id=" + issue.getId()),
                                                                                                                                         issue.getProject().getName(),
                                                                                                                                         issue.getDescription(),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                         ((issue.getOwner().getFirstName() != null ? issue.getOwner().getFirstName() : "") + " " + (issue.getOwner().getLastName() != null ? issue.getOwner().getLastName() : "")),
                                                                                                                                         componentString,
                                                                                                                                         (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                         (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                         lastModifiedDays,
                                                                                                                                         activityString,
                                                                                                                                       });
      }
      else
      {
        java.lang.String resolution = issue.getResolution() == null ? "" : issue.getResolution();
        if (!resolution.equals("") && org.itracker.services.util.ProjectUtilities.hasOption(org.itracker.services.util.ProjectUtilities.OPTION_PREDEFINED_RESOLUTIONS, issue.getProject().getOptions()))
        {
          resolution = org.itracker.services.util.IssueUtilities.getResolutionName(resolution, org.itracker.core.resources.ITrackerResources.getLocale());
        }
        msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.standard", new java.lang.Object[] {
                                                                                                                                         new java.lang.StringBuffer(url).append("/module-projects/view_issue.do?id=").append(issue.getId()).toString(),
                                                                                                                                         issue.getProject().getName(),
                                                                                                                                         issue.getDescription(),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                         resolution,
                                                                                                                                         org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                         ((null != issue.getOwner() && null != issue.getOwner().getFirstName() ? issue.getOwner().getFirstName() : "") + " " + (null != issue.getOwner() && null != issue.getOwner().getLastName() ? issue.getOwner().getLastName() : "")),
                                                                                                                                         componentString,
                                                                                                                                         (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                         (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                         activityString,
                                                                                                                                       });
      }
      emailService.sendEmail(receipients, subject, msgText);
      updateIssueActivityNotification(issue, true);
    }
  }
  catch (java.lang.Exception e)
  {
    logger.error(("handleIssueNotification: unexpected exception caught, throwi" + "ng runtime exception"), e);
    throw new java.lang.RuntimeException(e);
  }
}haha 
while (i < versions.size())
{
  versionString += (i != 0 ? ", " : "") + versions.get(i).getNumber();
  i++;
}{
  try
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("handleIssueNotificationhandleIssueNotification: called with " + "issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", lastModifiedDays: " + lastModifiedDays));
    }
    java.util.List<org.itracker.model.Notification> notifications;
    if (issue == null)
    {
      logger.warn(("handleIssueNotification: issue was null. Notification will n" + "ot be handled"));
      return;
    }
    if (lastModifiedDays == null || lastModifiedDays.intValue() < 0)
    {
      lastModifiedDays = java.lang.Integer.valueOf(org.itracker.web.scheduler.tasks.ReminderNotification.DEFAULT_ISSUE_AGE);
    }
    if (receipients == null)
    {
      java.util.ArrayList<javax.mail.internet.InternetAddress> recList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
      notifications = this.getIssueNotifications(issue);
      java.util.Iterator<org.itracker.model.Notification> it = notifications.iterator();
      org.itracker.model.User currentUser;
      while (it.hasNext())
      {
        currentUser = it.next().getUser();
        if (null != currentUser && null != currentUser.getEmailAddress() && null != currentUser.getEmail() && !recList.contains(currentUser.getEmailAddress()) && currentUser.getEmail().indexOf('@') >= 0)
        {
          recList.add(currentUser.getEmailAddress());
        }
      }
      receipients = recList.toArray(new javax.mail.internet.InternetAddress[] {
                                                                              });
    }
    labeled_1: {
                 java.util.List<org.itracker.model.IssueActivity> activity = getIssueService().getIssueActivity(issue.getId(), false);
                 issue.getActivities();
                 java.util.List<org.itracker.model.IssueHistory> histories = issue.getHistory();
                 java.util.Iterator<org.itracker.model.IssueHistory> it = histories.iterator();
                 org.itracker.model.IssueHistory history = null;
                 org.itracker.model.IssueHistory currentHistory;
                 history = getIssueService().getLastIssueHistory(issue.getId());
                 java.lang.Integer historyId = 0;
                 while (it.hasNext())
                 {
                   currentHistory = (org.itracker.model.IssueHistory) it.next();
                   if (logger.isDebugEnabled())
                   {
                     logger.debug(("handleIssueNotification: found history: " + currentHistory.getDescription() + " (time: " + currentHistory.getCreateDate()));
                   }
                   if (currentHistory.getId() > historyId)
                   {
                     historyId = currentHistory.getId();
                     history = currentHistory;
                   }
                 }
               }
    if (logger.isDebugEnabled() && null != history)
    {
      logger.debug(("handleIssueNotification: got most recent history: " + history + " (" + history.getDescription() + ")"));
    }
    java.util.List<org.itracker.model.Component> components = issue.getComponents();
    java.util.List<org.itracker.model.Version> versions = issue.getVersions();
    if (receipients.length > 0)
    {
      java.lang.String subject = "";
      if (type == org.itracker.model.Notification.Type.CREATED)
      {
        subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.created", new java.lang.Object[] {
                                                                                                                                           issue.getId(),
                                                                                                                                           issue.getProject().getName(),
                                                                                                                                           lastModifiedDays,
                                                                                                                                         });
      }
      else
        if (type == org.itracker.model.Notification.Type.ASSIGNED)
        {
          subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.assigned", new java.lang.Object[] {
                                                                                                                                              issue.getId(),
                                                                                                                                              issue.getProject().getName(),
                                                                                                                                              lastModifiedDays,
                                                                                                                                            });
        }
        else
          if (type == org.itracker.model.Notification.Type.CLOSED)
          {
            subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.closed", new java.lang.Object[] {
                                                                                                                                              issue.getId(),
                                                                                                                                              issue.getProject().getName(),
                                                                                                                                              lastModifiedDays,
                                                                                                                                            });
          }
          else
            if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.reminder", new java.lang.Object[] {
                                                                                                                                                  issue.getId(),
                                                                                                                                                  issue.getProject().getName(),
                                                                                                                                                  lastModifiedDays,
                                                                                                                                                });
            }
            else
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.updated", new java.lang.Object[] {
                                                                                                                                                 issue.getId(),
                                                                                                                                                 issue.getProject().getName(),
                                                                                                                                                 lastModifiedDays,
                                                                                                                                               });
            }
      java.lang.String activityString;
      java.lang.String componentString = "";
      java.lang.String versionString = "";
      java.lang.StringBuffer sb = new java.lang.StringBuffer();
      int i = 0;
      while (i < activity.size())
      {
        sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType())).append(": ").append(activity.get(i).getDescription()).append("\n");
        i++;
      }
      activityString = sb.toString();
      i = 0;
      while (i < components.size())
      {
        componentString += (i != 0 ? ", " : "") + components.get(i).getName();
        i++;
      }
      i = 0;
      slothmark: ;
      java.lang.String msgText = "";
      if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
      {
        msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.reminder", new java.lang.Object[] {
                                                                                                                                         (url + "/module-projects/view_issue.do?id=" + issue.getId()),
                                                                                                                                         issue.getProject().getName(),
                                                                                                                                         issue.getDescription(),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                         ((issue.getOwner().getFirstName() != null ? issue.getOwner().getFirstName() : "") + " " + (issue.getOwner().getLastName() != null ? issue.getOwner().getLastName() : "")),
                                                                                                                                         componentString,
                                                                                                                                         (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                         (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                         lastModifiedDays,
                                                                                                                                         activityString,
                                                                                                                                       });
      }
      else
      {
        java.lang.String resolution = issue.getResolution() == null ? "" : issue.getResolution();
        if (!resolution.equals("") && org.itracker.services.util.ProjectUtilities.hasOption(org.itracker.services.util.ProjectUtilities.OPTION_PREDEFINED_RESOLUTIONS, issue.getProject().getOptions()))
        {
          resolution = org.itracker.services.util.IssueUtilities.getResolutionName(resolution, org.itracker.core.resources.ITrackerResources.getLocale());
        }
        msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.standard", new java.lang.Object[] {
                                                                                                                                         new java.lang.StringBuffer(url).append("/module-projects/view_issue.do?id=").append(issue.getId()).toString(),
                                                                                                                                         issue.getProject().getName(),
                                                                                                                                         issue.getDescription(),
                                                                                                                                         org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                         resolution,
                                                                                                                                         org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                         ((null != issue.getOwner() && null != issue.getOwner().getFirstName() ? issue.getOwner().getFirstName() : "") + " " + (null != issue.getOwner() && null != issue.getOwner().getLastName() ? issue.getOwner().getLastName() : "")),
                                                                                                                                         componentString,
                                                                                                                                         (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                         (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                         activityString,
                                                                                                                                       });
      }
      emailService.sendEmail(receipients, subject, msgText);
      updateIssueActivityNotification(issue, true);
    }
  }
  catch (java.lang.Exception e)
  {
    logger.error(("handleIssueNotification: unexpected exception caught, throwi" + "ng runtime exception"), e);
    throw new java.lang.RuntimeException(e);
  }
}haha 
matches3
/Users/rem/metalift/txl/qbs/allbench//ConcreteWorkProductDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  if (ctd.getState().equals(wilos.utils.Constantes.State.STARTED))
  {
    tache_commence = true;
  }
}{
  _concreteWorkProductDescriptor = this.getConcreteWorkProductDescriptor(_concreteWorkProductDescriptor.getId());
  if (_concreteWorkProductDescriptor != null && _concreteWorkProductDescriptor.getParticipant() == null)
  {
    java.util.Set<wilos.model.misc.concretetask.ConcreteTaskDescriptor> lctd = _concreteWorkProductDescriptor.getProducerConcreteTasks();
    boolean tache_commence = false;
    java.util.Iterator extfor$iter = lctd.iterator();
    slothmark: ;
    if (!tache_commence)
    {
      this.removeConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
    }
    else
    {
      return _concreteWorkProductDescriptor;
    }
  }
  else
    if (_concreteWorkProductDescriptor.getParticipant() != null)
    {
      getParticipant(_concreteWorkProductDescriptor).getName();
      return _concreteWorkProductDescriptor;
    }
  return null;
}haha 
matches2
/Users/rem/metalift/txl/qbs/allbench//UserServiceImpl.java
while (i < editUsers.size())
{
  users.add(editUsers.get(i));
  i++;
}{
  java.util.HashSet<org.itracker.model.User> users = new java.util.HashSet<org.itracker.model.User>();
  java.util.List<org.itracker.model.User> editUsers = getUsersWithProjectPermission(projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT, true);
  int i = 0;
  slothmark: ;
  java.util.List<org.itracker.model.User> otherUsers = getUsersWithProjectPermission(projectId, new int[] {
                                                                                                            org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS,
                                                                                                            org.itracker.services.util.UserUtilities.PERMISSION_ASSIGNABLE,
                                                                                                          }, true, true);
  i = 0;
  while (i < otherUsers.size())
  {
    users.add(otherUsers.get(i));
    i++;
  }
  if (issue != null)
  {
    org.itracker.model.User creator = issue.getCreator();
    if (org.itracker.services.util.UserUtilities.hasPermission(getUsersMapOfProjectIdsAndSetOfPermissionTypes(creator, 0), projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS))
    {
      users.add(creator);
    }
    if (issue.getOwner() != null)
    {
      org.itracker.model.User owner = issue.getOwner();
      users.add(owner);
    }
  }
  else
    if (userId != null)
    {
      org.itracker.model.User creator = getUser(userId);
      if (org.itracker.services.util.UserUtilities.hasPermission(getUsersMapOfProjectIdsAndSetOfPermissionTypes(creator, 0), projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS))
      {
        users.add(creator);
      }
    }
  int j = 0;
  java.util.List<org.itracker.model.User> userList = new java.util.ArrayList<org.itracker.model.User>();
  java.util.Iterator<org.itracker.model.User> iter = users.iterator();
  while (iter.hasNext())
  {
    userList.add(((org.itracker.model.User) iter.next()));
    j++;
  }
  return userList;
}haha 
while (i < otherUsers.size())
{
  users.add(otherUsers.get(i));
  i++;
}{
  java.util.HashSet<org.itracker.model.User> users = new java.util.HashSet<org.itracker.model.User>();
  java.util.List<org.itracker.model.User> editUsers = getUsersWithProjectPermission(projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT, true);
  int i = 0;
  while (i < editUsers.size())
  {
    users.add(editUsers.get(i));
    i++;
  }
  java.util.List<org.itracker.model.User> otherUsers = getUsersWithProjectPermission(projectId, new int[] {
                                                                                                            org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS,
                                                                                                            org.itracker.services.util.UserUtilities.PERMISSION_ASSIGNABLE,
                                                                                                          }, true, true);
  i = 0;
  slothmark: ;
  if (issue != null)
  {
    org.itracker.model.User creator = issue.getCreator();
    if (org.itracker.services.util.UserUtilities.hasPermission(getUsersMapOfProjectIdsAndSetOfPermissionTypes(creator, 0), projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS))
    {
      users.add(creator);
    }
    if (issue.getOwner() != null)
    {
      org.itracker.model.User owner = issue.getOwner();
      users.add(owner);
    }
  }
  else
    if (userId != null)
    {
      org.itracker.model.User creator = getUser(userId);
      if (org.itracker.services.util.UserUtilities.hasPermission(getUsersMapOfProjectIdsAndSetOfPermissionTypes(creator, 0), projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS))
      {
        users.add(creator);
      }
    }
  int j = 0;
  java.util.List<org.itracker.model.User> userList = new java.util.ArrayList<org.itracker.model.User>();
  java.util.Iterator<org.itracker.model.User> iter = users.iterator();
  while (iter.hasNext())
  {
    userList.add(((org.itracker.model.User) iter.next()));
    j++;
  }
  return userList;
}haha 
while (iter.hasNext())
{
  userList.add(((org.itracker.model.User) iter.next()));
  j++;
}{
  java.util.HashSet<org.itracker.model.User> users = new java.util.HashSet<org.itracker.model.User>();
  java.util.List<org.itracker.model.User> editUsers = getUsersWithProjectPermission(projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT, true);
  int i = 0;
  while (i < editUsers.size())
  {
    users.add(editUsers.get(i));
    i++;
  }
  java.util.List<org.itracker.model.User> otherUsers = getUsersWithProjectPermission(projectId, new int[] {
                                                                                                            org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS,
                                                                                                            org.itracker.services.util.UserUtilities.PERMISSION_ASSIGNABLE,
                                                                                                          }, true, true);
  i = 0;
  while (i < otherUsers.size())
  {
    users.add(otherUsers.get(i));
    i++;
  }
  if (issue != null)
  {
    org.itracker.model.User creator = issue.getCreator();
    if (org.itracker.services.util.UserUtilities.hasPermission(getUsersMapOfProjectIdsAndSetOfPermissionTypes(creator, 0), projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS))
    {
      users.add(creator);
    }
    if (issue.getOwner() != null)
    {
      org.itracker.model.User owner = issue.getOwner();
      users.add(owner);
    }
  }
  else
    if (userId != null)
    {
      org.itracker.model.User creator = getUser(userId);
      if (org.itracker.services.util.UserUtilities.hasPermission(getUsersMapOfProjectIdsAndSetOfPermissionTypes(creator, 0), projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT_USERS))
      {
        users.add(creator);
      }
    }
  int j = 0;
  java.util.List<org.itracker.model.User> userList = new java.util.ArrayList<org.itracker.model.User>();
  java.util.Iterator<org.itracker.model.User> iter = users.iterator();
  slothmark: ;
  return userList;
}haha 
matches3
/Users/rem/metalift/txl/qbs/allbench//ParticipantBean.java
while (extfor$iter.hasNext() && !break_0)
{
  wilos.model.misc.wilosuser.Participant parti = (wilos.model.misc.wilosuser.Participant) extfor$iter.next();
  if (!break_0)
    if (parti.getLogin() != null)
    {
      if (!break_0)
        if (parti.getLogin().equals(loginParticipant))
        {
          this.participant = parti;
          break_0 = true;
        }
    }
}{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  java.lang.String loginParticipant = (java.lang.String) map.get("loginParti");
  java.util.Iterator extfor$iter = this.participantsList.iterator();
  boolean break_0 = false;
  slothmark: ;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//ListProjectsAction.java
while (projectIt.hasNext())
{
  org.itracker.model.Project project = projectIt.next();
  ptos.add(createProjectPTO(project, projectService, permissions));
}{
  java.util.List<org.itracker.model.Project> projects = projectService.getAllProjects();
  java.util.ArrayList<org.itracker.model.Project> projects_tmp = new java.util.ArrayList<org.itracker.model.Project>(projects);
  java.util.Iterator<org.itracker.model.Project> projectIt = projects.iterator();
  while (projectIt.hasNext())
  {
    org.itracker.model.Project project = (org.itracker.model.Project) projectIt.next();
    if (!org.itracker.services.util.UserUtilities.hasPermission(permissions, project.getId(), permissionFlags))
    {
      projects_tmp.remove(project);
    }
  }
  projects = projects_tmp;
  java.util.Collections.sort(projects, new org.itracker.model.Project.ProjectComparator());
  java.util.ArrayList<org.itracker.web.ptos.ProjectPTO> ptos = new java.util.ArrayList<org.itracker.web.ptos.ProjectPTO>(projects_tmp.size());
  projectIt = projects.iterator();
  slothmark: ;
  return ptos;
}haha 
while (projectIt.hasNext())
{
  org.itracker.model.Project project = projectIt.next();
  ptos.add(createProjectPTO(project, projectService, permissions));
}{
  labeled_1: {
               java.util.List<org.itracker.model.Project> projects = projectService.getAllAvailableProjects();
               java.util.ArrayList<org.itracker.model.Project> projects_tmp = new java.util.ArrayList<org.itracker.model.Project>(projects);
               java.util.Iterator<org.itracker.model.Project> projectIt = projects.iterator();
               while (projectIt.hasNext())
               {
                 org.itracker.model.Project project = (org.itracker.model.Project) projectIt.next();
                 if (!org.itracker.services.util.UserUtilities.hasPermission(permissions, project.getId(), permissionFlags))
                 {
                   projects_tmp.remove(project);
                 }
               }
             }
  projects = projects_tmp;
  java.util.Collections.sort(projects, new org.itracker.model.Project.ProjectComparator());
  java.util.ArrayList<org.itracker.web.ptos.ProjectPTO> ptos = new java.util.ArrayList<org.itracker.web.ptos.ProjectPTO>(projects_tmp.size());
  projectIt = projects.iterator();
  slothmark: ;
  return ptos;
}haha 
