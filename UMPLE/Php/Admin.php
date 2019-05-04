<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Admin
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Attributes
  private $userName;
  private $adminId;
  private $hashedPassword;

  //Admin State Machines
  private static $StatusNotLoggedIn = 1;
  private static $StatusLoggedIn = 2;
  private static $StatusLoggedInNull = 3;
  private static $StatusLoggedInSelectingAction = 4;
  private static $StatusLoggedInRegisteringNewStand = 5;
  private static $StatusLoggedInViewingStandURL = 6;
  private static $StatusLoggedInEditingStand = 7;
  private static $StatusLoggedInBrowsingStands = 8;
  private static $StatusLoggedInViewingStatistics = 9;
  private $status;
  private $statusLoggedIn;

  //Admin Associations
  private $expo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aUserName, $aAdminId, $aHashedPassword, $aExpo)
  {
    $this->userName = $aUserName;
    $this->adminId = $aAdminId;
    $this->hashedPassword = $aHashedPassword;
    $didAddExpo = $this->setExpo($aExpo);
    if (!$didAddExpo)
    {
      throw new Exception("Unable to create admin due to expo");
    }
    $this->setStatusLoggedIn(self::$StatusLoggedInNull);
    $this->setStatus(self::$StatusNotLoggedIn);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setUserName($aUserName)
  {
    $wasSet = false;
    $this->userName = $aUserName;
    $wasSet = true;
    return $wasSet;
  }

  public function setAdminId($aAdminId)
  {
    $wasSet = false;
    $this->adminId = $aAdminId;
    $wasSet = true;
    return $wasSet;
  }

  public function setHashedPassword($aHashedPassword)
  {
    $wasSet = false;
    $this->hashedPassword = $aHashedPassword;
    $wasSet = true;
    return $wasSet;
  }

  public function getUserName()
  {
    return $this->userName;
  }

  public function getAdminId()
  {
    return $this->adminId;
  }

  public function getHashedPassword()
  {
    return $this->hashedPassword;
  }

  public function getStatusFullName()
  {
    $answer = $this->getStatus();
    if ($this->statusLoggedIn != self::$StatusLoggedInNull) { $answer .= "." . $this->getStatusLoggedIn(); }
    return $answer;
  }

  public function getStatus()
  {
    if ($this->status == self::$StatusNotLoggedIn) { return "StatusNotLoggedIn"; }
    elseif ($this->status == self::$StatusLoggedIn) { return "StatusLoggedIn"; }
    return null;
  }

  public function getStatusLoggedIn()
  {
    if ($this->statusLoggedIn == self::$StatusLoggedInNull) { return "StatusLoggedInNull"; }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInSelectingAction) { return "StatusLoggedInSelectingAction"; }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInRegisteringNewStand) { return "StatusLoggedInRegisteringNewStand"; }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInViewingStandURL) { return "StatusLoggedInViewingStandURL"; }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInEditingStand) { return "StatusLoggedInEditingStand"; }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInBrowsingStands) { return "StatusLoggedInBrowsingStands"; }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInViewingStatistics) { return "StatusLoggedInViewingStatistics"; }
    return null;
  }

  public function login()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusNotLoggedIn)
    {
      if ($this->loginInformationOk)
      {
        $this->setStatus(self::$StatusLoggedIn);
        $wasEventProcessed = true;
      }
    }
    return $wasEventProcessed;
  }

  public function addNewAdmin()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      addNewAdmin(new Admin(userName, password));
      $this->setStatusLoggedIn(self::$StatusLoggedInSelectingAction);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function updateExpo()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      expo.update();
      $this->setStatusLoggedIn(self::$StatusLoggedInSelectingAction);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function registerNewStand()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      $this->setStatusLoggedIn(self::$StatusLoggedInRegisteringNewStand);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function browseStands()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      $this->setStatusLoggedIn(self::$StatusLoggedInBrowsingStands);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function viewStatistics()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusLoggedIn)
    {
      $this->setStatusLoggedIn(self::$StatusLoggedInViewingStatistics);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function editManually()
  {
    $wasEventProcessed = false;
    
    $aStatusLoggedIn = $this->statusLoggedIn;
    if ($aStatusLoggedIn == self::$StatusLoggedInRegisteringNewStand)
    {
      $this->exitStatusLoggedIn();
      $this->setStatusLoggedIn(self::$StatusLoggedInEditingStand);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function createEditURL()
  {
    $wasEventProcessed = false;
    
    $aStatusLoggedIn = $this->statusLoggedIn;
    if ($aStatusLoggedIn == self::$StatusLoggedInRegisteringNewStand)
    {
      $this->exitStatusLoggedIn();
      $this->setStatusLoggedIn(self::$StatusLoggedInViewingStandURL);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function returnToAdminPage()
  {
    $wasEventProcessed = false;
    
    $aStatusLoggedIn = $this->statusLoggedIn;
    if ($aStatusLoggedIn == self::$StatusLoggedInViewingStandURL)
    {
      $this->exitStatusLoggedIn();
      $this->setStatusLoggedIn(self::$StatusLoggedInSelectingAction);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function saveEdits()
  {
    $wasEventProcessed = false;
    
    $aStatusLoggedIn = $this->statusLoggedIn;
    if ($aStatusLoggedIn == self::$StatusLoggedInEditingStand)
    {
      $this->exitStatusLoggedIn();
      updateStand();
      $this->setStatusLoggedIn(self::$StatusLoggedInBrowsingStands);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function returnToActions()
  {
    $wasEventProcessed = false;
    
    $aStatusLoggedIn = $this->statusLoggedIn;
    if ($aStatusLoggedIn == self::$StatusLoggedInViewingStatistics)
    {
      $this->exitStatusLoggedIn();
      $this->setStatusLoggedIn(self::$StatusLoggedInSelectingAction);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitStatus()
  {
    if ($this->status == self::$StatusLoggedIn)
    {
      $this->exitLoggedIn();
    }
  }

  private function setStatus($aStatus)
  {
    $this->status = $aStatus;

    // entry actions and do activities
    if ($this->status == self::$StatusLoggedIn)
    {
      if ($this->statusLoggedIn == self::$StatusLoggedInNull) { $this->setStatusLoggedIn(self::$StatusLoggedInSelectingAction); }
    }
  }

  private function exitStatusLoggedIn()
  {
    if ($this->statusLoggedIn == self::$StatusLoggedInSelectingAction)
    {
      $this->setStatusLoggedIn(self::StatusLoggedInNull);
    }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInRegisteringNewStand)
    {
      $this->setStatusLoggedIn(self::StatusLoggedInNull);
    }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInViewingStandURL)
    {
      $this->setStatusLoggedIn(self::StatusLoggedInNull);
    }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInEditingStand)
    {
      $this->setStatusLoggedIn(self::StatusLoggedInNull);
    }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInBrowsingStands)
    {
      $this->setStatusLoggedIn(self::StatusLoggedInNull);
    }
    elseif ($this->statusLoggedIn == self::$StatusLoggedInViewingStatistics)
    {
      $this->setStatusLoggedIn(self::StatusLoggedInNull);
    }
  }

  private function setStatusLoggedIn($aStatusLoggedIn)
  {
    $this->statusLoggedIn = $aStatusLoggedIn;
    if ($this->status != self::$StatusLoggedIn && $aStatusLoggedIn != self::$StatusLoggedInNull) { $this->setStatus(self::$StatusLoggedIn); }

    // entry actions and do activities
    if ($this->statusLoggedIn == self::$StatusLoggedInRegisteringNewStand)
    {
      createNewStand();
    }
  }

  public function getExpo()
  {
    return $this->expo;
  }

  public function setExpo($aExpo)
  {
    $wasSet = false;
    if ($aExpo == null)
    {
      return $wasSet;
    }
    
    $existingExpo = $this->expo;
    $this->expo = $aExpo;
    if ($existingExpo != null && $existingExpo != $aExpo)
    {
      $existingExpo->removeAdmin($this);
    }
    $this->expo->addAdmin($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderExpo = $this->expo;
    $this->expo = null;
    $placeholderExpo->removeAdmin($this);
  }

}
?>