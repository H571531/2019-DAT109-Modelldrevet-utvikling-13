/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 54 "model.ump"
// line 177 "model.ump"
public class Study
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Study Attributes
  private String studyid;
  private String studyName;

  //Study Associations
  private List<Stand> stands;
  private Institute institute;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Study(String aStudyid, String aStudyName, Institute aInstitute)
  {
    studyid = aStudyid;
    studyName = aStudyName;
    stands = new ArrayList<Stand>();
    boolean didAddInstitute = setInstitute(aInstitute);
    if (!didAddInstitute)
    {
      throw new RuntimeException("Unable to create study due to institute");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStudyid(String aStudyid)
  {
    boolean wasSet = false;
    studyid = aStudyid;
    wasSet = true;
    return wasSet;
  }

  public boolean setStudyName(String aStudyName)
  {
    boolean wasSet = false;
    studyName = aStudyName;
    wasSet = true;
    return wasSet;
  }

  public String getStudyid()
  {
    return studyid;
  }

  public String getStudyName()
  {
    return studyName;
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
  /* Code from template association_GetOne */
  public Institute getInstitute()
  {
    return institute;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStands()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Stand addStand(String aStandid, String aName, Expo aExpo)
  {
    return new Stand(aStandid, aName, aExpo, this);
  }

  public boolean addStand(Stand aStand)
  {
    boolean wasAdded = false;
    if (stands.contains(aStand)) { return false; }
    Study existingStudy = aStand.getStudy();
    boolean isNewStudy = existingStudy != null && !this.equals(existingStudy);
    if (isNewStudy)
    {
      aStand.setStudy(this);
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
    //Unable to remove aStand, as it must always have a study
    if (!this.equals(aStand.getStudy()))
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
  /* Code from template association_SetOneToMany */
  public boolean setInstitute(Institute aInstitute)
  {
    boolean wasSet = false;
    if (aInstitute == null)
    {
      return wasSet;
    }

    Institute existingInstitute = institute;
    institute = aInstitute;
    if (existingInstitute != null && !existingInstitute.equals(aInstitute))
    {
      existingInstitute.removeStudy(this);
    }
    institute.addStudy(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=stands.size(); i > 0; i--)
    {
      Stand aStand = stands.get(i - 1);
      aStand.delete();
    }
    Institute placeholderInstitute = institute;
    this.institute = null;
    if(placeholderInstitute != null)
    {
      placeholderInstitute.removeStudy(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "studyid" + ":" + getStudyid()+ "," +
            "studyName" + ":" + getStudyName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "institute = "+(getInstitute()!=null?Integer.toHexString(System.identityHashCode(getInstitute())):"null");
  }
}