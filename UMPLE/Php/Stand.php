<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Stand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stand Attributes
  private $standid;
  private $name;

  //Stand State Machines
  private static $RegisterStatusVisitedGivenURL = 1;
  private static $RegisterStatusInfoUpdated = 2;
  private static $RegisterStatusViewingQRCode = 3;
  private $RegisterStatus;

  //Stand Associations
  private $expo;
  private $study;
  private $votes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStandid, $aName, $aExpo, $aStudy)
  {
    $this->standid = $aStandid;
    $this->name = $aName;
    $didAddExpo = $this->setExpo($aExpo);
    if (!$didAddExpo)
    {
      throw new Exception("Unable to create stand due to expo");
    }
    $didAddStudy = $this->setStudy($aStudy);
    if (!$didAddStudy)
    {
      throw new Exception("Unable to create stand due to study");
    }
    $this->votes = array();
    $this->setRegisterStatus(self::$RegisterStatusVisitedGivenURL);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStandid($aStandid)
  {
    $wasSet = false;
    $this->standid = $aStandid;
    $wasSet = true;
    return $wasSet;
  }

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function getStandid()
  {
    return $this->standid;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getRegisterStatusFullName()
  {
    $answer = $this->getRegisterStatus();
    return $answer;
  }

  public function getRegisterStatus()
  {
    if ($this->RegisterStatus == self::$RegisterStatusVisitedGivenURL) { return "RegisterStatusVisitedGivenURL"; }
    elseif ($this->RegisterStatus == self::$RegisterStatusInfoUpdated) { return "RegisterStatusInfoUpdated"; }
    elseif ($this->RegisterStatus == self::$RegisterStatusViewingQRCode) { return "RegisterStatusViewingQRCode"; }
    return null;
  }

  public function infoInput()
  {
    $wasEventProcessed = false;
    
    $aRegisterStatus = $this->RegisterStatus;
    if ($aRegisterStatus == self::$RegisterStatusVisitedGivenURL)
    {
      $this->setRegisterStatus(self::$RegisterStatusInfoUpdated);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function requestQR()
  {
    $wasEventProcessed = false;
    
    $aRegisterStatus = $this->RegisterStatus;
    if ($aRegisterStatus == self::$RegisterStatusVisitedGivenURL)
    {
      $this->setRegisterStatus(self::$RegisterStatusViewingQRCode);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function setRegisterStatus($aRegisterStatus)
  {
    $this->RegisterStatus = $aRegisterStatus;
  }

  public function getExpo()
  {
    return $this->expo;
  }

  public function getStudy()
  {
    return $this->study;
  }

  public function getVote_index($index)
  {
    $aVote = $this->votes[$index];
    return $aVote;
  }

  public function getVotes()
  {
    $newVotes = $this->votes;
    return $newVotes;
  }

  public function numberOfVotes()
  {
    $number = count($this->votes);
    return $number;
  }

  public function hasVotes()
  {
    $has = $this->numberOfVotes() > 0;
    return $has;
  }

  public function indexOfVote($aVote)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->votes as $vote)
    {
      if ($vote->equals($aVote))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
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
      $existingExpo->removeStand($this);
    }
    $this->expo->addStand($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setStudy($aStudy)
  {
    $wasSet = false;
    if ($aStudy == null)
    {
      return $wasSet;
    }
    
    $existingStudy = $this->study;
    $this->study = $aStudy;
    if ($existingStudy != null && $existingStudy != $aStudy)
    {
      $existingStudy->removeStand($this);
    }
    $this->study->addStand($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfVotes()
  {
    return 0;
  }

  public function addVoteVia($aVoteid, $aVisitor)
  {
    return new Vote($aVoteid, $this, $aVisitor);
  }

  public function addVote($aVote)
  {
    $wasAdded = false;
    if ($this->indexOfVote($aVote) !== -1) { return false; }
    $existingStand = $aVote->getStand();
    $isNewStand = $existingStand != null && $this !== $existingStand;
    if ($isNewStand)
    {
      $aVote->setStand($this);
    }
    else
    {
      $this->votes[] = $aVote;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeVote($aVote)
  {
    $wasRemoved = false;
    //Unable to remove aVote, as it must always have a stand
    if ($this !== $aVote->getStand())
    {
      unset($this->votes[$this->indexOfVote($aVote)]);
      $this->votes = array_values($this->votes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addVoteAt($aVote, $index)
  {  
    $wasAdded = false;
    if($this->addVote($aVote))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfVotes()) { $index = $this->numberOfVotes() - 1; }
      array_splice($this->votes, $this->indexOfVote($aVote), 1);
      array_splice($this->votes, $index, 0, array($aVote));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveVoteAt($aVote, $index)
  {
    $wasAdded = false;
    if($this->indexOfVote($aVote) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfVotes()) { $index = $this->numberOfVotes() - 1; }
      array_splice($this->votes, $this->indexOfVote($aVote), 1);
      array_splice($this->votes, $index, 0, array($aVote));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addVoteAt($aVote, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderExpo = $this->expo;
    $this->expo = null;
    $placeholderExpo->removeStand($this);
    $placeholderStudy = $this->study;
    $this->study = null;
    $placeholderStudy->removeStand($this);
    foreach ($this->votes as $aVote)
    {
      $aVote->delete();
    }
  }

}
?>