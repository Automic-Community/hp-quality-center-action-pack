package ota.client12  ;

import com4j.*;

/**
 * ScopeItem Factory
 */
@IID("{C0461045-0539-42AD-83DD-13DD397B6362}")
public interface IScopeItemFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * Returns the names of the entities' tables that support QPM.
   * </p>
   * <p>
   * Getter method for the COM property "SupportsQPMContentEntitiesTables"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList supportsQPMContentEntitiesTables();


  @VTID(17)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object supportsQPMContentEntitiesTables(
    int index);

  // Properties:
}
