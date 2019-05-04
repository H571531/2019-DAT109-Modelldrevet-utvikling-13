/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 167 "model.ump"
public class Expo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Expo Attributes
  private String expoid;
  private boolean voteRegistrationOpen;
  private boolean statisticOpenToPublic;

  //Expo State Machines
  public enum VoteRegistration { voteRegistrationClosed, voteRegistrationOpen }
  private VoteRegistration voteRegistration;
  public enum StatisticsStatus { statisticsOpen, statisticsClosed }
  private StatisticsStatus statisticsStatus;

  //Expo Associations
  private List<Stand> stands;
  private List<Admin> admins;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Expo(String aExpoid, boolean aVoteRegistrationOpen, boolean aStatisticOpenToPublic)
  {
    expoid = aExpoid;
    voteRegistrationOpen = aVoteRegistrationOpen;
    statisticOpenToPublic = aStatisticOpenToPublic;
    stands = new ArrayList<Stand>();
    admins = new ArrayList<Admin>();
    setVoteRegistration(VoteRegistration.voteRegistrationClosed);
    setStatisticsStatus(StatisticsStatus.statisticsOpen);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setExpoid(String aExpoid)
  {
    boolean wasSet = false;
    expoid = aExpoid;
    wasSet = true;
    return wasSet;
  }

  public boolean setVoteRegistrationOpen(boolean aVoteRegistrationOpen)
  {
    boolean wasSet = false;
    voteRegistrationOpen = aVoteRegistrationOpen;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatisticOpenToPublic(boolean aStatisticOpenToPublic)
  {
    boolean wasSet = false;
    statisticOpenToPublic = aStatisticOpenToPublic;
    wasSet = true;
    return wasSet;
  }

  public String getExpoid()
  {
    return expoid;
  }

  public boolean getVoteRegistrationOpen()
  {
    return voteRegistrationOpen;
  }

  public boolean getStatisticOpenToPublic()
  {
    return statisticOpenToPublic;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isVoteRegistrationOpen()
  {
    return voteRegistrationOpen;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isStatisticOpenToPublic()
  {
    return statisticOpenToPublic;
  }

  public String getVoteRegistrationFullName()
  {
    String answer = voteRegistration.toString();
    return answer;
  }

  public String getStatisticsStatusFullName()
  {
    String answer = statisticsStatus.toString();
    return answer;
  }

  public VoteRegistration getVoteRegistration()
  {
    return voteRegistration;
  }

  public StatisticsStatus getStatisticsStatus()
  {
    return statisticsStatus;
  }

  public boolean openRegistration()
  {
    boolean wasEventProcessed = false;
    
    VoteRegistration aVoteRegistration = voteRegistration;
    switch (aVoteRegistration)
    {
      case voteRegistrationClosed:
        setVoteRegistration(VoteRegistration.voteRegistrationOpen);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean closeRegistration()
  {
    boolean wasEventProcessed = false;
    
    VoteRegistration aVoteRegistration = voteRegistration;
    switch (aVoteRegistration)
    {
      case voteRegistrationOpen:
        setVoteRegistration(VoteRegistration.voteRegistrationClosed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean closeStatistics()
  {
    boolean wasEventProcessed = false;
    
    StatisticsStatus aStatisticsStatus = statisticsStatus;
    switch (aStatisticsStatus)
    {
      case statisticsOpen:
        setStatisticsStatus(StatisticsStatus.statisticsClosed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean openStatistics()
  {
    boolean wasEventProcessed = false;
    
    StatisticsStatus aStatisticsStatus = statisticsStatus;
    switch (aStatisticsStatus)
    {
      case statisticsClosed:
        setStatisticsStatus(StatisticsStatus.statisticsOpen);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setVoteRegistration(VoteRegistration aVoteRegistration)
  {
    voteRegistration = aVoteRegistration;
  }

  private void setStatisticsStatus(StatisticsStatus aStatisticsStatus)
  {
    statisticsStatus = aStatisticsStatus;
  }
  /* Code from template association_GetMany */
  public Stand getStand(int index)
  {
    Stand aStand = stands.get(index);
    return aStand;
  }

  public List<Stand> getStands()
  {
    List<Stand> newStands = Collections.unmodifiableList(stands);
    return newStands;
  }

  public int numberOfStands()
  {
    int number = stands.size();
    return number;
  }

  public boolean hasStands()
  {
    boolean has = stands.size() > 0;
    return has;
  }

  public int indexOfStand(Stand aStand)
  {
    int index = stands.indexOf(aStand);
    return index;
  }
  /* Code from template association_GetMany */
  public Admin getAdmin(int index)
  {
    Admin aAdmin = admins.get(index);
    return aAdmin;
  }

  public List<Admin> getAdmins()
  {
    List<Admin> newAdmins = Collections.unmodifiableList(admins);
    return newAdmins;
  }

  public int numberOfAdmins()
  {
    int number = admins.size();
    return number;
  }

  public boolean hasAdmins()
  {
    boolean has = admins.size() > 0;
    return has;
  }

  public int indexOfAdmin(Admin aAdmin)
  {
    int index = admins.indexOf(aAdmin);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStands()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Stand addStand(String aStandid, String aName, Study aStudy)
  {
    return new Stand(aStandid, aName, this, aStudy);
  }

  public boolean addStand(Stand aStand)
  {
    boolean wasAdded = false;
    if (stands.contains(aStand)) { return false; }
    Expo existingExpo = aStand.getExpo();
    boolean isNewExpo = existingExpo != null && !this.equals(existingExpo);
    if (isNewExpo)
    {
      aStand.setExpo(this);
    }
    else
    {
      stands.add(aStand);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStand(Stand aStand)
  {
    boolean wasRemoved = false;
    //Unable to remove aStand, as it must always have a expo
    if (!this.equals(aStand.getExpo()))
    {
      stands.remove(aStand);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStandAt(Stand aStand, int index)
  {  
    boolean wasAdded = false;
    if(addStand(aStand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStands()) { index = numberOfStands() - 1; }
      stands.remove(aStand);
      stands.add(index, aStand);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStandAt(Stand aStand, int index)
  {
    boolean wasAdded = false;
    if(stands.contains(aStand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStands()) { index = numberOfStands() - 1; }
      stands.remove(aStand);
      stands.add(index, aStand);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStandAt(aStand, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAdmins()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Admin addAdmin(String aUserName, String aAdminId, String aHashedPassword)
  {
    return new Admin(aUserName, aAdminId, aHashedPassword, this);
  }

  public boolean addAdmin(Admin aAdmin)
  {
    boolean wasAdded = false;
    if (admins.contains(aAdmin)) { return false; }
    Expo existingExpo = aAdmin.getExpo();
    boolean isNewExpo = existingExpo != null && !this.equals(existingExpo);
    if (isNewExpo)
    {
      aAdmin.setExpo(this);
    }
    else
    {
      admins.add(aAdmin);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAdmin(Admin aAdmin)
  {
    boolean wasRemoved = false;
    //Unable to remove aAdmin, as it must always have a expo
    if (!this.equals(aAdmin.getExpo()))
    {
      admins.remove(aAdmin);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAdminAt(Admin aAdmin, int index)
  {  
    boolean wasAdded = false;
    if(addAdmin(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAdminAt(Admin aAdmin, int index)
  {
    boolean wasAdded = false;
    if(admins.contains(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAdminAt(aAdmin, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=stands.size(); i > 0; i--)
    {
      Stand aStand = stands.get(i - 1);
      aStand.delete();
    }
    for(int i=admins.size(); i > 0; i--)
    {
      Admin aAdmin = admins.get(i - 1);
      aAdmin.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "expoid" + ":" + getExpoid()+ "," +
            "voteRegistrationOpen" + ":" + getVoteRegistrationOpen()+ "," +
            "statisticOpenToPublic" + ":" + getStatisticOpenToPublic()+ "]";
  }
}