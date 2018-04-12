/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleDescriptorService.java
labeled_1: {
             wilos.model.misc.concreterole.ConcreteRoleDescriptor crd = this.getConcreteRoleDescriptor(_crdid);
             java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listTmp = this.concreteActivityService.getAllConcreteActivities();
             java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listToReturn = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
             java.util.Iterator extfor$iter = listTmp.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
               if (ca.getConcreteBreakdownElements().contains(crd))
               {
                 listToReturn.add(ca);
               }
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//GuidanceService.java
labeled_1: {
             java.util.Set<wilos.model.spem2.task.TaskDefinition> tmp = new java.util.HashSet<wilos.model.spem2.task.TaskDefinition>();
             this.guidanceDao.getSessionFactory().getCurrentSession().saveOrUpdate(_guidance);
             java.util.Iterator extfor$iter = _guidance.getTaskDefinitions().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.spem2.task.TaskDefinition td = (wilos.model.spem2.task.TaskDefinition) extfor$iter.next();
               tmp.add(td);
             }
           }haha 
labeled_2: {
             java.util.Set<wilos.model.spem2.role.RoleDefinition> tmp = new java.util.HashSet<wilos.model.spem2.role.RoleDefinition>();
             this.guidanceDao.getSessionFactory().getCurrentSession().saveOrUpdate(_guidance);
             java.util.Iterator extfor$iter = _guidance.getRoleDefinitions().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.spem2.role.RoleDefinition td = (wilos.model.spem2.role.RoleDefinition) extfor$iter.next();
               tmp.add(td);
             }
           }haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//MoveIssueFormAction.java
labeled_1: {
             java.util.Map<java.lang.Integer, java.util.Set<org.itracker.model.PermissionType>> userPermissions = getUserPermissions(request.getSession());
             java.util.List<org.itracker.model.Project> availableProjects = new java.util.ArrayList<org.itracker.model.Project>();
             int i = 0;
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
             }
             java.util.Collections.sort(availableProjects, new org.itracker.model.Project.ProjectComparator());
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//IterationService.java
labeled_1: {
             java.util.Set<wilos.model.misc.concreteiteration.ConcreteIteration> tmp = new java.util.HashSet<wilos.model.misc.concreteiteration.ConcreteIteration>();
             this.iterationDao.getSessionFactory().getCurrentSession().saveOrUpdate(_iteration);
             this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
             java.util.Iterator extfor$iter = _iteration.getConcreteIterations().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concreteiteration.ConcreteIteration cit = (wilos.model.misc.concreteiteration.ConcreteIteration) extfor$iter.next();
               if (cit.getProject().getId().equals(_project.getId()))
                 tmp.add(cit);
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//RoleDao.java
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
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//PhaseService.java
labeled_1: {
             java.util.Set<wilos.model.misc.concretephase.ConcretePhase> tmp = new java.util.HashSet<wilos.model.misc.concretephase.ConcretePhase>();
             this.phaseDao.getSessionFactory().getCurrentSession().saveOrUpdate(_phase);
             this.projectDao.getSessionFactory().getCurrentSession().saveOrUpdate(_project);
             java.util.Iterator extfor$iter = _phase.getConcretePhases().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concretephase.ConcretePhase cph = (wilos.model.misc.concretephase.ConcretePhase) extfor$iter.next();
               if (cph.getProject().getId().equals(_project.getId()))
                 tmp.add(cph);
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//EditProjectFormActionUtil.java
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
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//WorkProductSExpTableBean.java
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
           }haha 
labeled_2: {
             if (numberOfFinishedActivity == this.concreteActivityService.getConcreteActivitiesFromProject(project).size())
             {
               this.allConcreteActivitiesAreFinishedWorkProduct = true;
             }
             else
             {
               this.allConcreteActivitiesAreFinishedWorkProduct = false;
             }
           }haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ConcreteRoleAffectationService.java
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
               while (extfor$iter.hasNext())
               {
                 wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement concreteBreakdownElement = (wilos.model.misc.concretebreakdownelement.ConcreteBreakdownElement) extfor$iter.next();
                 if (concreteBreakdownElement.getId().equals(_activityId))
                 {
                   concreteRDList.add(concreteRD);
                 }
               }
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//RoleService.java
labeled_1: {
             java.util.List<wilos.model.misc.wilosuser.WilosUser> listUser = new java.util.ArrayList<wilos.model.misc.wilosuser.WilosUser>();
             java.util.List<wilos.model.misc.wilosuser.Role> role = this.roleDao.getRole();
             int i = 0;
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
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ProcessManagerBean.java
labeled_1: {
             if (this.getProcessManagerList().size() == 0)
             {
               this.processManagerView = "processManagerView_null";
             }
             else
             {
               this.processManagerView = "processManagerView_not_null";
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//NotificationServiceImpl.java
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
           }haha 
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
           }haha 
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
           }haha 
labeled_4: {
             boolean result = false;
             if (issue != null && userId != null)
             {
               java.util.List<org.itracker.model.Notification> notifications = getIssueNotifications(issue, false, false);
               int i = 0;
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
               }
             }
             result = false;
           }haha 
