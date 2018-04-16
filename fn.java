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
