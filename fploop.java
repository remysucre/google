/Users/remywang/metalift/txl/qbs/allbench//NotificationServiceImpl.java
while (it.hasNext())
{
  currentUser = it.next().getUser();
  if (null != currentUser && null != currentUser.getEmailAddress() && null != currentUser.getEmail() && !recList.contains(currentUser.getEmailAddress()) && currentUser.getEmail().indexOf('@') >= 0)
  {
    recList.add(currentUser.getEmailAddress());
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
      slothmark: ;
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
while (i < components.size())
{
  componentString += (i != 0 ? ", " : "") + components.get(i).getName();
  i++;
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
      slothmark: ;
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
while (i < versions.size())
{
  versionString += (i != 0 ? ", " : "") + versions.get(i).getNumber();
  i++;
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
      slothmark: ;
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
matches3
/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
while (extfor$iter.hasNext())
{
  java.lang.Object obj = (java.lang.Object) extfor$iter.next();
  wilos.model.misc.concreteactivity.ConcreteActivity ca = (wilos.model.misc.concreteactivity.ConcreteActivity) obj;
  concreteActivities.add(ca);
}{
  java.util.List<wilos.model.misc.concreteactivity.ConcreteActivity> concreteActivities = new java.util.ArrayList<wilos.model.misc.concreteactivity.ConcreteActivity>();
  java.util.Iterator extfor$iter = this.getHibernateTemplate().loadAll(null).iterator();
  slothmark: ;
  return concreteActivities;
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
}{
  _concreteWorkProductDescriptor = this.getConcreteWorkProductDescriptor(_concreteWorkProductDescriptor.getId());
  if (_concreteWorkProductDescriptor != null && _concreteWorkProductDescriptor.getParticipant() == null)
  {
    java.util.Set<wilos.model.misc.concretetask.ConcreteTaskDescriptor> lctd = _concreteWorkProductDescriptor.getProducerConcreteTasks();
    boolean tache_commence = false;
    java.util.Iterator extfor$iter = lctd.iterator();
    slothmark: ;
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
matches1
/Users/remywang/metalift/txl/qbs/allbench//UserServiceImpl.java
while (i < editUsers.size())
{
  users.add(editUsers.get(i));
  i++;
}{
  java.util.HashSet<org.itracker.model.User> users = new java.util.HashSet<org.itracker.model.User>();
  java.util.List<org.itracker.model.User> editUsers = getUsersWithProjectPermission(projectId, org.itracker.services.util.UserUtilities.PERMISSION_EDIT, true);
  int i = 0;
  slothmark: ;
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
while (i < otherUsers.size())
{
  users.add(otherUsers.get(i));
  i++;
}{
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
  slothmark: ;
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
while (iter.hasNext())
{
  userList.add(((org.itracker.model.User) iter.next()));
  j++;
}{
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
  slothmark: ;
  return userList;
}haha 
matches3
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
while (idIt.hasNext())
{
  java.lang.Integer id = (java.lang.Integer) idIt.next();
  org.itracker.model.Component c = getComponentDAO().findById(id);
  components.add(c);
}{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.List<org.itracker.model.Component> components = new java.util.ArrayList<org.itracker.model.Component>(componentIds.size());
  org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
  java.util.Iterator<java.lang.Integer> idIt = componentIds.iterator();
  slothmark: ;
  setIssueComponents(issue, components, user, true);
  return true;
}haha 
while (versionsIdIt.hasNext())
{
  java.lang.Integer id = versionsIdIt.next();
  versions.add(getVersionDAO().findByPrimaryKey(id));
}{
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  org.itracker.model.User user = userDAO.findByPrimaryKey(userId);
  java.util.ArrayList<org.itracker.model.Version> versions = new java.util.ArrayList<org.itracker.model.Version>(versionIds.size());
  java.util.Iterator<java.lang.Integer> versionsIdIt = versionIds.iterator();
  slothmark: ;
  return setIssueVersions(issue, versions, user, true);
}haha 
matches2
/Users/remywang/metalift/txl/qbs/allbench//ListProjectsAction.java
while (projectIt.hasNext())
{
  org.itracker.model.Project project = projectIt.next();
  ptos.add(createProjectPTO(project, projectService, permissions));
}{
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
  slothmark: ;
  return ptos;
}haha 
while (projectIt.hasNext())
{
  org.itracker.model.Project project = projectIt.next();
  ptos.add(createProjectPTO(project, projectService, permissions));
}{
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
             }
  projects = projects_tmp;
  java.util.Collections.sort(projects, new org.itracker.model.Project.ProjectComparator());
  java.util.ArrayList<org.itracker.web.ptos.ProjectPTO> ptos = new java.util.ArrayList<org.itracker.web.ptos.ProjectPTO>(projects_tmp.size());
  projectIt = projects.iterator();
  slothmark: ;
  return ptos;
}haha 
matches2
