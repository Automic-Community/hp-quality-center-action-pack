package ota.client12  ;

import com4j.*;

/**
 * Represents a type of user action. Actions are listed in the AC_ACTION_NAME field of the Actions table.
 */
@IID("{8592C4BF-439B-45CB-A0E8-B7FE9E6E4FB5}")
public interface ICustomizationAction2 extends ota.client12.ICustomizationAction {
  // Methods:
  /**
   * <p>
   * Checks whether the customization item originated from the project template.
   * </p>
   * <p>
   * Getter method for the COM property "IsTemplate"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(20)
  boolean isTemplate();


  // Properties:
}
