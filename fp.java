/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
while (bde.getSuperActivities().size() != 0)
{
  bde = bde.getSuperActivities().iterator().next();
}haha 
while (attachments.hasMoreElements())
{
  java.lang.String currentAttachment = (java.lang.String) attachments.nextElement();
  if (currentAttachment.matches((".*" + file)))
  {
    attachment = new java.lang.String(currentAttachment);
    attachment = attachment.replace("/", java.io.File.separator);
  }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.activity.Activity act = (wilos.model.spem2.activity.Activity) extfor$iter.next();
  tmp.add(act);
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretetask.ConcreteTaskDescriptor concreteTaskDescriptor = (wilos.model.misc.concretetask.ConcreteTaskDescriptor) extfor$iter.next();
  concreteTaskDescriptors.add(concreteTaskDescriptor);
}haha 
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
}haha 
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity sca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  sca.getConcreteBreakdownElements().remove(_concreteRoledescriptor);
  this.concreteActivityService.saveConcreteActivity(sca);
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration bde = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
  tmp.add(bde);
}haha 
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
}haha 
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
}haha 
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concreteiteration.ConcreteIteration tmp = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter$1.next();
  if (lastConcreteIteration == null || tmp.getInstanciationOrder() > lastConcreteIteration.getInstanciationOrder())
  {
    lastConcreteIteration = tmp;
  }
}haha 
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
}haha 
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
}haha 
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
}haha 
while (extfor$iter.hasNext() && !break_0)
{
  java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (!break_0)
    if (((java.lang.String) hashMap.get("id")).equals(_id))
    {
      nb = ((java.lang.Integer) hashMap.get("nbOccurences")).intValue();
      break_0 = true;
    }
}haha 
matches8
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase bde = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
  tmp.add(bde);
}haha 
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
}haha 
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
}haha 
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
}haha 
while (extfor$iter$1.hasNext())
{
  wilos.model.misc.concretephase.ConcretePhase tmp = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter$1.next();
  if (lastConcretePhase == null || tmp.getInstanciationOrder() > lastConcretePhase.getInstanciationOrder())
  {
    lastConcretePhase = tmp;
  }
}haha 
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
}haha 
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
}haha 
while (extfor$iter.hasNext() && !break_0)
{
  java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (!break_0)
    if (((java.lang.String) hashMap.get("id")).equals(_id))
    {
      nb = ((java.lang.Integer) hashMap.get("nbOccurences")).intValue();
      break_0 = true;
    }
}haha 
matches8
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
  java.util.HashMap<java.lang.String, java.lang.Object> currentElement = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (currentElement.get("id").equals(workProductId) && currentElement.get("nodeType").equals("node"))
  {
    currentElement.put("expansionImage", CONTRACT_TABLE_ARROW);
    currentElement.put("isDisabled", false);
    parentList.remove(currentElement);
  }
}haha 
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter.next();
  if (bde instanceof wilos.model.spem2.role.RoleDescriptor)
  {
    wilos.model.spem2.role.RoleDescriptor rd = (wilos.model.spem2.role.RoleDescriptor) bde;
    rolesList.add(new javax.faces.model.SelectItem(rd.getId(), rd.getPresentationName()));
  }
}haha 
while (extfor$iter$1.hasNext())
{
  wilos.model.spem2.breakdownelement.BreakdownElement bde = (wilos.model.spem2.breakdownelement.BreakdownElement) extfor$iter$1.next();
  if (bde instanceof wilos.model.spem2.role.RoleDescriptor)
  {
    rolesList.add(new javax.faces.model.SelectItem(bde.getId(), bde.getPresentationName()));
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
}haha 
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
}haha 
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
}haha 
matches11
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
while (iter.hasNext())
{
  ((org.itracker.model.IssueActivity) iter.next()).setNotificationSent(notificationSent);
}haha 
while (iterator.hasNext())
{
  org.itracker.model.User projectOwner = (org.itracker.model.User) iterator.next();
  if (projectOwner != null && (!activeOnly || projectOwner.getStatus() == org.itracker.services.util.UserUtilities.STATUS_ACTIVE))
  {
    issueNotifications.add(new org.itracker.model.Notification(projectOwner, issue, org.itracker.model.Notification.Role.PO));
  }
}haha 
matches13
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
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
}haha 
while (extfor$iter.hasNext())
{
  java.util.HashMap<java.lang.String, java.lang.Object> processDescription = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (((java.lang.String) processDescription.get("id")).equals(processId))
  {
    processDescription.put("isEditable", new java.lang.Boolean(true));
  }
}haha 
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
  processesList.add(new javax.faces.model.SelectItem(process.getId(), process.getPresentationName()));
}haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbe = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  if (cbe instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
  {
    if (!this.createConcreteTaskDescriptor(_taskName, _project, td, ((wilos.model.misc.concreteactivity.ConcreteActivity) cbe)))
      return false;
  }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement element = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  tmp.add(element);
}haha 
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
}haha 
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
}haha 
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbe = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  if (cbe instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
  {
    if (!this.createConcreteRoleDescriptor(_roleName, _project, rd, ((wilos.model.misc.concreteactivity.ConcreteActivity) cbe)))
      return false;
  }
}haha 
matches6
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
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
}haha 
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
}haha 
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
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement tmp = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  if (tmp instanceof wilos.model.misc.concreteactivity.ConcreteActivity)
  {
    if (((wilos.model.misc.concreteactivity.ConcreteActivity) tmp).getActivity().getId().equals(_activity.getId()))
    {
      nbExistingConcreteActivitiesChildren++;
      concreteActivitiesSisters.add(((wilos.model.misc.concreteactivity.ConcreteActivity) tmp));
    }
  }
}haha 
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
}haha 
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
}haha 
while (extfor$iter.hasNext() && !break_0)
{
  java.util.HashMap<java.lang.String, java.lang.Object> hashMap = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (!break_0)
    if (((java.lang.String) hashMap.get("id")).equals(_id))
    {
      nb = ((java.lang.Integer) hashMap.get("nbOccurences")).intValue();
      break_0 = true;
    }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity bde = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  tmp.add(bde);
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
matches11
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
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
}haha 
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
while (extfor$iter.hasNext())
{
  java.util.HashMap<java.lang.String, java.lang.Object> ligne = (java.util.HashMap<java.lang.String, java.lang.Object>) extfor$iter.next();
  if (!(java.lang.Boolean) ligne.get("hasOtherManager"))
  {
    java.lang.Boolean testAffectation = (java.lang.Boolean) ligne.get("affected");
    java.lang.String project_id = (java.lang.String) ligne.get("project_id");
    affectedManagedProjects.put(project_id, testAffectation);
  }
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
  this.concreteRoleDescriptorsMap.put(concreteRoleDescriptor.getConcreteName(), true);
}haha 
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
}haha 
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
}haha 
while (it.hasNext())
{
  java.util.HashMap<java.lang.String, java.lang.Object> value2 = it.next();
  java.lang.String projectId2 = (java.lang.String) value2.get("project_id");
  if (retour.contains(projectId2))
  {
    value2.put("affected", newAffectation);
    this.saveProjectManagerAffectation();
  }
}haha 
matches9
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
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteactivity.ConcreteActivity sca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
  sca.getConcreteBreakdownElements().remove(_concreteWorkProductDescriptor);
  this.concreteActivityService.saveConcreteActivity(sca);
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
matches6
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
while (permssionsIt.hasNext())
{
  org.itracker.model.Permission permission2 = (org.itracker.model.Permission) permssionsIt.next();
  if (org.itracker.model.Permission.PERMISSION_PROPERTIES_COMPARATOR.compare(permission, permission2) == 0)
  {
    return permission2;
  }
}haha 
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
}haha 
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
}haha 
while (delIterator.hasNext())
{
  org.itracker.model.Permission permission = (org.itracker.model.Permission) delIterator.next();
  permissionDAO.delete(permission);
}haha 
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
while (i < permissionTypes.length)
{
  perm[i] = permissionTypes[i];
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
matches9
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
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
}haha 
while (idIt.hasNext())
{
  java.lang.Integer id = (java.lang.Integer) idIt.next();
  org.itracker.model.Component c = getComponentDAO().findById(id);
  components.add(c);
}haha 
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
while (iter.hasNext())
{
  ((org.itracker.model.IssueActivity) iter.next()).setNotificationSent(notificationSent);
}haha 
while (iterator.hasNext())
{
  activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
  i++;
}haha 
matches9
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityService.java
while (extfor$iter.hasNext())
{
  wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement cbde = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
  tmp.add(cbde);
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
}haha 
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
matches3
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkBreakdownElementService.java
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
}haha 
while (extfor$iter.hasNext())
{
  wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement cwbde = (wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement) extfor$iter.next();
  tmp.add(cwbde);
}haha 
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
matches5
/Users/remywang/metalift/txl/qbs/allbench//ParticipantService.java
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
}haha 
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
}haha 
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
}haha 
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
}haha 
matches4
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
