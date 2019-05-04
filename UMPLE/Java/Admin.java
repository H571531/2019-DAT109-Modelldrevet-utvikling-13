/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/



// line 106 "model.ump"
// line 197 "model.ump"
public class Admin
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Attributes
  private String userName;
  private String adminId;
  private String hashedPassword;

  //Admin State Machines
  public enum Status { notLoggedIn, loggedIn }
  public enum StatusLoggedIn { Null, selectingAction, registeringNewStand, viewingStandURL, editingStand, browsingStands, viewingStatistics }
  private Status status;
  private StatusLoggedIn statusLoggedIn;

  //Admin Associations
  private Expo expo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aUserName, String aAdminId, String aHashedPassword, Expo aExpo)
  {
    userName = aUserName;
    adminId = aAdminId;
    hashedPassword = aHashedPassword;
    boolean didAddExpo = setExpo(aExpo);
    if (!didAddExpo)
    {
      throw new RuntimeException("Unable to create admin due to expo");
    }
    setStatusLoggedIn(StatusLoggedIn.Null);
    setStatus(Status.notLoggedIn);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdminId(String aAdminId)
  {
    boolean wasSet = false;
    adminId = aAdminId;
    wasSet = true;
    return wasSet;
  }

  public boolean setHashedPassword(String aHashedPassword)
  {
    boolean wasSet = false;
    hashedPassword = aHashedPassword;
    wasSet = true;
    return wasSet;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getAdminId()
  {
    return adminId;
  }

  public String getHashedPassword()
  {
    return hashedPassword;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    if (statusLoggedIn != StatusLoggedIn.Null) { answer += "." + statusLoggedIn.toString(); }
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public StatusLoggedIn getStatusLoggedIn()
  {
    return statusLoggedIn;
  }

  public boolean login()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case notLoggedIn:
        if (loginInformationOk)
        {
          setStatus(Status.loggedIn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean addNewAdmin()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case loggedIn:
        exitStatusLoggedIn();
        // line 125 "model.ump"
        addNewAdmin(new Admin(userName, password));
        setStatusLoggedIn(StatusLoggedIn.selectingAction);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean updateExpo()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case loggedIn:
        exitStatusLoggedIn();
        // line 127 "model.ump"
        expo.update();
        setStatusLoggedIn(StatusLoggedIn.selectingAction);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean registerNewStand()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case loggedIn:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.registeringNewStand);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean browseStands()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case loggedIn:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.browsingStands);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean viewStatistics()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case loggedIn:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.viewingStatistics);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean editManually()
  {
    boolean wasEventProcessed = false;
    
    StatusLoggedIn aStatusLoggedIn = statusLoggedIn;
    switch (aStatusLoggedIn)
    {
      case registeringNewStand:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.editingStand);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean createEditURL()
  {
    boolean wasEventProcessed = false;
    
    StatusLoggedIn aStatusLoggedIn = statusLoggedIn;
    switch (aStatusLoggedIn)
    {
      case registeringNewStand:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.viewingStandURL);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean returnToAdminPage()
  {
    boolean wasEventProcessed = false;
    
    StatusLoggedIn aStatusLoggedIn = statusLoggedIn;
    switch (aStatusLoggedIn)
    {
      case viewingStandURL:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.selectingAction);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean saveEdits()
  {
    boolean wasEventProcessed = false;
    
    StatusLoggedIn aStatusLoggedIn = statusLoggedIn;
    switch (aStatusLoggedIn)
    {
      case editingStand:
        exitStatusLoggedIn();
        // line 149 "model.ump"
        updateStand();
        setStatusLoggedIn(StatusLoggedIn.browsingStands);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean returnToActions()
  {
    boolean wasEventProcessed = false;
    
    StatusLoggedIn aStatusLoggedIn = statusLoggedIn;
    switch (aStatusLoggedIn)
    {
      case viewingStatistics:
        exitStatusLoggedIn();
        setStatusLoggedIn(StatusLoggedIn.selectingAction);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case loggedIn:
        exitStatusLoggedIn();
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case loggedIn:
        if (statusLoggedIn == StatusLoggedIn.Null) { setStatusLoggedIn(StatusLoggedIn.selectingAction); }
        break;
    }
  }

  private void exitStatusLoggedIn()
  {
    switch(statusLoggedIn)
    {
      case selectingAction:
        setStatusLoggedIn(StatusLoggedIn.Null);
        break;
      case registeringNewStand:
        setStatusLoggedIn(StatusLoggedIn.Null);
        break;
      case viewingStandURL:
        setStatusLoggedIn(StatusLoggedIn.Null);
        break;
      case editingStand:
        setStatusLoggedIn(StatusLoggedIn.Null);
        break;
      case browsingStands:
        setStatusLoggedIn(StatusLoggedIn.Null);
        break;
      case viewingStatistics:
        setStatusLoggedIn(StatusLoggedIn.Null);
        break;
    }
  }

  private void setStatusLoggedIn(StatusLoggedIn aStatusLoggedIn)
  {
    statusLoggedIn = aStatusLoggedIn;
    if (status != Status.loggedIn && aStatusLoggedIn != StatusLoggedIn.Null) { setStatus(Status.loggedIn); }

    // entry actions and do activities
    switch(statusLoggedIn)
    {
      case registeringNewStand:
        // line 137 "model.ump"
        createNewStand();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Expo getExpo()
  {
    return expo;
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
      existingExpo.removeAdmin(this);
    }
    expo.addAdmin(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Expo placeholderExpo = expo;
    this.expo = null;
    if(placeholderExpo != null)
    {
      placeholderExpo.removeAdmin(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "userName" + ":" + getUserName()+ "," +
            "adminId" + ":" + getAdminId()+ "," +
            "hashedPassword" + ":" + getHashedPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "expo = "+(getExpo()!=null?Integer.toHexString(System.identityHashCode(getExpo())):"null");
  }
}