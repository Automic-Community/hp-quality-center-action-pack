package ota.client12  ;

import com4j.*;

/**
 * Deprecated. Use ITestLabFolder. Manages the tests and test sets included in one test set folder.
 */
@IID("{42A0D93D-2E7A-4D93-A9DF-754928F7F6BC}")
public interface ITestSetFolder3 extends ota.client12.ITestSetFolder2 {
  // Methods:
  /**
   * <p>
   * The cycle to which the folder is assigned.
   * </p>
   * <p>
   * Setter method for the COM property "TargetCycle"
   * </p>
   * @param pVal Mandatory java.lang.Object parameter.
   */

  @DISPID(28) //= 0x1c. The runtime will prefer the VTID if present
  @VTID(38)
  void targetCycle(
    @MarshalAs(NativeType.VARIANT) java.lang.Object pVal);


  /**
   * <p>
   * The cycle to which the folder is assigned.
   * </p>
   * <p>
   * Getter method for the COM property "TargetCycle"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(28) //= 0x1c. The runtime will prefer the VTID if present
  @VTID(39)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object targetCycle();


  /**
   * <p>
   * Returns the list of fields of the test set folder.
   * </p>
   * <p>
   * Getter method for the COM property "FolderFields"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(29) //= 0x1d. The runtime will prefer the VTID if present
  @VTID(40)
  ota.client12.IList folderFields();


  @VTID(40)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object folderFields(
    int index);

  // Properties:
}
