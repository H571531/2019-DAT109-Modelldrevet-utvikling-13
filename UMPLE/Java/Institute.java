/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!*/


import java.util.*;

// line 61 "model.ump"
// line 182 "model.ump"
public class Institute
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Institute Attributes
  private String instituteid;

  //Institute Associations
  private List<Study> studies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Institute(String aInstituteid)
  {
    instituteid = aInstituteid;
    studies = new ArrayList<Study>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInstituteid(String aInstituteid)
  {
    boolean wasSet = false;
    instituteid = aInstituteid;
    wasSet = true;
    return wasSet;
  }

  public String getInstituteid()
  {
    return instituteid;
  }
  /* Code from template association_GetMany */
  public Study getStudy(int index)
  {
    Study aStudy = studies.get(index);
    return aStudy;
  }

  public List<Study> getStudies()
  {
    List<Study> newStudies = Collections.unmodifiableList(studies);
    return newStudies;
  }

  public int numberOfStudies()
  {
    int number = studies.size();
    return number;
  }

  public boolean hasStudies()
  {
    boolean has = studies.size() > 0;
    return has;
  }

  public int indexOfStudy(Study aStudy)
  {
    int index = studies.indexOf(aStudy);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudies()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Study addStudy(String aStudyid, String aStudyName)
  {
    return new Study(aStudyid, aStudyName, this);
  }

  public boolean addStudy(Study aStudy)
  {
    boolean wasAdded = false;
    if (studies.contains(aStudy)) { return false; }
    Institute existingInstitute = aStudy.getInstitute();
    boolean isNewInstitute = existingInstitute != null && !this.equals(existingInstitute);
    if (isNewInstitute)
    {
      aStudy.setInstitute(this);
    }
    else
    {
      studies.add(aStudy);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudy(Study aStudy)
  {
    boolean wasRemoved = false;
    //Unable to remove aStudy, as it must always have a institute
    if (!this.equals(aStudy.getInstitute()))
    {
      studies.remove(aStudy);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudyAt(Study aStudy, int index)
  {  
    boolean wasAdded = false;
    if(addStudy(aStudy))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudies()) { index = numberOfStudies() - 1; }
      studies.remove(aStudy);
      studies.add(index, aStudy);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStudyAt(Study aStudy, int index)
  {
    boolean wasAdded = false;
    if(studies.contains(aStudy))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudies()) { index = numberOfStudies() - 1; }
      studies.remove(aStudy);
      studies.add(index, aStudy);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStudyAt(aStudy, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=studies.size(); i > 0; i--)
    {
      Study aStudy = studies.get(i - 1);
      aStudy.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "instituteid" + ":" + getInstituteid()+ "]";
  }
}