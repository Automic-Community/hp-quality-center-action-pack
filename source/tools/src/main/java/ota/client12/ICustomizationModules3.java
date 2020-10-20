package ota.client12  ;

import com4j.*;

/**
 * Services for managing the customization modules.
 */
@IID("{109DCBCF-8D7D-4280-837B-4F950604B48B}")
public interface ICustomizationModules3 extends ota.client12.ICustomizationModules2 {
  // Methods:
  /**
   * <p>
   * The list of project module IDs.
   * </p>
   * <p>
   * Getter method for the COM property "Modules"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(18)
  ota.client12.IList modules();


  @VTID(18)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object modules(
    int index);

  /**
   * <p>
   * Returns the module specified by the module ID.
   * </p>
   * <p>
   * Getter method for the COM property "Module"
   * </p>
   * @param moduleID Mandatory int parameter.
   * @return  Returns a value of type ota.client12.IModule
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(19)
  ota.client12.IModule module(
    int moduleID);


  // Properties:
}
