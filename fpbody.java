/Users/remywang/metalift/txl/qbs/allbench//ConcreteActivityDao.java
{
  java.util.List concreteactvities = this.getHibernateTemplate().find("from ConcreteActivity a where a.id=?", _id);
  return concreteactvities.size() > 0;
}haha 
{
  java.util.List concreteactvities = this.getHibernateTemplate().find("from ConcreteActivity a where a.prefix=?", _prefix);
  if (concreteactvities.size() > 0)
    return (wilos.model.misc.concreteactivity.ConcreteActivity) concreteactvities.get(0);
  else
    return null;
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
matches3
/Users/remywang/metalift/txl/qbs/allbench//IssueServiceImpl.java
{
  int i = 0;
  org.itracker.model.Issue issue = getIssueDAO().findByPrimaryKey(issueId);
  java.util.Collection<org.itracker.model.IssueAttachment> attachments = issue.getAttachments();
  i = attachments.size();
  return i;
}haha 
matches1
