<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/

class Study
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Study Attributes
  private $studyid;
  private $studyName;

  //Study Associations
  private $stands;
  private $institute;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStudyid, $aStudyName, $aInstitute)
  {
    $this->studyid = $aStudyid;
    $this->studyName = $aStudyName;
    $this->stands = array();
    $didAddInstitute = $this->setInstitute($aInstitute);
    if (!$didAddInstitute)
    {
      throw new Exception("Unable to create study due to institute");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStudyid($aStudyid)
  {
    $wasSet = false;
    $this->studyid = $aStudyid;
    $wasSet = true;
    return $wasSet;
  }

  public function setStudyName($aStudyName)
  {
    $wasSet = false;
    $this->studyName = $aStudyName;
    $wasSet = true;
    return $wasSet;
  }

  public function getStudyid()
  {
    return $this->studyid;
  }

  public function getStudyName()
  {
    return $this->studyName;
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

  public function getInstitute()
  {
    return $this->institute;
  }

  public static function minimumNumberOfStands()
  {
    return 0;
  }

  public function addStandVia($aStandid, $aName, $aExpo)
  {
    return new Stand($aStandid, $aName, $aExpo, $this);
  }

  public function addStand($aStand)
  {
    $wasAdded = false;
    if ($this->indexOfStand($aStand) !== -1) { return false; }
    $existingStudy = $aStand->getStudy();
    $isNewStudy = $existingStudy != null && $this !== $existingStudy;
    if ($isNewStudy)
    {
      $aStand->setStudy($this);
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
    //Unable to remove aStand, as it must always have a study
    if ($this !== $aStand->getStudy())
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

  public function setInstitute($aInstitute)
  {
    $wasSet = false;
    if ($aInstitute == null)
    {
      return $wasSet;
    }
    
    $existingInstitute = $this->institute;
    $this->institute = $aInstitute;
    if ($existingInstitute != null && $existingInstitute != $aInstitute)
    {
      $existingInstitute->removeStudy($this);
    }
    $this->institute->addStudy($this);
    $wasSet = true;
    return $wasSet;
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
    $placeholderInstitute = $this->institute;
    $this->institute = null;
    $placeholderInstitute->removeStudy($this);
  }

}
?>