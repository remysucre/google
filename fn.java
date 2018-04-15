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
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProcessManagerBean.java
{
  labeled_1: {
               if (this.getProcessManagerList().size() == 0)
               {
                 this.processManagerView = "processManagerView_null";
               }
               else
               {
                 this.processManagerView = "processManagerView_not_null";
               }
             }
  return this.processManagerView;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
{
  labeled_2: {
               if (this.getParticipantsList().size() == 0)
               {
                 this.participantView = "participantView_null";
               }
               else
               {
                 this.participantView = "participantView_not_null";
               }
             }
  return this.participantView;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
{
  labeled_2: {
               java.util.List<org.itracker.model.User> users = new java.util.ArrayList<org.itracker.model.User>();
               if (projectId != null)
               {
                 java.util.List<org.itracker.model.Permission> permissions = permissionDAO.findByProjectIdAndPermission(projectId, permissionType);
                 for (org.itracker.model.Permission permission : permissions) {
                                                                                users.add(permission.getUser());
                                                                              }
               }
             }
  return users;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
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
