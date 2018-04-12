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
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration cit = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  if (cit.getProject().getId().equals(_project.getId()))
    tmp.add(cit);
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  if (cph.getProject().getId().equals(_project.getId()))
    tmp.add(cph);
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
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject().getId().equals(_project.getId()))
    tmp.add(cact);
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject().getId().equals(_project.getId()))
    tmp.add(cact);
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
  if (process.getPresentationName().equals(_presentationName) && !_processId.equals(process.getId()))
    r = true;
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
}haha 
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
matches5
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
while (iterator.hasNext())
{
  componentIds.add(((org.itracker.model.Component) iterator.next()).getId());
}haha 
while (iterator.hasNext())
{
  versionIds.add(((org.itracker.model.Version) iterator.next()).getId());
}haha 
while (iterator.hasNext())
{
  activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
  i++;
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
while (projectIt.hasNext())
{
  org.itracker.model.Project project = (org.itracker.model.Project) projectIt.next();
  if (!org.itracker.services.util.UserUtilities.hasPermission(permissions, project.getId(), permissionFlags))
  {
    projects_tmp.remove(project);
  }
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact.getProject() != null && cact.getProject().equals(_cact))
  {
    tmp.add(cact);
  }
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
}haha 
matches2