matches4
/Users/remywang/metalift/txl/qbs/allbench//ProcessBean.java
labeled_1: {
             boolean r;
             r = false;
             java.util.Iterator extfor$iter = this.processService.getAllProcesses().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.spem2.process.Process process = (wilos.model.spem2.process.Process) extfor$iter.next();
               if (process.getPresentationName().equals(_presentationName) && !_processId.equals(process.getId()))
                 r = true;
             }
             r = false;
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ActivityService.java
labeled_1: {
             java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
             this.activityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_act);
             java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
               if (cact.getProject().getId().equals(_project.getId()))
                 tmp.add(cact);
             }
           }haha 
labeled_2: {
             java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
             java.util.Iterator extfor$iter = _act.getConcreteActivities().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
               if (cact.getProject().getId().equals(_project.getId()))
                 tmp.add(cact);
             }
           }haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ProjectService.java
labeled_1: {
             boolean found = false;
             java.lang.String projectName;
             java.util.List<wilos.model.misc.project.Project> projects = this.projectDao.getAllProjects();
             java.util.Iterator extfor$iter = projects.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
               projectName = project.getConcreteName().toUpperCase();
               if (projectName.equals(_projectName.toUpperCase()))
               {
                 return true;
               }
             }
           }haha 
labeled_2: {
             java.util.List<wilos.model.misc.project.Project> projectList = new java.util.ArrayList<wilos.model.misc.project.Project>();
             projectList = this.projectDao.getAllProjects();
             projects = projectList.toArray(new wilos.model.misc.project.Project[projectList.size()]);
             sortProject();
           }haha 
labeled_3: {
             java.util.Set<wilos.model.misc.project.Project> unfinishedP = new java.util.HashSet<wilos.model.misc.project.Project>();
             java.util.List<wilos.model.misc.project.Project> projects = this.projectDao.getAllProjects();
             java.util.Iterator extfor$iter = projects.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
               if (!project.getIsFinished())
               {
                 unfinishedP.add(project);
               }
             }
           }haha 
labeled_4: {
             java.util.Iterator extfor$iter = _cact.getConcreteBreakdownElements().iterator();
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
             }
           }haha 
labeled_5: {
             java.util.Set<wilos.model.misc.project.Project> projectList = new java.util.HashSet<wilos.model.misc.project.Project>();
             java.util.List<wilos.model.misc.project.Project> tmpList = new java.util.ArrayList<wilos.model.misc.project.Project>();
             tmpList = this.projectDao.getAllProjects();
             java.util.Iterator extfor$iter = tmpList.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
               if (project.getProcess() == null)
                 projectList.add(project);
             }
           }haha 
labeled_6: {
             java.util.Set<wilos.model.misc.project.Project> projectList = new java.util.HashSet<wilos.model.misc.project.Project>();
             java.util.List<wilos.model.misc.project.Project> tmpList = new java.util.ArrayList<wilos.model.misc.project.Project>();
             tmpList = this.projectDao.getAllProjects();
             java.util.Iterator extfor$iter = tmpList.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.project.Project project = (wilos.model.misc.project.Project) extfor$iter.next();
               if (project.getProcess() != null)
                 projectList.add(project);
             }
           }haha 
