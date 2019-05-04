/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/



// line 67 "model.ump"
// line 187 "model.ump"
public class Vote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Vote Attributes
  private String voteid;

  //Vote Associations
  private Stand stand;
  private Visitor visitor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Vote(String aVoteid, Stand aStand, Visitor aVisitor)
  {
    voteid = aVoteid;
    boolean didAddStand = setStand(aStand);
    if (!didAddStand)
    {
      throw new RuntimeException("Unable to create vote due to stand");
    }
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create vote due to visitor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setVoteid(String aVoteid)
  {
    boolean wasSet = false;
    voteid = aVoteid;
    wasSet = true;
    return wasSet;
  }

  public String getVoteid()
  {
    return voteid;
  }
  /* Code from template association_GetOne */
  public Stand getStand()
  {
    return stand;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_SetOneToMany */
  public boolean setStand(Stand aStand)
  {
    boolean wasSet = false;
    if (aStand == null)
    {
      return wasSet;
    }

    Stand existingStand = stand;
    stand = aStand;
    if (existingStand != null && !existingStand.equals(aStand))
    {
      existingStand.removeVote(this);
    }
    stand.addVote(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setVisitor(Visitor aVisitor)
  {
    boolean wasSet = false;
    if (aVisitor == null)
    {
      return wasSet;
    }

    Visitor existingVisitor = visitor;
    visitor = aVisitor;
    if (existingVisitor != null && !existingVisitor.equals(aVisitor))
    {
      existingVisitor.removeVote(this);
    }
    visitor.addVote(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Stand placeholderStand = stand;
    this.stand = null;
    if(placeholderStand != null)
    {
      placeholderStand.removeVote(this);
    }
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeVote(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "voteid" + ":" + getVoteid()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "stand = "+(getStand()!=null?Integer.toHexString(System.identityHashCode(getStand())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}