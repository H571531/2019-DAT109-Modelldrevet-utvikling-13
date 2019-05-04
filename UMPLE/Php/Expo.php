<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Expo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Expo Attributes
  private $expoid;
  private $voteRegistrationOpen;
  private $statisticOpenToPublic;

  //Expo State Machines
  private static $VoteRegistrationVoteRegistrationClosed = 1;
  private static $VoteRegistrationVoteRegistrationOpen = 2;
  private $voteRegistration;

  private static $StatisticsStatusStatisticsOpen = 1;
  private static $StatisticsStatusStatisticsClosed = 2;
  private $statisticsStatus;

  //Expo Associations
  private $stands;
  private $admins;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aExpoid, $aVoteRegistrationOpen, $aStatisticOpenToPublic)
  {
    $this->expoid = $aExpoid;
    $this->voteRegistrationOpen = $aVoteRegistrationOpen;
    $this->statisticOpenToPublic = $aStatisticOpenToPublic;
    $this->stands = array();
    $this->admins = array();
    $this->setVoteRegistration(self::$VoteRegistrationVoteRegistrationClosed);
    $this->setStatisticsStatus(self::$StatisticsStatusStatisticsOpen);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setExpoid($aExpoid)
  {
    $wasSet = false;
    $this->expoid = $aExpoid;
    $wasSet = true;
    return $wasSet;
  }

  public function setVoteRegistrationOpen($aVoteRegistrationOpen)
  {
    $wasSet = false;
    $this->voteRegistrationOpen = $aVoteRegistrationOpen;
    $wasSet = true;
    return $wasSet;
  }

  public function setStatisticOpenToPublic($aStatisticOpenToPublic)
  {
    $wasSet = false;
    $this->statisticOpenToPublic = $aStatisticOpenToPublic;
    $wasSet = true;
    return $wasSet;
  }

  public function getExpoid()
  {
    return $this->expoid;
  }

  public function getVoteRegistrationOpen()
  {
    return $this->voteRegistrationOpen;
  }

  public function getStatisticOpenToPublic()
  {
    return $this->statisticOpenToPublic;
  }

  public function isVoteRegistrationOpen()
  {
    return $this->voteRegistrationOpen;
  }

  public function isStatisticOpenToPublic()
  {
    return $this->statisticOpenToPublic;
  }

  public function getVoteRegistrationFullName()
  {
    $answer = $this->getVoteRegistration();
    return $answer;
  }

  public function getStatisticsStatusFullName()
  {
    $answer = $this->getStatisticsStatus();
    return $answer;
  }

  public function getVoteRegistration()
  {
    if ($this->voteRegistration == self::$VoteRegistrationVoteRegistrationClosed) { return "VoteRegistrationVoteRegistrationClosed"; }
    elseif ($this->voteRegistration == self::$VoteRegistrationVoteRegistrationOpen) { return "VoteRegistrationVoteRegistrationOpen"; }
    return null;
  }

  public function getStatisticsStatus()
  {
    if ($this->statisticsStatus == self::$StatisticsStatusStatisticsOpen) { return "StatisticsStatusStatisticsOpen"; }
    elseif ($this->statisticsStatus == self::$StatisticsStatusStatisticsClosed) { return "StatisticsStatusStatisticsClosed"; }
    return null;
  }

  public function openRegistration()
  {
    $wasEventProcessed = false;
    
    $aVoteRegistration = $this->voteRegistration;
    if ($aVoteRegistration == self::$VoteRegistrationVoteRegistrationClosed)
    {
      $this->setVoteRegistration(self::$VoteRegistrationVoteRegistrationOpen);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function closeRegistration()
  {
    $wasEventProcessed = false;
    
    $aVoteRegistration = $this->voteRegistration;
    if ($aVoteRegistration == self::$VoteRegistrationVoteRegistrationOpen)
    {
      $this->setVoteRegistration(self::$VoteRegistrationVoteRegistrationClosed);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function closeStatistics()
  {
    $wasEventProcessed = false;
    
    $aStatisticsStatus = $this->statisticsStatus;
    if ($aStatisticsStatus == self::$StatisticsStatusStatisticsOpen)
    {
      $this->setStatisticsStatus(self::$StatisticsStatusStatisticsClosed);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function openStatistics()
  {
    $wasEventProcessed = false;
    
    $aStatisticsStatus = $this->statisticsStatus;
    if ($aStatisticsStatus == self::$StatisticsStatusStatisticsClosed)
    {
      $this->setStatisticsStatus(self::$StatisticsStatusStatisticsOpen);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function setVoteRegistration($aVoteRegistration)
  {
    $this->voteRegistration = $aVoteRegistration;
  }

  private function setStatisticsStatus($aStatisticsStatus)
  {
    $this->statisticsStatus = $aStatisticsStatus;
  }

  public function getStand_index($index)
  {
    $aStand = $this->stands[$index];
    return $aStand;
  }

  public function getStands()
  {
    $newStands = $this->stands;
    return $newStands;
  }

  public function numberOfStands()
  {
    $number = count($this->stands);
    return $number;
  }

  public function hasStands()
  {
    $has = $this->numberOfStands() > 0;
    return $has;
  }

  public function indexOfStand($aStand)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->stands as $stand)
    {
      if ($stand->equals($aStand))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAdmin_index($index)
  {
    $aAdmin = $this->admins[$index];
    return $aAdmin;
  }

  public function getAdmins()
  {
    $newAdmins = $this->admins;
    return $newAdmins;
  }

  public function numberOfAdmins()
  {
    $number = count($this->admins);
    return $number;
  }

  public function hasAdmins()
  {
    $has = $this->numberOfAdmins() > 0;
    return $has;
  }

  public function indexOfAdmin($aAdmin)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->admins as $admin)
    {
      if ($admin->equals($aAdmin))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfStands()
  {
    return 0;
  }

  public function addStandVia($aStandid, $aName, $aStudy)
  {
    return new Stand($aStandid, $aName, $this, $aStudy);
  }

  public function addStand($aStand)
  {
    $wasAdded = false;
    if ($this->indexOfStand($aStand) !== -1) { return false; }
    $existingExpo = $aStand->getExpo();
    $isNewExpo = $existingExpo != null && $this !== $existingExpo;
    if ($isNewExpo)
    {
      $aStand->setExpo($this);
    }
    else
    {
      $this->stands[] = $aStand;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStand($aStand)
  {
    $wasRemoved = false;
    //Unable to remove aStand, as it must always have a expo
    if ($this !== $aStand->getExpo())
    {
      unset($this->stands[$this->indexOfStand($aStand)]);
      $this->stands = array_values($this->stands);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStandAt($aStand, $index)
  {  
    $wasAdded = false;
    if($this->addStand($aStand))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStands()) { $index = $this->numberOfStands() - 1; }
      array_splice($this->stands, $this->indexOfStand($aStand), 1);
      array_splice($this->stands, $index, 0, array($aStand));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStandAt($aStand, $index)
  {
    $wasAdded = false;
    if($this->indexOfStand($aStand) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStands()) { $index = $this->numberOfStands() - 1; }
      array_splice($this->stands, $this->indexOfStand($aStand), 1);
      array_splice($this->stands, $index, 0, array($aStand));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStandAt($aStand, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAdmins()
  {
    return 0;
  }

  public function addAdminVia($aUserName, $aAdminId, $aHashedPassword)
  {
    return new Admin($aUserName, $aAdminId, $aHashedPassword, $this);
  }

  public function addAdmin($aAdmin)
  {
    $wasAdded = false;
    if ($this->indexOfAdmin($aAdmin) !== -1) { return false; }
    $existingExpo = $aAdmin->getExpo();
    $isNewExpo = $existingExpo != null && $this !== $existingExpo;
    if ($isNewExpo)
    {
      $aAdmin->setExpo($this);
    }
    else
    {
      $this->admins[] = $aAdmin;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAdmin($aAdmin)
  {
    $wasRemoved = false;
    //Unable to remove aAdmin, as it must always have a expo
    if ($this !== $aAdmin->getExpo())
    {
      unset($this->admins[$this->indexOfAdmin($aAdmin)]);
      $this->admins = array_values($this->admins);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAdminAt($aAdmin, $index)
  {  
    $wasAdded = false;
    if($this->addAdmin($aAdmin))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAdmins()) { $index = $this->numberOfAdmins() - 1; }
      array_splice($this->admins, $this->indexOfAdmin($aAdmin), 1);
      array_splice($this->admins, $index, 0, array($aAdmin));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAdminAt($aAdmin, $index)
  {
    $wasAdded = false;
    if($this->indexOfAdmin($aAdmin) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAdmins()) { $index = $this->numberOfAdmins() - 1; }
      array_splice($this->admins, $this->indexOfAdmin($aAdmin), 1);
      array_splice($this->admins, $index, 0, array($aAdmin));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAdminAt($aAdmin, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->stands as $aStand)
    {
      $aStand->delete();
    }
    foreach ($this->admins as $aAdmin)
    {
      $aAdmin->delete();
    }
  }

}
?>