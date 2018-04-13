/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.task.TaskDefinition td = (wilos.model.spem2.task.TaskDefinition) extfor$iter.next();
  tmp.add(td);
}{
  labeled_1: {
               java.util.Set<wilos.model.spem2.task.TaskDefinition> tmp = new java.util.HashSet<wilos.model.spem2.task.TaskDefinition>();
               this.guidanceDao.getSessionFactory().getCurrentSession().saveOrUpdate(_guidance);
               java.util.Iterator extfor$iter = _guidance.getTaskDefinitions().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.role.RoleDefinition td = (wilos.model.spem2.role.RoleDefinition) extfor$iter.next();
  tmp.add(td);
}{
  labeled_2: {
               java.util.Set<wilos.model.spem2.role.RoleDefinition> tmp = new java.util.HashSet<wilos.model.spem2.role.RoleDefinition>();
               this.guidanceDao.getSessionFactory().getCurrentSession().saveOrUpdate(_guidance);
               java.util.Iterator extfor$iter = _guidance.getRoleDefinitions().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//MoveIssueFormAction.java
while (i < projects.size())
{
  if (projects.get(i).getId() != null && !projects.get(i).equals(issue.getProject()))
  {
    if (org.itracker.services.util.UserUtilities.hasPermission(userPermissions, projects.get(i).getId(), new int[] {
                                                                                                                     org.itracker.services.util.UserUtilities.PERMISSION_EDIT,
                                                                                                                     org.itracker.services.util.UserUtilities.PERMISSION_CREATE,
                                                                                                                   }))
    {
      availableProjects.add(projects.get(i));
    }
  }
  i++;
}{
  labeled_1: {
               java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> userPermissions = getUserPermissions(request.getSession());
               java.util.List<org.itracker.model.Project> availableProjects = new java.util.ArrayList<org.itracker.model.Project>();
               int i = 0;
               slothmark: ;
               java.util.Collections.sort(availableProjects, new org.itracker.model.Project.ProjectComparator());
             }
  return availableProjects;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (ca.getConcreteBreakdownElements().contains(crd))
  {
    listToReturn.add(ca);
  }
}{
  labeled_1: {
               wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = this.getConcreteRoleDescriptor(_crdid);
               java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listTmp = this.concreteActivityService.getAllConcreteActivities();
               java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listToReturn = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
               java.util.Iterator extfor$iter = listTmp.iterator();
               slothmark: ;
             }
  return listToReturn;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration cit = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  if (cit.getProject().getId().equals(_project.getId()))
    tmp.add(cit);
}{
  labeled_1: {
               java.util.Set<wilos.model.misc.concreteiteration.ConcreteIteration> tmp = new java.util.HashSet<wilos.model.misc.concreteiteration.ConcreteIteration>();
               this.iterationDao.getSessionFactory().getCurrentSession().saveOrUpdate(_iteration);
               this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
               java.util.Iterator extfor$iter = _iteration.getConcreteIterations().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  if (cph.getProject().getId().equals(_project.getId()))
    tmp.add(cph);
}{
  labeled_1: {
               java.util.Set<wilos.model.misc.concretephase.ConcretePhase> tmp = new java.util.HashSet<wilos.model.misc.concretephase.ConcretePhase>();
               this.phaseDao.getSessionFactory().getCurrentSession().saveOrUpdate(_phase);
               this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
               java.util.Iterator extfor$iter = _phase.getConcretePhases().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//EditProjectFormActionUtil.java
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
                 slothmark: ;
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//WorkProductSExpTableBean.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getState().equals(wilos.utils.Constantes.State.FINISHED))
  {
    numberOfFinishedActivity++;
  }
}{
  int numberOfFinishedActivity = 0;
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  if (this.concreteActivityService.getConcreteActivitiesFromProject(project).size() != 0)
  {
    labeled_1: {
                 java.util.Iterator extfor$iter = this.concreteActivityService.getConcreteActivitiesFromProject(project).iterator();
                 slothmark: ;
               }
    labeled_2: {
                 if (numberOfFinishedActivity == this.concreteActivityService.getConcreteActivitiesFromProject(project).size())
                 {
                   this.allConcreteActivitiesAreFinishedWorkProduct = true;
                 }
                 else
                 {
                   this.allConcreteActivitiesAreFinishedWorkProduct = false;
                 }
               }
  }
  return this.allConcreteActivitiesAreFinishedWorkProduct;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//RoleService.java
while (i < user.size())
{
  int a = 0;
  while (a < role.size())
  {
    if (user.get(i).getRole_id().equalsIgnoreCase(role.get(a).getRole_id()))
    {
      user.get(i).setRole_name(role.get(a).getName());
      wilos.model.misc.wilosuser.WilosUser userok = user.get(i);
      listUser.add(userok);
    }
    a++;
  }
  i++;
}{
  labeled_1: {
               java.util.List<wilos.model.misc.wilosuser.WilosUser> listUser = new java.util.ArrayList<wilos.model.misc.wilosuser.WilosUser>();
               java.util.List<wilos.model.misc.wilosuser.Role> role = this.roleDao.getRole();
               int i = 0;
               slothmark: ;
             }
  return listUser;
}haha 
while (a < role.size())
{
  if (user.get(i).getRole_id().equalsIgnoreCase(role.get(a).getRole_id()))
  {
    user.get(i).setRole_name(role.get(a).getName());
    wilos.model.misc.wilosuser.WilosUser userok = user.get(i);
    listUser.add(userok);
  }
  a++;
}{
  labeled_1: {
               java.util.List<wilos.model.misc.wilosuser.WilosUser> listUser = new java.util.ArrayList<wilos.model.misc.wilosuser.WilosUser>();
               java.util.List<wilos.model.misc.wilosuser.Role> role = this.roleDao.getRole();
               int i = 0;
               while (i < user.size())
               {
                 int a = 0;
                 slothmark: ;
                 i++;
               }
             }
  return listUser;
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleAffectationService.java
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRD = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$1.next();
  concreteRD = this.concreteRoleDescriptorService.getConcreteRoleDescriptor(concreteRD.getId());
  java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> globalCA = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
  globalCA.addAll(concreteRD.getSuperConcreteActivities());
  java.util.Iterator extfor$iter = globalCA.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement concreteBreakdownElement = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
    if (concreteBreakdownElement.getId().equals(_activityId))
    {
      concreteRDList.add(concreteRD);
    }
  }
}{
  labeled_2: {
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> concreteRDList = new java.util.ArrayList<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> globalCRD = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorsForProject(_projectId);
               java.util.Iterator extfor$iter$1 = globalCRD.iterator();
               slothmark: ;
             }
  return concreteRDList;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement concreteBreakdownElement = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  if (concreteBreakdownElement.getId().equals(_activityId))
  {
    concreteRDList.add(concreteRD);
  }
}{
  labeled_2: {
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> concreteRDList = new java.util.ArrayList<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> globalCRD = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorsForProject(_projectId);
               java.util.Iterator extfor$iter$1 = globalCRD.iterator();
               while (extfor$iter$1.hasNext())
               {
                 wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRD = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$1.next();
                 concreteRD = this.concreteRoleDescriptorService.getConcreteRoleDescriptor(concreteRD.getId());
                 java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> globalCA = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
                 globalCA.addAll(concreteRD.getSuperConcreteActivities());
                 java.util.Iterator extfor$iter = globalCA.iterator();
                 slothmark: ;
               }
             }
  return concreteRDList;
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//NotificationServiceImpl.java
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
                 slothmark: ;
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
               slothmark: ;
             }
  if (logger.isDebugEnabled() && null != history)
  {
    logger.debug(("handleIssueNotification: got most recent history: " + history + " (" + history.getDescription() + ")"));
  }
  java.util.Iterator<javax.mail.internet.InternetAddress> iaIt = recipientsLocales.keySet().iterator();
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
  }
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
  org.itracker.model.Notification notification = iterator.next();
  org.itracker.model.User notificationUser = notification.getUser();
  if (!activeOnly || notificationUser.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE)
  {
    issueNotifications.add(notification);
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
                 slothmark: ;
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
    while (iterator.hasNext())
    {
      org.itracker.model.User projectOwner = (org.itracker.model.User) iterator.next();
      if (projectOwner != null && (!activeOnly || projectOwner.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
      {
        issueNotifications.add(new org.itracker.model.Notification(projectOwner, issue, org.itracker.model.Notification.Role.PO));
      }
    }
  }
  if (logger.isDebugEnabled())
  {
    logger.debug(("getIssueNotifications: returning " + issueNotifications));
  }
  return issueNotifications;
}haha 
while (i < notifications.size())
{
  if (role == org.itracker.model.Notification.Role.ANY || notifications.get(i).getRole() == role)
  {
    if (notifications.get(i).getUser().getId().equals(userId))
    {
      result = true;
    }
  }
  i++;
}{
  labeled_4: {
               boolean result = false;
               if (issue != null && userId != null)
               {
                 java.util.List<org.itracker.model.Notification> notifications = getIssueNotifications(issue, false, false);
                 int i = 0;
                 slothmark: ;
               }
               result = false;
             }
  return result;
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
  if (process.getPresentationName().equals(_presentationName) && !_processId.equals(process.getId()))
    r = true;
}{
  labeled_1: {
               boolean r;
               r = false;
               java.util.Iterator extfor$iter = this.processService.getAllProcesses().iterator();
               slothmark: ;
               r = false;
             }
  return r;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  projectName = project.getConcreteName().toUpperCase();
  if (projectName.equals(_projectName.toUpperCase()))
  {
    return true;
  }
}{
  labeled_1: {
               boolean found = false;
               java.lang.String projectName;
               java.util.List<wilos.model.misc.project.Project> projects = this.projectDao.getAllProjects();
               java.util.Iterator extfor$iter = projects.iterator();
               slothmark: ;
             }
  return found;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  if (!project.getIsFinished())
  {
    unfinishedP.add(project);
  }
}{
  labeled_3: {
               java.util.Set<wilos.model.misc.project.Project> unfinishedP = new java.util.HashSet<wilos.model.misc.project.Project>();
               java.util.List<wilos.model.misc.project.Project> projects = this.projectDao.getAllProjects();
               java.util.Iterator extfor$iter = projects.iterator();
               slothmark: ;
             }
  return unfinishedP;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  if (element instanceof wilos.model.misc.concreterole.ConcreteRoleDescriptor)
  {
    wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) element;
    tmp.add(crd);
  }
  else
    if (!(element instanceof wilos.model.misc.concretetask.ConcreteTaskDescriptor) && !(element instanceof wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor) && !(element instanceof wilos.model.misc.concretemilestone.ConcreteMilestone))
    {
      wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) element;
      tmp.addAll(this.getConcreteRoleDescriptorsFromProject(cact));
    }
}{
  java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> tmp = new java.util.HashSet<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
  labeled_4: {
               java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  if (project.getProcess() == null)
    projectList.add(project);
}{
  labeled_5: {
               java.util.Set<wilos.model.misc.project.Project> projectList = new java.util.HashSet<wilos.model.misc.project.Project>();
               java.util.List<wilos.model.misc.project.Project> tmpList = new java.util.ArrayList<wilos.model.misc.project.Project>();
               tmpList = this.projectDao.getAllProjects();
               java.util.Iterator extfor$iter = tmpList.iterator();
               slothmark: ;
             }
  return projectList;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  if (project.getProcess() != null)
    projectList.add(project);
}{
  labeled_6: {
               java.util.Set<wilos.model.misc.project.Project> projectList = new java.util.HashSet<wilos.model.misc.project.Project>();
               java.util.List<wilos.model.misc.project.Project> tmpList = new java.util.ArrayList<wilos.model.misc.project.Project>();
               tmpList = this.projectDao.getAllProjects();
               java.util.Iterator extfor$iter = tmpList.iterator();
               slothmark: ;
             }
  return projectList;
}haha 
matches5
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject().getId().equals(_project.getId()))
    tmp.add(cact);
}{
  labeled_1: {
               java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
               this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
               java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject().getId().equals(_project.getId()))
    tmp.add(cact);
}{
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  labeled_2: {
               java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
               java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  if (ctd.getState().equals(wilos.utils.Constantes.State.STARTED))
  {
    wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = ctd.getMainConcreteRoleDescriptor();
    wilos.model.misc.wilosuser.Participant participant = this.concreteRoleDescriptorService.getParticipant(crd);
    java.lang.String login = this.participantService.getParticipantLogin(participant);
    if (login.equals(user.getLogin()))
    {
      allowed = false;
    }
  }
}{
  wilos.model.misc.wilosuser.Participant user = getParticipantFromSession();
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  this.projectService = (wilos.business.services.misc.project.ProjectService) context.getApplication().getVariableResolver().resolveVariable(context, "ProjectService");
  labeled_1: {
               java.lang.Boolean allowed = true;
               java.lang.String project_id = (java.lang.String) line.get("project_id");
               java.util.List<wilos.model.misc.concretetask.ConcreteTaskDescriptor> concreteTasks = this.projectService.getProcessService().getTaskDescriptorService().getConcreteTaskDescriptorDao().getAllConcreteTaskDescriptorsForProject(project_id);
               java.util.Iterator extfor$iter = concreteTasks.iterator();
               slothmark: ;
             }
  return allowed;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkProductDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (ca.getConcreteBreakdownElements().contains(crd))
  {
    listToReturn.add(ca);
  }
}{
  labeled_1: {
               wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor crd = this.getConcreteWorkProductDescriptor(_cwpdid);
               java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listTmp = this.concreteActivityService.getAllConcreteActivities();
               java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listToReturn = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
               java.util.Iterator extfor$iter = listTmp.iterator();
               slothmark: ;
             }
  return listToReturn;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
while (iterator.hasNext())
{
  componentIds.add(((org.itracker.model.Component) iterator.next()).getId());
}{
  labeled_1: {
               java.util.HashSet<java.lang.Integer> componentIds = new java.util.HashSet<java.lang.Integer>();
               org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
               java.util.Collection<org.itracker.model.Component> components = issue.getComponents();
               java.util.Iterator<org.itracker.model.Component> iterator = components.iterator();
               slothmark: ;
             }
  return componentIds;
}haha 
while (iterator.hasNext())
{
  versionIds.add(((org.itracker.model.Version) iterator.next()).getId());
}{
  labeled_2: {
               java.util.HashSet<java.lang.Integer> versionIds = new java.util.HashSet<java.lang.Integer>();
               org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
               java.util.Collection<org.itracker.model.Version> versions = issue.getVersions();
               java.util.Iterator<org.itracker.model.Version> iterator = versions.iterator();
               slothmark: ;
             }
  return versionIds;
}haha 
while (iterator.hasNext())
{
  activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
  i++;
}{
  labeled_3: {
               int i = 0;
               java.util.Collection<org.itracker.model.IssueActivity> activity = getIssueActivityDAO().findByIssueIdAndNotification(issueId, notificationSent);
               org.itracker.model.IssueActivity[] activityArray = new org.itracker.model.IssueActivity[activity.size()];
               java.util.Iterator<org.itracker.model.IssueActivity> iterator = activity.iterator();
               slothmark: ;
             }
  return java.util.Arrays.asList(activityArray);
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject() != null && cact.getProject().equals(_cact))
  {
    tmp.add(cact);
  }
}{
  labeled_1: {
               java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
               this.concreteActivityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
               java.util.Iterator extfor$iter = this.getAllConcreteActivities().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
while (projectIt.hasNext())
{
  org.itracker.model.Project project = (org.itracker.model.Project) projectIt.next();
  if (!org.itracker.services.util.UserUtilities.hasPermission(permissions, project.getId(), permissionFlags))
  {
    projects_tmp.remove(project);
  }
}{
  labeled_1: {
               java.util.List<org.itracker.model.Project> projects = projectService.getAllAvailableProjects();
               java.util.ArrayList<org.itracker.model.Project> projects_tmp = new java.util.ArrayList<org.itracker.model.Project>(projects);
               java.util.Iterator<org.itracker.model.Project> projectIt = projects.iterator();
               slothmark: ;
             }
  projects = projects_tmp;
  java.util.Collections.sort(projects, new org.itracker.model.Project.ProjectComparator());
  java.util.ArrayList<org.itracker.web.ptos.ProjectPTO> ptos = new java.util.ArrayList<org.itracker.web.ptos.ProjectPTO>(projects_tmp.size());
  projectIt = projects.iterator();
  while (projectIt.hasNext())
  {
    org.itracker.model.Project project = projectIt.next();
    ptos.add(createProjectPTO(project, projectService, permissions));
  }
  return ptos;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkBreakdownElementService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cwbde = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement) extfor$iter.next();
  wilos.model.spem2.workbreakdownelement.WorkBreakdownElement wbde = cwbde.getWorkBreakdownElement();
  if (!(cwbde instanceof wilos.model.misc.project.Project) && wbde != null)
  {
    java.lang.String id = cwbde.getProject().getId();
    if (id.equals(_project.getId()) && wbde.getSuccessors().size() != 0)
    {
      tmp.add(cwbde);
    }
  }
}{
  this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  labeled_1: {
               java.util.List<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement> tmp = new java.util.ArrayList<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement>();
               java.util.Iterator extfor$iter = this.concreteWorkBreakdownElementDao.getAllConcreteWorkBreakdownElements().iterator();
               slothmark: ;
             }
  return tmp;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ParticipantService.java
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor projectConcreteRole = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$1.next();
  java.util.Iterator extfor$iter = participantConcreteRolesList.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
    if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
      concreteRolesList.add(concreteRoleDescriptor);
  }
}{
  labeled_1: {
               java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> concreteRolesList = new java.util.HashSet<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
               java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> participantConcreteRolesList = this.getParticipant(_participantId).getConcreteRoleDescriptors();
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> projectConcreteRolesList = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorsForProject(_projectId);
               if (projectConcreteRolesList != null)
               {
                 java.util.Iterator extfor$iter$1 = projectConcreteRolesList.iterator();
                 slothmark: ;
               }
             }
  return concreteRolesList;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
    concreteRolesList.add(concreteRoleDescriptor);
}{
  labeled_1: {
               java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> concreteRolesList = new java.util.HashSet<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
               java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> participantConcreteRolesList = this.getParticipant(_participantId).getConcreteRoleDescriptors();
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> projectConcreteRolesList = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorsForProject(_projectId);
               if (projectConcreteRolesList != null)
               {
                 java.util.Iterator extfor$iter$1 = projectConcreteRolesList.iterator();
                 while (extfor$iter$1.hasNext())
                 {
                   wilos.model.misc.concreterole.ConcreteRoleDescriptor projectConcreteRole = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$1.next();
                   java.util.Iterator extfor$iter = participantConcreteRolesList.iterator();
                   slothmark: ;
                 }
               }
             }
  return concreteRolesList;
}haha 
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor projectConcreteRole = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$1.next();
  java.util.Iterator extfor$iter = participantConcreteRolesList.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
    if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
      if (!concreteRoleDescriptor.getRoleDescriptor().getPresentationName().equals(""))
        concreteRolesList.add(concreteRoleDescriptor.getRoleDescriptor().getName());
  }
}{
  labeled_2: {
               java.util.Set<java.lang.String> concreteRolesList = new java.util.HashSet<java.lang.String>();
               java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> participantConcreteRolesList = this.getParticipant(_participantId).getConcreteRoleDescriptors();
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> projectConcreteRolesList = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorsForProject(_projectId);
               if (projectConcreteRolesList != null)
               {
                 java.util.Iterator extfor$iter$1 = projectConcreteRolesList.iterator();
                 slothmark: ;
               }
             }
  return concreteRolesList;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
    if (!concreteRoleDescriptor.getRoleDescriptor().getPresentationName().equals(""))
      concreteRolesList.add(concreteRoleDescriptor.getRoleDescriptor().getName());
}{
  labeled_2: {
               java.util.Set<java.lang.String> concreteRolesList = new java.util.HashSet<java.lang.String>();
               java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> participantConcreteRolesList = this.getParticipant(_participantId).getConcreteRoleDescriptors();
               java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> projectConcreteRolesList = this.concreteRoleDescriptorService.getAllConcreteRoleDescriptorsForProject(_projectId);
               if (projectConcreteRolesList != null)
               {
                 java.util.Iterator extfor$iter$1 = projectConcreteRolesList.iterator();
                 while (extfor$iter$1.hasNext())
                 {
                   wilos.model.misc.concreterole.ConcreteRoleDescriptor projectConcreteRole = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter$1.next();
                   java.util.Iterator extfor$iter = participantConcreteRolesList.iterator();
                   slothmark: ;
                 }
               }
             }
  return concreteRolesList;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project p = (wilos.model.misc.project.Project) extfor$iter.next();
  if (chargedParticipant.getAffectedProjectList().contains(p))
  {
    affectedProjectList.add(p);
  }
}{
  labeled_3: {
               java.util.List<wilos.model.misc.project.Project> affectedProjectList = new java.util.ArrayList<wilos.model.misc.project.Project>();
               java.util.HashSet<wilos.model.misc.project.Project> allProjectList = new java.util.HashSet<wilos.model.misc.project.Project>();
               wilos.model.misc.wilosuser.Participant chargedParticipant = new wilos.model.misc.wilosuser.Participant();
               java.lang.String login = participant.getLogin();
               chargedParticipant = this.participantDao.getParticipant(login);
               allProjectList = (java.util.HashSet<wilos.model.misc.project.Project>) this.projectService.getUnfinishedProjects();
               java.util.Iterator extfor$iter = allProjectList.iterator();
               slothmark: ;
             }
  return affectedProjectList;
}haha 
matches5
/Users/remywang/metalift/txl/qbs/allbench//LoginService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.wilosuser.WilosUser user = (wilos.model.misc.wilosuser.WilosUser) extfor$iter.next();
  if (user.getLogin() != null)
  {
    userLogin = user.getLogin().toUpperCase();
    if (userLogin.equals(_login.toUpperCase()))
      found = true;
  }
}{
  labeled_1: {
               boolean found = false;
               java.lang.String userLogin;
               java.util.List<wilos.model.misc.wilosuser.WilosUser> wilosUsers = this.wilosUserDao.getAllWilosUsers();
               java.util.Iterator extfor$iter = wilosUsers.iterator();
               slothmark: ;
             }
  return found;
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.wilosuser.WilosUser user = (wilos.model.misc.wilosuser.WilosUser) extfor$iter.next();
  if (user.getLogin() != null)
  {
    userLogin = user.getLogin().toUpperCase();
    if (!userLogin.equalsIgnoreCase(_login_old))
    {
      if (userLogin.equals(_login.toUpperCase()))
      {
        found = true;
      }
    }
  }
}{
  labeled_2: {
               boolean found = false;
               java.lang.String userLogin;
               java.util.List<wilos.model.misc.wilosuser.WilosUser> wilosUsers = this.wilosUserDao.getAllWilosUsers();
               java.util.Iterator extfor$iter = wilosUsers.iterator();
               slothmark: ;
             }
  return found;
}haha 
matches2
