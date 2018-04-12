/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.task.TaskDefinition td = (wilos.model.spem2.task.TaskDefinition) extfor$iter.next();
  tmp.add(td);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.role.RoleDefinition td = (wilos.model.spem2.role.RoleDefinition) extfor$iter.next();
  tmp.add(td);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) extfor$iter.next();
  tmp.add(act);
}haha 
matches3
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
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor concreteTaskDescriptor = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  concreteTaskDescriptors.add(concreteTaskDescriptor);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (ca.getConcreteBreakdownElements().contains(crd))
  {
    listToReturn.add(ca);
  }
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration bde = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  tmp.add(bde);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration cit = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  if (cit.getProject().getId().equals(_project.getId()))
    tmp.add(cit);
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase bde = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  tmp.add(bde);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  if (cph.getProject().getId().equals(_project.getId()))
    tmp.add(cph);
}haha 
matches2
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
}haha 
while (fieldsIt.hasNext())
{
  ci = (org.itracker.model.CustomField) fieldsIt.next();
  fieldInfos.add(new org.itracker.web.actions.admin.project.EditProjectFormActionUtil.CustomFieldInfo(ci.getId(), org.itracker.services.util.CustomFieldUtilities.getCustomFieldName(ci.getId(), org.itracker.web.util.LoginUtilities.getCurrentLocale(request)), org.itracker.services.util.CustomFieldUtilities.getTypeString(ci.getFieldType(), org.itracker.web.util.LoginUtilities.getCurrentLocale(request))));
}haha 
while (it.hasNext())
{
  org.itracker.web.ptos.ProjectScriptPTO projectScript = new org.itracker.web.ptos.ProjectScriptPTO(it.next(), locale);
  scriptPTOs.add(projectScript);
}haha 
matches3
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
}haha 
while (extfor$iter.hasNext())
{
  java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (hashMap.get("id").equals(workProductId))
  {
    if (hashMap.get("nodeType").equals("node"))
    {
      hashMap.put("expansionImage", EXPAND_TABLE_ARROW);
      hashMap.put("isDisabled", true);
      index = this.expTableContentWorkProduct.indexOf(hashMap);
      this.expTableContentWorkProduct.addAll((index + 1), this.projectService.getDifferentPathsOfWorkProductDescriptorInProcess(process, workProductName));
      return;
    }
  }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (!cact.getState().equals(wilos.utils.Constantes.State.FINISHED))
  {
    activityList.add(new javax.faces.model.SelectItem(cact.getId(), cact.getConcreteName()));
  }
}haha 
while (extfor$iter.hasNext())
{
  java.util.HashMap hm = (java.util.HashMap) extfor$iter.next();
  if (!hm.get("in").equals(hm.get("flag_in")))
  {
    inputConcreteTasksIDs.add(((java.lang.String) hm.get("ID")));
  }
  if (!hm.get("out").equals(hm.get("flag_out")))
  {
    outputConcreteTasksIDs.add(((java.lang.String) hm.get("ID")));
  }
  if (!hm.get("inOptionnal").equals(hm.get("flag_inOptionnal")))
  {
    inputOptionnalConcreteTasksIDs.add(((java.lang.String) hm.get("ID")));
  }
}haha 
matches4
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement concreteBreakdownElement = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  if (concreteBreakdownElement.getId().equals(_activityId))
  {
    concreteRDList.add(concreteRD);
  }
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
}haha 
while (iterator.hasNext())
{
  org.itracker.model.Notification notification = iterator.next();
  org.itracker.model.User notificationUser = notification.getUser();
  if (!activeOnly || notificationUser.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE)
  {
    issueNotifications.add(notification);
  }
}haha 
while (iterator.hasNext())
{
  org.itracker.model.User projectOwner = (org.itracker.model.User) iterator.next();
  if (projectOwner != null && (!activeOnly || projectOwner.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
  {
    issueNotifications.add(new org.itracker.model.Notification(projectOwner, issue, org.itracker.model.Notification.Role.PO));
  }
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
  processesList.add(new javax.faces.model.SelectItem(process.getId(), process.getPresentationName()));
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  if (!project.getIsFinished())
  {
    unfinishedP.add(project);
  }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  tmp.add(element);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  if (project.getProcess() == null)
    projectList.add(project);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
  if (project.getProcess() != null)
    projectList.add(project);
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity bde = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  tmp.add(bde);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject().getId().equals(_project.getId()))
    tmp.add(cact);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
  tmp.add(bde);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.guide.Guidance g = (wilos.model.spem2.guide.Guidance) extfor$iter.next();
  tmp.add(g);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  tmp.add(ca);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject().getId().equals(_project.getId()))
    tmp.add(cact);
}haha 
matches6
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project currentProject = (wilos.model.misc.project.Project) extfor$iter.next();
  java.util.HashMap<java.lang.String, java.lang.Object> line = new java.util.HashMap<java.lang.String, java.lang.Object>();
  line.put("project_id", currentProject.getId());
  line.put("affected", plist.get(currentProject));
  if (!plist.get(currentProject))
  {
    line.put("selectItem", (currentProject.getId() + "1"));
  }
  else
  {
    line.put("selectItem", (currentProject.getId() + "2"));
  }
  line.put("name", currentProject.getConcreteName());
  line.put("creationDate", formatter.format(currentProject.getCreationDate()));
  line.put("launchingDate", formatter.format(currentProject.getLaunchingDate()));
  line.put("description", currentProject.getDescription());
  wilos.model.misc.wilosuser.Participant projectManager = currentProject.getProjectManager();
  java.lang.String name = null;
  if (projectManager == null)
  {
    name = wilos.resources.LocaleBean.getText("component.table1participantprojectManager.noAffectation");
    line.put("visibleAffectManager", true);
    line.put("displayOptionProjectManager", true);
  }
  else
  {
    name = projectManager.getFirstname() + " " + projectManager.getName();
    if (projectManager.getId().equals(user.getId()))
    {
      line.put("selectItem", (currentProject.getId() + "3"));
      line.put("displayOptionProjectManager", true);
    }
    else
    {
      line.put("displayOptionProjectManager", false);
    }
  }
  line.put("projectManagerName", name);
  if (this.checkTasks(line))
  {
    line.put("activeTasks", wilos.resources.LocaleBean.getText("component.tableparticipantproject.noActiveTasks"));
    line.put("disabled", false);
  }
  else
  {
    line.put("activeTasks", wilos.resources.LocaleBean.getText("component.tableparticipantproject.activeTasks"));
    line.put("disabled", ((java.lang.Boolean) line.get("affected")));
  }
  this.affectedProjectsList.add(line);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project currentProject = (wilos.model.misc.project.Project) extfor$iter.next();
  wilos.model.misc.wilosuser.Participant projectManager = manageableProjects.get(currentProject);
  java.util.HashMap<java.lang.String, java.lang.Object> ligne = new java.util.HashMap<java.lang.String, java.lang.Object>();
  ligne.put("project_id", currentProject.getId());
  ligne.put("name", currentProject.getConcreteName());
  ligne.put("creationDate", formatter.format(currentProject.getCreationDate()));
  ligne.put("launchingDate", formatter.format(currentProject.getLaunchingDate()));
  ligne.put("description", currentProject.getDescription());
  if (projectManager == null)
  {
    ligne.put("projectManagerName", wilos.resources.LocaleBean.getText("component.table1participantprojectManager.noAffectation"));
    ligne.put("projectManager_id", "");
    ligne.put("affected", new java.lang.Boolean(false));
    ligne.put("hasOtherManager", new java.lang.Boolean(false));
    this.manageableProjectsList.add(ligne);
  }
  else
  {
    java.lang.String projectManagerName = projectManager.getFirstname().concat((" " + projectManager.getName()));
    ligne.put("projectManager_id", projectManager.getId());
    ligne.put("projectManagerName", projectManagerName);
    if (projectManager.getId().equals(user.getId()))
    {
      ligne.put("affected", new java.lang.Boolean(true));
      ligne.put("hasOtherManager", new java.lang.Boolean(false));
      this.manageableProjectsList.add(ligne);
    }
    else
    {
      ligne.put("affected", new java.lang.Boolean(true));
      ligne.put("hasOtherManager", new java.lang.Boolean(true));
      this.manageableProjectsList.add(ligne);
    }
  }
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
while (extfor$iter.hasNext())
{
  java.lang.Object obj = (java.lang.Object) extfor$iter.next();
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) obj;
  concreteActivities.add(ca);
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
}haha 
while (extfor$iter.hasNext() && !break_0)
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor tmpListeRd = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  if (!break_0)
    if (tmpListeRd.getParticipant() != null)
    {
      if (!break_0)
        if (tmpListeRd.getParticipant().getId().equals(_user.getId()))
        {
          this.concreteRoleDescriptorService.getConcreteRoleDescriptorDao().saveOrUpdateConcreteRoleDescriptor(tmpListeRd);
          _concreteWorkProductDescriptor.addResponsibleConcreteRoleDescriptor(tmpListeRd);
          break_0 = true;
        }
    }
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
}haha 
while (i < editUsers.size())
{
  users.add(editUsers.get(i));
  i++;
}haha 
while (i < otherUsers.size())
{
  users.add(otherUsers.get(i));
  i++;
}haha 
while (iter.hasNext())
{
  userList.add(((org.itracker.model.User) iter.next()));
  j++;
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
while (idIt.hasNext())
{
  java.lang.Integer id = (java.lang.Integer) idIt.next();
  org.itracker.model.Component c = getComponentDAO().findById(id);
  components.add(c);
}haha 
while (iterator.hasNext())
{
  org.itracker.model.Component component = iterator.next();
  if (!issue.getComponents().contains(component))
  {
    addComponentsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.added")).append(": ").append(component.getName()).toString());
    issue.getComponents().add(component);
  }
}haha 
while (iterator.hasNext())
{
  org.itracker.model.Version version = iterator.next();
  if (changesBuf.length() > 0)
  {
    changesBuf.append(", ");
  }
  changesBuf.append(version.getNumber());
  issue.getVersions().add(version);
}haha 
while (versionsIdIt.hasNext())
{
  java.lang.Integer id = versionsIdIt.next();
  versions.add(getVersionDAO().findByPrimaryKey(id));
}haha 
while (iterator.hasNext())
{
  componentIds.add(((org.itracker.model.Component) iterator.next()).getId());
}haha 
while (iterator.hasNext())
{
  versionIds.add(((org.itracker.model.Version) iterator.next()).getId());
}haha 
matches6
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbde = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  tmp.add(cbde);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject() != null && cact.getProject().equals(_cact))
  {
    tmp.add(cact);
  }
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
while (projectIt.hasNext())
{
  org.itracker.model.Project project = projectIt.next();
  ptos.add(createProjectPTO(project, projectService, permissions));
}haha 
while (projectIt.hasNext())
{
  org.itracker.model.Project project = projectIt.next();
  ptos.add(createProjectPTO(project, projectService, permissions));
}haha 
matches2
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
    concreteRolesList.add(concreteRoleDescriptor);
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
    if (!concreteRoleDescriptor.getRoleDescriptor().getPresentationName().equals(""))
      concreteRolesList.add(concreteRoleDescriptor.getRoleDescriptor().getName());
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.project.Project p = (wilos.model.misc.project.Project) extfor$iter.next();
  if (chargedParticipant.getAffectedProjectList().contains(p))
  {
    affectedProjectList.add(p);
  }
}haha 
matches5
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkBreakdownElementService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cwbde = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement) extfor$iter.next();
  tmp.add(cwbde);
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.wilosuser.Role r = (wilos.model.misc.wilosuser.Role) extfor$iter.next();
  this.roleListFilter.add(new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
}haha 
matches2
