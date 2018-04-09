/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
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
matches2
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
matches3
/Users/remywang/metalift/txl/qbs/allbench//WorkProductSExpTableBean.java
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
matches3
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
matches3
/Users/remywang/metalift/txl/qbs/allbench//NotificationServiceImpl.java
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
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
matches1
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
matches5
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
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
matches2
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkProductDescriptorService.java
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
matches3
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
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
matches2
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
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
matches1
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//ParticipantService.java
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
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
matches3
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
matches4
/Users/remywang/metalift/txl/qbs/allbench//WilosUserBean.java
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
matches2
