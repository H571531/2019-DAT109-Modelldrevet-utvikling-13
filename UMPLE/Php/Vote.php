<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Vote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Vote Attributes
  private $voteid;

  //Vote Associations
  private $stand;
  private $visitor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aVoteid, $aStand, $aVisitor)
  {
    $this->voteid = $aVoteid;
    $didAddStand = $this->setStand($aStand);
    if (!$didAddStand)
    {
      throw new Exception("Unable to create vote due to stand");
    }
    $didAddVisitor = $this->setVisitor($aVisitor);
    if (!$didAddVisitor)
    {
      throw new Exception("Unable to create vote due to visitor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setVoteid($aVoteid)
  {
    $wasSet = false;
    $this->voteid = $aVoteid;
    $wasSet = true;
    return $wasSet;
  }

  public function getVoteid()
  {
    return $this->voteid;
  }

  public function getStand()
  {
    return $this->stand;
  }

  public function getVisitor()
  {
    return $this->visitor;
  }

  public function setStand($aStand)
  {
    $wasSet = false;
    if ($aStand == null)
    {
      return $wasSet;
    }
    
    $existingStand = $this->stand;
    $this->stand = $aStand;
    if ($existingStand != null && $existingStand != $aStand)
    {
      $existingStand->removeVote($this);
    }
    $this->stand->addVote($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setVisitor($aVisitor)
  {
    $wasSet = false;
    if ($aVisitor == null)
    {
      return $wasSet;
    }
    
    $existingVisitor = $this->visitor;
    $this->visitor = $aVisitor;
    if ($existingVisitor != null && $existingVisitor != $aVisitor)
    {
      $existingVisitor->removeVote($this);
    }
    $this->visitor->addVote($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderStand = $this->stand;
    $this->stand = null;
    $placeholderStand->removeVote($this);
    $placeholderVisitor = $this->visitor;
    $this->visitor = null;
    $placeholderVisitor->removeVote($this);
  }

}
?>