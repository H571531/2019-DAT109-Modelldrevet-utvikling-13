/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 74 "model.ump"
// line 192 "model.ump"
public class Visitor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Visitor Attributes
  private String visitorid;

  //Visitor State Machines
  public enum Voting { QRCodeScanned, registeringNewVisitor, registered, viewingStandPage, voteRegistered }
  private Voting voting;

  //Visitor Associations
  private List<Vote> votes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Visitor(String aVisitorid)
  {
    visitorid = aVisitorid;
    votes = new ArrayList<Vote>();
    setVoting(Voting.QRCodeScanned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setVisitorid(String aVisitorid)
  {
    boolean wasSet = false;
    visitorid = aVisitorid;
    wasSet = true;
    return wasSet;
  }

  public String getVisitorid()
  {
    return visitorid;
  }

  public String getVotingFullName()
  {
    String answer = voting.toString();
    return answer;
  }

  public Voting getVoting()
  {
    return voting;
  }

  public boolean register()
  {
    boolean wasEventProcessed = false;
    
    Voting aVoting = voting;
    switch (aVoting)
    {
      case QRCodeScanned:
        setVoting(Voting.registeringNewVisitor);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean alreadyRegistered()
  {
    boolean wasEventProcessed = false;
    
    Voting aVoting = voting;
    switch (aVoting)
    {
      case QRCodeScanned:
        setVoting(Voting.viewingStandPage);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean phoneNumberEntered()
  {
    boolean wasEventProcessed = false;
    
    Voting aVoting = voting;
    switch (aVoting)
    {
      case registeringNewVisitor:
        // line 89 "model.ump"
        registerNewVisitor();
        setVoting(Voting.registered);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean redirect()
  {
    boolean wasEventProcessed = false;
    
    Voting aVoting = voting;
    switch (aVoting)
    {
      case registered:
        setVoting(Voting.viewingStandPage);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean voteButtonPushed()
  {
    boolean wasEventProcessed = false;
    
    Voting aVoting = voting;
    switch (aVoting)
    {
      case viewingStandPage:
        if (registered)
        {
        // line 99 "model.ump"
          registerNewVote();
          setVoting(Voting.voteRegistered);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setVoting(Voting aVoting)
  {
    voting = aVoting;

    // entry actions and do activities
    switch(voting)
    {
      case QRCodeScanned:
        // line 82 "model.ump"
        checkIfRegistered();
        break;
    }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfVotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Vote addVote(String aVoteid, Stand aStand)
  {
    return new Vote(aVoteid, aStand, this);
  }

  public boolean addVote(Vote aVote)
  {
    boolean wasAdded = false;
    if (votes.contains(aVote)) { return false; }
    Visitor existingVisitor = aVote.getVisitor();
    boolean isNewVisitor = existingVisitor != null && !this.equals(existingVisitor);
    if (isNewVisitor)
    {
      aVote.setVisitor(this);
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
    //Unable to remove aVote, as it must always have a visitor
    if (!this.equals(aVote.getVisitor()))
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
    for(int i=votes.size(); i > 0; i--)
    {
      Vote aVote = votes.get(i - 1);
      aVote.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "visitorid" + ":" + getVisitorid()+ "]";
  }
}