/Users/remywang/metalift/txl/qbs/allbench//MoveIssueFormAction.java
{
  org.apache.struts.action.ActionMessages errors = new org.apache.struts.action.ActionMessages();
  request.setAttribute("pageTitleKey", PAGE_TITLE_KEY);
  request.setAttribute("pageTitleArg", "itracker.web.generic.unknown");
  try
  {
    org.itracker.services.IssueService issueService = getITrackerServices().getIssueService();
    org.itracker.services.ProjectService projectService = getITrackerServices().getProjectService();
    java.lang.Integer issueId = java.lang.Integer.valueOf((request.getParameter("id") == null ? "-1" : request.getParameter("id")));
    org.itracker.model.Issue issue = issueService.getIssue(issueId);
    if (issue == null)
    {
      errors.add(org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE, new org.apache.struts.action.ActionMessage("itracker.web.error.invalidissue"));
    }
    else
    {
      request.setAttribute("pageTitleArg", issue.getId());
      if (errors.isEmpty())
      {
        if (!isPermissionGranted(request, issue))
        {
          return mapping.findForward(UNAUTHORIZED_PAGE);
        }
        java.util.List<org.itracker.model.Project> projects = projectService.getAllAvailableProjects();
        if (projects.size() == 0)
        {
          return mapping.findForward(UNAUTHORIZED_PAGE);
        }
        java.util.List<org.itracker.model.Project> availableProjects = getAvailableProjects(request, projects, issue);
        if (availableProjects.size() == 0)
        {
          errors.add(org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE, new org.apache.struts.action.ActionMessage("itracker.web.error.noprojects"));
        }
        if (errors.isEmpty())
        {
          setupMoveIssueForm(request, form, issue, availableProjects);
          return mapping.getInputForward();
        }
      }
    }
  }
  catch (java.lang.RuntimeException e)
  {
    log.error("Exception while creating move issue form.", e);
    errors.add(org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE, new org.apache.struts.action.ActionMessage("itracker.web.error.system"));
  }
  if (!errors.isEmpty())
  {
    saveErrors(request, errors);
  }
  return mapping.findForward("error");
}haha 
{
  org.itracker.web.forms.MoveIssueForm moveIssueForm = (org.itracker.web.forms.MoveIssueForm) form;
  if (moveIssueForm == null)
  {
    moveIssueForm = new org.itracker.web.forms.MoveIssueForm();
  }
  moveIssueForm.setIssueId(issue.getId());
  moveIssueForm.setCaller(request.getParameter("caller"));
  request.setAttribute("moveIssueForm", moveIssueForm);
  request.setAttribute("projects", availableProjects);
  request.setAttribute("issue", issue);
  saveToken(request);
  log.info("No errors while moving issue. Forwarding to move issue form.");
}haha 
{
  java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> userPermissions = getUserPermissions(request.getSession());
  if (!org.itracker.services.util.UserUtilities.hasPermission(userPermissions, issue.getProject().getId(), org.itracker.services.util.UserUtilities.PERMISSION_EDIT))
  {
    log.debug(("Unauthorized user requested access to move issue for issue " + issue.getId()));
    return false;
  }
  return true;
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
{
  return this.guidanceDao.getGuidance(_id);
}haha 
{
  return this.guidanceDao.getAllGuidances();
}haha 
{
  return this.guidanceDao.saveOrUpdateGuidance(_guidance);
}haha 
{
  this.guidanceDao.deleteGuidance(_guidance);
}haha 
{
  return this.guidanceDao.getGuidanceFromGuid(_guid);
}haha 
{
  java.lang.String filePathToBeReturn = "";
  java.lang.String folder = "";
  java.lang.String attachment = "";
  wilos.model.spem2.guide.Guidance g = null;
  java.lang.String guidCurrentProcess = null;
  java.lang.System.out.println(("Id : " + idGuidance));
  g = this.getGuidanceFromGuid(idGuidance);
  wilos.model.spem2.breakdownelement.BreakdownElement bde = null;
  if (g.getTaskDefinitions().size() != 0)
    bde = g.getTaskDefinitions().iterator().next().getTaskDescriptors().iterator().next();
  if (g.getRoleDefinitions().size() != 0)
    bde = g.getRoleDefinitions().iterator().next().getRoleDescriptors().iterator().next();
  if (g.getActivities().size() != 0)
    bde = g.getActivities().iterator().next();
  if (bde != null)
  {
    while (bde.getSuperActivities().size() != 0)
    {
      bde = bde.getSuperActivities().iterator().next();
    }
    if (bde instanceof wilos.model.spem2.process.Process)
    {
      guidCurrentProcess = bde.getGuid();
    }
  }
  if (g != null && guidCurrentProcess != null)
  {
    java.util.Enumeration attachments = new java.util.StringTokenizer(g.getAttachment(), "|", false);
    while (attachments.hasMoreElements())
    {
      java.lang.String currentAttachment = (java.lang.String) attachments.nextElement();
      if (currentAttachment.matches((".*" + file)))
      {
        attachment = new java.lang.String(currentAttachment);
        attachment = attachment.replace("/", java.io.File.separator);
      }
    }
    folder = this.processService.getProcessFromGuid(guidCurrentProcess).getFolderPath();
    filePathToBeReturn = folder + java.io.File.separator + guidCurrentProcess + java.io.File.separator + attachment;
    java.lang.System.out.println(("FOLDER+ATTCH: " + filePathToBeReturn));
  }
  return filePathToBeReturn;
}haha 
{
  return this.guidanceDao;
}haha 
{
  this.guidanceDao = _guidanceDao;
}haha 
{
  return processService;
}haha 
{
  this.processService = processService;
}haha 
{
  java.util.Set<wilos.model.spem2.activity.Activity> tmp = new java.util.HashSet<wilos.model.spem2.activity.Activity>();
  this.guidanceDao.getSessionFactory().getCurrentSession().saveOrUpdate(_guidance);
  java.util.Iterator extfor$iter = _guidance.getActivities().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) extfor$iter.next();
    tmp.add(act);
  }
  return tmp;
}haha 
matches11
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
{
  this.getConcreteRoleDescriptorDao().getSessionFactory().getCurrentSession().saveOrUpdate(_concreteRoleDescriptor);
  return _concreteRoleDescriptor.getParticipant();
}haha 
{
  this.concreteRoleDescriptorDao.saveOrUpdateConcreteRoleDescriptor(_concreteRoleDescriptor);
}haha 
{
  _concreteRoleDescriptor = this.getConcreteRoleDescriptor(_concreteRoleDescriptor.getId());
  if (_concreteRoleDescriptor != null)
  {
    wilos.model.misc.wilosuser.Participant user = _concreteRoleDescriptor.getParticipant();
    if (user == null)
    {
      _concreteRoleDescriptor.setParticipant(_participant);
      this.saveConcreteRoleDescriptor(_concreteRoleDescriptor);
      return _concreteRoleDescriptor;
    }
    else
    {
      _concreteRoleDescriptor.getParticipant().getName();
      return _concreteRoleDescriptor;
    }
  }
  else
  {
    return null;
  }
}haha 
{
  return this.getConcreteRoleDescriptorDao().getAllConcreteRoleDescriptors();
}haha 
{
  return this.concreteRoleDescriptorDao.getMainConcreteTaskDescriptorsForConcreteRoleDescriptor(_concreteRoleId);
}haha 
{
  java.util.Set<wilos.model.misc.concretetask.ConcreteTaskDescriptor> concreteTaskDescriptors = new java.util.HashSet<wilos.model.misc.concretetask.ConcreteTaskDescriptor>();
  this.concreteRoleDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteRoleDescriptor);
  this.concreteRoleDescriptorDao.getSessionFactory().getCurrentSession().refresh(_concreteRoleDescriptor);
  java.util.Iterator extfor$iter = _concreteRoleDescriptor.getPrimaryConcreteTaskDescriptors().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretetask.ConcreteTaskDescriptor concreteTaskDescriptor = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
    concreteTaskDescriptors.add(concreteTaskDescriptor);
  }
  return concreteTaskDescriptors;
}haha 
{
  return this.concreteRoleDescriptorDao.getMainConcreteTaskDescriptorsForConcreteRoleDescriptor(_concreteRoleDescriptor.getId());
}haha 
{
  _concreteRoleDescriptor = this.getConcreteRoleDescriptor(_concreteRoleDescriptor.getId());
  if (_concreteRoleDescriptor != null && _concreteRoleDescriptor.getParticipant() == null)
  {
    java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> lca = _concreteRoleDescriptor.getSuperConcreteActivities();
    java.util.Iterator extfor$iter$1 = lca.iterator();
    while (extfor$iter$1.hasNext())
    {
      wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter$1.next();
      java.util.Set<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> lcbe = ca.getConcreteBreakdownElements();
      java.util.Iterator extfor$iter = lcbe.iterator();
      while (extfor$iter.hasNext())
      {
        wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbe = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
        if (cbe instanceof wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor)
        {
          wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor cwpd = (wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor) cbe;
          if (cwpd.getWorkProductDescriptor().getResponsibleRoleDescriptor() != null)
          {
            if (_concreteRoleDescriptor.getRoleDescriptor().getId().equals(cwpd.getWorkProductDescriptor().getResponsibleRoleDescriptor().getId()))
            {
              return _concreteRoleDescriptor;
            }
          }
        }
        if (cbe instanceof wilos.model.misc.concretetask.ConcreteTaskDescriptor)
        {
          wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) cbe;
          if (ctd.getTaskDescriptor().getMainRole() != null)
          {
            if (_concreteRoleDescriptor.getRoleDescriptor().getId().equals(ctd.getTaskDescriptor().getMainRole().getId()))
            {
              return _concreteRoleDescriptor;
            }
          }
        }
      }
    }
    this.removeConcreteRoleDescriptor(_concreteRoleDescriptor);
  }
  else
    if (_concreteRoleDescriptor.getParticipant() != null)
    {
      _concreteRoleDescriptor.getParticipant().getName();
      return _concreteRoleDescriptor;
    }
  return null;
}haha 
{
  this.concreteRoleDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteRoledescriptor);
  java.util.Iterator extfor$iter = _concreteRoledescriptor.getSuperConcreteActivities().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteactivity.ConcreteActivity sca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
    sca.getConcreteBreakdownElements().remove(_concreteRoledescriptor);
    this.concreteActivityService.saveConcreteActivity(sca);
  }
  wilos.model.spem2.role.RoleDescriptor rd = _concreteRoledescriptor.getRoleDescriptor();
  this.roleDescriptorService.getRoleDescriptorDao().getSessionFactory().getCurrentSession().evict(rd);
  this.roleDescriptorService.getRoleDescriptorDao().getSessionFactory().getCurrentSession().saveOrUpdate(rd);
  this.roleDescriptorService.getRoleDescriptorDao().getSessionFactory().getCurrentSession().refresh(rd);
  rd.removeConcreteRoleDescriptor(_concreteRoledescriptor);
  this.roleDescriptorService.getConcreteRoleDescriptorDao().getSessionFactory().getCurrentSession().delete(_concreteRoledescriptor);
  this.roleDescriptorService.getRoleDescriptorDao().getSessionFactory().getCurrentSession().saveOrUpdate(rd);
}haha 
{
  return this.getConcreteRoleDescriptorDao().getAllConcreteRoleDescriptorsForProject(_projectId);
}haha 
{
  return this.concreteRoleDescriptorDao.getConcreteRoleDescriptor(_id);
}haha 
{
  return concreteRoleDescriptorDao;
}haha 
{
  this.concreteRoleDescriptorDao = _concreteRoleDescriptorDao;
}haha 
{
  return concreteActivityService;
}haha 
{
  this.concreteActivityService = _concreteActivityService;
}haha 
{
  return this.concreteRoleDescriptorDao.getAllConcreteRoleDescriptorsForARoleDescriptor(_roleDescriptorId);
}haha 
{
  return roleDescriptorService;
}haha 
{
  this.roleDescriptorService = roleDescriptorService;
}haha 
{
  wilos.model.spem2.role.RoleDescriptor rd = new wilos.model.spem2.role.RoleDescriptor();
  rd.setPresentationName("No Role");
  rd.setIsOptional(true);
  rd.setIsPlanned(false);
  rd.setHasMultipleOccurrences(true);
  rd.setGuid("No Role");
  rd.setDescription("outprocess role");
  this.getRoleDescriptorService().saveRoleDescriptor(rd);
  wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = new wilos.model.misc.concreterole.ConcreteRoleDescriptor();
  crd.addRoleDescriptor(rd);
  crd.addBreakdownElement(rd);
  crd.addPrimaryConcreteTaskDescriptor(_ctd);
  crd.setConcreteName((rd.getPresentationName() + "#1"));
  crd.setInstanciationOrder(1);
  java.lang.String projectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  crd.setProject(_ctd.getProject());
  crd.setParticipant(_user);
  this.saveConcreteRoleDescriptor(crd);
  return rd;
}haha 
{
  return participantDao;
}haha 
{
  this.participantDao = _participantDao;
}haha 
{
  return concreteTaskDescriptorDao;
}haha 
{
  this.concreteTaskDescriptorDao = _concreteTaskDescriptorDao;
}haha 
{
  this.concreteRoleDescriptorDao.saveOrUpdateConcreteRoleDescriptor(_crd);
  return _crd.getRoleDescriptor().getPresentationName();
}haha 
matches24
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
{
  java.util.Set<wilos.model.misc.concreteiteration.ConcreteIteration> tmp = new java.util.HashSet<wilos.model.misc.concreteiteration.ConcreteIteration>();
  this.iterationDao.getSessionFactory().getCurrentSession().saveOrUpdate(_iteration);
  java.util.Iterator extfor$iter = _iteration.getConcreteIterations().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteiteration.ConcreteIteration bde = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
    tmp.add(bde);
  }
  return tmp;
}haha 
{
  if (_occ > 0)
  {
    this.concreteActivityService.getConcreteActivityDao().getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
    java.util.ArrayList<wilos.model.misc.concreteiteration.ConcreteIteration> concreteIterationsSisters = new java.util.ArrayList<wilos.model.misc.concreteiteration.ConcreteIteration>();
    int nbExistingConcreteIterationChildren = 0;
    java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement tmp = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
      if (tmp instanceof wilos.model.misc.concreteiteration.ConcreteIteration)
      {
        if (((wilos.model.misc.concreteiteration.ConcreteIteration) tmp).getIteration().getId().equals(_iteration.getId()))
        {
          nbExistingConcreteIterationChildren++;
          concreteIterationsSisters.add(((wilos.model.misc.concreteiteration.ConcreteIteration) tmp));
        }
      }
    }
    int nbConcreteIterationSisters = nbExistingConcreteIterationChildren;
    int i = nbExistingConcreteIterationChildren + 1;
    while ((i <= nbExistingConcreteIterationChildren) + _occ)
    {
      wilos.model.misc.concreteiteration.ConcreteIteration ci = new wilos.model.misc.concreteiteration.ConcreteIteration();
      java.util.List<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = new java.util.ArrayList<wilos.model.spem2.breakdownelement.BreakdownElement>();
      bdes.addAll(this.activityService.getAllBreakdownElements(_iteration));
      if (_occ != 1 || nbExistingConcreteIterationChildren != 0)
      {
        if (_iteration.getPresentationName().equals(""))
          ci.setConcreteName((_iteration.getName() + "#" + i));
        else
          ci.setConcreteName((_iteration.getPresentationName() + "#" + i));
      }
      else
        if (_iteration.getPresentationName().equals(""))
        {
          ci.setConcreteName(_iteration.getName());
        }
        else
        {
          ci.setConcreteName(_iteration.getPresentationName());
        }
      this.concreteIterationSet(ci, _iteration, _project, i, _cact, _dispOrd);
      this.concreteIterationDao.saveOrUpdateConcreteIteration(ci);
      this.instanciationElement(bdes, _occ, _list, _project, _isInstanciated, ci);
      this.concreteIterationDao.saveOrUpdateConcreteIteration(ci);
      if (nbConcreteIterationSisters != 0)
      {
        wilos.model.misc.concreteiteration.ConcreteIteration lastConcreteIteration = null;
        java.util.Iterator extfor$iter$1 = concreteIterationsSisters.iterator();
        while (extfor$iter$1.hasNext())
        {
          wilos.model.misc.concreteiteration.ConcreteIteration tmp = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter$1.next();
          if (lastConcreteIteration == null || tmp.getInstanciationOrder() > lastConcreteIteration.getInstanciationOrder())
          {
            lastConcreteIteration = tmp;
          }
        }
        this.concreteWorkOrderService.saveConcreteWorkOrder(lastConcreteIteration.getId(), ci.getId(), wilos.utils.Constantes.WorkOrderType.FINISH_TO_START, _project.getId());
      }
      ++nbConcreteIterationSisters;
      concreteIterationsSisters.add(ci);
      i++;
    }
  }
}haha 
{
  _ci.addIteration(_iteration);
  _ci.setProject(_project);
  _ci.setBreakdownElement(_iteration);
  _ci.setInstanciationOrder(_i);
  _ci.setWorkBreakdownElement(_iteration);
  _ci.setActivity(_iteration);
  _cact.setConcreteBreakdownElements(this.concreteActivityService.getConcreteBreakdownElements(_cact));
  _ci.addSuperConcreteActivity(_cact);
  _ci.setDisplayOrder((_ci.getSuperConcreteActivity().getDisplayOrder() + java.lang.Integer.toString((_dispOrd + _i))));
}haha 
{
  int dispOrd = 0;
  java.util.Iterator extfor$iter = _bdes.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
    ++dispOrd;
    if (bde instanceof wilos.model.spem2.iteration.Iteration)
    {
      wilos.model.spem2.iteration.Iteration it = (wilos.model.spem2.iteration.Iteration) bde;
      int occ = this.giveNbOccurences(it.getId(), _list, false);
      if (occ == 0 && _occ > 0)
        occ = _occ;
      this.iterationInstanciation(_project, it, _ci, _list, occ, _isInstanciated, dispOrd);
    }
    else
      if (bde instanceof wilos.model.spem2.activity.Activity)
      {
        wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
        int occ = this.giveNbOccurences(act.getId(), _list, false);
        if (occ == 0 && _occ > 0)
          occ = _occ;
        this.activityService.activityInstanciation(_project, act, _ci, _list, occ, _isInstanciated, dispOrd);
      }
      else
        if (bde instanceof wilos.model.spem2.task.TaskDescriptor)
        {
          wilos.model.spem2.task.TaskDescriptor td = (wilos.model.spem2.task.TaskDescriptor) bde;
          int occ = this.giveNbOccurences(td.getId(), _list, false);
          if (occ == 0 && _occ > 0)
            occ = _occ;
          this.taskDescriptorService.taskDescriptorInstanciation(_project, td, _ci, occ, _isInstanciated, dispOrd);
        }
  }
}haha 
{
  if (_occ > 0)
  {
    java.util.Iterator extfor$iter = _cacts.iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concreteactivity.ConcreteActivity tmp = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
      java.lang.String strDispOrd = this.concreteActivityService.getMaxDisplayOrder(tmp);
      int dispOrd = java.lang.Integer.parseInt(strDispOrd) + 1;
      this.iterationInstanciation(_project, _it, tmp, _list, _occ, true, dispOrd);
      if (tmp instanceof wilos.model.misc.project.Project)
      {
        wilos.model.misc.project.Project pj = (wilos.model.misc.project.Project) tmp;
        this.projectDao.saveOrUpdateProject(pj);
      }
      else
        if (tmp instanceof wilos.model.misc.concretephase.ConcretePhase)
        {
          wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) tmp;
          this.concretePhaseDao.saveOrUpdateConcretePhase(cph);
        }
        else
          if (tmp instanceof wilos.model.misc.concreteiteration.ConcreteIteration)
          {
            wilos.model.misc.concreteiteration.ConcreteIteration cit = (wilos.model.misc.concreteiteration.ConcreteIteration) tmp;
            this.concreteIterationDao.saveOrUpdateConcreteIteration(cit);
          }
    }
  }
  else
  {
    java.util.Set<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = new java.util.HashSet<wilos.model.spem2.breakdownelement.BreakdownElement>();
    bdes.addAll(this.activityService.getAllBreakdownElements(_it));
    java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> cacts = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
    cacts.addAll(this.getAllConcreteIterationsForAProject(_it, _project));
    java.util.Iterator extfor$iter$1 = bdes.iterator();
    while (extfor$iter$1.hasNext())
    {
      wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter$1.next();
      if (bde instanceof wilos.model.spem2.iteration.Iteration)
      {
        wilos.model.spem2.iteration.Iteration it = (wilos.model.spem2.iteration.Iteration) bde;
        int occ = this.giveNbOccurences(it.getId(), _list, true);
        this.iterationUpdate(_project, it, cacts, _list, occ);
      }
      else
        if (bde instanceof wilos.model.spem2.activity.Activity)
        {
          wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
          int occ = this.giveNbOccurences(act.getId(), _list, true);
          this.activityService.activityUpdate(_project, act, cacts, _list, occ);
        }
        else
          if (bde instanceof wilos.model.spem2.task.TaskDescriptor)
          {
            wilos.model.spem2.task.TaskDescriptor td = (wilos.model.spem2.task.TaskDescriptor) bde;
            int occ = this.giveNbOccurences(td.getId(), _list, true);
            this.taskDescriptorService.taskDescriptorUpdate(_project, td, cacts, occ);
          }
    }
  }
}haha 
{
  int nb;
  if (!_isInstanciated)
    nb = 1;
  else
    nb = 0;
  java.util.Iterator extfor$iter = list.iterator();
  boolean break_0 = false;
  while (extfor$iter.hasNext() && !break_0)
  {
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (!break_0)
      if (((java.lang.String) hashMap.get("id")).equals(_id))
      {
        nb = ((java.lang.Integer) hashMap.get("nbOccurences")).intValue();
        break_0 = true;
      }
  }
  return nb;
}haha 
{
  return concreteIterationDao;
}haha 
{
  this.concreteIterationDao = concreteIterationDao;
}haha 
{
  return breakdownElementService;
}haha 
{
  this.breakdownElementService = breakdownElementService;
}haha 
{
  return activityService;
}haha 
{
  this.activityService = activityService;
}haha 
{
  return this.concreteActivityService;
}haha 
{
  this.concreteActivityService = _concreteActivityService;
}haha 
{
  return taskDescriptorService;
}haha 
{
  this.taskDescriptorService = taskDescriptorService;
}haha 
{
  return iterationDao;
}haha 
{
  this.iterationDao = iterationDao;
}haha 
{
  return concretePhaseDao;
}haha 
{
  this.concretePhaseDao = concretePhaseDao;
}haha 
{
  return projectDao;
}haha 
{
  this.projectDao = projectDao;
}haha 
{
  return this.concreteWorkOrderService;
}haha 
{
  this.concreteWorkOrderService = _concreteWorkOrderService;
}haha 
matches24
/Users/remywang/metalift/txl/qbs/allbench//RoleDao.java
{
  java.util.List role = this.getHibernateTemplate().loadAll(null);
  return role;
}haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
{
  java.util.Set<wilos.model.misc.concretephase.ConcretePhase> tmp = new java.util.HashSet<wilos.model.misc.concretephase.ConcretePhase>();
  this.phaseDao.getSessionFactory().getCurrentSession().saveOrUpdate(_phase);
  java.util.Iterator extfor$iter = _phase.getConcretePhases().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretephase.ConcretePhase bde = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
    tmp.add(bde);
  }
  return tmp;
}haha 
{
  int dispOrd = 0;
  java.util.Iterator extfor$iter = _bdes.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
    ++dispOrd;
    if (bde instanceof wilos.model.spem2.phase.Phase)
    {
      wilos.model.spem2.phase.Phase ph = (wilos.model.spem2.phase.Phase) bde;
      int occ = this.giveNbOccurences(ph.getId(), _list, false);
      this.phaseInstanciation(_project, ph, _project, _list, occ, _isInstanciated, dispOrd);
    }
    else
      if (bde instanceof wilos.model.spem2.iteration.Iteration)
      {
        wilos.model.spem2.iteration.Iteration it = (wilos.model.spem2.iteration.Iteration) bde;
        int occ = this.giveNbOccurences(it.getId(), _list, false);
        this.iterationService.iterationInstanciation(_project, it, _cp, _list, occ, _isInstanciated, dispOrd);
      }
      else
        if (bde instanceof wilos.model.spem2.activity.Activity)
        {
          wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
          int occ = this.giveNbOccurences(act.getId(), _list, false);
          this.activityService.activityInstanciation(_project, act, _cp, _list, occ, _isInstanciated, dispOrd);
        }
        else
          if (bde instanceof wilos.model.spem2.task.TaskDescriptor)
          {
            wilos.model.spem2.task.TaskDescriptor td = (wilos.model.spem2.task.TaskDescriptor) bde;
            int occ = this.giveNbOccurences(td.getId(), _list, false);
            this.taskDescriptorService.taskDescriptorInstanciation(_project, td, _cp, occ, _isInstanciated, dispOrd);
          }
  }
}haha 
{
  if (_occ > 0)
  {
    this.concreteActivityService.getConcreteActivityDao().getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
    java.util.ArrayList<wilos.model.misc.concretephase.ConcretePhase> concretePhasesSisters = new java.util.ArrayList<wilos.model.misc.concretephase.ConcretePhase>();
    int nbExistingConcretePhaseChildren = 0;
    java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement tmp = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
      if (tmp instanceof wilos.model.misc.concretephase.ConcretePhase)
      {
        if (((wilos.model.misc.concretephase.ConcretePhase) tmp).getPhase().getId().equals(_phase.getId()))
        {
          nbExistingConcretePhaseChildren++;
          concretePhasesSisters.add(((wilos.model.misc.concretephase.ConcretePhase) tmp));
        }
      }
    }
    int nbConcretePhaseSisters = nbExistingConcretePhaseChildren;
    int i = nbExistingConcretePhaseChildren + 1;
    while ((i <= nbExistingConcretePhaseChildren) + _occ)
    {
      wilos.model.misc.concretephase.ConcretePhase cp = new wilos.model.misc.concretephase.ConcretePhase();
      java.util.Set<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = new java.util.HashSet<wilos.model.spem2.breakdownelement.BreakdownElement>();
      bdes.addAll(this.activityService.getAllBreakdownElements(_phase));
      if (_occ != 1 || nbExistingConcretePhaseChildren != 0)
      {
        if (_phase.getPresentationName().equals(""))
          cp.setConcreteName((_phase.getName() + "#" + i));
        else
          cp.setConcreteName((_phase.getPresentationName() + "#" + i));
      }
      else
      {
        if (_phase.getPresentationName().equals(""))
          cp.setConcreteName(_phase.getName());
        else
          cp.setConcreteName(_phase.getPresentationName());
      }
      cp.addPhase(_phase);
      cp.setProject(_project);
      cp.setBreakdownElement(_phase);
      cp.setInstanciationOrder(i);
      cp.setWorkBreakdownElement(_phase);
      cp.setActivity(_phase);
      _cact.setConcreteBreakdownElements(this.concreteActivityService.getConcreteBreakdownElements(_cact));
      cp.addSuperConcreteActivity(_cact);
      cp.setDisplayOrder((cp.getSuperConcreteActivity().getDisplayOrder() + java.lang.Integer.toString((_dispOrd + i))));
      this.concretePhaseDao.saveOrUpdateConcretePhase(cp);
      this.peruseBreakdownElementList(bdes, _occ, _isInstanciated, _project, _phase, _list, cp);
      this.concretePhaseDao.saveOrUpdateConcretePhase(cp);
      if (nbConcretePhaseSisters != 0)
      {
        wilos.model.misc.concretephase.ConcretePhase lastConcretePhase = null;
        java.util.Iterator extfor$iter$1 = concretePhasesSisters.iterator();
        while (extfor$iter$1.hasNext())
        {
          wilos.model.misc.concretephase.ConcretePhase tmp = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter$1.next();
          if (lastConcretePhase == null || tmp.getInstanciationOrder() > lastConcretePhase.getInstanciationOrder())
          {
            lastConcretePhase = tmp;
          }
        }
        this.concreteWorkOrderService.saveConcreteWorkOrder(lastConcretePhase.getId(), cp.getId(), wilos.utils.Constantes.WorkOrderType.FINISH_TO_START, _project.getId());
      }
      ++nbConcretePhaseSisters;
      concretePhasesSisters.add(cp);
      i++;
    }
  }
}haha 
{
  java.util.Iterator extfor$iter = _bdes.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
    if (bde instanceof wilos.model.spem2.phase.Phase)
    {
      wilos.model.spem2.phase.Phase ph = (wilos.model.spem2.phase.Phase) bde;
      int occ = this.giveNbOccurences(ph.getId(), _list, true);
      this.phaseUpdate(_project, ph, _cacts, _list, occ);
    }
    else
      if (bde instanceof wilos.model.spem2.iteration.Iteration)
      {
        wilos.model.spem2.iteration.Iteration it = (wilos.model.spem2.iteration.Iteration) bde;
        int occ = this.giveNbOccurences(it.getId(), _list, true);
        this.iterationService.iterationUpdate(_project, it, _cacts, _list, occ);
      }
      else
        if (bde instanceof wilos.model.spem2.activity.Activity)
        {
          wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
          int occ = this.giveNbOccurences(act.getId(), _list, true);
          this.activityService.activityUpdate(_project, act, _cacts, _list, occ);
        }
        else
          if (bde instanceof wilos.model.spem2.task.TaskDescriptor)
          {
            wilos.model.spem2.task.TaskDescriptor td = (wilos.model.spem2.task.TaskDescriptor) bde;
            int occ = this.giveNbOccurences(td.getId(), _list, true);
            this.taskDescriptorService.taskDescriptorUpdate(_project, td, _cacts, occ);
          }
  }
}haha 
{
  if (_occ > 0)
  {
    java.util.Iterator extfor$iter = _cacts.iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concreteactivity.ConcreteActivity tmp = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
      java.lang.String strDispOrd = this.concreteActivityService.getMaxDisplayOrder(tmp);
      int dispOrd = java.lang.Integer.parseInt(strDispOrd) + 1;
      this.phaseInstanciation(_project, _phase, tmp, _list, _occ, true, dispOrd);
      if (tmp instanceof wilos.model.misc.project.Project)
      {
        wilos.model.misc.project.Project pj = (wilos.model.misc.project.Project) tmp;
        this.projectDao.saveOrUpdateProject(pj);
      }
      else
        if (tmp instanceof wilos.model.misc.concretephase.ConcretePhase)
        {
          wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) tmp;
          this.concretePhaseDao.saveOrUpdateConcretePhase(cph);
        }
    }
  }
  else
  {
    java.util.Set<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = new java.util.HashSet<wilos.model.spem2.breakdownelement.BreakdownElement>();
    bdes.addAll(this.activityService.getAllBreakdownElements(_phase));
    java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> cacts = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
    cacts.addAll(this.getAllConcretePhasesForAProject(_phase, _project));
    this.UpdateElementOfBreakdownElementList(bdes, _project, _phase, cacts, _list);
  }
}haha 
{
  int nb = 0;
  if (!_isInstanciated)
    nb = 1;
  java.util.Iterator extfor$iter = list.iterator();
  boolean break_0 = false;
  while (extfor$iter.hasNext() && !break_0)
  {
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (!break_0)
      if (((java.lang.String) hashMap.get("id")).equals(_id))
      {
        nb = ((java.lang.Integer) hashMap.get("nbOccurences")).intValue();
        break_0 = true;
      }
  }
  return nb;
}haha 
{
  return concretePhaseDao;
}haha 
{
  this.concretePhaseDao = concretePhaseDao;
}haha 
{
  return iterationService;
}haha 
{
  this.iterationService = iterationService;
}haha 
{
  return taskDescriptorService;
}haha 
{
  this.taskDescriptorService = taskDescriptorService;
}haha 
{
  return activityService;
}haha 
{
  this.activityService = activityService;
}haha 
{
  return this.concreteActivityService;
}haha 
{
  this.concreteActivityService = _concreteActivityService;
}haha 
{
  return phaseDao;
}haha 
{
  this.phaseDao = phaseDao;
}haha 
{
  return projectDao;
}haha 
{
  this.projectDao = projectDao;
}haha 
{
  return this.concreteWorkOrderService;
}haha 
{
  this.concreteWorkOrderService = _concreteWorkOrderService;
}haha 
matches22
/Users/remywang/metalift/txl/qbs/allbench//EditProjectFormActionUtil.java
{
  return id;
}haha 
{
  this.id = id;
}haha 
{
  return name;
}haha 
{
  this.name = name;
}haha 
{
  return type;
}haha 
{
  this.type = type;
}haha 
{
  return id;
}haha 
{
  this.id = id;
}haha 
{
  return number;
}haha 
{
  this.number = number;
}haha 
{
  return description;
}haha 
{
  this.description = description;
}haha 
{
  return lastModifiedDate;
}haha 
{
  this.lastModifiedDate = lastModifiedDate;
}haha 
{
  return countIssuesByVersion;
}haha 
{
  this.countIssuesByVersion = countIssuesByVersion;
}haha 
{
  return id;
}haha 
{
  this.id = id;
}haha 
{
  return name;
}haha 
{
  this.name = name;
}haha 
{
  return description;
}haha 
{
  this.description = description;
}haha 
{
  return lastModifiedDate;
}haha 
{
  this.lastModifiedDate = date;
}haha 
{
  return countIssuesByComponent;
}haha 
{
  this.countIssuesByComponent = countIssuesByComponent;
}haha 
{
  java.lang.String pageTitleKey;
  java.lang.String pageTitleArg = "";
  if ("update".equals(((org.itracker.web.forms.ProjectForm) form).getAction()))
  {
    pageTitleKey = "itracker.web.admin.editproject.title.update";
    if (form instanceof org.itracker.web.forms.ProjectForm)
    {
      org.itracker.model.Project project = projectService.getProject(((org.itracker.web.forms.ProjectForm) form).getId());
      if (null != project)
      {
        pageTitleArg = project.getName();
      }
    }
  }
  else
  {
    ((org.itracker.web.forms.ProjectForm) form).setAction("create");
    pageTitleKey = "itracker.web.admin.editproject.title.create";
  }
  request.setAttribute("pageTitleKey", pageTitleKey);
  request.setAttribute("pageTitleArg", pageTitleArg);
}haha 
matches27
/Users/remywang/metalift/txl/qbs/allbench//WorkProductSExpTableBean.java
{
  java.lang.String projectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  if (projectId != null && !this.selectedProcessId.equals("default"))
  {
    wilos.model.misc.project.Project project = projectService.getProject(projectId);
    if (project != null)
    {
      wilos.model.spem2.process.Process process = processService.getProcessDao().getProcess(selectedProcessId);
      if (process != null)
      {
        this.workProductDescriptorService.workProductsInstanciation(project, process, expTableContentWorkProduct);
      }
    }
    this.expTableContentWorkProduct.clear();
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.instanciation.instanciatedMessage"));
    wilos.presentation.web.tree.TreeBean tb = (wilos.presentation.web.tree.TreeBean) wilos.presentation.web.utils.WebCommonService.getBean("TreeBean");
    tb.rebuildProjectTree();
  }
}haha 
{
  if (!this.selectedProcessId.equals("default"))
  {
    wilos.model.spem2.process.Process process = this.processService.getProcess(this.selectedProcessId);
    if (!this.viewedProcessId.equals(process.getId()) || this.expTableContentWorkProduct.isEmpty())
    {
      this.viewedProcessId = process.getId();
      this.isExpandedTableWorkProduct.clear();
      this.expTableContentWorkProduct.clear();
      this.indentationContentWorkProduct.clear();
      java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> lines = this.getExpTableLineContent(process);
      this.expTableContentWorkProduct.addAll(lines);
    }
  }
  return this.expTableContentWorkProduct;
}haha 
{
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> lines = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  java.util.Iterator extfor$iter = this.processService.getWorkProductDescriptorsFromProcess(_process).iterator();
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
  }
  return lines;
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  java.lang.String workProductId = (java.lang.String) map.get("workProductId");
  java.lang.String workProductName = (java.lang.String) map.get("workProductName");
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  wilos.model.spem2.process.Process process = this.projectService.getProcessFromProject(project);
  java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> tmp = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  tmp.addAll(this.expTableContentWorkProduct);
  int index;
  java.util.Iterator extfor$iter = tmp.iterator();
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
  }
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  java.lang.String workProductId = (java.lang.String) map.get("workProductId");
  java.lang.String workProductName = (java.lang.String) map.get("workProductName");
  java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> parentList = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  parentList.addAll(this.expTableContentWorkProduct);
  java.util.Iterator extfor$iter = this.expTableContentWorkProduct.iterator();
  while (extfor$iter.hasNext())
  {
    java.util.HashMap<java.lang.String, java.lang.Object> currentElement = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (currentElement.get("id").equals(workProductId) && currentElement.get("nodeType").equals("node"))
    {
      currentElement.put("expansionImage", CONTRACT_TABLE_ARROW);
      currentElement.put("isDisabled", false);
      parentList.remove(currentElement);
    }
  }
  this.deleteChildrenWorkProduct(workProductName, parentList);
}haha 
{
  java.util.Iterator extfor$iter = _parentList.iterator();
  while (extfor$iter.hasNext())
  {
    java.util.HashMap<java.lang.String, java.lang.Object> child = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (child.get("parentId").equals(_parentName))
    {
      this.expTableContentWorkProduct.remove(child);
      deleteChildrenWorkProduct(((java.lang.String) child.get("id")), _parentList);
    }
    if (child.get("id").equals(_parentName))
    {
      child.put("expansionImage", CONTRACT_TABLE_ARROW);
      this.isExpandedTableWorkProduct.put(((java.lang.String) child.get("id")), false);
    }
  }
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  java.lang.String workProductId = (java.lang.String) map.get("workProductId");
  java.lang.Boolean b = this.isExpandedTableWorkProduct.get(workProductId);
  if (b == null)
  {
    this.isExpandedTableWorkProduct.put(workProductId, true);
    b = this.isExpandedTableWorkProduct.get(workProductId);
  }
  else
  {
    if (b)
    {
      b = false;
    }
    else
    {
      b = true;
    }
    this.isExpandedTableWorkProduct.put(workProductId, b);
  }
  if (b)
  {
    expandNodeActionWorkProduct();
  }
  else
  {
    contractNodeActionWorkProduct();
  }
}haha 
{
  this.expTableContentWorkProduct = _expTableContent;
}haha 
{
  return this.isExpandedTableWorkProduct;
}haha 
{
  this.isExpandedTableWorkProduct = _isExpanded;
}haha 
{
  return this.activityService;
}haha 
{
  this.activityService = _activityService;
}haha 
{
  return this.processService;
}haha 
{
  this.processService = _processService;
}haha 
{
  return this.projectService;
}haha 
{
  this.projectService = _projectService;
}haha 
{
  return this.selectedProcessId;
}haha 
{
  this.selectedProcessId = _selectedProcessGuid;
}haha 
{
  return this.indentationContentWorkProduct;
}haha 
{
  this.indentationContentWorkProduct = _indentationContent;
}haha 
{
  return this.workProductDescriptorService;
}haha 
{
  this.workProductDescriptorService = _workProductDescriptorService;
}haha 
{
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  wilos.model.spem2.process.Process process = this.projectService.getProcessFromProject(project);
  if (process != null)
  {
    this.isVisibleWorkProductInstanciationPanel = true;
  }
  else
  {
    this.isVisibleWorkProductInstanciationPanel = false;
  }
  return this.isVisibleWorkProductInstanciationPanel;
}haha 
{
  this.isVisibleWorkProductInstanciationPanel = _isVisibleWorkProductInstanciationPanel;
}haha 
{
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  wilos.model.spem2.process.Process process = this.projectService.getProcessFromProject(project);
  if (process != null)
  {
    this.isVisibleNewWorkProductPanel = true;
  }
  else
  {
    this.isVisibleNewWorkProductPanel = false;
  }
  return this.isVisibleNewWorkProductPanel;
}haha 
{
  this.isVisibleNewWorkProductPanel = _isVisibleNewWorkProduct;
}haha 
{
  return this.newWorkProductName;
}haha 
{
  this.newWorkProductName = _newWorkProductName;
}haha 
{
  this.selectedRoleDescriptorId = (java.lang.String) evt.getNewValue();
  this.addWorkProductRendered = !this.selectedRoleDescriptorId.equals("default") && !this.selectedConcreteActivityId.equals("default");
}haha 
{
  java.util.List<javax.faces.model.SelectItem> rolesList = new java.util.ArrayList<javax.faces.model.SelectItem>();
  rolesList.add(new javax.faces.model.SelectItem("default", wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.roleComboBoxDefa" + "ultChoice"))));
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  if (project != null)
  {
    wilos.model.spem2.process.Process process = project.getProcess();
    if (process != null)
    {
      wilos.model.misc.concreteactivity.ConcreteActivity cact = this.concreteActivityService.getConcreteActivity(this.selectedConcreteActivityId);
      if (this.selectedConcreteActivityId.equals(project.getId()))
      {
        java.util.SortedSet<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = this.processService.getAllBreakdownElements(process);
        java.util.Iterator extfor$iter = bdes.iterator();
        while (extfor$iter.hasNext())
        {
          wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
          if (bde instanceof wilos.model.spem2.role.RoleDescriptor)
          {
            wilos.model.spem2.role.RoleDescriptor rd = (wilos.model.spem2.role.RoleDescriptor) bde;
            rolesList.add(new javax.faces.model.SelectItem(rd.getId(), rd.getPresentationName()));
          }
        }
      }
      if (!this.selectedConcreteActivityId.equals("default") && !this.selectedConcreteActivityId.equals(project.getId()))
      {
        java.util.SortedSet<wilos.model.spem2.breakdownelement.BreakdownElement> bdEs = this.activityService.getAllBreakdownElements(cact.getActivity());
        java.util.Iterator extfor$iter$1 = bdEs.iterator();
        while (extfor$iter$1.hasNext())
        {
          wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter$1.next();
          if (bde instanceof wilos.model.spem2.role.RoleDescriptor)
          {
            rolesList.add(new javax.faces.model.SelectItem(bde.getId(), bde.getPresentationName()));
          }
        }
      }
      rolesList.add(new javax.faces.model.SelectItem("null", wilos.resources.LocaleBean.getText("component.project.workproductsinstanciation.noRole")));
    }
  }
  return rolesList;
}haha 
{
  this.clearProducerConcreteTasksSelectable = true;
  this.clearProducerConcreteTasksSelected = true;
  this.clearOptionalUserConcreteTasksSelectable = true;
  this.clearOptionalUserConcreteTasksSelected = true;
  this.clearMandatoryUserConcreteTasksSelectable = true;
  this.clearMandatoryUserConcreteTasksSelected = true;
  this.selectedConcreteActivityId = (java.lang.String) evt.getNewValue();
  this.addWorkProductRendered = !this.selectedRoleDescriptorId.equals("default") && !this.selectedConcreteActivityId.equals("default");
  this.visibleRoleComboBox = !this.selectedConcreteActivityId.equals("default");
}haha 
{
  return selectedRoleDescriptorId;
}haha 
{
  selectedRoleDescriptorId = _selectedRoleDescriptorId;
}haha 
{
  return selectedConcreteActivityId;
}haha 
{
  selectedConcreteActivityId = _selectedConcreteActivityId;
}haha 
{
  return newWorkProductDescription;
}haha 
{
  newWorkProductDescription = _newWorkProductDescription;
}haha 
{
  return addWorkProductRendered;
}haha 
{
  addWorkProductRendered = _addWorkProductRendered;
}haha 
{
  isVisibleNewWorkProductPanel = _isVisibleNewWorkProductPanel;
}haha 
{
  java.util.List<javax.faces.model.SelectItem> activityList = new java.util.ArrayList<javax.faces.model.SelectItem>();
  activityList.add(new javax.faces.model.SelectItem("default", wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.actComboBoxDefau" + "ltChoice"))));
  wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  if (project != null)
  {
    java.util.Iterator extfor$iter = this.concreteActivityService.getConcreteActivitiesFromProject(project).iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
      if (!cact.getState().equals(wilos.utils.Constantes.State.FINISHED))
      {
        activityList.add(new javax.faces.model.SelectItem(cact.getId(), cact.getConcreteName()));
      }
    }
    if (!project.getState().equals(wilos.utils.Constantes.State.FINISHED))
    {
      activityList.add(new javax.faces.model.SelectItem(project.getId(), project.getConcreteName()));
    }
  }
  return activityList;
}haha 
{
  if (this.newWorkProductName == "")
  {
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.project.workproductsinstanciation.noNameError"));
  }
  else
    if (this.selectedConcreteActivityId.equals("default"))
    {
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.noActivityDescri" + "ptorSelected")));
    }
    else
      if (this.newWorkProductDescription == "")
      {
        wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.noDescriptionErr" + "or")));
      }
      else
        if (this.selectedRoleDescriptorId.equals("default"))
        {
          wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.noRoleDescriptor" + "Selected")));
        }
        else
        {
          wilos.model.misc.project.Project project = this.projectService.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
          if (!this.selectedRoleDescriptorId.equals("default") && !this.selectedConcreteActivityId.equals("default"))
          {
            java.lang.System.out.println(("******** " + this.selectedRoleDescriptorId));
            wilos.model.spem2.role.RoleDescriptor rd = this.roleDescriptorService.getRoleDescriptor(this.selectedRoleDescriptorId);
            wilos.model.misc.concreteactivity.ConcreteActivity cact = this.concreteActivityService.getConcreteActivity(this.selectedConcreteActivityId);
            if (project.getId().equals(cact.getId()))
            {
              cact = null;
            }
            java.util.ArrayList<java.lang.String> inputConcreteTasksIDs = new java.util.ArrayList<java.lang.String>();
            java.util.ArrayList<java.lang.String> inputOptionnalConcreteTasksIDs = new java.util.ArrayList<java.lang.String>();
            java.util.ArrayList<java.lang.String> outputConcreteTasksIDs = new java.util.ArrayList<java.lang.String>();
            java.util.Iterator extfor$iter = this.mandatoryUserConcreteTasksSelectable.iterator();
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
            }
            if (this.workProductDescriptorService.createWorkProduct(this.newWorkProductName, this.newWorkProductDescription, project, rd, cact, this.activityEntryState, this.activityExitState, outputConcreteTasksIDs, inputOptionnalConcreteTasksIDs, inputConcreteTasksIDs))
            {
              wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText(("component.project.workproductsinstanciation.creationValidati" + "on")));
              this.setSelectTaskToAffectToProduct(false);
            }
            else
            {
              wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.project.workproductsinstanciation.creationError"));
            }
            wilos.presentation.web.tree.TreeBean treeBean = (wilos.presentation.web.tree.TreeBean) wilos.presentation.web.utils.WebCommonService.getBean("TreeBean");
            treeBean.rebuildProjectTree();
            wilos.presentation.web.project.ProjectAdvancementBean pab = (wilos.presentation.web.project.ProjectAdvancementBean) wilos.presentation.web.utils.WebCommonService.getBean("ProjectAdvancementBean");
            pab.refreshProjectTable();
            this.newWorkProductDescription = "";
            this.newWorkProductName = "";
            this.selectedRoleDescriptorId = "default";
            this.selectedConcreteActivityId = "default";
            this.addWorkProductRendered = false;
            this.visibleRoleComboBox = false;
            this.activityEntryState = "";
            this.activityExitState = "";
          }
        }
  return "";
}haha 
{
  return concreteActivityService;
}haha 
{
  concreteActivityService = _concreteActivityService;
}haha 
{
  return roleDescriptorService;
}haha 
{
  roleDescriptorService = _roleDescriptorService;
}haha 
{
  return visibleRoleComboBox;
}haha 
{
  visibleRoleComboBox = _visibleRoleComboBox;
}haha 
{
  this.allConcreteActivitiesAreFinishedWorkProduct = _allConcreteActivitiesAreFinished;
}haha 
{
  java.util.HashMap<java.lang.String, java.lang.Object> row = this.producerConcreteTasksSelectable.get(event.getRow());
  if (this.producerConcreteTasksSelected.contains(row))
  {
    this.producerConcreteTasksSelected.remove(row);
  }
  else
  {
    this.producerConcreteTasksSelected.add(row);
  }
}haha 
{
  java.util.HashMap<java.lang.String, java.lang.Object> row = this.optionalUserConcreteTasksSelectable.get(event.getRow());
  if (this.optionalUserConcreteTasksSelected.contains(row))
  {
    this.optionalUserConcreteTasksSelected.remove(row);
  }
  else
  {
    this.optionalUserConcreteTasksSelected.add(row);
  }
}haha 
{
  java.util.HashMap<java.lang.String, java.lang.Object> row = this.mandatoryUserConcreteTasksSelectable.get(event.getRow());
  if (this.mandatoryUserConcreteTasksSelected.contains(row))
  {
    this.mandatoryUserConcreteTasksSelected.remove(row);
  }
  else
  {
    this.mandatoryUserConcreteTasksSelected.add(row);
  }
}haha 
{
  return this.concreteTaskDescriptorService;
}haha 
{
  this.concreteTaskDescriptorService = _concreteTaskDescriptorService;
}haha 
{
  this.activitySelected = !this.selectedConcreteActivityId.equals("default");
  return this.activitySelected;
}haha 
{
  this.activitySelected = _activitySelected;
}haha 
{
  return this.activityEntryState;
}haha 
{
  this.activityEntryState = _activityEntryState;
}haha 
{
  return this.activityExitState;
}haha 
{
  this.activityExitState = _activityExitState;
}haha 
{
  if (this.clearProducerConcreteTasksSelectable)
  {
    this.producerConcreteTasksSelectable.clear();
    if (!this.selectedConcreteActivityId.equals("default"))
    {
      wilos.model.misc.concreteactivity.ConcreteActivity ca = this.concreteActivityService.getConcreteActivity(selectedConcreteActivityId);
      java.util.SortedSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> cbdes = this.concreteActivityService.getConcreteBreakdownElements(ca);
      java.util.Iterator extfor$iter = cbdes.iterator();
      while (extfor$iter.hasNext())
      {
        wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbde = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
        if (cbde instanceof wilos.model.misc.concretetask.ConcreteTaskDescriptor)
        {
          wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) cbde;
          java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
          hm.put("ID", ctd.getId());
          hm.put("name", ctd.getConcreteName());
          hm.put("selected", false);
          this.producerConcreteTasksSelectable.add(hm);
        }
      }
      this.clearProducerConcreteTasksSelectable = false;
    }
  }
  return this.producerConcreteTasksSelectable;
}haha 
{
  this.producerConcreteTasksSelectable = _producerConcreteTasksSelectable;
}haha 
{
  if (this.clearProducerConcreteTasksSelected)
  {
    this.producerConcreteTasksSelected.clear();
    this.clearProducerConcreteTasksSelected = false;
  }
  return this.producerConcreteTasksSelected;
}haha 
{
  this.producerConcreteTasksSelected = _producerConcreteTasksSelected;
}haha 
{
  if (this.clearOptionalUserConcreteTasksSelectable)
  {
    this.optionalUserConcreteTasksSelectable.clear();
    if (!this.selectedConcreteActivityId.equals("default"))
    {
      wilos.model.misc.concreteactivity.ConcreteActivity ca = this.concreteActivityService.getConcreteActivity(selectedConcreteActivityId);
      java.util.SortedSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> cbdes = this.concreteActivityService.getConcreteBreakdownElements(ca);
      java.util.Iterator extfor$iter = cbdes.iterator();
      while (extfor$iter.hasNext())
      {
        wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbde = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
        if (cbde instanceof wilos.model.misc.concretetask.ConcreteTaskDescriptor)
        {
          wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) cbde;
          java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
          hm.put("ID", ctd.getId());
          hm.put("name", ctd.getConcreteName());
          hm.put("selected", false);
          this.optionalUserConcreteTasksSelectable.add(hm);
        }
      }
      this.clearOptionalUserConcreteTasksSelectable = false;
    }
  }
  return this.optionalUserConcreteTasksSelectable;
}haha 
{
  this.optionalUserConcreteTasksSelectable = _optionalUserConcreteTasksSeletacble;
}haha 
{
  if (this.clearOptionalUserConcreteTasksSelected)
  {
    this.optionalUserConcreteTasksSelected.clear();
    this.clearOptionalUserConcreteTasksSelected = false;
  }
  return this.optionalUserConcreteTasksSelected;
}haha 
{
  this.optionalUserConcreteTasksSelected = _optionalUserConcreteTasksSelected;
}haha 
{
  if (this.clearMandatoryUserConcreteTasksSelectable)
  {
    this.mandatoryUserConcreteTasksSelectable.clear();
    if (!this.selectedConcreteActivityId.equals("default"))
    {
      wilos.model.misc.concreteactivity.ConcreteActivity ca = this.concreteActivityService.getConcreteActivity(selectedConcreteActivityId);
      java.util.SortedSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> cbdes = this.concreteActivityService.getConcreteBreakdownElements(ca);
      java.util.Iterator extfor$iter = cbdes.iterator();
      while (extfor$iter.hasNext())
      {
        wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbde = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
        if (cbde instanceof wilos.model.misc.concretetask.ConcreteTaskDescriptor)
        {
          wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) cbde;
          java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
          hm.put("ID", ctd.getId());
          hm.put("name", ctd.getConcreteName());
          hm.put("task", ctd);
          hm.put("in", false);
          hm.put("inOptionnal", false);
          hm.put("out", false);
          hm.put("flag_in", hm.get("in"));
          hm.put("flag_inOptionnal", hm.get("inOptionnal"));
          hm.put("flag_out", hm.get("out"));
          this.mandatoryUserConcreteTasksSelectable.add(hm);
        }
      }
      this.clearMandatoryUserConcreteTasksSelectable = false;
    }
  }
  return this.mandatoryUserConcreteTasksSelectable;
}haha 
{
  this.mandatoryUserConcreteTasksSelectable = _mandatoryUserConcreteTasksSelectable;
}haha 
{
  if (this.clearMandatoryUserConcreteTasksSelected)
  {
    this.mandatoryUserConcreteTasksSelected.clear();
    this.clearMandatoryUserConcreteTasksSelected = false;
  }
  return this.mandatoryUserConcreteTasksSelected;
}haha 
{
  this.mandatoryUserConcreteTasksSelected = _mandatoryUserConcreteTasksSelected;
}haha 
{
  return this.selectTaskToAffectToProduct;
}haha 
{
  if (!_selectTaskToAffectToProduct)
  {
    this.clearMandatoryUserConcreteTasksSelectable = true;
    this.clearMandatoryUserConcreteTasksSelected = true;
    this.clearOptionalUserConcreteTasksSelectable = true;
    this.clearOptionalUserConcreteTasksSelected = true;
    this.clearProducerConcreteTasksSelectable = true;
    this.clearProducerConcreteTasksSelected = true;
  }
  this.selectTaskToAffectToProduct = _selectTaskToAffectToProduct;
}haha 
{
  return this.concreteWorkProductDescriptorService;
}haha 
{
  this.concreteWorkProductDescriptorService = _concreteWorkProductDescriptorService;
}haha 
matches76
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleAffectationService.java
{
  return participantDao;
}haha 
{
  this.participantDao = participantDao;
}haha 
{
  wilos.model.misc.wilosuser.Participant currentParticipant = this.participantDao.getParticipantById(_wilosUserId);
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = this.concreteRoleDescriptorService.getConcreteRoleDescriptor(((java.lang.String) rolesParticipant.get("concreteId")));
  if (!(java.lang.Boolean) rolesParticipant.get("not_allowed"))
  {
    if ((java.lang.Boolean) rolesParticipant.get("affected"))
    {
      currentParticipant.addConcreteRoleDescriptor(concreteRoleDescriptor);
    }
    else
    {
      currentParticipant.removeConcreteRoleDescriptor(concreteRoleDescriptor);
    }
    this.concreteRoleDescriptorService.getConcreteRoleDescriptorDao().saveOrUpdateConcreteRoleDescriptor(concreteRoleDescriptor);
  }
  return "";
}haha 
{
  return this.concreteRoleDescriptorService;
}haha 
{
  this.concreteRoleDescriptorService = _concreteRoleDescriptorService;
}haha 
{
  java.util.HashMap<java.lang.String, java.lang.Boolean> roleStatus = new java.util.HashMap<java.lang.String, java.lang.Boolean>();
  wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = this.concreteRoleDescriptorService.getConcreteRoleDescriptor(_concreteId);
  if (crd.getParticipant() != null)
  {
    roleStatus.put("affected", new java.lang.Boolean(true));
    if (crd.getParticipant().getId().equals(_wilosUserId))
    {
      roleStatus.put("not_allowed", new java.lang.Boolean(false));
    }
    else
    {
      roleStatus.put("not_allowed", new java.lang.Boolean(true));
    }
  }
  else
  {
    roleStatus.put("affected", new java.lang.Boolean(false));
    roleStatus.put("not_allowed", new java.lang.Boolean(false));
  }
  return roleStatus;
}haha 
matches6
/Users/remywang/metalift/txl/qbs/allbench//RoleService.java
{
  if (id_role.equalsIgnoreCase("3"))
  {
    return "admin";
  }
  if (id_role.equalsIgnoreCase("1"))
  {
    return "projectDirector";
  }
  if (id_role.equalsIgnoreCase("2"))
  {
    return "processManager";
  }
  return "participant";
}haha 
{
  return roleDao;
}haha 
{
  this.roleDao = roleDao;
}haha 
{
  return this.roleDao.getRole();
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ProcessManagerBean.java
{
  boolean error = false;
  if (this.processManager.getName().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.lastnameRequired"));
  }
  if (this.processManager.getFirstname().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.firstnameRequired"));
  }
  if (this.processManager.getLogin().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.loginRequired"));
  }
  if (this.processManager.getPassword().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.passwordRequired"));
  }
  if (this.passwordConfirmation.trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.confirmpasswordRequired"));
  }
  if (!error)
  {
    if (this.loginService.loginExist(this.processManager.getLogin().trim()))
    {
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.loginalreadyexist"));
    }
    else
    {
      this.processManagerService.saveProcessManager(this.processManager);
      wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.processmanagercreate.success"));
    }
  }
  this.processManager = new wilos.model.misc.wilosuser.WilosUser();
  this.passwordConfirmation = new java.lang.String();
}haha 
{
  javax.faces.component.UIComponent passcomponent = _toValidate.findComponent("equal1PM");
  java.lang.String passValue = (java.lang.String) passcomponent.getAttributes().get("value");
  if (!_value.equals(passValue))
  {
    javax.faces.application.FacesMessage message = new javax.faces.application.FacesMessage();
    message.setSummary(wilos.resources.LocaleBean.getText("component.processmanagercreate.err.passwordnotequals"));
    message.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
    throw new javax.faces.validator.ValidatorException(message);
  }
}haha 
{
  return this.processManager;
}haha 
{
  this.processManager = _processManager;
}haha 
{
  return this.processManagerService;
}haha 
{
  this.processManagerService = _processManagerService;
}haha 
{
  return passwordConfirmation;
}haha 
{
  this.passwordConfirmation = passwordConfirmation;
}haha 
{
  return this.loginService;
}haha 
{
  this.loginService = _loginService;
}haha 
{
  this.processManagerList = new java.util.ArrayList<wilos.model.misc.wilosuser.WilosUser>();
  processManagerList.addAll(this.processManagerService.getProcessManagers());
  return this.processManagerList;
}haha 
{
  this.processManagerList = _processManagerList;
}haha 
{
  this.processManagerView = _processManagerView;
}haha 
matches13
/Users/remywang/metalift/txl/qbs/allbench//NotificationServiceImpl.java
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("sendNotification: called with notification: " + notification + ", type: " + url + ", url: " + url));
  }
  if (null == notification)
  {
    throw new java.lang.IllegalArgumentException("notification must not be null");
  }
  if (null == this.emailService || null == this.notificationDao)
  {
    throw new java.lang.IllegalStateException("service not initialized yet");
  }
  if (type == org.itracker.model.Notification.Type.SELF_REGISTER)
  {
    this.handleSelfRegistrationNotification(notification.getUser().getLogin(), notification.getUser().getEmailAddress(), url);
  }
  else
  {
    handleIssueNotification(notification.getIssue(), type, url);
  }
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("sendNotification: called with issue: " + issue + ", type: " + type + ", baseURL: " + baseURL));
  }
  handleIssueNotification(issue, type, baseURL);
}haha 
{
  if (null == emailService)
    throw new java.lang.IllegalArgumentException("email service must not be null");
  if (null != this.emailService)
  {
    throw new java.lang.IllegalStateException("email service allready set");
  }
  this.emailService = emailService;
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("handleSelfRegistrationNotification: called with login: " + login + ", toAddress" + toAddress + ", url: " + url));
  }
  try
  {
    if (toAddress != null && !"".equals(toAddress.getAddress()))
    {
      java.lang.String subject = org.itracker.core.resources.ITrackerResources.getString("itracker.email.selfreg.subject");
      java.lang.String msgText = org.itracker.core.resources.ITrackerResources.getString("itracker.email.selfreg.body", org.itracker.core.resources.ITrackerResources.getDefaultLocale(), new java.lang.Object[] {
                                                                                                                                                                                                                   login,
                                                                                                                                                                                                                   (url + "/login.do"),
                                                                                                                                                                                                                 });
      emailService.sendEmail(toAddress, subject, msgText);
    }
    else
    {
      throw new java.lang.IllegalArgumentException("To-address must be set for self registration notification.");
    }
  }
  catch (java.lang.RuntimeException e)
  {
    logger.error(("failed to handle self registration notification for " + toAddress), e);
    throw e;
  }
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("handleIssueNotification: called with issue: " + issue + ", type: " + type + "url: " + url));
  }
  this.handleIssueNotification(issue, type, url, null, null);
}haha 
{
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
{
  return org.itracker.web.util.ServletContextUtils.getItrackerServices().getIssueService();
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssueActivityNotification: called with " + issue + ", notificationSent: " + notificationSent));
  }
  java.util.Collection<org.itracker.model.IssueActivity> activity = getIssueActivityDao().findByIssueId(issue.getId());
  java.util.Iterator<org.itracker.model.IssueActivity> iter = activity.iterator();
  while (iter.hasNext())
  {
    ((org.itracker.model.IssueActivity) iter.next()).setNotificationSent(notificationSent);
  }
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("addIssueNotification: called with notification: " + notification));
  }
  org.itracker.model.Issue issue = notification.getIssue();
  if (!issue.getNotifications().contains(notification))
  {
    if (notification.getCreateDate() == null)
    {
      notification.setCreateDate(new java.util.Date());
    }
    if (notification.getLastModifiedDate() == null)
    {
      notification.setLastModifiedDate(new java.util.Date());
    }
    getNotificationDao().save(notification);
    issue.getNotifications().add(notification);
    getIssueDao().merge(issue);
    return true;
  }
  if (logger.isDebugEnabled())
  {
    logger.debug(("addIssueNotification: attempted to add duplicate notificatio" + "n " + notification + " for issue: " + issue));
  }
  return false;
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("getIssueNotifications: called with: " + issue));
  }
  return this.getIssueNotifications(issue, false, true);
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("getPrimaryIssueNotifications: called with: " + issue));
  }
  return this.getIssueNotifications(issue, true, false);
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("hasIssueNotification: called with: " + issue + ", userId: " + userId));
  }
  return hasIssueNotification(issue, userId, org.itracker.model.Notification.Role.ANY);
}haha 
{
  org.itracker.model.Notification notification = this.getNotificationDao().findById(notificationId);
  getNotificationDao().delete(notification);
  return true;
}haha 
{
  this.handleIssueNotification(issue, type, baseURL, receipients, lastModifiedDays);
}haha 
{
  return emailService;
}haha 
{
  return notificationDao;
}haha 
{
  return projectService;
}haha 
{
  this.projectService = projectService;
}haha 
{
  if (null == notificationDao)
  {
    throw new java.lang.IllegalArgumentException("notification dao must not be null");
  }
  if (null != this.notificationDao)
  {
    throw new java.lang.IllegalStateException("notification dao allready set");
  }
  this.notificationDao = notificationDao;
}haha 
{
  org.itracker.model.Notification notification = notificationDao.findById(notificationId);
  this.sendNotification(notification, type, url);
}haha 
{
  return issueActivityDao;
}haha 
{
  this.issueActivityDao = issueActivityDao;
}haha 
{
  return issueDao;
}haha 
{
  this.issueDao = issueDao;
}haha 
matches24
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
{
  if (this.processManagementService.hasBeenInstanciated(this.processId))
  {
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.process.management.deletionforbidden"));
  }
  else
  {
    this.processManagementService.removeProcess(this.processId);
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.process.management.deletiondone"));
  }
  this.visiblePopup = false;
}haha 
{
  this.visiblePopup = false;
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  this.processId = (java.lang.String) map.get("processId");
  this.visiblePopup = true;
}haha 
{
  if (this.processesList == null || this.processesList.size() != this.processService.getAllProcesses().size())
  {
    this.processesList = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
    java.util.Iterator extfor$iter = this.processService.getAllProcesses().iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
      java.util.HashMap<java.lang.String, java.lang.Object> processDescription = new java.util.HashMap<java.lang.String, java.lang.Object>();
      processDescription.put("presentationName", process.getPresentationName());
      processDescription.put("id", process.getId());
      processDescription.put("isEditable", new java.lang.Boolean(false));
      if (process.getProcessManager() != null)
      {
        wilos.model.misc.wilosuser.WilosUser manager = wilosUserService.getSimpleUser(process.getProcessManager());
        process.setProcessManager(manager.getId());
        processDescription.put("owner", (manager.getFirstname() + " " + manager.getName()));
      }
      this.processesList.add(processDescription);
    }
    return this.processesList;
  }
  return this.processesList;
}haha 
{
  java.lang.String processId = (java.lang.String) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("processEditId");
  java.util.Iterator extfor$iter = this.processesList.iterator();
  while (extfor$iter.hasNext())
  {
    java.util.HashMap<java.lang.String, java.lang.Object> processDescription = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (((java.lang.String) processDescription.get("id")).equals(processId))
    {
      processDescription.put("isEditable", new java.lang.Boolean(true));
    }
  }
}haha 
{
  java.lang.String processId = (java.lang.String) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("processSaveId");
  wilos.model.spem2.process.Process process = this.processService.getProcessDao().getProcess(processId);
  java.util.Iterator extfor$iter = this.getProcessesList().iterator();
  boolean break_0 = false;
  while (extfor$iter.hasNext() && !break_0)
  {
    java.util.HashMap<java.lang.String, java.lang.Object> processDescription = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (!break_0)
      if (((java.lang.String) processDescription.get("id")).equals(processId))
      {
        java.lang.String presentationName = (java.lang.String) processDescription.get("presentationName");
        if (presentationName.trim().length() == 0)
        {
          processDescription.put("presentationName", process.getPresentationName());
          wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.process.management.message.invalidName"));
        }
        else
          if (this.presentationNameAlreadyExists(presentationName, processId))
          {
            processDescription.put("presentationName", process.getPresentationName());
            wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.process.management.message.nameAlreadyExists"));
          }
          else
          {
            process.setPresentationName(presentationName);
            this.processService.getProcessDao().saveOrUpdateProcess(process);
            processDescription.put("isEditable", new java.lang.Boolean(false));
          }
        break_0 = true;
      }
  }
}haha 
{
  java.util.List<javax.faces.model.SelectItem> processesList = new java.util.ArrayList<javax.faces.model.SelectItem>();
  processesList.add(new javax.faces.model.SelectItem("default", wilos.resources.LocaleBean.getText("component.combobox.processchoice.defaultlabel")));
  java.util.List<wilos.model.spem2.process.Process> processes = this.processService.getProcessDao().getAllProcesses();
  java.util.Iterator extfor$iter = processes.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
    processesList.add(new javax.faces.model.SelectItem(process.getId(), process.getPresentationName()));
  }
  return processesList;
}haha 
{
  wilos.presentation.web.expandabletable.TasksExpTableBean tasksExpTableBean = (wilos.presentation.web.expandabletable.TasksExpTableBean) wilos.presentation.web.utils.WebCommonService.getBean("TasksExpTableBean");
  wilos.presentation.web.expandabletable.RolesExpTableBean rolesExpTableBean = (wilos.presentation.web.expandabletable.RolesExpTableBean) wilos.presentation.web.utils.WebCommonService.getBean("RolesExpTableBean");
  wilos.presentation.web.expandabletable.WorkProductsExpTableBean workproductsExpTableBean = (wilos.presentation.web.expandabletable.WorkProductsExpTableBean) wilos.presentation.web.utils.WebCommonService.getBean("WorkProductsExpTableBean");
  this.selectedProcessId = (java.lang.String) evt.getNewValue();
  tasksExpTableBean.setSelectedProcessId(((java.lang.String) evt.getNewValue()));
  rolesExpTableBean.setSelectedProcessId(((java.lang.String) evt.getNewValue()));
  workproductsExpTableBean.setSelectedProcessId(((java.lang.String) evt.getNewValue()));
  if (this.selectedProcessId.equals("default"))
  {
    this.isVisibleExpTable = false;
  }
  else
  {
    this.isVisibleExpTable = true;
  }
}haha 
{
  return this.selectedProcessId;
}haha 
{
  this.selectedProcessId = _processGuid;
}haha 
{
  return this.isVisibleExpTable;
}haha 
{
  this.isVisibleExpTable = _isVisibleExpTable;
}haha 
{
  java.lang.String projectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  wilos.model.misc.project.Project project = this.projectService.getProject(projectId);
  if (project.getProcess() == null)
  {
    this.readOnly = false;
  }
  else
  {
    this.readOnly = true;
  }
  return this.readOnly;
}haha 
{
  this.readOnly = _readOnly;
}haha 
{
  java.lang.String user_id = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.WILOS_USER_ID);
  java.lang.String projectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  if (projectId == null)
  {
    return false;
  }
  this.project = this.projectService.getProject(projectId);
  if (project == null)
  {
    return false;
  }
  if (this.project.getProjectManager() != null)
  {
    if (this.project.getProjectManager().getId().equals(user_id))
    {
      return true;
    }
  }
  return false;
}haha 
{
  boolean ok = false;
  java.lang.String user_id = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.WILOS_USER_ID);
  java.lang.String projectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  java.lang.String role = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.ROLE_TYPE);
  if (projectId == null)
  {
    return false;
  }
  this.project = this.projectService.getProject(projectId);
  if (project == null)
  {
    return false;
  }
  if (this.project.getProjectManager() != null)
  {
    if (this.project.getProjectManager().getId().equals(user_id))
    {
      ok = true;
    }
  }
  if (role.equals("projectDirector"))
  {
    if (project.getProjectDirector() != null)
    {
      if (project.getProjectDirector().equals(user_id))
      {
        ok = true;
      }
    }
  }
  return ok;
}haha 
{
  java.lang.String user_id = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.WILOS_USER_ID);
  java.lang.String role = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.ROLE_TYPE);
  java.lang.String projectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  if (projectId == null)
  {
    return false;
  }
  wilos.model.misc.project.Project project = this.projectService.getProject(projectId);
  if (project == null || project.equals("default"))
  {
    return false;
  }
  if (role.equals("projectDirector"))
  {
    if (project.getProjectDirector() != null)
    {
      if (project.getProjectDirector().equals(user_id))
      {
        return true;
      }
    }
  }
  return false;
}haha 
{
  this.isProjectManager = _isProjectManager;
}haha 
{
  this.processesList = _processesList;
}haha 
{
  if (this.getProcessesList().size() == 0)
  {
    this.processesListView = VIEW_NULL;
  }
  else
  {
    this.processesListView = VIEW_NOT_NULL;
  }
  return this.processesListView;
}haha 
{
  this.processesListView = _processesListView;
}haha 
{
  return this.processService;
}haha 
{
  this.processService = _processService;
}haha 
{
  return this.projectService;
}haha 
{
  this.projectService = _projectService;
}haha 
{
  return processManagementService;
}haha 
{
  this.processManagementService = processManagementService;
}haha 
{
  return processManagerService;
}haha 
{
  this.processManagerService = processManagerService;
}haha 
{
  return this.visiblePopup;
}haha 
{
  this.visiblePopup = _visiblePopup;
}haha 
{
  return this.instanciationDependenciesView;
}haha 
{
  this.instanciationDependenciesView = _instanciationDependenciesView;
}haha 
{
  return instanciateDependenciesWithProcess;
}haha 
{
  this.instanciateDependenciesWithProcess = _instanciateDependenciesWithProcess;
}haha 
{
  return wilosUserService;
}haha 
{
  this.wilosUserService = wilosUserService;
}haha 
matches37
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
{
  wilos.model.spem2.task.TaskDescriptor taskDesc = new wilos.model.spem2.task.TaskDescriptor();
  taskDesc.setPresentationName(_presentationName);
  taskDesc.setDescription(_description);
  taskDesc.setGuid(_guid);
  taskDesc.setPrefix("");
  taskDesc.setIsPlanned(true);
  taskDesc.setHasMultipleOccurrences(false);
  taskDesc.setIsOptional(false);
  taskDesc.setIsRepeatable(true);
  taskDesc.setIsOngoing(false);
  taskDesc.setIsEvenDriven(false);
  if (_mainRole != null)
  {
    _mainRole.addPrimaryTask(taskDesc);
  }
  this.taskDescriptorDao.saveOrUpdateTaskDescriptor(taskDesc);
  java.lang.System.out.println("### TaskDescriptor sauve");
  if (taskDesc.getId() != null)
    return taskDesc;
  else
    return null;
}haha 
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor concTaskDesc = new wilos.model.misc.concretetask.ConcreteTaskDescriptor();
  int i = java.lang.Math.abs(((int) java.lang.System.currentTimeMillis()));
  concTaskDesc.setConcreteName(_concreteName);
  concTaskDesc.setProject(_project);
  concTaskDesc.setInstanciationOrder(i);
  concTaskDesc.addSuperConcreteActivity(_cact);
  concTaskDesc.setTaskDescriptor(_td);
  concTaskDesc.setBreakdownElement(_td);
  concTaskDesc.setWorkBreakdownElement(_td);
  this.concreteTaskDescriptorDao.saveOrUpdateConcreteTaskDescriptor(concTaskDesc);
  this.concreteActivityDao.saveOrUpdateConcreteActivity(_cact);
  return concTaskDesc.getId() != null;
}haha 
{
  if (_role != null)
  {
    this.roleDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_role);
  }
  this.concreteActivityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
  this.concreteActivityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  wilos.model.spem2.task.TaskDescriptor td = this.createTaskDescriptor(_taskName, _taskDescription, _role, _taskName);
  if (_role == null)
  {
    td.setMainRole(null);
  }
  if (td == null)
  {
    return false;
  }
  if (recursive)
  {
    java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbe = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
      if (cbe instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
      {
        if (!this.createConcreteTaskDescriptor(_taskName, _project, td, ((wilos.model.misc.concreteactivity.ConcreteActivity) cbe)))
          return false;
      }
    }
  }
  if (!this.createConcreteTaskDescriptor(_taskName, _project, td, _cact))
    return false;
  else
    return true;
}haha 
{
  this.projectDao.saveOrUpdateProject(_project);
}haha 
{
  boolean ok = false;
  wilos.model.misc.project.Project project = this.getProject(projectId);
  if (project.getProcess() == null && project.getParticipants().size() == 0 && project.getProjectManager() == null)
  {
    this.projectDao.deleteProject(project);
    ok = true;
  }
  return ok;
}haha 
{
  java.util.Comparator comparator = new java.util.Comparator()
                                    {
                                      public int compare (java.lang.Object o1, java.lang.Object o2)
                                      {
                                        wilos.model.misc.project.Project p1 = (wilos.model.misc.project.Project) o1;
                                        wilos.model.misc.project.Project p2 = (wilos.model.misc.project.Project) o2;
                                        return p1.getConcreteName().compareTo(p2.getConcreteName());
                                      }
                                    };
  java.util.Arrays.sort(projects, comparator);
}haha 
{
  wilos.model.misc.project.Project p1 = (wilos.model.misc.project.Project) o1;
  wilos.model.misc.project.Project p2 = (wilos.model.misc.project.Project) o2;
  return p1.getConcreteName().compareTo(p2.getConcreteName());
}haha 
{
  java.util.Set<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> tmp = new java.util.HashSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement>();
  this.getProjectDao().getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  this.getProjectDao().getSessionFactory().getCurrentSession().refresh(_project);
  java.util.Iterator extfor$iter = _project.getConcreteBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
    tmp.add(element);
  }
  return tmp;
}haha 
{
  return this.projectDao;
}haha 
{
  this.projectDao = _projectDao;
}haha 
{
  java.util.List<wilos.model.misc.project.Project> projectList = new java.util.ArrayList<wilos.model.misc.project.Project>();
  projectList = this.projectDao.getAllProjects();
  return projectList;
}haha 
{
  return this.projectDao.getProject(_id);
}haha 
{
  return this.participantDao;
}haha 
{
  return this.processService;
}haha 
{
  this.processService = _processService;
}haha 
{
  this.participantDao = _participantDao;
}haha 
{
  return project.getParticipants();
}haha 
{
  this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  return _project.getProcess();
}haha 
{
  return projects;
}haha 
{
  this.projects = projects;
}haha 
{
  return this.activityDao;
}haha 
{
  this.activityDao = _activityDao;
}haha 
{
  this.processService.getProcessDao().getSessionFactory().getCurrentSession().saveOrUpdate(_process);
  wilos.model.misc.project.Project project = this.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> lines = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  java.lang.String path = _process.getPresentationName();
  return this.giveRoleDescriptorsPathName(project, _process, path, _roleName, lines);
}haha 
{
  final java.lang.String TABLE_LEAF = "images/expandableTable/leaf.gif";
  java.util.Iterator extfor$iter = _act.getBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
    if (bde instanceof wilos.model.spem2.role.RoleDescriptor)
    {
      if (bde.getPresentationName().equals(_roleName))
      {
        path += " / " + bde.getPresentationName();
        java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
        hm.put("nodeType", "leaf");
        hm.put("expansionImage", TABLE_LEAF);
        hm.put("id", bde.getId());
        hm.put("name", path);
        int nbcrd = this.getConcreteRoleDescriptorsFromProject(_project).size();
        if (nbcrd > 0)
        {
          hm.put("nbOccurences", new java.lang.Integer(0));
        }
        else
        {
          hm.put("nbOccurences", new java.lang.Integer(1));
        }
        hm.put("parentId", _roleName);
        lines.add(hm);
      }
    }
    else
      if (bde instanceof wilos.model.spem2.activity.Activity)
      {
        wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
        java.lang.String newPath = path + " / " + act.getPresentationName();
        lines = this.giveRoleDescriptorsPathName(_project, act, newPath, _roleName, lines);
      }
  }
  return lines;
}haha 
{
  return concreteActivityDao;
}haha 
{
  concreteActivityDao = _concreteActivityDao;
}haha 
{
  return concreteTaskDescriptorDao;
}haha 
{
  concreteTaskDescriptorDao = _concreteTaskDescriptorDao;
}haha 
{
  java.util.Set<wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor> tmp = new java.util.HashSet<wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor>();
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
  java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
    if (element instanceof wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor)
    {
      wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor cwpd = (wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor) element;
      tmp.add(cwpd);
    }
    else
      if (!(element instanceof wilos.model.misc.concretetask.ConcreteTaskDescriptor) && !(element instanceof wilos.model.misc.concreterole.ConcreteRoleDescriptor) && !(element instanceof wilos.model.misc.concretemilestone.ConcreteMilestone))
      {
        wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) element;
        tmp.addAll(this.getConcreteWorkProductDescriptorsFromProject(cact));
      }
  }
  return tmp;
}haha 
{
  this.processService.getProcessDao().getSessionFactory().getCurrentSession().saveOrUpdate(_process);
  wilos.model.misc.project.Project project = this.getProject(((java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID)));
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> lines = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  java.lang.String path = _process.getPresentationName();
  return this.giveWorkProductDescriptorsPathName(project, _process, path, _workProductName, lines);
}haha 
{
  final java.lang.String TABLE_LEAF = "images/expandableTable/leaf.gif";
  java.util.Iterator extfor$iter = _act.getBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
    if (bde instanceof wilos.model.spem2.workproduct.WorkProductDescriptor)
    {
      if (bde.getPresentationName().equals(_workProductName))
      {
        _path += " / " + bde.getPresentationName();
        java.util.HashMap<java.lang.String, java.lang.Object> hm = new java.util.HashMap<java.lang.String, java.lang.Object>();
        hm.put("nodeType", "leaf");
        hm.put("expansionImage", TABLE_LEAF);
        hm.put("id", bde.getId());
        hm.put("name", _path);
        int nbcwpd = this.getConcreteWorkProductDescriptorsFromProject(_project).size();
        if (nbcwpd > 0)
        {
          hm.put("nbOccurences", new java.lang.Integer(0));
        }
        else
        {
          hm.put("nbOccurences", new java.lang.Integer(1));
        }
        hm.put("parentId", _workProductName);
        _lines.add(hm);
      }
    }
    else
      if (bde instanceof wilos.model.spem2.activity.Activity)
      {
        wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
        java.lang.String newPath = _path + " / " + act.getPresentationName();
        _lines = this.giveWorkProductDescriptorsPathName(_project, act, newPath, _workProductName, _lines);
      }
  }
  return _lines;
}haha 
{
  this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  this.projectDao.getSessionFactory().getCurrentSession().refresh(_project);
  this.stateService.updateStateTo(_project, wilos.utils.Constantes.State.READY);
}haha 
{
  return concreteActivityService;
}haha 
{
  concreteActivityService = _concreteActivityService;
}haha 
{
  return concreteRoleDescriptorDao;
}haha 
{
  this.concreteRoleDescriptorDao = concreteRoleDescriptorDao;
}haha 
{
  return taskDescriptorDao;
}haha 
{
  this.taskDescriptorDao = taskDescriptorDao;
}haha 
{
  return roleDescriptorDao;
}haha 
{
  this.roleDescriptorDao = roleDescriptorDao;
}haha 
{
  if (_task != null)
  {
    this.taskDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_task);
  }
  this.concreteActivityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
  wilos.model.spem2.role.RoleDescriptor rd = this.createRoleDescriptor(_roleName, _roleDescription, _task, _roleName);
  if (rd == null)
  {
    return false;
  }
  java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbe = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
    if (cbe instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
    {
      if (!this.createConcreteRoleDescriptor(_roleName, _project, rd, ((wilos.model.misc.concreteactivity.ConcreteActivity) cbe)))
        return false;
    }
  }
  if (!this.createConcreteRoleDescriptor(_roleName, _project, rd, _cact))
    return false;
  else
    return true;
}haha 
{
  wilos.model.spem2.role.RoleDescriptor roleDesc = new wilos.model.spem2.role.RoleDescriptor();
  roleDesc.setIsOutOfProcess(true);
  roleDesc.setPresentationName(_presentationName);
  roleDesc.setDescription(_description);
  roleDesc.setGuid(_guid);
  roleDesc.setPrefix("");
  roleDesc.setIsPlanned(true);
  roleDesc.setHasMultipleOccurrences(false);
  roleDesc.setIsOptional(false);
  if (_mainTask != null)
  {
    _mainTask.addMainRole(roleDesc);
  }
  this.roleDescriptorDao.saveOrUpdateRoleDescriptor(roleDesc);
  if (roleDesc.getId() != null)
    return roleDesc;
  else
    return null;
}haha 
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concRoleDesc = new wilos.model.misc.concreterole.ConcreteRoleDescriptor();
  concRoleDesc.setConcreteName(_concreteName);
  concRoleDesc.setProject(_project);
  concRoleDesc.setInstanciationOrder(1);
  concRoleDesc.addSuperConcreteActivity(_cact);
  concRoleDesc.setRoleDescriptor(_rd);
  concRoleDesc.setBreakdownElement(_rd);
  this.concreteRoleDescriptorDao.saveOrUpdateConcreteRoleDescriptor(concRoleDesc);
  return concRoleDesc.getId() != null;
}haha 
{
  return this.concreteWorkBreakdownElementService;
}haha 
{
  this.concreteWorkBreakdownElementService = _concreteWorkBreakdownElementService;
}haha 
{
  return this.stateService;
}haha 
{
  this.stateService = _stateService;
}haha 
matches47
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
{
  int nbConcreteActivitiesSisters = _nbExistingConcreteActivitiesChildren;
  int i = _nbExistingConcreteActivitiesChildren + 1;
  while ((i <= _nbExistingConcreteActivitiesChildren) + _occ)
  {
    wilos.model.misc.concreteactivity.ConcreteActivity cact = new wilos.model.misc.concreteactivity.ConcreteActivity();
    java.util.List<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = new java.util.ArrayList<wilos.model.spem2.breakdownelement.BreakdownElement>();
    bdes.addAll(this.getAllBreakdownElements(_activity));
    if (_occ != 1 || _nbExistingConcreteActivitiesChildren != 0)
    {
      if (_activity.getPresentationName().equals(""))
        cact.setConcreteName((_activity.getName() + "#" + i));
      else
        cact.setConcreteName((_activity.getPresentationName() + "#" + i));
    }
    else
    {
      if (_activity.getPresentationName().equals(""))
        cact.setConcreteName(_activity.getName());
      else
        cact.setConcreteName(_activity.getPresentationName());
    }
    cact.addActivity(_activity);
    cact.setProject(_project);
    cact.setBreakdownElement(_activity);
    cact.setInstanciationOrder(i);
    cact.setWorkBreakdownElement(_activity);
    cact.setActivity(_activity);
    _cact.setConcreteBreakdownElements(this.concreteActivityService.getConcreteBreakdownElements(_cact));
    cact.addSuperConcreteActivity(_cact);
    cact.setDisplayOrder((cact.getSuperConcreteActivity().getDisplayOrder() + java.lang.Integer.toString((_dispOrd + i))));
    this.concreteActivityService.saveConcreteActivity(cact);
    int dispOrd = 0;
    java.util.Iterator extfor$iter = bdes.iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
      dispOrd++;
      if (bde instanceof wilos.model.spem2.activity.Activity)
      {
        wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
        int occ = this.giveNbOccurences(act.getId(), _list, false);
        if (occ == 0 && _occ > 0)
          occ = _occ;
        this.activityInstanciation(_project, act, cact, _list, occ, false, dispOrd);
      }
      else
        if (bde instanceof wilos.model.spem2.task.TaskDescriptor)
        {
          wilos.model.spem2.task.TaskDescriptor td = (wilos.model.spem2.task.TaskDescriptor) bde;
          int occ = this.giveNbOccurences(td.getId(), _list, false);
          if (occ == 0 && _occ > 0)
            occ = _occ;
          this.taskDescriptorService.taskDescriptorInstanciation(_project, td, cact, occ, false, dispOrd);
        }
    }
    this.concreteActivityService.saveConcreteActivity(cact);
    if (nbConcreteActivitiesSisters != 0)
    {
      wilos.model.misc.concreteactivity.ConcreteActivity lastConcreteActivity = null;
      java.util.Iterator extfor$iter$1 = _concreteActivitiesSisters.iterator();
      while (extfor$iter$1.hasNext())
      {
        wilos.model.misc.concreteactivity.ConcreteActivity tmp = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter$1.next();
        if (lastConcreteActivity == null || tmp.getInstanciationOrder() > lastConcreteActivity.getInstanciationOrder())
        {
          lastConcreteActivity = tmp;
        }
      }
      this.concreteWorkOrderService.saveConcreteWorkOrder(lastConcreteActivity.getId(), cact.getId(), wilos.utils.Constantes.WorkOrderType.FINISH_TO_START, _project.getId());
    }
    nbConcreteActivitiesSisters++;
    _concreteActivitiesSisters.add(cact);
    i++;
  }
}haha 
{
  if (_occ > 0)
  {
    this.concreteActivityService.getConcreteActivityDao().getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
    java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity> concreteActivitiesSisters = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
    int nbExistingConcreteActivitiesChildren = 0;
    java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement tmp = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
      if (tmp instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
      {
        if (((wilos.model.misc.concreteactivity.ConcreteActivity) tmp).getActivity().getId().equals(_activity.getId()))
        {
          nbExistingConcreteActivitiesChildren++;
          concreteActivitiesSisters.add(((wilos.model.misc.concreteactivity.ConcreteActivity) tmp));
        }
      }
    }
    this.peruseConcreteActivitiesChildren(nbExistingConcreteActivitiesChildren, _occ, _project, _activity, _dispOrd, _cact, _list, concreteActivitiesSisters);
  }
}haha 
{
  if (_occ > 0)
  {
    java.util.Iterator extfor$iter = _cacts.iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concreteactivity.ConcreteActivity tmp = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
      java.lang.String strDispOrd = this.concreteActivityService.getMaxDisplayOrder(tmp);
      int dispOrd = java.lang.Integer.parseInt(strDispOrd) + 1;
      this.activityInstanciation(_project, _act, tmp, _list, _occ, true, dispOrd);
      if (tmp instanceof wilos.model.misc.project.Project)
      {
        wilos.model.misc.project.Project pj = (wilos.model.misc.project.Project) tmp;
        this.projectDao.saveOrUpdateProject(pj);
      }
      else
        if (tmp instanceof wilos.model.misc.concretephase.ConcretePhase)
        {
          wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) tmp;
          this.concretePhaseDao.saveOrUpdateConcretePhase(cph);
        }
        else
          if (tmp instanceof wilos.model.misc.concreteiteration.ConcreteIteration)
          {
            wilos.model.misc.concreteiteration.ConcreteIteration cit = (wilos.model.misc.concreteiteration.ConcreteIteration) tmp;
            this.concreteIterationDao.saveOrUpdateConcreteIteration(cit);
          }
          else
            if (tmp instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
            {
              wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) tmp;
              this.concreteActivityService.saveConcreteActivity(cact);
            }
      this.concreteActivityService.saveConcreteActivity(tmp);
    }
  }
  else
  {
    java.util.Set<wilos.model.spem2.breakdownelement.BreakdownElement> bdes = new java.util.HashSet<wilos.model.spem2.breakdownelement.BreakdownElement>();
    bdes.addAll(this.getAllBreakdownElements(_act));
    java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> cacts = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
    cacts.addAll(this.getAllConcreteActivitiesForAProject(_act, _project));
    java.util.Iterator extfor$iter$1 = bdes.iterator();
    while (extfor$iter$1.hasNext())
    {
      wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter$1.next();
      if (bde instanceof wilos.model.spem2.activity.Activity)
      {
        wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) bde;
        int occ = this.giveNbOccurences(act.getId(), _list, true);
        this.activityUpdate(_project, act, cacts, _list, occ);
      }
      else
        if (bde instanceof wilos.model.spem2.task.TaskDescriptor)
        {
          wilos.model.spem2.task.TaskDescriptor td = (wilos.model.spem2.task.TaskDescriptor) bde;
          int occ = this.giveNbOccurences(td.getId(), _list, true);
          this.taskDescriptorService.taskDescriptorUpdate(_project, td, cacts, occ);
        }
    }
  }
}haha 
{
  int nb = 0;
  if (!_isInstanciated)
    nb = 1;
  java.util.Iterator extfor$iter = list.iterator();
  boolean break_0 = false;
  while (extfor$iter.hasNext() && !break_0)
  {
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (!break_0)
      if (((java.lang.String) hashMap.get("id")).equals(_id))
      {
        nb = ((java.lang.Integer) hashMap.get("nbOccurences")).intValue();
        break_0 = true;
      }
  }
  return nb;
}haha 
{
  java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteactivity.ConcreteActivity bde = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
    tmp.add(bde);
  }
  return tmp;
}haha 
{
  java.util.SortedSet<wilos.model.spem2.breakdownelement.BreakdownElement> tmp = new java.util.TreeSet<wilos.model.spem2.breakdownelement.BreakdownElement>();
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.Iterator extfor$iter = _act.getBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
    tmp.add(bde);
  }
  return tmp;
}haha 
{
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.Set<wilos.model.spem2.guide.Guidance> tmp = new java.util.HashSet<wilos.model.spem2.guide.Guidance>();
  java.util.Iterator extfor$iter = _act.getGuidances().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.guide.Guidance g = (wilos.model.spem2.guide.Guidance) extfor$iter.next();
    tmp.add(g);
  }
  return tmp;
}haha 
{
  this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
  java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
  java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
    tmp.add(ca);
  }
  return tmp;
}haha 
{
  return this.activityDao.getActivity(_id);
}haha 
{
  return this.activityDao.getAllActivities();
}haha 
{
  return this.activityDao.getActivityFromGuid(_guid);
}haha 
{
  return this.activityDao.saveOrUpdateActivity(_activity);
}haha 
{
  this.activityDao.deleteActivity(_activity);
}haha 
{
  return this.activityDao;
}haha 
{
  this.activityDao = _activityDao;
}haha 
{
  return concreteIterationDao;
}haha 
{
  this.concreteIterationDao = concreteIterationDao;
}haha 
{
  return concretePhaseDao;
}haha 
{
  this.concretePhaseDao = concretePhaseDao;
}haha 
{
  return projectDao;
}haha 
{
  this.projectDao = projectDao;
}haha 
{
  return taskDescriptorService;
}haha 
{
  this.taskDescriptorService = taskDescriptorService;
}haha 
{
  return this.concreteActivityService;
}haha 
{
  this.concreteActivityService = _concreteActivityService;
}haha 
{
  return this.concreteWorkOrderService;
}haha 
{
  this.concreteWorkOrderService = _concreteWorkOrderService;
}haha 
matches27
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
{
  boolean error = false;
  if (this.participant.getName().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.lastnameRequired"));
  }
  if (this.participant.getFirstname().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.firstnameRequired"));
  }
  if (this.participant.getEmailAddress().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.emailRequired"));
  }
  if (this.participant.getLogin().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.loginRequired"));
  }
  if (this.participant.getPassword().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordRequired"));
  }
  if (this.passwordConfirmation.trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.confirmpasswordRequired"));
  }
  if (!error)
  {
    if (this.loginService.loginExist(this.participant.getLogin().trim()))
    {
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.loginalreadyexist"));
    }
    else
    {
      this.participantService.saveParticipant(this.participant);
      wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.success"));
      wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
    }
  }
  this.participant = new wilos.model.misc.wilosuser.Participant();
}haha 
{
  wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
  wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.cancel"));
}haha 
{
  boolean error = false;
  java.lang.String encryptedCurrentPassword = wilos.utils.Security.encode(this.currentPassword);
  if (this.currentPassword.length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordRequired"));
  }
  else
    if (!this.getParticipantFromSession().getPassword().equals(encryptedCurrentPassword))
    {
      error = true;
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.badpassword"));
    }
    else
      if (this.participant.getName().trim().length() == 0)
      {
        error = true;
        wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.lastnameRequired"));
      }
      else
        if (this.participant.getFirstname().trim().length() == 0)
        {
          error = true;
          wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.firstnameRequired"));
        }
        else
          if (this.participant.getEmailAddress().trim().length() == 0)
          {
            wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.emailRequired"));
            error = true;
          }
  if (!error)
  {
    this.participant.setPassword(this.getParticipantFromSession().getPassword());
    this.participantService.saveParticipantWithoutEncryption(this.participant);
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.participantUpdate.success"));
    wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
  }
}haha 
{
  boolean error = false;
  java.lang.String encryptedCurrentPassword = wilos.utils.Security.encode(this.currentPassword);
  if (this.currentPassword.length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordRequired"));
  }
  else
    if (!this.getParticipantFromSession().getPassword().equals(encryptedCurrentPassword))
    {
      error = true;
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.badpassword"));
    }
    else
      if (this.participant.getPassword().trim().length() == 0)
      {
        error = true;
        wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.newpasswordRequired"));
      }
      else
        if (this.passwordConfirmation.trim().length() == 0)
        {
          error = true;
          wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.confirmpasswordRequired"));
        }
  if (!error)
  {
    this.participantService.saveParticipant(this.participant);
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.passwordsuccess"));
    wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
    this.displayPasswordEdition = false;
  }
  this.participant = new wilos.model.misc.wilosuser.Participant();
}haha 
{
  java.lang.String enteredEmail = (java.lang.String) _value;
  java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
  java.util.regex.Matcher m = p.matcher(enteredEmail);
  boolean matchFound = m.matches();
  if (!matchFound)
  {
    javax.faces.application.FacesMessage message = new javax.faces.application.FacesMessage();
    message.setSummary(wilos.resources.LocaleBean.getText("component.forminscription.err.invalidemail"));
    message.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
    throw new javax.faces.validator.ValidatorException(message);
  }
}haha 
{
  if (selectedPanel.equalsIgnoreCase("pass"))
  {
    selectedPanel = "default";
  }
  else
  {
    selectedPanel = "pass";
  }
  return null;
}haha 
{
  return selectedPanel;
}haha 
{
  selectedPanel = "default";
  return null;
}haha 
{
  this.participantsList = this.getParticipantsList();
  java.util.Iterator extfor$iter = this.participantService.getParticipants().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.wilosuser.Participant e = (wilos.model.misc.wilosuser.Participant) extfor$iter.next();
    if (e.getName().equalsIgnoreCase(this.participant.getName()) && e.getFirstname().equalsIgnoreCase(this.participant.getFirstname()) && e.getEmailAddress().equalsIgnoreCase(this.participant.getEmailAddress()))
    {
      this.participant = e;
      this.currentPassword = e.generateNewPassword();
      this.participant.setPassword(this.currentPassword);
      this.participantService.saveParticipantWithoutEncryption(this.participant);
      this.participantService.saveParticipant(this.participant);
      this.displayPasswordEdition = false;
      this.participant = new wilos.model.misc.wilosuser.Participant();
    }
  }
}haha 
{
  this.concreteRoleDescriptors = new java.util.ArrayList<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
  return this.concreteRoleDescriptors;
}haha 
{
  this.concreteRoleDescriptors = _concreteRoleDescriptors;
}haha 
{
  javax.faces.component.UIComponent passcomponent = _toValidate.findComponent("equal1");
  java.lang.String passValue = (java.lang.String) passcomponent.getAttributes().get("value");
  if (!_value.equals(passValue))
  {
    javax.faces.application.FacesMessage message = new javax.faces.application.FacesMessage();
    message.setSummary(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordnotequals"));
    message.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
    throw new javax.faces.validator.ValidatorException(message);
  }
}haha 
{
  return this.participant;
}haha 
{
  this.participant = _participant;
}haha 
{
  return this.participantService;
}haha 
{
  this.participantService = _participantService;
}haha 
{
  return this.projectService;
}haha 
{
  this.projectService = _projectService;
}haha 
{
  return this.loginService;
}haha 
{
  this.loginService = _loginService;
}haha 
{
  return this.passwordConfirmation;
}haha 
{
  this.passwordConfirmation = _passwordConfirmation;
}haha 
{
  this.participantsList = new java.util.ArrayList<wilos.model.misc.wilosuser.Participant>();
  participantsList.addAll(this.participantService.getParticipants());
  return this.participantsList;
}haha 
{
  this.participantsList = _participantsList;
}haha 
{
  wilos.model.misc.wilosuser.Participant user = getParticipantFromSession();
  if (user instanceof wilos.model.misc.wilosuser.Participant)
  {
    this.affectedProjectsList = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
    java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean> plist = new java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean>();
    plist = (java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean>) this.participantService.getProjectsForAParticipant(user);
    java.util.Iterator extfor$iter = plist.keySet().iterator();
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
    }
  }
  return affectedProjectsList;
}haha 
{
  this.affectedProjectsList = affectedProjectsList;
}haha 
{
  wilos.model.misc.wilosuser.Participant user = getParticipantFromSession();
  java.util.HashMap<java.lang.String, java.lang.Boolean> affectedProjects = new java.util.HashMap<java.lang.String, java.lang.Boolean>();
  java.util.Iterator extfor$iter = this.affectedProjectsList.iterator();
  while (extfor$iter.hasNext())
  {
    java.util.HashMap<java.lang.String, java.lang.Object> ligne = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    java.lang.Boolean testAffectation = (java.lang.Boolean) ligne.get("affected");
    java.lang.String project_id = (java.lang.String) ligne.get("project_id");
    if (!testAffectation)
    {
      if (wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID) != null)
      {
        if (wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID).equals(project_id) && !testAffectation)
        {
          this.visiblePopup = true;
          wilos.presentation.web.tree.TreeBean treeBean = (wilos.presentation.web.tree.TreeBean) wilos.presentation.web.utils.WebCommonService.getBean("TreeBean");
          treeBean.cleanTreeDisplay();
        }
      }
    }
    affectedProjects.put(project_id, testAffectation);
  }
  this.participantService.saveProjectsForAParticipant(user, affectedProjects);
}haha 
{
  java.lang.Boolean isForAssignment = (java.lang.Boolean) evt.getNewValue();
  this.selectedProjectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  if (!isForAssignment)
  {
    this.visiblePopup = true;
  }
  else
  {
    this.participantService.saveProjectForAProjectManager(this.getParticipantFromSession().getId(), this.selectedProjectId, true);
  }
}haha 
{
  this.selectedProjectId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.PROJECT_ID);
  this.participantService.saveProjectForAProjectManager(this.getParticipantFromSession().getId(), this.selectedProjectId, true);
}haha 
{
  this.visiblePopup = true;
}haha 
{
  this.participantService.saveProjectForAProjectManager(this.getParticipantFromSession().getId(), this.selectedProjectId, false);
  this.visiblePopup = false;
}haha 
{
  this.visiblePopup = false;
}haha 
{
  wilos.model.misc.wilosuser.Participant user = getParticipantFromSession();
  if (user instanceof wilos.model.misc.wilosuser.Participant)
  {
    this.manageableProjectsList = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
    java.util.HashMap<wilos.model.misc.project.Project, wilos.model.misc.wilosuser.Participant> manageableProjects = (java.util.HashMap<wilos.model.misc.project.Project, wilos.model.misc.wilosuser.Participant>) this.participantService.getManageableProjectsForAParticipant(user);
    java.util.Iterator extfor$iter = manageableProjects.keySet().iterator();
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
    }
  }
  return this.manageableProjectsList;
}haha 
{
  this.manageableProjectsList = manageableProjectsList;
}haha 
{
  wilos.model.misc.wilosuser.Participant user = getParticipantFromSession();
  java.util.Map<java.lang.String, java.lang.Boolean> affectedManagedProjects = new java.util.HashMap<java.lang.String, java.lang.Boolean>();
  java.util.Iterator extfor$iter = this.manageableProjectsList.iterator();
  while (extfor$iter.hasNext())
  {
    java.util.HashMap<java.lang.String, java.lang.Object> ligne = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    if (!(java.lang.Boolean) ligne.get("hasOtherManager"))
    {
      java.lang.Boolean testAffectation = (java.lang.Boolean) ligne.get("affected");
      java.lang.String project_id = (java.lang.String) ligne.get("project_id");
      affectedManagedProjects.put(project_id, testAffectation);
    }
  }
  this.participantService.saveManagedProjectsForAParticipant(user, affectedManagedProjects);
}haha 
{
  java.lang.String userId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.WILOS_USER_ID);
  wilos.model.misc.wilosuser.Participant user = this.participantService.getParticipant(userId);
  return user;
}haha 
{
  wilos.model.misc.wilosuser.Participant user = this.getParticipantFromSession();
  if (user != null)
  {
    this.participant = user;
    this.participant.setPassword("");
    this.isSetParticipantFromSession = "ok";
  }
  else
  {
    this.isSetParticipantFromSession = "null";
  }
  return this.isSetParticipantFromSession;
}haha 
{
  this.isSetParticipantFromSession = _msg;
}haha 
{
  if (this.getManageableProjectsList().size() == 0)
  {
    this.selectManageableProjectView = "manageable_no_records_view";
  }
  else
  {
    this.selectManageableProjectView = "manageable_records_view";
  }
  return selectManageableProjectView;
}haha 
{
  this.selectManageableProjectView = selectManageableProjectView;
}haha 
{
  return formatter;
}haha 
{
  this.formatter = formatter;
}haha 
{
  this.concreteRoleDescriptorHeaders.addAll(this.getConcreteRoleDescriptorsMap().keySet());
  return this.concreteRoleDescriptorHeaders;
}haha 
{
  this.concreteRoleDescriptorHeaders = _concreteRoleDescriptorHeaders;
}haha 
{
  this.concreteRoleDescriptorsMap = new java.util.HashMap<java.lang.String, java.lang.Boolean>();
  java.util.List<wilos.model.misc.concreterole.ConcreteRoleDescriptor> concreteRoleDescriptorsForAParticipant = new java.util.ArrayList<wilos.model.misc.concreterole.ConcreteRoleDescriptor>();
  java.util.Iterator extfor$iter = concreteRoleDescriptorsForAParticipant.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
    this.concreteRoleDescriptorsMap.put(concreteRoleDescriptor.getConcreteName(), true);
  }
  return this.concreteRoleDescriptorsMap;
}haha 
{
  this.concreteRoleDescriptorsMap = _concreteRoleDescriptorsMap;
}haha 
{
  if (this.getAffectedProjectsList().size() == 0)
  {
    this.selectAffectedProjectView = "affected_no_records_view";
  }
  else
  {
    this.selectAffectedProjectView = "affected_records_view";
  }
  return this.selectAffectedProjectView;
}haha 
{
  this.selectAffectedProjectView = _selectAffectedProjectView;
}haha 
{
  this.participantView = _participantView;
}haha 
{
  this.participant = new wilos.model.misc.wilosuser.Participant();
  this.cleanBean = "ok";
  return this.cleanBean;
}haha 
{
  this.cleanBean = _cleanBean;
}haha 
{
  return visiblePopup;
}haha 
{
  this.visiblePopup = visiblePopup;
}haha 
{
  return currentPassword;
}haha 
{
  this.currentPassword = _currentPassword;
}haha 
{
  return displayPasswordEdition;
}haha 
{
  this.displayPasswordEdition = _displayPasswordEdition;
}haha 
{
  return concreteRoleDescriptorService;
}haha 
{
  this.concreteRoleDescriptorService = concreteRoleDescriptorService;
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  this.setTestDelete(((java.lang.String) map.get("loginParti")));
  this.visiblePopup = true;
}haha 
{
  return testDelete;
}haha 
{
  this.testDelete = deleteParticipant;
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  java.lang.String loginParticipant = (java.lang.String) map.get("loginParti");
  java.util.Iterator extfor$iter = this.participantsList.iterator();
  boolean break_0 = false;
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
  }
}haha 
{
  this.visiblePopup = false;
}haha 
{
  this.visiblePopup = false;
}haha 
{
  java.lang.String retour = evt.getNewValue().toString();
  wilos.model.misc.wilosuser.Participant user = getParticipantFromSession();
  java.util.Iterator<java.util.HashMap<java.lang.String, java.lang.Object>> it = null;
  java.util.Iterator extfor$iter = this.affectedProjectsList.iterator();
  boolean break_1 = false;
  while (extfor$iter.hasNext() && !break_1)
  {
    java.util.HashMap<java.lang.String, java.lang.Object> value = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
    java.lang.String projectId = (java.lang.String) value.get("project_id");
    wilos.model.misc.project.Project project = this.projectService.getProject(projectId);
    if (!break_1)
      if (retour.contains(projectId))
      {
        char test = ((java.lang.String) evt.getNewValue()).charAt((((java.lang.String) evt.getNewValue()).length() - 1));
        boolean newAffectation = false;
        if (!break_1)
          if (test == '1')
          {
            value.put("selectItem", (projectId + "1"));
            value.put("affected", false);
            this.saveProjectsAffectation();
            break_1 = true;
          }
          else
            if (!break_1)
              if (test == '2')
              {
                if (!break_1)
                  value.put("selectItem", (projectId + "2"));
                if (!break_1)
                  value.put("affected", true);
                if (!break_1)
                  if (project.getProjectManager() != null)
                  {
                    if (!break_1)
                      if (project.getProjectManager().getId().equals(user.getId()))
                      {
                        if (!break_1)
                          this.participantService.saveProjectForAProjectManager(user.getId(), projectId, false);
                      }
                  }
                if (!break_1)
                  this.saveProjectsAffectation();
                break_1 = true;
              }
              else
                if (!break_1)
                  if (test == '3')
                  {
                    if (!break_1)
                      value.put("selectItem", (projectId + "3"));
                    if (!break_1)
                      value.put("affected", true);
                    if (!break_1)
                      this.saveProjectsAffectation();
                    if (!break_1)
                      newAffectation = true;
                    if (!break_1)
                      this.getManageableProjectsList();
                    if (!break_1)
                      it = this.manageableProjectsList.iterator();
                    while (it.hasNext())
                    {
                      java.util.HashMap<java.lang.String, java.lang.Object> value2 = it.next();
                      java.lang.String projectId2 = (java.lang.String) value2.get("project_id");
                      if (retour.contains(projectId2))
                      {
                        value2.put("affected", newAffectation);
                        this.saveProjectManagerAffectation();
                      }
                    }
                    break_1 = true;
                  }
      }
  }
}haha 
{
  wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.tableparticipantproject.success"));
}haha 
matches67
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
{
  if (_concreteactivity != null)
  {
    this.getHibernateTemplate().saveOrUpdate(_concreteactivity);
    return _concreteactivity.getId();
  }
  return "";
}haha 
{
  java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> concreteActivities = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
  java.util.Iterator extfor$iter = this.getHibernateTemplate().loadAll(null).iterator();
  while (extfor$iter.hasNext())
  {
    java.lang.Object obj = (java.lang.Object) extfor$iter.next();
    wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) obj;
    concreteActivities.add(ca);
  }
  return concreteActivities;
}haha 
{
  java.util.List concreteactvities = this.getHibernateTemplate().find("from ConcreteActivity a where a.id=?", _id);
  return concreteactvities.size() > 0;
}haha 
{
  if (_id == null)
    return null;
  if (!_id.equals(""))
    return null;
  return null;
}haha 
{
  java.util.List concreteactvities = this.getHibernateTemplate().find("from ConcreteActivity a where a.prefix=?", _prefix);
  if (concreteactvities.size() > 0)
    return (wilos.model.misc.concreteactivity.ConcreteActivity) concreteactvities.get(0);
  else
    return null;
}haha 
{
  this.getHibernateTemplate().delete(_concreteactivity);
}haha 
{
  if (!_name.equals(""))
  {
    java.util.List activities = this.getHibernateTemplate().find("from Activity a where a.name=?", _name);
    if (activities.size() > 0)
      return (wilos.model.misc.concreteactivity.ConcreteActivity) activities.get(0);
  }
  return null;
}haha 
matches7
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkProductDescriptorService.java
{
  return this.concreteWorkProductDescriptorDao.getConcreteWorkProductDescriptor(_concreteWorkProductDescriptorId);
}haha 
{
  _concreteWorkProductDescriptor = this.getConcreteWorkProductDescriptor(_concreteWorkProductDescriptor.getId());
  if (_concreteWorkProductDescriptor != null && _concreteWorkProductDescriptor.getParticipant() == null)
  {
    java.util.Set<wilos.model.misc.concretetask.ConcreteTaskDescriptor> lctd = _concreteWorkProductDescriptor.getProducerConcreteTasks();
    boolean tache_commence = false;
    java.util.Iterator extfor$iter = lctd.iterator();
    while (extfor$iter.hasNext())
    {
      wilos.model.misc.concretetask.ConcreteTaskDescriptor ctd = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
      if (ctd.getState().equals(wilos.utils.Constantes.State.STARTED))
      {
        tache_commence = true;
      }
    }
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
{
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  boolean isOutOfProcess = _concreteWorkProductDescriptor.getWorkProductDescriptor().getIsOutOfProcess();
  _concreteWorkProductDescriptor.removeAllProducerConcreteTasks();
  _concreteWorkProductDescriptor.removeAllOptionalUserConcreteTasks();
  _concreteWorkProductDescriptor.removeAllMandatoryUserConcreteTasks();
  java.util.Iterator extfor$iter = _concreteWorkProductDescriptor.getSuperConcreteActivities().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteactivity.ConcreteActivity sca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
    sca.getConcreteBreakdownElements().remove(_concreteWorkProductDescriptor);
    this.concreteActivityService.saveConcreteActivity(sca);
  }
  wilos.model.spem2.workproduct.WorkProductDescriptor wpd = _concreteWorkProductDescriptor.getWorkProductDescriptor();
  this.workProductDescriptorService.getWorkProductDescriptorDao().getSessionFactory().getCurrentSession().evict(wpd);
  this.workProductDescriptorService.getWorkProductDescriptorDao().getSessionFactory().getCurrentSession().saveOrUpdate(wpd);
  this.workProductDescriptorService.getWorkProductDescriptorDao().getSessionFactory().getCurrentSession().refresh(wpd);
  wpd.removeConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
  this.workProductDescriptorService.getConcreteWorkProductDescriptorDao().getSessionFactory().getCurrentSession().delete(_concreteWorkProductDescriptor);
  this.workProductDescriptorService.getWorkProductDescriptorDao().getSessionFactory().getCurrentSession().saveOrUpdate(wpd);
  if (isOutOfProcess)
  {
    this.workProductDescriptorService.getWorkProductDescriptorDao().deleteWorkProductDescriptor(wpd);
  }
}haha 
{
  this.concreteWorkProductDescriptorDao.saveOrUpdateConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
}haha 
{
  return concreteWorkProductDescriptorDao;
}haha 
{
  concreteWorkProductDescriptorDao = _concreteWorkProductDescriptorDao;
}haha 
{
  return concreteActivityService;
}haha 
{
  concreteActivityService = _concreteActivityService;
}haha 
{
  return workProductDescriptorService;
}haha 
{
  workProductDescriptorService = _workProductDescriptorService;
}haha 
{
  return this.getConcreteWorkProductDescriptorDao().getAllConcreteWorkProductDescriptorsForProject(_projectId);
}haha 
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor rmrd = _concreteWorkProductDescriptor.getResponsibleConcreteRoleDescriptor();
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  if (rmrd != null)
  {
    rmrd.removeConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
  }
  _concreteWorkProductDescriptor.setParticipant(null);
  _concreteWorkProductDescriptor.setState(wilos.utils.Constantes.State.CREATED);
  this.concreteWorkProductDescriptorDao.saveOrUpdateConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
  this.concreteRoleDescriptorService.saveConcreteRoleDescriptor(rmrd);
  wilos.model.misc.wilosuser.Participant currentParticipant = this.participantService.getParticipantDao().getParticipant(_participant.getLogin());
  currentParticipant.removeConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
  this.participantService.getParticipantDao().saveOrUpdateParticipant(currentParticipant);
}haha 
{
  if (_concreteWorkProductDescriptor.getParticipant() == null)
  {
    return true;
  }
  else
  {
    return _concreteWorkProductDescriptor.getParticipant().equals(_user.getId());
  }
}haha 
{
  _concreteWorkProductDescriptor = this.concreteWorkProductDescriptorDao.getConcreteWorkProductDescriptor(_concreteWorkProductDescriptor.getId());
  if (_concreteWorkProductDescriptor != null)
  {
    if (_concreteWorkProductDescriptor.getParticipant() == null)
    {
      _concreteWorkProductDescriptor.setParticipant(_user.getId());
      this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
      wilos.model.spem2.role.RoleDescriptor responsibleRole = _concreteWorkProductDescriptor.getWorkProductDescriptor().getResponsibleRoleDescriptor();
      if (responsibleRole != null)
      {
        this.roleDescriptorService.getRoleDescriptorDao().getSessionFactory().getCurrentSession().saveOrUpdate(responsibleRole);
        java.util.Set<wilos.model.misc.concreterole.ConcreteRoleDescriptor> listecrd = responsibleRole.getConcreteRoleDescriptors();
        java.util.Iterator extfor$iter = listecrd.iterator();
        boolean break_0 = false;
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
        }
      }
      _concreteWorkProductDescriptor.setState(wilos.utils.Constantes.State.READY);
      this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
      this.participantService.saveConcreteWorkProductDescriptorForAParticipant(_user, _concreteWorkProductDescriptor);
      return _concreteWorkProductDescriptor;
    }
    else
    {
      _concreteWorkProductDescriptor.getWorkProductDescriptor().getResponsibleRoleDescriptor();
      return _concreteWorkProductDescriptor;
    }
  }
  return null;
}haha 
{
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
  }
  return afficher;
}haha 
{
  return concreteRoleDescriptorService;
}haha 
{
  concreteRoleDescriptorService = _concreteRoleDescriptorService;
}haha 
{
  return roleDescriptorService;
}haha 
{
  roleDescriptorService = _roleDescriptorService;
}haha 
{
  return participantService;
}haha 
{
  this.participantService = participantService;
}haha 
{
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  java.lang.String partis = _concreteWorkProductDescriptor.getParticipant();
  wilos.model.misc.wilosuser.Participant parti = this.participantService.getParticipant(partis);
  this.participantService.getParticipantDao().saveOrUpdateParticipant(parti);
  return parti;
}haha 
{
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  wilos.model.spem2.workproduct.WorkProductDescriptor workProductDescriptor = _concreteWorkProductDescriptor.getWorkProductDescriptor();
  this.workProductDescriptorService.getWorkProductDescriptorDao().saveOrUpdateWorkProductDescriptor(workProductDescriptor);
  return workProductDescriptor;
}haha 
{
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  return _concreteWorkProductDescriptor.getSuperConcreteActivities();
}haha 
{
  this.concreteWorkProductDescriptorDao.getSessionFactory().getCurrentSession().saveOrUpdate(_concreteWorkProductDescriptor);
  wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = _concreteWorkProductDescriptor.getResponsibleConcreteRoleDescriptor();
  this.concreteRoleDescriptorService.getConcreteRoleDescriptorDao().saveOrUpdateConcreteRoleDescriptor(crd);
  return crd;
}haha 
matches25
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
{
  org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
  return user;
}haha 
{
  org.itracker.model.User user = userDAO.findByLogin(login);
  if (user == null)
    throw new org.itracker.persistence.dao.NoSuchEntityException("User " + login + " not found.");
  return user;
}haha 
{
  org.itracker.model.User user = userDAO.findByLogin(login);
  return user.getPassword();
}haha 
{
  java.util.List<org.itracker.model.User> users = userDAO.findAll();
  return users;
}haha 
{
  java.util.List<org.itracker.model.User> users = userDAO.findActive();
  return users;
}haha 
{
  java.util.List<org.itracker.model.User> superUsers = userDAO.findSuperUsers();
  return superUsers;
}haha 
{
  try
  {
    if (user == null || user.getLogin() == null || user.getLogin().equals(""))
    {
      throw new org.itracker.services.exceptions.UserException("User data was null, or login was empty.");
    }
    try
    {
      this.getUserByLogin(user.getLogin());
      throw new org.itracker.services.exceptions.UserException("User already exists with login: " + user.getLogin());
    }
    catch (org.itracker.persistence.dao.NoSuchEntityException e)
    {
    }
    try
    {
      org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
      if (authenticator != null)
      {
        java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
        values.put("userService", this);
        values.put("configurationService", configurationService);
        authenticator.initialize(values);
        authenticator.createProfile(user, null, org.itracker.services.util.AuthenticationConstants.AUTH_TYPE_UNKNOWN, org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN);
      }
      else
      {
        throw new org.itracker.services.exceptions.AuthenticatorException("Unable to create new authenticator.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
      }
    }
    catch (java.lang.IllegalAccessException ex)
    {
      throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " can not be instantiated.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
    }
    catch (java.lang.InstantiationException ex)
    {
      throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " can not be instantiated.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
    }
    catch (java.lang.ClassCastException ex)
    {
      throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
    }
    user.setStatus(org.itracker.services.util.UserUtilities.STATUS_ACTIVE);
    user.setRegistrationType(user.getRegistrationType());
    userDAO.save(user);
    return user;
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ex)
  {
    throw new org.itracker.services.exceptions.UserException("Could not create user.", ex);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      authenticator.updateProfile(user, org.itracker.services.util.AuthenticationConstants.UPDATE_TYPE_CORE, null, org.itracker.services.util.AuthenticationConstants.AUTH_TYPE_UNKNOWN, org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN);
    }
    else
    {
      logger.warn(("updateUser: no authenticator, throwing AuthenticatorExceptio" + "n"));
      throw new org.itracker.services.exceptions.AuthenticatorException("Unable to create new authenticator.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
    }
  }
  catch (java.lang.IllegalAccessException ex)
  {
    logger.error(("updateUser: IllegalAccessException caught, throwing Authenti" + "catorException"), ex);
    throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " can not be instantiated.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
  }
  catch (java.lang.InstantiationException ex)
  {
    logger.error(("updateUser: InstantiationException caught, throwing Authenti" + "catorException"), ex);
    throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " can not be instantiated.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
  }
  catch (java.lang.ClassCastException ex)
  {
    logger.error(("updateUser: ClassCastException caught, throwing Authenticato" + "rException"), ex);
    throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ex)
  {
    logger.error(("updateUser: AuthenticatorException caught, throwing Authenti" + "catorException"), ex);
    throw new org.itracker.services.exceptions.UserException("Unable to update user.", ex);
  }
  java.lang.Integer id = user.getId();
  userDAO.detach(user);
  org.itracker.model.User existinguser = userDAO.findByPrimaryKey(id);
  userDAO.refresh(existinguser);
  existinguser.setLogin(user.getLogin());
  existinguser.setFirstName(user.getFirstName());
  existinguser.setLastName(user.getLastName());
  existinguser.setEmail(user.getEmail());
  existinguser.setSuperUser(user.isSuperUser());
  existinguser.setStatus(user.getStatus());
  if (user.getPassword() != null && !user.getPassword().equals(""))
  {
    if (logger.isInfoEnabled())
    {
      logger.info(("updateUser: setting new password for " + user.getLogin()));
    }
    existinguser.setPassword(user.getPassword());
  }
  userDAO.saveOrUpdate(existinguser);
  return existinguser;
}haha 
{
  java.lang.String password = org.itracker.services.util.UserUtilities.generatePassword();
  user.setPassword(org.itracker.services.util.UserUtilities.encryptPassword(password));
  return password;
}haha 
{
  org.itracker.model.UserPreferences newUserPrefs = new org.itracker.model.UserPreferences();
  try
  {
    org.itracker.model.User user = userPrefs.getUser();
    newUserPrefs = userPreferencesDAO.findByUserId(user.getId());
    if (newUserPrefs == null)
    {
      newUserPrefs = new org.itracker.model.UserPreferences();
    }
    newUserPrefs.setSaveLogin(userPrefs.getSaveLogin());
    newUserPrefs.setUserLocale(userPrefs.getUserLocale());
    newUserPrefs.setNumItemsOnIndex(userPrefs.getNumItemsOnIndex());
    newUserPrefs.setNumItemsOnIssueList(userPrefs.getNumItemsOnIssueList());
    newUserPrefs.setShowClosedOnIssueList(userPrefs.getShowClosedOnIssueList());
    newUserPrefs.setSortColumnOnIssueList(userPrefs.getSortColumnOnIssueList());
    newUserPrefs.setHiddenIndexSections(userPrefs.getHiddenIndexSections());
    newUserPrefs.setRememberLastSearch(userPrefs.getRememberLastSearch());
    newUserPrefs.setUseTextActions(userPrefs.getUseTextActions());
    newUserPrefs.setUser(user);
    if (userPrefs.isNew())
    {
      newUserPrefs.setCreateDate(new java.util.Date());
      newUserPrefs.setLastModifiedDate(userPrefs.getCreateDate());
      user.setPreferences(newUserPrefs);
      userDAO.saveOrUpdate(user);
    }
    else
    {
      this.userPreferencesDAO.saveOrUpdate(newUserPrefs);
      newUserPrefs = userPreferencesDAO.findByUserId(user.getId());
      user.setUserPreferences(newUserPrefs);
    }
    try
    {
      org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
      if (authenticator != null)
      {
        java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
        values.put("userService", this);
        values.put("configurationService", configurationService);
        authenticator.initialize(values);
        authenticator.updateProfile(user, org.itracker.services.util.AuthenticationConstants.UPDATE_TYPE_PREFERENCE, null, org.itracker.services.util.AuthenticationConstants.AUTH_TYPE_UNKNOWN, org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN);
      }
      else
      {
        throw new org.itracker.services.exceptions.AuthenticatorException("Unable to create new authenticator.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
      }
    }
    catch (java.lang.IllegalAccessException ex)
    {
      throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " can not be instantiated.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
    }
    catch (java.lang.InstantiationException ex)
    {
      throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " can not be instantiated.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
    }
    catch (java.lang.ClassCastException ex)
    {
      throw new org.itracker.services.exceptions.AuthenticatorException("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class.", org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR, ex);
    }
    if (newUserPrefs != null)
      return newUserPrefs;
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ex)
  {
    throw new org.itracker.services.exceptions.UserException("Unable to create new preferences.", ex);
  }
  return userPrefs;
}haha 
{
  user.getProjects().clear();
  userDAO.save(user);
}haha 
{
  return userDAO.findUsersForProjectByAllPermissionTypeList(projectID, permissionTypes);
}haha 
{
  java.util.List<org.itracker.model.Permission> permissions = permissionDAO.findByUserId(user.getId());
  return permissions;
}haha 
{
  java.util.List<org.itracker.model.Permission> permissions = new java.util.ArrayList<org.itracker.model.Permission>();
  org.itracker.model.User user = getUser(userId);
  if (user != null)
  {
    try
    {
      org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
      if (authenticator != null)
      {
        java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
        values.put("userService", this);
        values.put("configurationService", configurationService);
        authenticator.initialize(values);
        permissions = authenticator.getUserPermissions(user, org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN);
      }
      logger.debug(("Found " + permissions.size() + " permissions for user " + user.getLogin()));
    }
    catch (java.lang.IllegalAccessException ex)
    {
      throw new java.lang.RuntimeException("Authenticator class " + authenticatorClassName + " can not be instantiated.", ex);
    }
    catch (java.lang.InstantiationException ex)
    {
      throw new java.lang.RuntimeException("Authenticator class " + authenticatorClassName + " can not be instantiated.", ex);
    }
    catch (java.lang.ClassCastException ex)
    {
      throw new java.lang.RuntimeException("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class.", ex);
    }
    catch (org.itracker.services.exceptions.AuthenticatorException ex)
    {
      throw new java.lang.RuntimeException("Authenticator exception: ", ex);
    }
  }
  return permissions;
}haha 
{
  boolean successful = false;
  try
  {
    org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
    user.getPermissions().addAll(permissions);
    try
    {
      org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
      if (authenticator != null)
      {
        java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
        values.put("userService", this);
        values.put("configurationService", configurationService);
        authenticator.initialize(values);
        if (authenticator.updateProfile(user, org.itracker.services.util.AuthenticationConstants.UPDATE_TYPE_PERMISSION_SET, null, org.itracker.services.util.AuthenticationConstants.AUTH_TYPE_UNKNOWN, org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN))
        {
        }
      }
      else
      {
        logger.error("Unable to create new authenticator.");
        throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
      }
      successful = true;
    }
    catch (java.lang.IllegalAccessException iae)
    {
      logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
      throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
    }
    catch (java.lang.InstantiationException ie)
    {
      logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
      throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
    }
    catch (java.lang.ClassCastException cce)
    {
      logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
      throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
    }
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ae)
  {
    logger.warn(("Error setting user (" + userId + ") permissions.  AuthenticatorException."), ae);
    successful = false;
  }
  return successful;
}haha 
{
  boolean successful = false;
  if (newPermissions == null || newPermissions.size() == 0)
  {
    return successful;
  }
  try
  {
    newPermissions.addAll(getUserPermissionsLocal(getUser(userId)));
    setUserPermissions(userId, newPermissions);
    successful = true;
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ae)
  {
    logger.warn(("Error setting user (" + userId + ") permissions.  AuthenticatorException."), ae);
    successful = false;
  }
  return successful;
}haha 
{
  java.util.Iterator<org.itracker.model.Permission> permssionsIt = permissions.iterator();
  while (permssionsIt.hasNext())
  {
    org.itracker.model.Permission permission2 = (org.itracker.model.Permission) permssionsIt.next();
    if (org.itracker.model.Permission.PERMISSION_PROPERTIES_COMPARATOR.compare(permission, permission2) == 0)
    {
      return permission2;
    }
  }
  return null;
}haha 
{
  boolean hasChanges = false;
  java.util.TreeSet<org.itracker.model.Permission> pSet = new java.util.TreeSet<org.itracker.model.Permission>(org.itracker.model.Permission.PERMISSION_PROPERTIES_COMPARATOR);
  pSet.addAll(newPermissions);
  org.itracker.model.User usermodel = this.getUser(userId);
  java.util.Set<org.itracker.model.Permission> current = new java.util.TreeSet<org.itracker.model.Permission>(org.itracker.model.Permission.PERMISSION_PROPERTIES_COMPARATOR);
  current.addAll(usermodel.getPermissions());
  java.util.Set<org.itracker.model.Permission> remove = new java.util.TreeSet<org.itracker.model.Permission>(org.itracker.model.Permission.PERMISSION_PROPERTIES_COMPARATOR);
  remove.addAll(current);
  remove.removeAll(pSet);
  java.util.Set<org.itracker.model.Permission> add = new java.util.TreeSet<org.itracker.model.Permission>(org.itracker.model.Permission.PERMISSION_PROPERTIES_COMPARATOR);
  add.addAll(pSet);
  add.removeAll(current);
  org.itracker.model.Permission p;
  java.util.Iterator<org.itracker.model.Permission> pIt = remove.iterator();
  boolean skip_0 = false;
  while (pIt.hasNext())
  {
    skip_0 = false;
    p = find(usermodel.getPermissions(), ((org.itracker.model.Permission) pIt.next()));
    if (!skip_0)
      if (null == p)
      {
        skip_0 = true;
      }
    if (!skip_0)
      if (usermodel.getPermissions().contains(p))
      {
        if (!skip_0)
          usermodel.getPermissions().remove(p);
        if (!skip_0)
          permissionDAO.delete(p);
        if (!skip_0)
          hasChanges = true;
      }
  }
  pIt = add.iterator();
  while (pIt.hasNext())
  {
    p = pIt.next();
    if (null == find(usermodel.getPermissions(), p) && !usermodel.getPermissions().contains(p))
    {
      p.setUser(usermodel);
      usermodel.getPermissions().add(p);
      permissionDAO.save(p);
      hasChanges = true;
    }
  }
  if (hasChanges)
  {
    userDAO.saveOrUpdate(usermodel);
  }
  return hasChanges;
}haha 
{
  boolean successful = false;
  if (newPermissions == null || newPermissions.size() == 0)
  {
    return successful;
  }
  try
  {
    java.util.Iterator<org.itracker.model.Permission> delIterator = newPermissions.iterator();
    while (delIterator.hasNext())
    {
      org.itracker.model.Permission permission = (org.itracker.model.Permission) delIterator.next();
      permissionDAO.delete(permission);
    }
    successful = true;
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ae)
  {
    logger.warn(("Error setting user (" + userId + ") permissions.  AuthenticatorException."), ae);
    successful = false;
  }
  return successful;
}haha 
{
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
    }
  }
  return permissionsMap;
}haha 
{
  return getUsersWithProjectPermission(projectId, permissionType, true);
}haha 
{
  return getUsersWithAnyProjectPermission(projectId, new int[] {
                                                                 permissionType,
                                                               }, activeOnly);
}haha 
{
  return getUsersWithAnyProjectPermission(projectId, permissionTypes, true);
}haha 
{
  int[] perm = new int[permissionTypes.length];
  int i = 0;
  while (i < permissionTypes.length)
  {
    perm[i] = permissionTypes[i];
    i++;
  }
  return getUsersWithAnyProjectPermission(projectId, perm, true);
}haha 
{
  return getUsersWithProjectPermission(projectId, permissionTypes, false, activeOnly);
}haha 
{
  java.util.List<org.itracker.model.User> userList = new java.util.ArrayList<org.itracker.model.User>();
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.Map<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      userList = authenticator.getUsersWithProjectPermission(projectId, permissionTypes, requireAll, activeOnly, org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN);
    }
    if (logger.isDebugEnabled())
    {
      logger.debug(("getUsersWithProjectPermission: Found " + userList.size() + " users with project " + projectId + " permissions " + java.util.Arrays.toString(permissionTypes) + (requireAll ? "[AllReq," : "[AnyReq,") + (activeOnly ? "ActiveUsersOnly]" : "AllUsers]")));
    }
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("getUsersWithProjectPermission: Authenticator class " + authenticatorClassName + " can not be instantiated."), iae);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("getUsersWithProjectPermission: Authenticator class " + authenticatorClassName + " can not be instantiated."), ie);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("getUsersWithProjectPermission: Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."), cce);
  }
  catch (org.itracker.services.exceptions.AuthenticatorException ae)
  {
    logger.error(("getUsersWithProjectPermission: Authenticator exception caugh" + "t."), ae);
  }
  return userList;
}haha 
{
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
  while (iter.hasNext())
  {
    userList.add(((org.itracker.model.User) iter.next()));
    j++;
  }
  return userList;
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      return authenticator.checkLogin(login, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      if (authenticator.allowProfileCreation(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource)))
      {
        return authenticator.allowRegistration(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
      }
      return false;
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      return authenticator.allowProfileCreation(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      return authenticator.allowProfileUpdates(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      return authenticator.allowPasswordUpdates(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      return authenticator.allowPermissionUpdates(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
{
  try
  {
    org.itracker.services.authentication.PluggableAuthenticator authenticator = (org.itracker.services.authentication.PluggableAuthenticator) authenticatorClass.newInstance();
    if (authenticator != null)
    {
      java.util.HashMap<java.lang.String, java.lang.Object> values = new java.util.HashMap<java.lang.String, java.lang.Object>();
      values.put("userService", this);
      values.put("configurationService", configurationService);
      authenticator.initialize(values);
      return authenticator.allowPreferenceUpdates(user, authentication, authType, (reqSource == 0 ? org.itracker.services.util.AuthenticationConstants.REQ_SOURCE_UNKNOWN : reqSource));
    }
    logger.error("Unable to create new authenticator.");
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.IllegalAccessException iae)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.InstantiationException ie)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " can not be instantiated."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
  catch (java.lang.ClassCastException cce)
  {
    logger.error(("Authenticator class " + authenticatorClassName + " does not extend the PluggableAuthenticator class."));
    throw new org.itracker.services.exceptions.AuthenticatorException(org.itracker.services.exceptions.AuthenticatorException.SYSTEM_ERROR);
  }
}haha 
matches34
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  return issue;
}haha 
{
  logger.warn("getAllIssues: use of deprecated API");
  if (logger.isDebugEnabled())
  {
    logger.debug("getAllIssues: stacktrace was", new java.lang.RuntimeException());
  }
  return getIssueDAO().findAll();
}haha 
{
  return getIssueDAO().countAllIssues();
}haha 
{
  return getIssuesCreatedByUser(userId, true);
}haha 
{
  final java.util.List<org.itracker.model.Issue> issues;
  if (availableProjectsOnly)
  {
    issues = getIssueDAO().findByCreatorInAvailableProjects(userId, org.itracker.services.util.IssueUtilities.STATUS_CLOSED);
  }
  else
  {
    issues = getIssueDAO().findByCreator(userId, org.itracker.services.util.IssueUtilities.STATUS_CLOSED);
  }
  return issues;
}haha 
{
  return getIssuesOwnedByUser(userId, true);
}haha 
{
  final java.util.List<org.itracker.model.Issue> issues;
  if (availableProjectsOnly)
  {
    issues = getIssueDAO().findByOwnerInAvailableProjects(userId, org.itracker.services.util.IssueUtilities.STATUS_RESOLVED);
  }
  else
  {
    issues = getIssueDAO().findByOwner(userId, org.itracker.services.util.IssueUtilities.STATUS_RESOLVED);
  }
  return issues;
}haha 
{
  return getIssuesWatchedByUser(userId, true);
}haha 
{
  final java.util.List<org.itracker.model.Issue> issues;
  if (availableProjectsOnly)
  {
    issues = getIssueDAO().findByNotificationInAvailableProjects(userId, org.itracker.services.util.IssueUtilities.STATUS_CLOSED);
  }
  else
  {
    issues = getIssueDAO().findByNotification(userId, org.itracker.services.util.IssueUtilities.STATUS_CLOSED);
  }
  return issues;
}haha 
{
  return getUnassignedIssues(true);
}haha 
{
  final java.util.List<org.itracker.model.Issue> issues;
  if (availableProjectsOnly)
  {
    issues = getIssueDAO().findByStatusLessThanEqualToInAvailableProjects(org.itracker.services.util.IssueUtilities.STATUS_UNASSIGNED);
  }
  else
  {
    issues = getIssueDAO().findByStatusLessThanEqualTo(org.itracker.services.util.IssueUtilities.STATUS_UNASSIGNED);
  }
  return issues;
}haha 
{
  java.util.List<org.itracker.model.Issue> issues = getIssueDAO().findByStatus(status);
  return issues;
}haha 
{
  java.util.List<org.itracker.model.Issue> issues = getIssueDAO().findByStatusLessThan(status);
  return issues;
}haha 
{
  java.util.List<org.itracker.model.Issue> issues = getIssueDAO().findBySeverity(severity);
  return issues;
}haha 
{
  return getIssuesByProjectId(projectId, org.itracker.services.util.IssueUtilities.STATUS_END);
}haha 
{
  java.util.List<org.itracker.model.Issue> issues = getIssueDAO().findByProjectAndLowerStatus(projectId, status);
  return issues;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  org.itracker.model.User user = issue.getCreator();
  return user;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  org.itracker.model.User user = issue.getOwner();
  return user;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.List<org.itracker.model.Component> components = issue.getComponents();
  return components;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.List<org.itracker.model.Version> versions = issue.getVersions();
  return versions;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.List<org.itracker.model.IssueAttachment> attachments = issue.getAttachments();
  return attachments;
}haha 
{
  return getIssueDAO().findByPrimaryKey(issueId).getHistory();
}haha 
{
  org.itracker.model.Project project = getProjectDAO().findByPrimaryKey(projectId);
  org.itracker.model.User creator = getUserDAO().findByPrimaryKey(userId);
  if (project.getStatus() != org.itracker.model.Status.ACTIVE)
  {
    throw new org.itracker.services.exceptions.ProjectException("Project is not active.");
  }
  org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity(issue, creator, org.itracker.model.IssueActivityType.ISSUE_CREATED);
  activity.setDescription((org.itracker.core.resources.ITrackerResources.getString("itracker.activity.system.createdfor") + " " + creator.getFirstName() + " " + creator.getLastName()));
  activity.setIssue(issue);
  if (!(createdById == null || createdById.equals(userId)))
  {
    org.itracker.model.User createdBy = getUserDAO().findByPrimaryKey(createdById);
    activity.setUser(createdBy);
    org.itracker.model.Notification watchModel = new org.itracker.model.Notification();
    watchModel.setUser(createdBy);
    watchModel.setIssue(issue);
    watchModel.setRole(org.itracker.model.Notification.Role.CONTRIBUTER);
    issue.getNotifications().add(watchModel);
  }
  java.util.List<org.itracker.model.IssueActivity> activities = new java.util.ArrayList<org.itracker.model.IssueActivity>();
  activities.add(activity);
  issue.setActivities(activities);
  issue.setProject(project);
  issue.setCreator(creator);
  getIssueDAO().save(issue);
  return issue;
}haha 
{
  java.lang.String existingTargetVersion = null;
  getIssueDAO().detach(issueDirty);
  org.itracker.model.Issue persistedIssue = getIssueDAO().findByPrimaryKey(issueDirty.getId());
  getIssueDAO().refresh(persistedIssue);
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssue: updating issue " + issueDirty + "\n(from " + persistedIssue + ")"));
  }
  org.itracker.model.User user = getUserDAO().findByPrimaryKey(userId);
  if (persistedIssue.getProject().getStatus() != org.itracker.model.Status.ACTIVE)
  {
    throw new org.itracker.services.exceptions.ProjectException("Project " + persistedIssue.getProject().getName() + " is not active.");
  }
  if (!persistedIssue.getDescription().equalsIgnoreCase(issueDirty.getDescription()))
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("updateIssue: updating description from " + persistedIssue.getDescription()));
    }
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.DESCRIPTION_CHANGE);
    activity.setDescription((org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.from") + ": " + persistedIssue.getDescription()));
    activity.setUser(user);
    activity.setIssue(issueDirty);
    issueDirty.getActivities().add(activity);
  }
  if (persistedIssue.getResolution() != null && !persistedIssue.getResolution().equalsIgnoreCase(issueDirty.getResolution()))
  {
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.RESOLUTION_CHANGE);
    activity.setDescription((org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.from") + ": " + persistedIssue.getResolution()));
    activity.setUser(user);
    activity.setIssue(issueDirty);
    issueDirty.getActivities().add(activity);
  }
  if (null == persistedIssue.getStatus() || !persistedIssue.getStatus().equals(issueDirty.getStatus()))
  {
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.STATUS_CHANGE);
    activity.setDescription((org.itracker.services.util.IssueUtilities.getStatusName(persistedIssue.getStatus()) + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.to") + " " + org.itracker.services.util.IssueUtilities.getStatusName(issueDirty.getStatus())));
    activity.setUser(user);
    activity.setIssue(issueDirty);
    issueDirty.getActivities().add(activity);
  }
  if (issueDirty.getSeverity() != null && !issueDirty.getSeverity().equals(persistedIssue.getSeverity()) && issueDirty.getSeverity() != -1)
  {
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.SEVERITY_CHANGE);
    activity.setDescription((org.itracker.services.util.IssueUtilities.getSeverityName(persistedIssue.getSeverity()) + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.to") + " " + org.itracker.services.util.IssueUtilities.getSeverityName(issueDirty.getSeverity())));
    activity.setUser(user);
    activity.setIssue(issueDirty);
    issueDirty.getActivities().add(activity);
  }
  if (persistedIssue.getTargetVersion() != null && issueDirty.getTargetVersion() != null && !persistedIssue.getTargetVersion().getId().equals(issueDirty.getTargetVersion().getId()))
  {
    existingTargetVersion = persistedIssue.getTargetVersion().getNumber();
    org.itracker.model.Version version = this.getVersionDAO().findByPrimaryKey(issueDirty.getTargetVersion().getId());
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.TARGETVERSION_CHANGE);
    java.lang.String description = existingTargetVersion + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.to") + " ";
    description += version.getNumber();
    activity.setDescription(description);
    activity.setUser(user);
    activity.setIssue(issueDirty);
    issueDirty.getActivities().add(activity);
  }
  org.itracker.model.User newOwner = issueDirty.getOwner();
  issueDirty.setOwner(persistedIssue.getOwner());
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssue: assigning from " + issueDirty.getOwner() + " to " + newOwner));
  }
  assignIssue(issueDirty, newOwner, user, false);
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssue: updated assignment: " + issueDirty));
  }
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssue: merging issue " + issueDirty + " to " + persistedIssue));
  }
  persistedIssue = getIssueDAO().merge(issueDirty);
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssue: merged issue for saving: " + persistedIssue));
  }
  getIssueDAO().saveOrUpdate(persistedIssue);
  if (logger.isDebugEnabled())
  {
    logger.debug(("updateIssue: saved issue: " + persistedIssue));
  }
  return persistedIssue;
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("moveIssue: " + issue + " to project#" + projectId + ", user#" + userId));
  }
  org.itracker.model.Project project = getProjectDAO().findByPrimaryKey(projectId);
  org.itracker.model.User user = getUserDAO().findByPrimaryKey(userId);
  if (logger.isDebugEnabled())
  {
    logger.debug(("moveIssue: " + issue + " to project: " + project + ", user: " + user));
  }
  org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
  activity.setActivityType(org.itracker.model.IssueActivityType.ISSUE_MOVE);
  activity.setDescription((issue.getProject().getName() + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.to") + " " + project.getName()));
  activity.setUser(user);
  activity.setIssue(issue);
  issue.setProject(project);
  issue.getActivities().add(activity);
  if (logger.isDebugEnabled())
  {
    logger.debug(("moveIssue: updated issue: " + issue));
  }
  try
  {
    getIssueDAO().saveOrUpdate(issue);
  }
  catch (java.lang.Exception e)
  {
    logger.error(("moveIssue: failed to save issue: " + issue), e);
    return null;
  }
  if (logger.isDebugEnabled())
  {
    logger.debug(("moveIssue: saved move-issue to " + project));
  }
  return issue;
}haha 
{
  getIssueHistoryDAO().saveOrUpdate(history);
  history.getIssue().getHistory().add(history);
  getIssueDAO().saveOrUpdate(history.getIssue());
  return true;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  setIssueFields(issue, fields, true);
  return true;
}haha 
{
  java.util.List<org.itracker.model.IssueField> issueFields = issue.getFields();
  if (fields.size() > 0)
  {
    int i = 0;
    while (i < fields.size())
    {
      org.itracker.model.IssueField field = fields.get(i);
      if (issueFields.contains(field))
      {
        issueFields.remove(field);
      }
      org.itracker.model.CustomField customField = getCustomFieldDAO().findByPrimaryKey(fields.get(i).getCustomField().getId());
      field.setCustomField(customField);
      field.setIssue(issue);
      issueFields.add(field);
      i++;
    }
  }
  issue.setFields(issueFields);
  if (save)
  {
    logger.debug("setIssueFields: save was true");
    getIssueDAO().saveOrUpdate(issue);
  }
  return true;
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.List<org.itracker.model.Component> components = new java.util.ArrayList<org.itracker.model.Component>(componentIds.size());
  org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
  java.util.Iterator<java.lang.Integer> idIt = componentIds.iterator();
  while (idIt.hasNext())
  {
    java.lang.Integer id = (java.lang.Integer) idIt.next();
    org.itracker.model.Component c = getComponentDAO().findById(id);
    components.add(c);
  }
  setIssueComponents(issue, components, user, true);
  return true;
}haha 
{
  if (issue.getComponents() == null)
  {
    if (logger.isInfoEnabled())
    {
      logger.info("setIssueComponents: components was null");
    }
    issue.setComponents(new java.util.ArrayList<org.itracker.model.Component>(components.size()));
  }
  if (components.isEmpty() && !issue.getComponents().isEmpty())
  {
    addComponentsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.all")).append(" ").append(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.removed")).toString());
    issue.getComponents().clear();
  }
  else
  {
    java.util.Collections.sort(issue.getComponents(), org.itracker.model.Component.NAME_COMPARATOR);
    java.util.Iterator<org.itracker.model.Component> iterator = issue.getComponents().iterator();
    while (iterator.hasNext())
    {
      org.itracker.model.Component component = (org.itracker.model.Component) iterator.next();
      if (components.contains(component))
      {
        components.remove(component);
      }
      else
      {
        addComponentsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.removed")).append(": ").append(component.getName()).toString());
        iterator.remove();
      }
    }
    java.util.Collections.sort(components, org.itracker.model.Component.NAME_COMPARATOR);
    iterator = components.iterator();
    while (iterator.hasNext())
    {
      org.itracker.model.Component component = iterator.next();
      if (!issue.getComponents().contains(component))
      {
        addComponentsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.added")).append(": ").append(component.getName()).toString());
        issue.getComponents().add(component);
      }
    }
  }
  if (save)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug("setIssueComponents: save was true");
    }
    getIssueDAO().saveOrUpdate(issue);
  }
  return true;
}haha 
{
  org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
  activity.setActivityType(org.itracker.model.IssueActivityType.COMPONENTS_MODIFIED);
  activity.setDescription(description);
  activity.setIssue(issue);
  activity.setUser(user);
  issue.getActivities().add(activity);
}haha 
{
  if (issue.getVersions() == null)
  {
    if (logger.isInfoEnabled())
    {
      logger.info("setIssueVersions: versions were null!");
    }
    issue.setVersions(new java.util.ArrayList<org.itracker.model.Version>());
  }
  if (versions.isEmpty() && !issue.getVersions().isEmpty())
  {
    addVersionsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.all")).append(" ").append(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.removed")).toString());
    issue.getVersions().clear();
  }
  else
  {
    java.util.Collections.sort(issue.getVersions(), org.itracker.model.Version.VERSION_COMPARATOR);
    java.lang.StringBuilder changesBuf = new java.lang.StringBuilder();
    java.util.Iterator<org.itracker.model.Version> iterator = issue.getVersions().iterator();
    while (iterator.hasNext())
    {
      org.itracker.model.Version version = iterator.next();
      if (versions.contains(version))
      {
        versions.remove(version);
      }
      else
      {
        if (changesBuf.length() > 0)
        {
          changesBuf.append(", ");
        }
        changesBuf.append(version.getNumber());
        iterator.remove();
      }
    }
    if (changesBuf.length() > 0)
    {
      addVersionsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.removed")).append(": ").append(changesBuf).toString());
    }
    changesBuf = new java.lang.StringBuilder();
    java.util.Collections.sort(versions, org.itracker.model.Version.VERSION_COMPARATOR);
    iterator = versions.iterator();
    while (iterator.hasNext())
    {
      org.itracker.model.Version version = iterator.next();
      if (changesBuf.length() > 0)
      {
        changesBuf.append(", ");
      }
      changesBuf.append(version.getNumber());
      issue.getVersions().add(version);
    }
    if (changesBuf.length() > 0)
    {
      addVersionsModifiedActivity(issue, user, new java.lang.StringBuilder(org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.added")).append(": ").append(changesBuf).toString());
    }
  }
  if (save)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("setIssueVersions: updating issue: " + issue));
    }
    getIssueDAO().saveOrUpdate(issue);
  }
  return true;
}haha 
{
  org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
  activity.setActivityType(org.itracker.model.IssueActivityType.TARGETVERSION_CHANGE);
  activity.setDescription(description);
  activity.setIssue(issue);
  activity.setUser(user);
  issue.getActivities().add(activity);
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
  java.util.ArrayList<org.itracker.model.Version> versions = new java.util.ArrayList<org.itracker.model.Version>(versionIds.size());
  java.util.Iterator<java.lang.Integer> versionsIdIt = versionIds.iterator();
  while (versionsIdIt.hasNext())
  {
    java.lang.Integer id = versionsIdIt.next();
    versions.add(getVersionDAO().findByPrimaryKey(id));
  }
  return setIssueVersions(issue, versions, user, true);
}haha 
{
  org.itracker.model.IssueRelation issueRelation = getIssueRelationDAO().findByPrimaryKey(relationId);
  return issueRelation;
}haha 
{
  org.itracker.model.User user = getUserDAO().findByPrimaryKey(userId);
  if (null == user)
  {
    throw new java.lang.IllegalArgumentException("Invalid user-id: " + userId);
  }
  if (issueId != null && relatedIssueId != null)
  {
    int matchingRelationType = org.itracker.services.util.IssueUtilities.getMatchingRelationType(relationType);
    org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
    org.itracker.model.Issue relatedIssue = getIssueDAO().findByPrimaryKey(relatedIssueId);
    org.itracker.model.IssueRelation relationA = new org.itracker.model.IssueRelation();
    relationA.setRelationType(relationType);
    relationA.setIssue(issue);
    relationA.setRelatedIssue(relatedIssue);
    relationA.setMatchingRelationId(0);
    relationA.setLastModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
    getIssueRelationDAO().saveOrUpdate(relationA);
    org.itracker.model.IssueRelation relationB = new org.itracker.model.IssueRelation();
    relationB.setRelationType(matchingRelationType);
    relationB.setIssue(relatedIssue);
    relationB.setRelatedIssue(issue);
    relationB.setMatchingRelationId(relationA.getId());
    relationB.setLastModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
    getIssueRelationDAO().saveOrUpdate(relationB);
    relationA.setMatchingRelationId(relationB.getId());
    getIssueRelationDAO().saveOrUpdate(relationA);
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.RELATION_ADDED);
    activity.setDescription(org.itracker.core.resources.ITrackerResources.getString("itracker.activity.relation.add", new java.lang.Object[] {
                                                                                                                                               org.itracker.services.util.IssueUtilities.getRelationName(relationType),
                                                                                                                                               relatedIssueId,
                                                                                                                                             }));
    activity.setIssue(issue);
    issue.getActivities().add(activity);
    activity.setUser(user);
    getIssueDAO().saveOrUpdate(issue);
    activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.RELATION_ADDED);
    activity.setDescription(org.itracker.core.resources.ITrackerResources.getString("itracker.activity.relation.add", new java.lang.Object[] {
                                                                                                                                               org.itracker.services.util.IssueUtilities.getRelationName(matchingRelationType),
                                                                                                                                               issueId,
                                                                                                                                             }));
    activity.setIssue(relatedIssue);
    activity.setUser(user);
    relatedIssue.getActivities().add(activity);
    getIssueDAO().saveOrUpdate(relatedIssue);
    return true;
  }
  return false;
}haha 
{
  org.itracker.model.IssueRelation issueRelation = getIssueRelationDAO().findByPrimaryKey(relationId);
  java.lang.Integer issueId = issueRelation.getIssue().getId();
  java.lang.Integer relatedIssueId = issueRelation.getRelatedIssue().getId();
  java.lang.Integer matchingRelationId = issueRelation.getMatchingRelationId();
  if (matchingRelationId != null)
  {
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.RELATION_REMOVED);
    activity.setDescription(org.itracker.core.resources.ITrackerResources.getString("itracker.activity.relation.removed", issueId.toString()));
  }
  org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
  activity.setActivityType(org.itracker.model.IssueActivityType.RELATION_REMOVED);
  activity.setDescription(org.itracker.core.resources.ITrackerResources.getString("itracker.activity.relation.removed", relatedIssueId.toString()));
  getIssueRelationDAO().delete(issueRelation);
}haha 
{
  return assignIssue(issueId, userId, userId);
}haha 
{
  return assignIssue(getIssueDAO().findByPrimaryKey(issueId), getUserDAO().findByPrimaryKey(userId), getUserDAO().findByPrimaryKey(assignedByUserId), true);
}haha 
{
  if (issue.getOwner() == user || null != issue.getOwner() && issue.getOwner().equals(user))
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("assignIssue: attempted to reassign " + issue + " to current owner " + user));
    }
    return false;
  }
  if (null == user)
  {
    if (logger.isInfoEnabled())
    {
      logger.info(("assignIssue: call to unasign " + issue));
    }
    return unassignIssue(issue, assignedByUser, save);
  }
  if (logger.isInfoEnabled())
  {
    logger.info(("assignIssue: assigning " + issue + " to " + user));
  }
  org.itracker.model.User currOwner = issue.getOwner();
  if (!user.equals(currOwner))
  {
    if (currOwner != null && !notificationService.hasIssueNotification(issue, currOwner.getId(), org.itracker.model.Notification.Role.IP))
    {
      org.itracker.model.Notification notification = new org.itracker.model.Notification(currOwner, issue, org.itracker.model.Notification.Role.IP);
      if (save)
      {
        notificationService.addIssueNotification(notification);
      }
      else
      {
        issue.getNotifications().add(notification);
      }
    }
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.OWNER_CHANGE);
    activity.setDescription(((currOwner == null ? "[" + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.unassigned") + "]" : currOwner.getLogin()) + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.to") + " " + user.getLogin()));
    activity.setUser(assignedByUser);
    activity.setIssue(issue);
    issue.getActivities().add(activity);
    issue.setOwner(user);
    if (logger.isDebugEnabled())
    {
      logger.debug(("assignIssue: current status: " + issue.getStatus()));
    }
    if (issue.getStatus() < org.itracker.services.util.IssueUtilities.STATUS_ASSIGNED)
    {
      issue.setStatus(org.itracker.services.util.IssueUtilities.STATUS_ASSIGNED);
      if (logger.isDebugEnabled())
      {
        logger.debug(("assignIssue: new status set to " + issue.getStatus()));
      }
    }
    if (save)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("assignIssue: saving re-assigned issue");
      }
      getIssueDAO().saveOrUpdate(issue);
      notificationService.sendNotification(issue, org.itracker.model.Notification.Type.ASSIGNED, org.itracker.web.util.ServletContextUtils.getItrackerServices().getConfigurationService().getSystemBaseURL());
    }
  }
  return true;
}haha 
{
  if (logger.isDebugEnabled())
  {
    logger.debug(("unassignIssue: " + issue));
  }
  if (issue.getOwner() != null)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(("unassignIssue: unassigning from " + issue.getOwner()));
    }
    if (!notificationService.hasIssueNotification(issue, issue.getOwner().getId(), org.itracker.model.Notification.Role.CONTRIBUTER))
    {
      org.itracker.model.Notification notification = new org.itracker.model.Notification(issue.getOwner(), issue, org.itracker.model.Notification.Role.CONTRIBUTER);
      if (save)
      {
        notificationService.addIssueNotification(notification);
      }
      else
      {
        issue.getNotifications().add(notification);
      }
    }
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity(issue, unassignedByUser, org.itracker.model.IssueActivityType.OWNER_CHANGE);
    activity.setDescription((issue.getOwner().getLogin() + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.to") + " [" + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.unassigned") + "]"));
    issue.setOwner(null);
    if (issue.getStatus() >= org.itracker.services.util.IssueUtilities.STATUS_ASSIGNED)
    {
      issue.setStatus(org.itracker.services.util.IssueUtilities.STATUS_UNASSIGNED);
    }
    if (save)
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("unassignIssue: saving unassigned issue..");
      }
      getIssueDAO().saveOrUpdate(issue);
      notificationService.sendNotification(issue, org.itracker.model.Notification.Type.ASSIGNED, org.itracker.web.util.ServletContextUtils.getItrackerServices().getConfigurationService().getSystemBaseURL());
    }
  }
  return true;
}haha 
{
  org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
  activity.setActivityType(org.itracker.model.IssueActivityType.SYSTEM_UPDATE);
  activity.setDescription(org.itracker.core.resources.ITrackerResources.getString("itracker.activity.system.status"));
  java.util.ArrayList<org.itracker.model.IssueActivity> activities = new java.util.ArrayList<org.itracker.model.IssueActivity>();
  activity.setIssue(updateissue);
  activity.setUser(getUserDAO().findByPrimaryKey(userId));
  updateissue.getActivities().add(activity);
  org.itracker.model.Issue updated = updateIssue(updateissue, userId);
  updated.getActivities().addAll(activities);
  getIssueDAO().saveOrUpdate(updated);
  return updated;
}haha 
{
  if (issueId == null)
  {
    return;
  }
  java.util.Collection<org.itracker.model.IssueActivity> activity = getIssueActivityDAO().findByIssueId(issueId);
  java.util.Iterator<org.itracker.model.IssueActivity> iter = activity.iterator();
  while (iter.hasNext())
  {
    ((org.itracker.model.IssueActivity) iter.next()).setNotificationSent(notificationSent);
  }
}haha 
{
  org.itracker.model.Issue issue = attachment.getIssue();
  attachment.setFileName(("attachment_issue_" + issue.getId() + "_" + attachment.getOriginalFileName()));
  attachment.setFileData((data == null ? new byte[0] : data));
  if (logger.isDebugEnabled())
  {
    logger.debug(("addIssueAttachment: adding attachment " + attachment));
  }
  issue.getAttachments().add(attachment);
  if (logger.isDebugEnabled())
  {
    logger.debug(("addIssueAttachment: saving updated issue " + issue));
  }
  this.getIssueDAO().saveOrUpdate(issue);
  return true;
}haha 
{
  if (attachmentId != null && data != null)
  {
    org.itracker.model.IssueAttachment attachment = getIssueAttachmentDAO().findByPrimaryKey(attachmentId);
    attachment.setFileData(data);
    return true;
  }
  return false;
}haha 
{
  if (fileName != null && data != null)
  {
    org.itracker.model.IssueAttachment attachment = getIssueAttachmentDAO().findByFileName(fileName);
    attachment.setFileData(data);
    return true;
  }
  return false;
}haha 
{
  org.itracker.model.IssueAttachment attachementBean = this.getIssueAttachmentDAO().findByPrimaryKey(attachmentId);
  getIssueAttachmentDAO().delete(attachementBean);
  return true;
}haha 
{
  org.itracker.model.IssueHistory history = getIssueHistoryDAO().findByPrimaryKey(entryId);
  if (history != null)
  {
    history.setStatus(org.itracker.services.util.IssueUtilities.HISTORY_STATUS_REMOVED);
    org.itracker.model.IssueActivity activity = new org.itracker.model.IssueActivity();
    activity.setActivityType(org.itracker.model.IssueActivityType.REMOVE_HISTORY);
    activity.setDescription((org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.entry") + " " + entryId + " " + org.itracker.core.resources.ITrackerResources.getString("itracker.web.generic.removed") + "."));
    getIssueHistoryDAO().delete(history);
    return history.getIssue().getId();
  }
  return java.lang.Integer.valueOf((-1));
}haha 
{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  org.itracker.model.Project project = issue.getProject();
  return project;
}haha 
{
  int i = 0;
  java.util.Collection<org.itracker.model.IssueActivity> activity = getIssueActivityDAO().findByIssueId(issueId);
  org.itracker.model.IssueActivity[] activityArray = new org.itracker.model.IssueActivity[activity.size()];
  java.util.Iterator<org.itracker.model.IssueActivity> iterator = activity.iterator();
  while (iterator.hasNext())
  {
    activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
    i++;
  }
  return java.util.Arrays.asList(activityArray);
}haha 
{
  return getIssueAttachmentDAO().countAll().longValue();
}haha 
{
  logger.warn("getAllIssueAttachments: use of deprecated API");
  if (logger.isDebugEnabled())
  {
    logger.debug("getAllIssueAttachments: stacktrace was", new java.lang.RuntimeException());
  }
  java.util.List<org.itracker.model.IssueAttachment> attachments = getIssueAttachmentDAO().findAll();
  return attachments;
}haha 
{
  org.itracker.model.IssueAttachment attachment = getIssueAttachmentDAO().findByPrimaryKey(attachmentId);
  return attachment;
}haha 
{
  byte[] data;
  org.itracker.model.IssueAttachment attachment = getIssueAttachmentDAO().findByPrimaryKey(attachmentId);
  data = attachment.getFileData();
  return data;
}haha 
{
  int i = 0;
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.Collection<org.itracker.model.IssueAttachment> attachments = issue.getAttachments();
  i = attachments.size();
  return i;
}haha 
{
  return getIssueDAO().latestModificationDate(projectId);
}haha 
{
  org.itracker.model.Issue issue = getIssue(issueId);
  java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> permissions = getUserDAO().getUsersMapOfProjectsAndPermissionTypes(user);
  return org.itracker.services.util.IssueUtilities.canViewIssue(issue, user.getId(), permissions);
}haha 
{
  java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> permissions = getUserDAO().getUsersMapOfProjectsAndPermissionTypes(user);
  return org.itracker.services.util.IssueUtilities.canViewIssue(issue, user.getId(), permissions);
}haha 
{
  return userDAO;
}haha 
{
  return issueDAO;
}haha 
{
  return projectDAO;
}haha 
{
  return issueActivityDAO;
}haha 
{
  return this.versionDAO;
}haha 
{
  return this.componentDAO;
}haha 
{
  return customFieldDAO;
}haha 
{
  return issueHistoryDAO;
}haha 
{
  return issueRelationDAO;
}haha 
{
  return issueAttachmentDAO;
}haha 
{
  return getIssueAttachmentDAO().totalAttachmentsSize().longValue() / 1024;
}haha 
{
  return getIssueDAO().query(queryModel, user, userPermissions);
}haha 
{
  return getIssueAttachmentDAO().totalAttachmentsSize();
}haha 
matches71
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
{
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
  while (projectIt.hasNext())
  {
    org.itracker.model.Project project = projectIt.next();
    ptos.add(createProjectPTO(project, projectService, permissions));
  }
  return ptos;
}haha 
{
  final java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> permissions = org.itracker.web.util.RequestHelper.getUserPermissions(request.getSession());
  org.itracker.services.ProjectService projectService = this.getITrackerServices().getProjectService();
  request.setAttribute("projects", getPTOs(projectService, new int[] {
                                                                       org.itracker.services.util.UserUtilities.PERMISSION_VIEW_ALL,
                                                                       org.itracker.services.util.UserUtilities.PERMISSION_VIEW_USERS,
                                                                     }, permissions));
  java.lang.String pageTitleKey = "itracker.web.listprojects.title";
  java.lang.String pageTitleArg = "";
  request.setAttribute("pageTitleKey", pageTitleKey);
  request.setAttribute("pageTitleArg", pageTitleArg);
  log.info("ListProjectsAction: Forward: listprojects");
  return mapping.findForward("list_projects");
}haha 
{
  org.itracker.web.ptos.ProjectPTO pto = new org.itracker.web.ptos.ProjectPTO(project, projectService, permissions);
  return pto;
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityService.java
{
  java.util.SortedSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement> tmp = new java.util.TreeSet<wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement>();
  this.concreteActivityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
  java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbde = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
    tmp.add(cbde);
  }
  return tmp;
}haha 
{
  this.concreteActivityDao.saveOrUpdateConcreteActivity(_concreteActivity);
}haha 
{
  return this.concreteActivityDao.existsConcreteActivity(_concreteActivityId);
}haha 
{
  return this.concreteActivityDao.getConcreteActivity(_concreteActivityId);
}haha 
{
  return this.concreteActivityDao.getAllConcreteActivities();
}haha 
{
  return concreteActivityDao;
}haha 
{
  this.concreteActivityDao = concreteActivityDao;
}haha 
{
  return concreteMilestoneService;
}haha 
{
  concreteMilestoneService = _concreteMilestoneService;
}haha 
{
  return this.stateService;
}haha 
{
  this.stateService = _stateService;
}haha 
{
  return this.concreteActivityDao.getMaxDisplayOrder(_cact);
}haha 
matches12
/Users/remywang/metalift/txl/qbs/allbench//ParticipantService.java
{
  return this.concreteRoleDescriptorService;
}haha 
{
  this.concreteRoleDescriptorService = _concreteRoleDescriptorService;
}haha 
{
  return this.participantDao;
}haha 
{
  this.participantDao = _participantDao;
}haha 
{
  return this.projectService;
}haha 
{
  this.projectService = _projectService;
}haha 
{
  return this.participantDao.getAllParticipants();
}haha 
{
  return this.participantDao.getParticipantById(_id);
}haha 
{
  _participant.setPassword(wilos.utils.Security.encode(_participant.getPassword()));
  participantDao.saveOrUpdateParticipant(_participant);
}haha 
{
  if (_participant.getNewPassword() != null && !_participant.getNewPassword().trim().equalsIgnoreCase(""))
  {
    _participant.setPassword(wilos.utils.Security.encode(_participant.getNewPassword()));
  }
  participantDao.saveOrUpdateParticipant(_participant);
}haha 
{
  wilos.model.misc.wilosuser.Participant participant = this.getParticipant(participantId);
  if (participant != null)
  {
    this.participantDao.deleteParticipant(participant);
  }
}haha 
{
  this.participantDao.getSessionFactory().getCurrentSession().saveOrUpdate(_participant);
  java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean> affectedProjectList = new java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean>();
  java.util.HashSet<wilos.model.misc.project.Project> allProjectList = new java.util.HashSet<wilos.model.misc.project.Project>();
  allProjectList = (java.util.HashSet<wilos.model.misc.project.Project>) this.projectService.getUnfinishedProjects();
  java.util.Iterator extfor$iter = allProjectList.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.project.Project p = (wilos.model.misc.project.Project) extfor$iter.next();
    java.util.Set<wilos.model.misc.project.Project> tmp = _participant.getAffectedProjectList();
    if (tmp.contains(p))
    {
      affectedProjectList.put(p, true);
    }
    else
    {
      affectedProjectList.put(p, false);
    }
  }
  return affectedProjectList;
}haha 
{
  wilos.model.misc.wilosuser.Participant currentParticipant = this.getParticipantDao().getParticipant(participant.getLogin());
  wilos.model.misc.project.Project currentProject;
  java.util.Iterator extfor$iter = affectedProjects.keySet().iterator();
  while (extfor$iter.hasNext())
  {
    java.lang.String projectId = (java.lang.String) extfor$iter.next();
    currentProject = this.projectService.getProject(projectId);
    if (java.lang.Boolean.valueOf(affectedProjects.get(projectId)) == true)
    {
      currentParticipant.addAffectedProject(currentProject);
    }
    else
    {
      currentParticipant.removeAffectedProject(currentProject);
      if (currentProject.getProjectManager() != null)
      {
        if (currentProject.getProjectManager().getId().equals(currentParticipant.getId()))
        {
          currentParticipant.removeManagedProject(currentProject);
          this.projectService.saveProject(currentProject);
        }
      }
    }
  }
  this.participantDao.saveOrUpdateParticipant(currentParticipant);
}haha 
{
  wilos.model.misc.wilosuser.Participant currentParticipant = this.getParticipantDao().getParticipantById(_participantId);
  wilos.model.misc.project.Project currentProject = this.projectService.getProject(_projectId);
  if (_isForAssignment)
  {
    currentParticipant.addManagedProject(currentProject);
  }
  else
  {
    currentParticipant.removeManagedProject(currentProject);
  }
  this.participantDao.saveOrUpdateParticipant(currentParticipant);
}haha 
{
  java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean> affectedProjectList = new java.util.HashMap<wilos.model.misc.project.Project, java.lang.Boolean>();
  java.util.HashMap<wilos.model.misc.project.Project, wilos.model.misc.wilosuser.Participant> manageableProjectList = new java.util.HashMap<wilos.model.misc.project.Project, wilos.model.misc.wilosuser.Participant>();
  affectedProjectList = this.getProjectsForAParticipant(participant);
  java.util.Iterator extfor$iter = affectedProjectList.keySet().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
    if (affectedProjectList.get(project).booleanValue() == true)
    {
      if (project.getProjectManager() == null)
      {
        manageableProjectList.put(project, null);
      }
      else
      {
        manageableProjectList.put(project, project.getProjectManager());
      }
    }
  }
  return manageableProjectList;
}haha 
{
  wilos.model.misc.wilosuser.Participant currentParticipant = this.getParticipantDao().getParticipant(participant.getLogin());
  wilos.model.misc.project.Project currentProject;
  java.util.Iterator extfor$iter = managedProjects.keySet().iterator();
  while (extfor$iter.hasNext())
  {
    java.lang.String projectId = (java.lang.String) extfor$iter.next();
    currentProject = this.projectService.getProject(projectId);
    if ((java.lang.Boolean) managedProjects.get(projectId))
    {
      currentParticipant.addManagedProject(currentProject);
    }
    else
      currentParticipant.removeManagedProject(currentProject);
  }
  this.participantDao.saveOrUpdateParticipant(currentParticipant);
}haha 
{
  return new wilos.business.webservices.transfertobject.ParticipantTO(participantDao.getParticipant(_login));
}haha 
{
  this.participantDao.getSessionFactory().getCurrentSession().saveOrUpdate(participant);
  return participant.getLogin();
}haha 
{
  return this.concreteWorkProductDescriptorService;
}haha 
{
  this.concreteWorkProductDescriptorService = _concreteWorkProductDescriptorService;
}haha 
{
  wilos.model.misc.wilosuser.Participant currentParticipant = this.getParticipantDao().getParticipant(_participant.getLogin());
  currentParticipant.addConcreteWorkProductDescriptor(_concreteWorkProductDescriptor);
  this.participantDao.saveOrUpdateParticipant(currentParticipant);
}haha 
matches21
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkBreakdownElementService.java
{
  this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
  java.util.List<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement> tmp = new java.util.ArrayList<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement>();
  java.util.Iterator extfor$iter = this.concreteWorkBreakdownElementDao.getAllConcreteWorkBreakdownElements().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cwbde = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement) extfor$iter.next();
    wilos.model.spem2.workbreakdownelement.WorkBreakdownElement wbde = cwbde.getWorkBreakdownElement();
    if (!(cwbde instanceof wilos.model.misc.project.Project) && wbde != null)
    {
      java.lang.String id = cwbde.getProject().getId();
      if (id.equals(_project.getId()))
      {
        tmp.add(cwbde);
      }
    }
  }
  return tmp;
}haha 
{
  this.concreteWorkBreakdownElementDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cwbde);
  java.util.List<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement> tmp = new java.util.ArrayList<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement>();
  java.util.Iterator extfor$iter = _cwbde.getSuperConcreteActivities().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cwbde = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement) extfor$iter.next();
    tmp.add(cwbde);
  }
  return tmp;
}haha 
{
  boolean instanciable = false;
  this.concreteWorkBreakdownElementDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cwbde);
  wilos.model.spem2.workbreakdownelement.WorkBreakdownElement pred = _cwbde.getWorkBreakdownElement();
  int s = 0;
  java.util.Iterator extfor$iter = pred.getSuccessors().iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.spem2.workbreakdownelement.WorkOrder wo = (wilos.model.spem2.workbreakdownelement.WorkOrder) extfor$iter.next();
    wilos.model.spem2.workbreakdownelement.WorkBreakdownElement succ = wo.getSuccessor();
    int p = _cwbde.getConcreteSuccessors().size();
    s += this.workBreakdownElementService.getAllConcreteWorkBreakdownElementsFromWorkBreakdownElement(succ).size();
    if (p < s)
    {
      instanciable = true;
    }
  }
  return instanciable;
}haha 
{
  return this.concreteWorkBreakdownElementDao.getConcreteWorkBreakdownElement(_id);
}haha 
{
  return this.concreteWorkBreakdownElementDao;
}haha 
{
  this.concreteWorkBreakdownElementDao = _concreteWorkBreakdownElementDao;
}haha 
{
  return this.projectDao;
}haha 
{
  this.projectDao = _projectDao;
}haha 
{
  return this.workBreakdownElementService;
}haha 
{
  this.workBreakdownElementService = _workBreakdownElementService;
}haha 
{
  return this.concreteWorkOrderService;
}haha 
{
  this.concreteWorkOrderService = _concreteWorkOrderService;
}haha 
{
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> predecessorHashMap = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  if (_cwbde != null)
  {
    _cwbde = this.getConcreteWorkBreakdownElement(_cwbde.getId());
    if (_cwbde != null)
    {
      java.util.Iterator extfor$iter = _cwbde.getConcretePredecessors().iterator();
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
      }
    }
  }
  return predecessorHashMap;
}haha 
{
  java.util.List<java.util.HashMap<java.lang.String, java.lang.Object>> successorHashMap = new java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>>();
  if (_cwbde != null)
  {
    _cwbde = this.getConcreteWorkBreakdownElement(_cwbde.getId());
    if (_cwbde != null)
    {
      java.util.Iterator extfor$iter = _cwbde.getConcreteSuccessors().iterator();
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
      }
    }
  }
  return successorHashMap;
}haha 
{
  return this.projectDao.getProject(_id);
}haha 
matches15
/Users/remywang/metalift/txl/qbs/allbench//LoginService.java
{
  return this.wilosUserDao;
}haha 
{
  this.wilosUserDao = _wilosUserDao;
}haha 
{
  wilos.model.misc.wilosuser.WilosUser wilosUsers = this.wilosUserDao.getUserByLogin(_login);
  if (wilosUsers.getPassword().equals(_password))
  {
    return wilosUsers;
  }
  return null;
}haha 
{
  if (wilosuser instanceof wilos.model.misc.wilosuser.Participant)
    return true;
  else
    return false;
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//WilosUserBean.java
{
  return participantService;
}haha 
{
  this.participantService = participantService;
}haha 
{
  return newpassword;
}haha 
{
  this.newpassword = newpassword;
}haha 
{
  return passwordConfirmation;
}haha 
{
  this.passwordConfirmation = passwordConfirmation;
}haha 
{
  return currentPassword;
}haha 
{
  this.currentPassword = currentPassword;
}haha 
{
  this.isSetUserFromSession = isSetUserFromSession;
}haha 
{
  wilos.model.misc.wilosuser.WilosUser user = this.getUserFromSession();
  if (user != null)
  {
    this.user = user;
    this.user.setPassword("");
    this.isSetUserFromSession = "ok";
  }
  else
  {
    this.isSetUserFromSession = "null";
  }
  return this.isSetUserFromSession;
}haha 
{
  java.lang.String userId = (java.lang.String) wilos.presentation.web.utils.WebSessionService.getAttribute(wilos.presentation.web.utils.WebSessionService.WILOS_USER_ID);
  this.user = this.wilosUserService.getSimpleUser(userId);
  this.currentPassword = this.user.getPassword();
  return user;
}haha 
{
  boolean error = false;
  this.user.setRole_id(this.selectRole);
  try
  {
    this.emailValidation(null, null, this.user.getEmailAddress());
  }
  catch (javax.faces.validator.ValidatorException ve)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.projectdirectorcreate.err.emailNotValid"));
  }
  if (error == false)
  {
    if (this.loginService.loginExist(this.user.getLogin().trim(), this.userold.getLogin()))
    {
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.projectdirectorcreate.err.loginalreadyexist"));
    }
    else
    {
      if (this.user.getRole_id().equalsIgnoreCase("0") && this.participantService.getParticipant(user.getId()) == null)
      {
        wilos.model.misc.wilosuser.Participant p = new wilos.model.misc.wilosuser.Participant();
        p.setEmailAddress(this.user.getEmailAddress());
        p.setFirstname(this.user.getFirstname());
        p.setName(this.user.getName());
        p.setLogin(this.user.getLogin());
        p.setKeyPassword(this.user.getKeyPassword());
        p.setNewPassword(this.user.getNewPassword());
        p.setPassword(this.user.getPassword());
        p.setRole_id(this.user.getRole_id());
        this.wilosUserService.deleteWilosuser(this.user.getId());
        this.participantService.saveParticipantWithoutEncryption(p);
      }
      else
        if (!this.user.getRole_id().equalsIgnoreCase("0") && this.participantService.getParticipant(user.getId()) != null)
        {
          wilos.model.misc.wilosuser.WilosUser p = new wilos.model.misc.wilosuser.WilosUser();
          p.setEmailAddress(this.user.getEmailAddress());
          p.setFirstname(this.user.getFirstname());
          p.setName(this.user.getName());
          p.setLogin(this.user.getLogin());
          p.setKeyPassword(this.user.getKeyPassword());
          p.setNewPassword(this.user.getNewPassword());
          p.setPassword(this.user.getPassword());
          p.setRole_id(this.user.getRole_id());
          this.participantService.deleteParticipant(user.getId());
          this.wilosUserService.saveWilosUser(p);
        }
        else
        {
          wilos.model.misc.wilosuser.WilosUser p = new wilos.model.misc.wilosuser.WilosUser();
          p.setId(this.user.getId());
          p.setEmailAddress(this.user.getEmailAddress());
          p.setFirstname(this.user.getFirstname());
          p.setName(this.user.getName());
          p.setLogin(this.user.getLogin());
          p.setKeyPassword(this.user.getKeyPassword());
          p.setNewPassword(this.user.getNewPassword());
          p.setPassword(this.user.getPassword());
          p.setRole_id(this.user.getRole_id());
          this.wilosUserService.saveWilosUser(p);
        }
      wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.projectdirectorcreate.success"));
      this.userList = null;
      this.user = new wilos.model.misc.wilosuser.WilosUser();
      if (this.selectItemFilter.equals("99"))
      {
        this.getUserList();
      }
      else
      {
        this.getUserByRole(this.selectItemFilter);
      }
    }
  }
}haha 
{
  boolean error = false;
  this.user.setPassword(wilos.utils.Security.encode(this.user.getPassword()));
  if (this.user.getPassword().equalsIgnoreCase(this.currentPassword))
  {
    if (this.newpassword != null && this.passwordConfirmation != null)
    {
      error = this.updatePasswordAction();
      if (error == false && this.newpassword.trim().length() != 0)
      {
        this.user.setPassword(wilos.utils.Security.encode(this.newpassword));
      }
    }
    try
    {
      this.emailValidation(null, null, this.user.getEmailAddress());
    }
    catch (javax.faces.validator.ValidatorException ve)
    {
      error = true;
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.badpassword"));
    }
    if (error == false)
    {
      this.wilosUserService.saveWilosUser(this.user);
      wilos.presentation.web.wilosuser.LoginBean lb = (wilos.presentation.web.wilosuser.LoginBean) wilos.presentation.web.utils.WebCommonService.getBean("LoginBean");
      lb.setUser(this.user);
      wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.projectdirectorcreate.success"));
    }
  }
  else
  {
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.badpassword"));
  }
}haha 
{
  this.userList = new java.util.ArrayList<wilos.model.misc.wilosuser.WilosUser>();
  this.userList.addAll(this.affectedtoService.affected(this.roleService.getRoleUser(this.wilosUserService.getUserByRole(role_id))));
  return this.userList;
}haha 
{
  java.lang.String choix = (java.lang.String) evt.getNewValue();
  if (choix.equals("99"))
  {
    this.userList.clear();
    this.setUserList(getUserList());
  }
  else
  {
    this.setUserList(getUserByRole(choix));
  }
}haha 
{
  if (this.userList == null || this.userList.size() == 0)
  {
    buildUserList();
  }
  return this.userList;
}haha 
{
  this.userList = new java.util.ArrayList<wilos.model.misc.wilosuser.WilosUser>();
  this.userList.addAll(this.affectedtoService.affected(this.roleService.getRoleUser(this.wilosUserService.getUser())));
  if (this.userList == null)
  {
    this.wilosUserView = "participantView_null";
  }
  else
  {
    this.wilosUserView = "participantView_not_null";
  }
}haha 
{
  this.user = this.wilosUserService.getSimpleUser(id);
  return this.userList;
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  java.lang.String idUser = (java.lang.String) map.get("idUser");
  this.user = wilosUserService.getSimpleUser(idUser);
  this.userold = this.user;
}haha 
{
  java.lang.String enteredEmail = (java.lang.String) _value;
  java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
  java.util.regex.Matcher m = p.matcher(enteredEmail);
  boolean matchFound = m.matches();
  if (!matchFound)
  {
    javax.faces.application.FacesMessage message = new javax.faces.application.FacesMessage();
    message.setSummary(wilos.resources.LocaleBean.getText("component.forminscription.err.invalidemail"));
    message.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
    throw new javax.faces.validator.ValidatorException(message);
  }
}haha 
{
  javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
  java.util.Map map = context.getExternalContext().getRequestParameterMap();
  this.setTestDelete(((java.lang.String) map.get("idUser")));
  this.visiblePopup = true;
}haha 
{
  boolean suppression = this.wilosUserService.deleteWilosuser(this.testDelete);
  if (suppression)
  {
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.participantList.deleteparti.success"));
  }
  else
  {
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.participantList.deleteparti.failed"));
  }
  this.visiblePopup = false;
}haha 
{
  this.visiblePopup = false;
}haha 
{
  return testDelete;
}haha 
{
  this.testDelete = deleteUser;
}haha 
{
  this.selectRole = (java.lang.String) evt.getNewValue();
}haha 
{
  this.roleItem = new java.util.ArrayList<javax.faces.model.SelectItem>();
  java.util.List<wilos.model.misc.wilosuser.Role> roles = this.roleService.getRoleDao().getRole();
  java.util.Iterator extfor$iter = roles.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.wilosuser.Role r = (wilos.model.misc.wilosuser.Role) extfor$iter.next();
    if (!r.getRole_id().equalsIgnoreCase(userold.getRole_id()))
      this.roleItem.add(new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
    else
      this.roleItem.add(0, new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
  }
  return this.roleItem;
}haha 
{
  this.roleListFilter = new java.util.ArrayList<javax.faces.model.SelectItem>();
  java.util.List<wilos.model.misc.wilosuser.Role> roles = this.roleService.getRoleDao().getRole();
  java.util.Iterator extfor$iter = roles.iterator();
  while (extfor$iter.hasNext())
  {
    wilos.model.misc.wilosuser.Role r = (wilos.model.misc.wilosuser.Role) extfor$iter.next();
    this.roleListFilter.add(new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
  }
  wilos.model.misc.wilosuser.Role r = new wilos.model.misc.wilosuser.Role();
  r.setName(wilos.resources.LocaleBean.getText("component.participantlist.all"));
  r.setRole_id("99");
  this.roleListFilter.add(0, new javax.faces.model.SelectItem(r.getRole_id(), r.getName()));
  return this.roleListFilter;
}haha 
{
  this.userold = this.user;
  this.user = this.wilosUserService.getUserByLogin(this.user.getLogin());
  if (this.userold.getKeyPassword().equals(this.user.getKeyPassword()))
  {
    this.user.setNewPassword(this.userold.getNewPassword());
    this.user.setKeyPassword("");
    this.wilosUserService.saveWilosUser(this.user);
    wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.passwordsuccess"));
  }
  else
  {
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.formforgetpassword.code.invalid"));
  }
}haha 
{
  this.user = this.wilosUserService.getUserByEmail(this.user.getEmailAddress());
  if (user != null)
  {
    this.user.setKeyPassword(this.user.generateNewPassword());
    java.lang.String message = wilos.resources.LocaleBean.getText("component.formforgetpassword.mail.header.name") + " " + this.user.getFirstname() + " " + this.user.getName() + ",</br></br>";
    message += wilos.resources.LocaleBean.getText("component.formforgetpassword.mail.body") + " : <b>" + this.user.getKeyPassword() + " </b> </br>" + wilos.resources.LocaleBean.getText("component.formforgetpassword.mail.end");
    ;
    java.lang.String[] recipient = new java.lang.String[1];
    recipient[0] = this.user.getEmailAddress();
    java.lang.String subject = wilos.resources.LocaleBean.getText("component.formForgottenPassword.title");
    try
    {
      wilos.presentation.web.utils.SendMail.postMail(recipient, subject, message, "wilos.be@gmail.com");
      wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
      wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.formforgetpassword.mail.sended"));
      this.wilosUserService.saveWilosUser(this.user);
    }
    catch (javax.mail.MessagingException e)
    {
      wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
      wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.formforgetpassword.mail.not.sended"));
    }
  }
  else
  {
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.invalidemail"));
  }
}haha 
{
  wilos.presentation.web.utils.WebCommonService.changeContentPage("forgottenPassword");
  wilos.presentation.web.utils.WebSessionService.setAttribute(wilos.presentation.web.utils.WebSessionService.USER_GUIDE, "guide.forgotten.password");
}haha 
{
  this.roleListFilter = roleListFilter;
}haha 
{
  return user;
}haha 
{
  this.user = user;
}haha 
{
  return wilosUserService;
}haha 
{
  this.wilosUserService = wilosUserService;
}haha 
{
  this.userList = userList;
}haha 
{
  return roleService;
}haha 
{
  this.roleService = roleService;
}haha 
{
  return loginService;
}haha 
{
  this.loginService = loginService;
}haha 
{
  return userold;
}haha 
{
  this.userold = userold;
}haha 
{
  return visiblePopup;
}haha 
{
  this.visiblePopup = visiblePopup;
}haha 
{
  this.wilosUserView = wilosUserView;
}haha 
{
  return selectRole;
}haha 
{
  this.selectRole = selectRole;
}haha 
{
  this.roleItem = roleItem;
}haha 
{
  return affectedtoService;
}haha 
{
  this.affectedtoService = affectedtoService;
}haha 
{
  return selectItemFilter;
}haha 
{
  this.selectItemFilter = selectItemFilter;
}haha 
{
  wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
  wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.formforgetpassword.cancel"));
  wilos.presentation.web.utils.WebSessionService.setAttribute(wilos.presentation.web.utils.WebSessionService.USER_GUIDE, "guide.accueil");
}haha 
{
  wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
  wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.cancel"));
}haha 
{
  wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
  wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.formforgetpassword.cancel.key"));
}haha 
{
  this.user = new wilos.model.misc.wilosuser.WilosUser();
  return cleanUser;
}haha 
{
  this.cleanUser = cleanUser;
}haha 
{
  boolean error = false;
  if (this.user.getPassword().trim().length() == 0)
  {
    error = true;
    wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordRequired"));
  }
  else
    if (this.newpassword == null)
    {
    }
    else
      if (this.newpassword.trim().length() == 0 && selectedPanel.equalsIgnoreCase("pass"))
      {
        error = true;
        wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.newpasswordRequired"));
      }
      else
        if (this.passwordConfirmation.trim().length() == 0 && selectedPanel.equalsIgnoreCase("pass"))
        {
          error = true;
          wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.confirmpasswordRequired"));
        }
  if (!error && selectedPanel.equalsIgnoreCase("pass"))
  {
    wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.passwordsuccess"));
  }
  return error;
}haha 
{
  if (selectedPanel.equalsIgnoreCase("pass"))
  {
    selectedPanel = "default";
  }
  else
  {
    selectedPanel = "pass";
  }
  return null;
}haha 
{
  return selectedPanel;
}haha 
{
  javax.faces.component.UIComponent passcomponent = _toValidate.findComponent("equal1");
  java.lang.String passValue = (java.lang.String) passcomponent.getAttributes().get("value");
  if (!_value.equals(passValue))
  {
    javax.faces.application.FacesMessage message = new javax.faces.application.FacesMessage();
    message.setSummary(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordnotequals"));
    message.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
    throw new javax.faces.validator.ValidatorException(message);
  }
}haha 
{
  boolean error = true;
  try
  {
    wilos.model.misc.wilosuser.WilosUser userExist = this.wilosUserService.getUserByLogin(this.user.getLogin().trim());
    if (userExist.getLogin() != null)
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.loginalreadyexist"));
  }
  catch (java.lang.Exception e)
  {
    error = false;
  }
  if (!error)
  {
    if (this.user.getName().length() == 0)
    {
      wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.lastnameRequired"));
    }
    else
      if (this.user.getFirstname().length() == 0)
      {
        wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.firstnameRequired"));
      }
      else
        if (this.user.getEmailAddress().length() == 0)
        {
          wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.emailRequired"));
        }
        else
          if (this.user.getLogin().length() == 0)
          {
            wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.loginRequired"));
          }
          else
            if (this.user.getPassword().length() == 0)
            {
              wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordRequired"));
            }
            else
              if (this.user.getPassword().length() < 6)
              {
                java.lang.System.out.println((this.user.getPassword().length() + "***"));
                wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.passwordRequiredSixChar"));
              }
              else
                if (this.passwordConfirmation.length() == 0)
                {
                  wilos.presentation.web.utils.WebCommonService.addErrorMessage(wilos.resources.LocaleBean.getText("component.forminscription.err.confirmpasswordRequired"));
                }
                else
                {
                  this.user.setPassword(wilos.utils.Security.encode(this.user.getPassword()));
                  wilos.model.misc.wilosuser.Participant p = new wilos.model.misc.wilosuser.Participant();
                  p.setId(this.user.getId());
                  p.setLogin(this.user.getLogin());
                  this.participantService.saveParticipantWithoutEncryption(p);
                  wilos.model.misc.wilosuser.Participant Pa = this.participantService.getParticipantDao().getParticipant(p.getLogin());
                  this.user.setId(Pa.getId());
                  this.wilosUserService.saveWilosUser(this.user);
                  wilos.presentation.web.utils.WebCommonService.addInfoMessage(wilos.resources.LocaleBean.getText("component.forminscription.success"));
                  wilos.presentation.web.utils.WebCommonService.changeContentPage("wilos");
                  wilos.presentation.web.utils.WebSessionService.setAttribute(wilos.presentation.web.utils.WebSessionService.USER_GUIDE, "guide.end.inscription");
                }
  }
  this.user = new wilos.model.misc.wilosuser.WilosUser();
}haha 
{
  this.user = new wilos.model.misc.wilosuser.WilosUser();
  this.cleanBean = "ok";
  return this.cleanBean;
}haha 
{
  this.cleanBean = _cleanBean;
}haha 
{
}haha 
matches66
