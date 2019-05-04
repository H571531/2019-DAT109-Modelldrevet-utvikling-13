/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 36 "model.ump"
// line 172 "model.ump"
public class Stand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stand Attributes
  private String standid;
  private String name;

  //Stand State Machines
  public enum RegisterStatus { visitedGivenURL, infoUpdated, viewingQRCode }
  private RegisterStatus registerStatus;

  //Stand Associations
  private Expo expo;
  private Study study;
  private List<Vote> votes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Stand(String aStandid, String aName, Expo aExpo, Study aStudy)
  {
    standid = aStandid;
    name = aName;
    boolean didAddExpo = setExpo(aExpo);
    if (!didAddExpo)
    {
      throw new RuntimeException("Unable to create stand due to expo");
    }
    boolean didAddStudy = setStudy(aStudy);
    if (!didAddStudy)
    {
      throw new RuntimeException("Unable to create stand due to study");
    }
    votes = new ArrayList<Vote>();
    setRegisterStatus(RegisterStatus.visitedGivenURL);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStandid(String aStandid)
  {
    boolean wasSet = false;
    standid = aStandid;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getStandid()
  {
    return standid;
  }

  public String getName()
  {
    return name;
  }

  public String getRegisterStatusFullName()
  {
    String answer = registerStatus.toString();
    return answer;
  }

  public RegisterStatus getRegisterStatus()
  {
    return registerStatus;
  }

  public boolean infoInput()
  {
    boolean wasEventProcessed = false;
    
    RegisterStatus aRegisterStatus = registerStatus;
    switch (aRegisterStatus)
    {
      case visitedGivenURL:
        setRegisterStatus(RegisterStatus.infoUpdated);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean requestQR()
  {
    boolean wasEventProcessed = false;
    
    RegisterStatus aRegisterStatus = registerStatus;
    switch (aRegisterStatus)
    {
      case visitedGivenURL:
        setRegisterStatus(RegisterStatus.viewingQRCode);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setRegisterStatus(RegisterStatus aRegisterStatus)
  {
    registerStatus = aRegisterStatus;
  }
  /* Code from template association_GetOne */
  public Expo getExpo()
  {
    return expo;
  }
  /* Code from template association_GetOne */
  public Study getStudy()
  {
    return study;
  }
  /* Code from template association_GetMany */
  public Vote getVote(int index)
  {
    Vote aVote = votes.get(index);
    return aVote;
  }

  public List<Vote> getVotes()
  {
    List<Vote> newVotes = Collections.unmodifiableList(votes);
    return newVotes;
  }

  public int numberOfVotes()
  {
    int number = votes.size();
    return number;
  }

  public boolean hasVotes()
  {
    boolean has = votes.size() > 0;
    return has;
  }

  public int indexOfVote(Vote aVote)
  {
    int index = votes.indexOf(aVote);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setExpo(Expo aExpo)
  {
    boolean wasSet = false;
    if (aExpo == null)
    {
      return wasSet;
    }

    Expo existingExpo = expo;
    expo = aExpo;
    if (existingExpo != null && !existingExpo.equals(aExpo))
    {
      existingExpo.removeStand(this);
    }
    expo.addStand(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setStudy(Study aStudy)
  {
    boolean wasSet = false;
    if (aStudy == null)
    {
      return wasSet;
    }

    Study existingStudy = study;
    study = aStudy;
    if (existingStudy != null && !existingStudy.equals(aStudy))
    {
      existingStudy.removeStand(this);
    }
    study.addStand(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfVotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Vote addVote(String aVoteid, Visitor aVisitor)
  {
    return new Vote(aVoteid, this, aVisitor);
  }

  public boolean addVote(Vote aVote)
  {
    boolean wasAdded = false;
    if (votes.contains(aVote)) { return false; }
    Stand existingStand = aVote.getStand();
    boolean isNewStand = existingStand != null && !this.equals(existingStand);
    if (isNewStand)
    {
      aVote.setStand(this);
    }
    else
    {
      votes.add(aVote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeVote(Vote aVote)
  {
    boolean wasRemoved = false;
    //Unable to remove aVote, as it must always have a stand
    if (!this.equals(aVote.getStand()))
    {
      votes.remove(aVote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addVoteAt(Vote aVote, int index)
  {  
    boolean wasAdded = false;
    if(addVote(aVote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfVotes()) { index = numberOfVotes() - 1; }
      votes.remove(aVote);
      votes.add(index, aVote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveVoteAt(Vote aVote, int index)
  {
    boolean wasAdded = false;
    if(votes.contains(aVote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfVotes()) { index = numberOfVotes() - 1; }
      votes.remove(aVote);
      votes.add(index, aVote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addVoteAt(aVote, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Expo placeholderExpo = expo;
    this.expo = null;
    if(placeholderExpo != null)
    {
      placeholderExpo.removeStand(this);
    }
    Study placeholderStudy = study;
    this.study = null;
    if(placeholderStudy != null)
    {
      placeholderStudy.removeStand(this);
    }
    for(int i=votes.size(); i > 0; i--)
    {
      Vote aVote = votes.get(i - 1);
      aVote.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "standid" + ":" + getStandid()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "expo = "+(getExpo()!=null?Integer.toHexString(System.identityHashCode(getExpo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "study = "+(getStudy()!=null?Integer.toHexString(System.identityHashCode(getStudy())):"null");
  }
}