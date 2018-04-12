/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
while (extfor$iter.hasNext())
{
  wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) extfor$iter.next();
  tmp.add(act);
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor concreteTaskDescriptor = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  concreteTaskDescriptors.add(concreteTaskDescriptor);
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration bde = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  tmp.add(bde);
}haha 
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration tmp = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter$1.next();
  if (lastConcreteIteration == null || tmp.getInstanciationOrder() > lastConcreteIteration.getInstanciationOrder())
  {
    lastConcreteIteration = tmp;
  }
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase bde = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  tmp.add(bde);
}haha 
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase tmp = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter$1.next();
  if (lastConcretePhase == null || tmp.getInstanciationOrder() > lastConcretePhase.getInstanciationOrder())
  {
    lastConcretePhase = tmp;
  }
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//EditProjectFormActionUtil.java
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (!cact.getState().equals(wilos.utils.Constantes.State.FINISHED))
  {
    activityList.add(new javax.faces.model.SelectItem(cact.getId(), cact.getConcreteName()));
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
while (i < activity.size())
{
  sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType())).append(": ").append(activity.get(i).getDescription()).append("\n");
  i++;
}haha 
while (i < components.size())
{
  componentString += (i != 0 ? ", " : "") + components.get(i).getName();
  i++;
}haha 
while (i < versions.size())
{
  versionString += (i != 0 ? ", " : "") + versions.get(i).getNumber();
  i++;
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
}haha 
while (it.hasNext())
{
  javax.mail.internet.InternetAddress internetAddress = (javax.mail.internet.InternetAddress) it.next();
  localeMapping.put(internetAddress, locale);
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
while (i < activity.size())
{
  sb.append(org.itracker.services.util.IssueUtilities.getActivityName(activity.get(i).getActivityType(), currentLocale)).append(": ").append(activity.get(i).getDescription()).append("\n");
  i++;
}haha 
while (i < components.size())
{
  componentString += (i != 0 ? ", " : "") + components.get(i).getName();
  i++;
}haha 
while (i < versions.size())
{
  versionString += (i != 0 ? ", " : "") + versions.get(i).getNumber();
  i++;
}haha 
while (iterator.hasNext())
{
  org.itracker.model.User projectOwner = (org.itracker.model.User) iterator.next();
  if (projectOwner != null && (!activeOnly || projectOwner.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
  {
    issueNotifications.add(new org.itracker.model.Notification(projectOwner, issue, org.itracker.model.Notification.Role.PO));
  }
}haha 
matches11
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
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  tmp.add(element);
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity tmp = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter$1.next();
  if (lastConcreteActivity == null || tmp.getInstanciationOrder() > lastConcreteActivity.getInstanciationOrder())
  {
    lastConcreteActivity = tmp;
  }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity bde = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
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
matches4
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  this.concreteRoleDescriptorsMap.put(concreteRoleDescriptor.getConcreteName(), true);
}haha 
matches1
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
  wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  if (ctd.getState().equals(wilos.utils.Constantes.State.STARTED))
  {
    tache_commence = true;
  }
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
}haha 
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity cact2 = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  if (cact1.getId().equals(cact2.getId()))
  {
    return true;
  }
}haha 
matches4
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
  activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
  i++;
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