matches6
/Users/remywang/metalift/txl/qbs/allbench//ParticipantBean.java
labeled_1: {
             java.lang.Boolean allowed = true;
             java.lang.String project_id = (java.lang.String) line.get("project_id");
             java.util.List<wilos.model.misc.concretetask.ConcreteTaskDescriptor> concreteTasks = this.projectService.getProcessService().getTaskDescriptorService().getConcreteTaskDescriptorDao().getAllConcreteTaskDescriptorsForProject(project_id);
             java.util.Iterator extfor$iter = concreteTasks.iterator();
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
             }
           }haha 
labeled_2: {
             if (this.getParticipantsList().size() == 0)
             {
               this.participantView = "participantView_null";
             }
             else
             {
               this.participantView = "participantView_not_null";
             }
           }haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
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
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkProductDescriptorService.java
labeled_1: {
             wilos.model.misc.concreteworkproduct.ConcreteWorkProductDescriptor crd = this.getConcreteWorkProductDescriptor(_cwpdid);
             java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listTmp = this.concreteActivityService.getAllConcreteActivities();
             java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> listToReturn = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
             java.util.Iterator extfor$iter = listTmp.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
               if (ca.getConcreteBreakdownElements().contains(crd))
               {
                 listToReturn.add(ca);
               }
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
labeled_1: {
             java.util.Collection<org.itracker.model.User> users = userDAO.findAll();
             int size = users.size();
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
           }haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
labeled_1: {
             java.util.HashSet<java.lang.Integer> componentIds = new java.util.HashSet<java.lang.Integer>();
             org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
             java.util.Collection<org.itracker.model.Component> components = issue.getComponents();
             java.util.Iterator<org.itracker.model.Component> iterator = components.iterator();
             while (iterator.hasNext())
             {
               componentIds.add(((org.itracker.model.Component) iterator.next()).getId());
             }
           }haha 
labeled_2: {
             java.util.HashSet<java.lang.Integer> versionIds = new java.util.HashSet<java.lang.Integer>();
             org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
             java.util.Collection<org.itracker.model.Version> versions = issue.getVersions();
             java.util.Iterator<org.itracker.model.Version> iterator = versions.iterator();
             while (iterator.hasNext())
             {
               versionIds.add(((org.itracker.model.Version) iterator.next()).getId());
             }
           }haha 
labeled_3: {
             int i = 0;
             java.util.Collection<org.itracker.model.IssueActivity> activity = getIssueActivityDAO().findByIssueIdAndNotification(issueId, notificationSent);
             org.itracker.model.IssueActivity[] activityArray = new org.itracker.model.IssueActivity[activity.size()];
             java.util.Iterator<org.itracker.model.IssueActivity> iterator = activity.iterator();
             while (iterator.hasNext())
             {
               activityArray[i] = (org.itracker.model.IssueActivity) iterator.next();
               i++;
             }
           }haha 
labeled_4: {
             java.util.List<org.itracker.model.IssueHistory> history = getIssueHistoryDAO().findByIssueId(issueId);
             if (null != history && history.size() > 0)
             {
               java.util.Collections.sort(history, org.itracker.model.AbstractEntity.ID_COMPARATOR);
               return history.get((history.size() - 1));
             }
           }haha 
labeled_5: {
             java.util.Collection<org.itracker.model.Issue> issues = getIssueDAO().findByProjectAndLowerStatus(projectId, org.itracker.services.util.IssueUtilities.STATUS_RESOLVED);
             int size = issues.size();
           }haha 
labeled_6: {
             java.util.Collection<org.itracker.model.Issue> issues = getIssueDAO().findByProjectAndHigherStatus(projectId, org.itracker.services.util.IssueUtilities.STATUS_RESOLVED);
             int size = issues.size();
           }haha 
labeled_7: {
             java.util.Collection<org.itracker.model.Issue> issues = getIssueDAO().findByProject(projectId);
             int size = issues.size();
           }haha 
matches7
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
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
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityService.java
labeled_1: {
             java.util.Set<wilos.model.misc.concreteactivity.ConcreteActivity> tmp = new java.util.HashSet<wilos.model.misc.concreteactivity.ConcreteActivity>();
             this.concreteActivityDao.getSessionFactory().getCurrentSession().saveOrUpdate(_cact);
             java.util.Iterator extfor$iter = this.getAllConcreteActivities().iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.concreteactivity.ConcreteActivity cact = (wilos.model.misc.concreteactivity.ConcreteActivity) extfor$iter.next();
               if (cact.getProject() != null && cact.getProject().equals(_cact))
               {
                 tmp.add(cact);
               }
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//ParticipantService.java
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
                 while (extfor$iter.hasNext())
                 {
                   wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
                   if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
                     concreteRolesList.add(concreteRoleDescriptor);
                 }
               }
             }
           }haha 
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
                 while (extfor$iter.hasNext())
                 {
                   wilos.model.misc.concreterole.ConcreteRoleDescriptor concreteRoleDescriptor = (wilos.model.misc.concreterole.ConcreteRoleDescriptor) extfor$iter.next();
                   if (projectConcreteRole.getId().equals(concreteRoleDescriptor.getId()))
                     if (!concreteRoleDescriptor.getRoleDescriptor().getPresentationName().equals(""))
                       concreteRolesList.add(concreteRoleDescriptor.getRoleDescriptor().getName());
                 }
               }
             }
           }haha 
