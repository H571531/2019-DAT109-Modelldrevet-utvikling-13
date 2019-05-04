<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Visitor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Visitor Attributes
  private $visitorid;

  //Visitor State Machines
  private static $VotingQRCodeScanned = 1;
  private static $VotingRegisteringNewVisitor = 2;
  private static $VotingRegistered = 3;
  private static $VotingViewingStandPage = 4;
  private static $VotingVoteRegistered = 5;
  private $Voting;

  //Visitor Associations
  private $votes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aVisitorid)
  {
    $this->visitorid = $aVisitorid;
    $this->votes = array();
    $this->setVoting(self::$VotingQRCodeScanned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setVisitorid($aVisitorid)
  {
    $wasSet = false;
    $this->visitorid = $aVisitorid;
    $wasSet = true;
    return $wasSet;
  }

  public function getVisitorid()
  {
    return $this->visitorid;
  }

  public function getVotingFullName()
  {
    $answer = $this->getVoting();
    return $answer;
  }

  public function getVoting()
  {
    if ($this->Voting == self::$VotingQRCodeScanned) { return "VotingQRCodeScanned"; }
    elseif ($this->Voting == self::$VotingRegisteringNewVisitor) { return "VotingRegisteringNewVisitor"; }
    elseif ($this->Voting == self::$VotingRegistered) { return "VotingRegistered"; }
    elseif ($this->Voting == self::$VotingViewingStandPage) { return "VotingViewingStandPage"; }
    elseif ($this->Voting == self::$VotingVoteRegistered) { return "VotingVoteRegistered"; }
    return null;
  }

  public function register()
  {
    $wasEventProcessed = false;
    
    $aVoting = $this->Voting;
    if ($aVoting == self::$VotingQRCodeScanned)
    {
      $this->setVoting(self::$VotingRegisteringNewVisitor);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function alreadyRegistered()
  {
    $wasEventProcessed = false;
    
    $aVoting = $this->Voting;
    if ($aVoting == self::$VotingQRCodeScanned)
    {
      $this->setVoting(self::$VotingViewingStandPage);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function phoneNumberEntered()
  {
    $wasEventProcessed = false;
    
    $aVoting = $this->Voting;
    if ($aVoting == self::$VotingRegisteringNewVisitor)
    {
      registerNewVisitor();
      $this->setVoting(self::$VotingRegistered);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function redirect()
  {
    $wasEventProcessed = false;
    
    $aVoting = $this->Voting;
    if ($aVoting == self::$VotingRegistered)
    {
      $this->setVoting(self::$VotingViewingStandPage);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function voteButtonPushed()
  {
    $wasEventProcessed = false;
    
    $aVoting = $this->Voting;
    if ($aVoting == self::$VotingViewingStandPage)
    {
      if ($this->registered)
      {
        registerNewVote();
        $this->setVoting(self::$VotingVoteRegistered);
        $wasEventProcessed = true;
      }
    }
    return $wasEventProcessed;
  }

  private function setVoting($aVoting)
  {
    $this->Voting = $aVoting;

    // entry actions and do activities
    if ($this->Voting == self::$VotingQRCodeScanned)
    {
      checkIfRegistered();
    }
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

  public static function minimumNumberOfVotes()
  {
    return 0;
  }

  public function addVoteVia($aVoteid, $aStand)
  {
    return new Vote($aVoteid, $aStand, $this);
  }

  public function addVote($aVote)
  {
    $wasAdded = false;
    if ($this->indexOfVote($aVote) !== -1) { return false; }
    $existingVisitor = $aVote->getVisitor();
    $isNewVisitor = $existingVisitor != null && $this !== $existingVisitor;
    if ($isNewVisitor)
    {
      $aVote->setVisitor($this);
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
    //Unable to remove aVote, as it must always have a visitor
    if ($this !== $aVote->getVisitor())
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
    foreach ($this->votes as $aVote)
    {
      $aVote->delete();
    }
  }

}
?>