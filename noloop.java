/Users/rem/metalift/txl/qbs/allbench//RoleDao.java
labeled_1: {
             wilos.model.misc.wilosuser.Role r;
             r = null;
             java.util.List<wilos.model.misc.wilosuser.Role> list = this.getHibernateTemplate().find(" from Role r where r.role_id=?", id);
             if (list.size() > 0)
             {
               r = (wilos.model.misc.wilosuser.Role) list.get(0);
             }
             else
             {
               r = null;
             }
             return r;
           }{
  slothmark: ;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//AffectedtoDao.java
labeled_1: {
             String r;
             r = "";
             java.util.List affected = this.getHibernateTemplate().find("from Affectedto wu where wu.participant_id=?", _id);
             if (affected.size() > 0)
             {
               r = "false";
             }
             else
             {
               r = "true";
             }
           }{
  slothmark: ;
  return r;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//WorkProductSExpTableBean.java
labeled_2: {
             if (numberOfFinishedActivity == this.concreteActivityService.getConcreteActivitiesFromProject(project).size())
             {
               this.allConcreteActivitiesAreFinishedWorkProduct = true;
             }
             else
             {
               this.allConcreteActivitiesAreFinishedWorkProduct = false;
             }
           }{
  int numberOfFinishedActivity = 0;
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  if (this.concreteActivityService.getConcreteActivitiesFromProject(project).size() != 0)
  {
    labeled_1: {
                 java.util.Iterator extfor$iter = this.concreteActivityService.getConcreteActivitiesFromProject(project).iterator();
                 while (extfor$iter.hasNext())
                 {
                   wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
                   if (cact.getState().equals(wilos.utils.Constantes.State.FINISHED))
                   {
                     numberOfFinishedActivity++;
                   }
                 }
               }
    slothmark: ;
  }
  return this.allConcreteActivitiesAreFinishedWorkProduct;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//ProcessManagerBean.java
labeled_1: {
             if (this.getProcessManagerList().size() == 0)
             {
               this.processManagerView = "processManagerView_null";
             }
             else
             {
               this.processManagerView = "processManagerView_not_null";
             }
           }{
  slothmark: ;
  return this.processManagerView;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//ProjectService.java
labeled_2: {
             java.util.List<wilos.model.misc.project.Project> projectList = new java.util.ArrayList<wilos.model.misc.project.Project>();
             projectList = this.projectDao.getAllProjects();
             projects = projectList.toArray(new wilos.model.misc.project.Project[projectList.size()]);
             sortProject();
           }{
  slothmark: ;
  return java.util.Arrays.asList(projects);
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//ConcreteActivityDao.java
labeled_1: {
             String r;
             r = "";
             java.util.List cacts = this.getHibernateTemplate().find(("from ConcreteActivity ca where ca.id=? order by displayOrder" + " DESC"), _cact.getId());
             java.lang.String res = ((wilos.model.misc.concreteactivity.ConcreteActivity) cacts.get(0)).getDisplayOrder();
             if (res.length() > 0)
             {
               r = res.substring((res.length() - 1));
             }
             else
               r = "0";
           }{
  slothmark: ;
  return r;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//UserServiceImpl.java
labeled_1: {
             java.util.Collection<org.itracker.model.User> users = userDAO.findAll();
             int size = users.size();
           }{
  slothmark: ;
  return size;
}haha 
labeled_2: {
             java.util.List<org.itracker.model.User> users = new java.util.ArrayList<org.itracker.model.User>();
             if (projectId != null)
             {
               java.util.List<org.itracker.model.Permission> permissions = permissionDAO.findByProjectIdAndPermission(projectId, permissionType);
               for (org.itracker.model.Permission permission : permissions) {
                                                                              users.add(permission.getUser());
                                                                            }
             }
           }{
  slothmark: ;
  return users;
}haha 
matches2
/Users/rem/metalift/txl/qbs/allbench//ParticipantBean.java
labeled_2: {
             if (this.getParticipantsList().size() == 0)
             {
               this.participantView = "participantView_null";
             }
             else
             {
               this.participantView = "participantView_not_null";
             }
           }{
  slothmark: ;
  return this.participantView;
}haha 
matches1
/Users/rem/metalift/txl/qbs/allbench//IssueServiceImpl.java
labeled_4: {
             java.util.List<org.itracker.model.IssueHistory> history = getIssueHistoryDAO().findByIssueId(issueId);
             if (null != history && history.size() > 0)
             {
               java.util.Collections.sort(history, org.itracker.model.AbstractEntity.ID_COMPARATOR);
               return history.get((history.size() - 1));
             }
           }{
  slothmark: ;
  return null;
}haha 
labeled_5: {
             java.util.Collection<org.itracker.model.Issue> issues = getIssueDAO().findByProjectAndLowerStatus(projectId, org.itracker.services.util.IssueUtilities.STATUS_RESOLVED);
             int size = issues.size();
           }{
  slothmark: ;
  return size;
}haha 
labeled_6: {
             java.util.Collection<org.itracker.model.Issue> issues = getIssueDAO().findByProjectAndHigherStatus(projectId, org.itracker.services.util.IssueUtilities.STATUS_RESOLVED);
             int size = issues.size();
           }{
  slothmark: ;
  return size;
}haha 
labeled_7: {
             java.util.Collection<org.itracker.model.Issue> issues = getIssueDAO().findByProject(projectId);
             int size = issues.size();
           }{
  slothmark: ;
  return size;
}haha 
matches4
/Users/rem/metalift/txl/qbs/allbench//WilosUserBean.java
labeled_1: {
             java.util.List<wilos.model.misc.wilosuser.WilosUser> l = this.getUserByRole(this.selectItemFilter);
             if (l.size() > 0)
             {
               this.setWilosUserView("participantView_not_null");
             }
             else
             {
               this.setWilosUserView("participantView_null");
             }
           }{
  slothmark: ;
  return wilosUserView;
}haha 
matches1