labeled_3: {
             java.util.List<wilos.model.misc.project.Project> affectedProjectList = new java.util.ArrayList<wilos.model.misc.project.Project>();
             java.util.HashSet<wilos.model.misc.project.Project> allProjectList = new java.util.HashSet<wilos.model.misc.project.Project>();
             wilos.model.misc.wilosuser.Participant chargedParticipant = new wilos.model.misc.wilosuser.Participant();
             java.lang.String login = participant.getLogin();
             chargedParticipant = this.participantDao.getParticipant(login);
             allProjectList = (java.util.HashSet<wilos.model.misc.project.Project>) this.projectService.getUnfinishedProjects();
             java.util.Iterator extfor$iter = allProjectList.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.project.Project p = (wilos.model.misc.project.Project) extfor$iter.next();
               if (chargedParticipant.getAffectedProjectList().contains(p))
               {
                 affectedProjectList.add(p);
               }
             }
           }haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//ConcreteWorkBreakdownElementService.java
labeled_1: {
             java.util.List<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement> tmp = new java.util.ArrayList<wilos.model.misc.concreteworkbreakdownelement.ConcreteWorkBreakdownElement>();
             java.util.Iterator extfor$iter = this.concreteWorkBreakdownElementDao.getAllConcreteWorkBreakdownElements().iterator();
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
             }
           }haha 
matches1
/Users/remywang/metalift/txl/qbs/allbench//LoginService.java
labeled_1: {
             boolean found = false;
             java.lang.String userLogin;
             java.util.List<wilos.model.misc.wilosuser.WilosUser> wilosUsers = this.wilosUserDao.getAllWilosUsers();
             java.util.Iterator extfor$iter = wilosUsers.iterator();
             while (extfor$iter.hasNext())
             {
               wilos.model.misc.wilosuser.WilosUser user = (wilos.model.misc.wilosuser.WilosUser) extfor$iter.next();
               if (user.getLogin() != null)
               {
                 userLogin = user.getLogin().toUpperCase();
                 if (userLogin.equals(_login.toUpperCase()))
                   found = true;
               }
             }
           }haha 
labeled_2: {
             boolean found = false;
             java.lang.String userLogin;
             java.util.List<wilos.model.misc.wilosuser.WilosUser> wilosUsers = this.wilosUserDao.getAllWilosUsers();
             java.util.Iterator extfor$iter = wilosUsers.iterator();
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
             }
           }haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//WilosUserBean.java
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
           }haha 
matches1
