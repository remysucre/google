/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) extfor$iter.next();
  tmp.add(act);
}{
  java.util.Set<wilos.model.spem2.activity.Activity> tmp = new java.util.HashSet<wilos.model.spem2.activity.Activity>();
  this.guidanceDao.getSessionFactory().getCurrentSession().saveOrUpdate(_guidance);
  java.util.Iterator extfor$iter = _guidance.getActivities().iterator();
  slothmark: ;
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor concreteTaskDescriptor = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  concreteTaskDescriptors.add(concreteTaskDescriptor);
}{
  java.util.Set<wilos.model.misc.concretetask.ConcreteTaskDescriptor> concreteTaskDescriptors = new java.util.HashSet<wilos.model.misc.concretetask.ConcreteTaskDescriptor>();
  this.concreteRoleDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteRoleDescriptor);
  this.concreteRoleDescriptorDao.getSessionFactory().getCurrentSession().refresh(_concreteRoleDescriptor);
  java.util.Iterator extfor$iter = _concreteRoleDescriptor.getPrimaryConcreteTaskDescriptors().iterator();
  slothmark: ;
  return concreteTaskDescriptors;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration bde = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  tmp.add(bde);
}{
  java.util.Set<wilos.model.misc.concreteiteration.ConcreteIteration> tmp = new java.util.HashSet<wilos.model.misc.concreteiteration.ConcreteIteration>();
  this.iterationDao.getSessionFactory().getCurrentSession().saveOrUpdate(_iteration);
  java.util.Iterator extfor$iter = _iteration.getConcreteIterations().iterator();
  slothmark: ;
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase bde = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  tmp.add(bde);
}{
  java.util.Set<wilos.model.misc.concretephase.ConcretePhase> tmp = new java.util.HashSet<wilos.model.misc.concretephase.ConcretePhase>();
  this.phaseDao.getSessionFactory().getCurrentSession().saveOrUpdate(_phase);
  java.util.Iterator extfor$iter = _phase.getConcretePhases().iterator();
  slothmark: ;
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//EditProjectFormActionUtil.java
while (fieldsIt.hasNext())
{
  ci = (org.itracker.model.CustomField) fieldsIt.next();
  fieldInfos.add(new org.itracker.web.actions.admin.project.EditProjectFormActionUtil.CustomFieldInfo(ci.getId(), org.itracker.services.util.CustomFieldUtilities.getCustomFieldName(ci.getId(), org.itracker.web.util.LoginUtilities.getCurrentLocale(request)), org.itracker.services.util.CustomFieldUtilities.getTypeString(ci.getFieldType(), org.itracker.web.util.LoginUtilities.getCurrentLocale(request))));
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
  slothmark: ;
  request.setAttribute("customFields", fieldInfos);
  java.util.List<org.itracker.model.ProjectScript> scripts = project.getScripts();
  java.util.Collections.sort(scripts, org.itracker.model.ProjectScript.FIELD_PRIORITY_COMPARATOR);
  java.util.Locale locale = org.itracker.web.util.LoginUtilities.getCurrentLocale(request);
  java.util.Iterator<org.itracker.model.ProjectScript> it = scripts.iterator();
  java.util.List<org.itracker.web.ptos.ProjectScriptPTO> scriptPTOs = new java.util.ArrayList<org.itracker.web.ptos.ProjectScriptPTO>(scripts.size());
  while (it.hasNext())
  {
    org.itracker.web.ptos.ProjectScriptPTO projectScript = new org.itracker.web.ptos.ProjectScriptPTO(it.next(), locale);
    scriptPTOs.add(projectScript);
  }
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
matches2
/Users/remywang/metalift/txl/qbs/allbench//WorkProductSExpTableBean.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.workproduct.WorkProductDescriptor wpd = (wilos.model.spem2.workproduct.WorkProductDescriptor) extfor$iter.next();
  java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
  int nb = this.processService.getWorkProductDescriptorsWithTheSameNameNumberInProcess(_process, wpd.getPresentationName());
  if (nb == 0)
  {
    hm.put("nodeType", "leaf");
    hm.put("expansionImage", TABLE_LEAF);
  }
  else
  {
    hm.put("nodeType", "node");
    hm.put("expansionImage", CONTRACT_TABLE_ARROW);
  }
  hm.put("id", wpd.getId());
  hm.put("name", wpd.getPresentationName());
  hm.put("isDisabled", false);
  int nbcwpd = this.projectService.getConcreteWorkProductDescriptorsFromProject(project).size();
  if (nbcwpd > 0)
  {
    hm.put("nbOccurences", new java.lang.Integer(0));
  }
  else
  {
    hm.put("nbOccurences", new java.lang.Integer(1));
  }
  hm.put("parentId", wpd.getPresentationName());
  lines.add(hm);
}{
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> lines = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  java.util.Iterator extfor$iter = this.processService.getWorkProductDescriptorsFromProcess(_process).iterator();
  slothmark: ;
  return lines;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (!cact.getState().equals(wilos.utils.Constantes.State.FINISHED))
  {
    activityList.add(new javax.faces.model.SelectItem(cact.getId(), cact.getConcreteName()));
  }
}{
  java.util.List<javax.faces.model.SelectItem> activityList = new java.util.ArrayList<javax.faces.model.SelectItem>();
  activityList.add(new javax.faces.model.SelectItem("default", wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.actComboBoxDefau" + "ltChoice"))));
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  if (project != null)
  {
    java.util.Iterator extfor$iter = this.concreteActivityService.getConcreteActivitiesFromProject(project).iterator();
    slothmark: ;
    if (!project.getState().equals(wilos.utils.Constantes.State.FINISHED))
    {
      activityList.add(new javax.faces.model.SelectItem(project.getId(), project.getConcreteName()));
    }
  }
  return activityList;
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//NotificationServiceImpl.java
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
while (i < activity.size())
{
  sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType())).append(": ").append(activity.get(i).getDescription()).append("\n");
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
      slothmark: ;
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
while (it.hasNext())
{
  currentUser = it.next().getUser();
  if (null != currentUser && null != currentUser.getEmailAddress() && null != currentUser.getEmail() && !localeMapping.keySet().contains(currentUser.getEmailAddress()))
  {
    try
    {
      localeMapping.put(currentUser.getEmailAddress(), org.itracker.core.resources.ITrackerResources.getLocale(currentUser.getPreferences().getUserLocale()));
    }
    catch (java.lang.RuntimeException re)
    {
      localeMapping.put(currentUser.getEmailAddress(), org.itracker.core.resources.ITrackerResources.getLocale());
    }
  }
}{
  try
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("handleIssueNotificationhandleIssueNotification: running as t" + "hread, called with issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", lastModifiedDays: " + lastModifiedDays));
    }
    final java.lang.Integer notModifiedSince;
    if (lastModifiedDays == null || lastModifiedDays.intValue() < 0)
    {
      notModifiedSince = java.lang.Integer.valueOf(org.itracker.web.scheduler.tasks.ReminderNotification.DEFAULT_ISSUE_AGE);
    }
    else
    {
      notModifiedSince = lastModifiedDays;
    }
    try
    {
      if (logger.isDebugEnabled())
      {
        logger.debug(("handleIssueNotificationhandleIssueNotification.run: running " + "as thread, called with issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", notModifiedSince: " + notModifiedSince));
      }
      final java.util.List<org.itracker.model.Notification> notifications;
      if (issue == null)
      {
        logger.warn(("handleIssueNotification: issue was null. Notification will n" + "ot be handled"));
        return;
      }
      java.util.Map<javax.mail.internet.InternetAddress, java.util.Locale> localeMapping = null;
      if (receipients == null)
      {
        notifications = this.getIssueNotifications(issue);
        localeMapping = new java.util.Hashtable<javax.mail.internet.InternetAddress, java.util.Locale>(notifications.size());
        java.util.Iterator<org.itracker.model.Notification> it = notifications.iterator();
        org.itracker.model.User currentUser;
        slothmark: ;
      }
      else
      {
        localeMapping = new java.util.Hashtable<javax.mail.internet.InternetAddress, java.util.Locale>(1);
        java.util.Locale locale = org.itracker.core.resources.ITrackerResources.getLocale();
        java.util.Iterator<javax.mail.internet.InternetAddress> it = java.util.Arrays.asList(receipients).iterator();
        while (it.hasNext())
        {
          javax.mail.internet.InternetAddress internetAddress = (javax.mail.internet.InternetAddress) it.next();
          localeMapping.put(internetAddress, locale);
        }
      }
      this.handleNotification(issue, type, notModifiedSince, localeMapping, url);
    }
    catch (java.lang.Exception e)
    {
      logger.error("run: failed to process notification", e);
    }
  }
  catch (java.lang.Exception e)
  {
    logger.error(("handleIssueNotification: unexpected exception caught, throwi" + "ng runtime exception"), e);
    throw new java.lang.RuntimeException(e);
  }
}haha 
while (it.hasNext())
{
  javax.mail.internet.InternetAddress internetAddress = (javax.mail.internet.InternetAddress) it.next();
  localeMapping.put(internetAddress, locale);
}{
  try
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("handleIssueNotificationhandleIssueNotification: running as t" + "hread, called with issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", lastModifiedDays: " + lastModifiedDays));
    }
    final java.lang.Integer notModifiedSince;
    if (lastModifiedDays == null || lastModifiedDays.intValue() < 0)
    {
      notModifiedSince = java.lang.Integer.valueOf(org.itracker.web.scheduler.tasks.ReminderNotification.DEFAULT_ISSUE_AGE);
    }
    else
    {
      notModifiedSince = lastModifiedDays;
    }
    try
    {
      if (logger.isDebugEnabled())
      {
        logger.debug(("handleIssueNotificationhandleIssueNotification.run: running " + "as thread, called with issue: " + issue + ", type: " + type + "url: " + url + ", receipients: " + (null == receipients ? "<null>" : java.lang.String.valueOf(java.util.Arrays.asList(receipients))) + ", notModifiedSince: " + notModifiedSince));
      }
      final java.util.List<org.itracker.model.Notification> notifications;
      if (issue == null)
      {
        logger.warn(("handleIssueNotification: issue was null. Notification will n" + "ot be handled"));
        return;
      }
      java.util.Map<javax.mail.internet.InternetAddress, java.util.Locale> localeMapping = null;
      if (receipients == null)
      {
        notifications = this.getIssueNotifications(issue);
        localeMapping = new java.util.Hashtable<javax.mail.internet.InternetAddress, java.util.Locale>(notifications.size());
        java.util.Iterator<org.itracker.model.Notification> it = notifications.iterator();
        org.itracker.model.User currentUser;
        while (it.hasNext())
        {
          currentUser = it.next().getUser();
          if (null != currentUser && null != currentUser.getEmailAddress() && null != currentUser.getEmail() && !localeMapping.keySet().contains(currentUser.getEmailAddress()))
          {
            try
            {
              localeMapping.put(currentUser.getEmailAddress(), org.itracker.core.resources.ITrackerResources.getLocale(currentUser.getPreferences().getUserLocale()));
            }
            catch (java.lang.RuntimeException re)
            {
              localeMapping.put(currentUser.getEmailAddress(), org.itracker.core.resources.ITrackerResources.getLocale());
            }
          }
        }
      }
      else
      {
        localeMapping = new java.util.Hashtable<javax.mail.internet.InternetAddress, java.util.Locale>(1);
        java.util.Locale locale = org.itracker.core.resources.ITrackerResources.getLocale();
        java.util.Iterator<javax.mail.internet.InternetAddress> it = java.util.Arrays.asList(receipients).iterator();
        slothmark: ;
      }
      this.handleNotification(issue, type, notModifiedSince, localeMapping, url);
    }
    catch (java.lang.Exception e)
    {
      logger.error("run: failed to process notification", e);
    }
  }
  catch (java.lang.Exception e)
  {
    logger.error(("handleIssueNotification: unexpected exception caught, throwi" + "ng runtime exception"), e);
    throw new java.lang.RuntimeException(e);
  }
}haha 
while (iaIt.hasNext())
{
  javax.mail.internet.InternetAddress internetAddress = (javax.mail.internet.InternetAddress) iaIt.next();
  if (localeRecipients.keySet().contains(recipientsLocales.get(internetAddress)))
  {
    localeRecipients.get(recipientsLocales.get(internetAddress)).add(internetAddress);
  }
  else
  {
    java.util.Set<javax.mail.internet.InternetAddress> addresses = new java.util.HashSet<javax.mail.internet.InternetAddress>();
    localeRecipients.put(recipientsLocales.get(internetAddress), addresses);
  }
}{
  java.util.Set<javax.mail.internet.InternetAddress> recipients = recipientsLocales.keySet();
  java.util.Map<java.util.Locale, java.util.Set<javax.mail.internet.InternetAddress>> localeRecipients = new java.util.Hashtable<java.util.Locale, java.util.Set<javax.mail.internet.InternetAddress>>();
  java.util.List<org.itracker.model.Component> components = issue.getComponents();
  java.util.List<org.itracker.model.Version> versions = issue.getVersions();
  java.util.List<org.itracker.model.IssueActivity> activity = getIssueService().getIssueActivity(issue.getId(), false);
  issue.getActivities();
  java.util.List<org.itracker.model.IssueHistory> histories = issue.getHistory();
  java.util.Iterator<org.itracker.model.IssueHistory> it = histories.iterator();
  labeled_2: {
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
  java.util.Iterator<javax.mail.internet.InternetAddress> iaIt = recipientsLocales.keySet().iterator();
  slothmark: ;
  java.util.Iterator<java.util.Locale> localesIt = localeRecipients.keySet().iterator();
  try
  {
    while (localesIt.hasNext())
    {
      java.util.Locale currentLocale = (java.util.Locale) localesIt.next();
      recipients = localeRecipients.get(currentLocale);
      if (recipients.size() > 0)
      {
        java.lang.String subject = "";
        if (type == org.itracker.model.Notification.Type.CREATED)
        {
          subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.created", currentLocale, new java.lang.Object[] {
                                                                                                                                                            issue.getId(),
                                                                                                                                                            issue.getProject().getName(),
                                                                                                                                                            notModifiedSince,
                                                                                                                                                          });
        }
        else
          if (type == org.itracker.model.Notification.Type.ASSIGNED)
          {
            subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.assigned", currentLocale, new java.lang.Object[] {
                                                                                                                                                               issue.getId(),
                                                                                                                                                               issue.getProject().getName(),
                                                                                                                                                               notModifiedSince,
                                                                                                                                                             });
          }
          else
            if (type == org.itracker.model.Notification.Type.CLOSED)
            {
              subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.closed", currentLocale, new java.lang.Object[] {
                                                                                                                                                               issue.getId(),
                                                                                                                                                               issue.getProject().getName(),
                                                                                                                                                               notModifiedSince,
                                                                                                                                                             });
            }
            else
              if (type == org.itracker.model.Notification.Type.ISSUE_REMINDER)
              {
                subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.reminder", currentLocale, new java.lang.Object[] {
                                                                                                                                                                   issue.getId(),
                                                                                                                                                                   issue.getProject().getName(),
                                                                                                                                                                   notModifiedSince,
                                                                                                                                                                 });
              }
              else
              {
                subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.subject.updated", currentLocale, new java.lang.Object[] {
                                                                                                                                                                  issue.getId(),
                                                                                                                                                                  issue.getProject().getName(),
                                                                                                                                                                  notModifiedSince,
                                                                                                                                                                });
              }
        java.lang.String activityString;
        java.lang.String componentString = "";
        java.lang.String versionString = "";
        java.lang.StringBuffer sb = new java.lang.StringBuffer();
        int i = 0;
        while (i < activity.size())
        {
          sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType(), currentLocale)).append(": ").append(activity.get(i).getDescription()).append("\n");
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
          msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.reminder", currentLocale, new java.lang.Object[] {
                                                                                                                                                          (url + "/module-projects/view_issue.do?id=" + issue.getId()),
                                                                                                                                                          issue.getProject().getName(),
                                                                                                                                                          issue.getDescription(),
                                                                                                                                                          org.itracker.services.util.IssueUtilities.getStatusName(issue.getStatus()),
                                                                                                                                                          org.itracker.services.util.IssueUtilities.getSeverityName(issue.getSeverity()),
                                                                                                                                                          ((issue.getOwner().getFirstName() != null ? issue.getOwner().getFirstName() : "") + " " + (issue.getOwner().getLastName() != null ? issue.getOwner().getLastName() : "")),
                                                                                                                                                          componentString,
                                                                                                                                                          (history == null ? "" : history.getUser().getFirstName() + " " + history.getUser().getLastName()),
                                                                                                                                                          (history == null ? "" : org.itracker.services.util.HTMLUtilities.removeMarkup(history.getDescription())),
                                                                                                                                                          notModifiedSince,
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
          msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.issue.body.standard", currentLocale, new java.lang.Object[] {
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
        if (logger.isInfoEnabled())
        {
          logger.info(new java.lang.StringBuilder("handleNotification: sending notification for ").append(issue).append(" (").append(type).append(") to ").append(currentLocale).append("-users (").append((recipients + ")")).toString());
        }
        emailService.sendEmail(recipients, subject, msgText);
        if (logger.isDebugEnabled())
        {
          logger.debug(("handleNotification: sent notification for " + issue));
        }
      }
      updateIssueActivityNotification(issue, true);
      if (logger.isDebugEnabled())
      {
        logger.debug(("handleNotification: sent notification for locales " + localeRecipients.keySet() + " recipients: " + localeRecipients.values()));
      }
    }
  }
  catch (java.lang.RuntimeException e)
  {
    logger.error(("handleNotification: failed to notify: " + issue + " (locales: " + localeRecipients.keySet() + ")"), e);
  }
}haha 
while (iterator.hasNext())
{
  org.itracker.model.User projectOwner = (org.itracker.model.User) iterator.next();
  if (projectOwner != null && (!activeOnly || projectOwner.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
  {
    issueNotifications.add(new org.itracker.model.Notification(projectOwner, issue, org.itracker.model.Notification.Role.PO));
  }
}{
  if (logger.isDebugEnabled())
  {
    logger.debug(("getIssueNotifications: called with issue: " + issue + ", primaryOnly: " + primaryOnly + ", activeOnly: " + activeOnly));
  }
  java.util.List<org.itracker.model.Notification> issueNotifications = new java.util.ArrayList<org.itracker.model.Notification>();
  if (issue == null)
  {
    logger.warn("getIssueNotifications: no issue, throwing exception");
    throw new java.lang.IllegalArgumentException("issue must not be null");
  }
  if (!primaryOnly)
  {
    labeled_3: {
                 java.util.List<org.itracker.model.Notification> notifications = getNotificationDao().findByIssueId(issue.getId());
                 java.util.Iterator<org.itracker.model.Notification> iterator = notifications.iterator();
                 while (iterator.hasNext())
                 {
                   org.itracker.model.Notification notification = iterator.next();
                   org.itracker.model.User notificationUser = notification.getUser();
                   if (!activeOnly || notificationUser.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE)
                   {
                     issueNotifications.add(notification);
                   }
                 }
               }
  }
  boolean hasOwner = false;
  if (issue != null)
  {
    if (issue.getOwner() != null)
    {
      org.itracker.model.User ownerModel = issue.getOwner();
      if (ownerModel != null && (!activeOnly || ownerModel.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
      {
        issueNotifications.add(new org.itracker.model.Notification(ownerModel, issue, org.itracker.model.Notification.Role.OWNER));
        hasOwner = true;
      }
    }
    if (!primaryOnly || !hasOwner)
    {
      org.itracker.model.User creatorModel = issue.getCreator();
      if (creatorModel != null && (!activeOnly || creatorModel.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
      {
        issueNotifications.add(new org.itracker.model.Notification(creatorModel, issue, org.itracker.model.Notification.Role.CREATOR));
      }
    }
    org.itracker.model.Project project = getProjectService().getProject(issue.getProject().getId());
    java.util.Collection<org.itracker.model.User> projectOwners = project.getOwners();
    java.util.Iterator<org.itracker.model.User> iterator = projectOwners.iterator();
    slothmark: ;
  }
  if (logger.isDebugEnabled())
  {
    logger.debug(("getIssueNotifications: returning " + issueNotifications));
  }
  return issueNotifications;
}haha 
matches8
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity bde = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  tmp.add(bde);
}{
  java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
  slothmark: ;
  return tmp;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.guide.Guidance g = (wilos.model.spem2.guide.Guidance) extfor$iter.next();
  tmp.add(g);
}{
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.Set<wilos.model.spem2.guide.Guidance> tmp = new java.util.HashSet<wilos.model.spem2.guide.Guidance>();
  java.util.Iterator extfor$iter = _act.getGuidances().iterator();
  slothmark: ;
  return tmp;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  tmp.add(ca);
}{
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
  java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
  slothmark: ;
  return tmp;
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
  processesList.add(new javax.faces.model.SelectItem(process.getId(), process.getPresentationName()));
}{
  java.util.List<javax.faces.model.SelectItem> processesList = new java.util.ArrayList<javax.faces.model.SelectItem>();
  processesList.add(new javax.faces.model.SelectItem("default", wilos.resources.LocaleBean.getText("component.combobox.processchoice.defaultlabel")));
  java.util.List<wilos.model.spem2.process.Process> processes = this.processService.getProcessDao().getAllProcesses();
  java.util.Iterator extfor$iter = processes.iterator();
  slothmark: ;
  return processesList;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  tmp.add(element);
}{
  java.util.Set<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> tmp = new java.util.HashSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement>();
  this.getProjectDao().getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  this.getProjectDao().getSessionFactory().getCurrentSession().refresh(_project);
  java.util.Iterator extfor$iter = _project.getConcreteBreakdownElements().iterator();
  slothmark: ;
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  this.concreteRoleDescriptorsMap.put(concreteRoleDescriptor.getConcreteName(), true);
}{
  this.concreteRoleDescriptorsMap = new java.util.HashMap<java.lang.String, java.lang.Boolean>();
  java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> concreteRoleDescriptorsForAParticipant = new java.util.ArrayList<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
  java.util.Iterator extfor$iter = concreteRoleDescriptorsForAParticipant.iterator();
  slothmark: ;
  return this.concreteRoleDescriptorsMap;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
while (extfor$iter.hasNext())
{
  java.lang.Object obj = (java.lang.Object) extfor$iter.next();
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) obj;
  concreteActivities.add(ca);
}{
  java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> concreteActivities = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
  java.util.Iterator extfor$iter = this.getHibernateTemplate().loadAll(null).iterator();
  slothmark: ;
  return concreteActivities;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkProductDescriptorService.java
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
while (extfor$iter$2.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor tmpListeRd = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$2.next();
  wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = this.concreteRoleDescriptorService.getConcreteRoleDescriptor(tmpListeRd.getId());
  if (crd.getParticipant() == null)
  {
    afficher = false;
  }
  else
  {
    if (crd.getParticipant().getId().equals(_participant.getId()))
    {
      java.util.Iterator extfor$iter$1 = crd.getSuperConcreteActivities().iterator();
      while (extfor$iter$1.hasNext())
      {
        wilos.model.misc.concreteactivity.ConcreteActivity cact1 = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter$1.next();
        java.util.Iterator extfor$iter = _concreteWorkProductDescriptor.getSuperConcreteActivities().iterator();
        while (extfor$iter.hasNext())
        {
          wilos.model.misc.concreteactivity.ConcreteActivity cact2 = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
          if (cact1.getId().equals(cact2.getId()))
          {
            return true;
          }
        }
      }
    }
  }
}{
  boolean afficher = false;
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  wilos.model.spem2.workproduct.WorkProductDescriptor tmp = _concreteWorkProductDescriptor.getWorkProductDescriptor();
  wilos.model.spem2.role.RoleDescriptor tmpRoleDescriptor;
  wilos.model.spem2.workproduct.WorkProductDescriptor tmp2 = this.workProductDescriptorService.getWorkProductDescriptorById(tmp.getId());
  if (tmp2.getResponsibleRoleDescriptor() == null)
  {
    return false;
  }
  tmpRoleDescriptor = tmp2.getResponsibleRoleDescriptor();
  wilos.model.spem2.role.RoleDescriptor rd = this.roleDescriptorService.getRoleDescriptor(tmpRoleDescriptor.getId());
  java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> listeRd = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorForARoleDescriptor(rd.getId());
  java.util.Iterator extfor$iter$2 = listeRd.iterator();
  slothmark: ;
  return afficher;
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
while (i < projects.size())
{
  org.itracker.model.Project project = projects.get(i);
  if (project.getOptions() >= org.itracker.services.util.ProjectUtilities.OPTION_ALLOW_SELF_REGISTERED_CREATE)
  {
    java.util.Set<org.itracker.model.PermissionType> projectPermissions = permissionsMap.get(project.getId());
    if (projectPermissions == null)
    {
      projectPermissions = new java.util.HashSet<org.itracker.model.PermissionType>();
      permissionsMap.put(project.getId(), projectPermissions);
    }
    if (org.itracker.services.util.ProjectUtilities.hasOption(org.itracker.services.util.ProjectUtilities.OPTION_ALLOW_SELF_REGISTERED_CREATE, project.getOptions()))
    {
      projectPermissions.add(org.itracker.model.PermissionType.ISSUE_VIEW_USERS);
      projectPermissions.add(org.itracker.model.PermissionType.ISSUE_CREATE);
    }
    if (org.itracker.services.util.ProjectUtilities.hasOption(org.itracker.services.util.ProjectUtilities.OPTION_ALLOW_SELF_REGISTERED_VIEW_ALL, project.getOptions()))
    {
      projectPermissions.add(org.itracker.model.PermissionType.ISSUE_VIEW_ALL);
    }
  }
  i++;
}{
  java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> permissionsMap = new java.util.HashMap<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>>();
  if (user == null)
  {
    return permissionsMap;
  }
  java.util.List<org.itracker.model.Permission> permissionList = new java.util.ArrayList<org.itracker.model.Permission>();
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      permissionList = authenticator.getUserPermissions(user, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.debug(("Found " + permissionList.size() + " permissions for user " + user.getLogin()));
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ae)
  {
    logger.error(("Authenticator exception: " + ae.getMessage()));
    logger.debug("Authenticator exception: ", ae);
  }
  permissionsMap = org.itracker.services.util.UserUtilities.mapPermissionTypesByProjectId(permissionList);
  if (allowSelfRegister)
  {
    java.util.List<org.itracker.model.Project> projects = projectService.getAllProjects();
    int i = 0;
    slothmark: ;
  }
  return permissionsMap;
}haha 
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
matches4
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
while (idIt.hasNext())
{
  java.lang.Integer id = (java.lang.Integer) idIt.next();
  org.itracker.model.Component c = getComponentDAO().findById(id);
  components.add(c);
}{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.List<org.itracker.model.Component> components = new java.util.ArrayList<org.itracker.model.Component>(componentIds.size());
  org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
  java.util.Iterator<java.lang.Integer> idIt = componentIds.iterator();
  slothmark: ;
  setIssueComponents(issue, components, user, true);
  return true;
}haha 
while (iterator.hasNext())
{
  activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
  i++;
}{
  int i = 0;
  java.util.Collection<org.itracker.model.IssueActivity> activity = getIssueActivityDAO().findByIssueId(issueId);
  org.itracker.model.IssueActivity[] activityArray = new org.itracker.model.IssueActivity[activity.size()];
  java.util.Iterator<org.itracker.model.IssueActivity> iterator = activity.iterator();
  slothmark: ;
  return java.util.Arrays.asList(activityArray);
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
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
matches2
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkBreakdownElementService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cwbde = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement) extfor$iter.next();
  tmp.add(cwbde);
}{
  this.concreteWorkBreakdownElementDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cwbde);
  java.util.List<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement> tmp = new java.util.ArrayList<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement>();
  java.util.Iterator extfor$iter = _cwbde.getSuperConcreteActivities().iterator();
  slothmark: ;
  return tmp;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkOrder cwo = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkOrder) extfor$iter.next();
  java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cPred = this.getConcreteWorkBreakdownElementDao().getConcreteWorkBreakdownElement(cwo.getConcreteWorkOrderId().getConcretePredecessorId());
  hm.put("pred", cPred.getConcreteName());
  hm.put("plannedStartingDate", cPred.getPlannedStartingDate());
  hm.put("plannedFinishingDate", cPred.getPlannedFinishingDate());
  hm.put("linkType", cwo.getConcreteLinkType());
  predecessorHashMap.add(hm);
}{
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> predecessorHashMap = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  if (_cwbde != null)
  {
    _cwbde = this.getConcreteWorkBreakdownElement(_cwbde.getId());
    if (_cwbde != null)
    {
      java.util.Iterator extfor$iter = _cwbde.getConcretePredecessors().iterator();
      slothmark: ;
    }
  }
  return predecessorHashMap;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkOrder cwo = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkOrder) extfor$iter.next();
  java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cSucc = this.getConcreteWorkBreakdownElementDao().getConcreteWorkBreakdownElement(cwo.getConcreteWorkOrderId().getConcreteSuccessorId());
  hm.put("succ", cSucc.getConcreteName());
  hm.put("plannedStartingDate", cSucc.getPlannedStartingDate());
  hm.put("plannedFinishingDate", cSucc.getPlannedFinishingDate());
  hm.put("linkType", cwo.getConcreteLinkType());
  successorHashMap.add(hm);
}{
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> successorHashMap = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  if (_cwbde != null)
  {
    _cwbde = this.getConcreteWorkBreakdownElement(_cwbde.getId());
    if (_cwbde != null)
    {
      java.util.Iterator extfor$iter = _cwbde.getConcreteSuccessors().iterator();
      slothmark: ;
    }
  }
  return successorHashMap;
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//WilosUserBean.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.wilosuser.Role r = (wilos.model.misc.wilosuser.Role) extfor$iter.next();
  if (!r.getRole_id().equalsIgnoreCase(userold.getRole_id()))
    this.roleItem.add(new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
  else
    this.roleItem.add(0, new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
}{
  this.roleItem = new java.util.ArrayList<javax.faces.model.SelectItem>();
  java.util.List<wilos.model.misc.wilosuser.Role> roles = this.roleService.getRoleDao().getRole();
  java.util.Iterator extfor$iter = roles.iterator();
  slothmark: ;
  return this.roleItem;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.wilosuser.Role r = (wilos.model.misc.wilosuser.Role) extfor$iter.next();
  this.roleListFilter.add(new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
}{
  this.roleListFilter = new java.util.ArrayList<javax.faces.model.SelectItem>();
  java.util.List<wilos.model.misc.wilosuser.Role> roles = this.roleService.getRoleDao().getRole();
  java.util.Iterator extfor$iter = roles.iterator();
  slothmark: ;
  wilos.model.misc.wilosuser.Role r = new wilos.model.misc.wilosuser.Role();
  r.setName(wilos.resources.LocaleBean.getText("component.participantlist.all"));
  r.setRole_id("99");
  this.roleListFilter.add(0, new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
  return this.roleListFilter;
}haha 
matches2