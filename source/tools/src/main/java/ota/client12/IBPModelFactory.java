package ota.client12  ;

import com4j.*;

/**
 * Services for managing BP Models.
 */
@IID("{46F7D33E-46BF-4A90-9435-0834558D9165}")
public interface IBPModelFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * Get the link between all the models in a project
   * </p>
   * <p>
   * Getter method for the COM property "BPModelNetworkLinks"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList bpModelNetworkLinks();


  @VTID(17)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object bpModelNetworkLinks(
    int index);

  // Properties:
}
