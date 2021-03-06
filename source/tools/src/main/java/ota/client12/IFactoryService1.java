package ota.client12  ;

import com4j.*;

/**
 * For HP use. Interface providing factory services to extensions.
 */
@IID("{9A5EE173-31D4-4C90-9900-D671FF960BF3}")
public interface IFactoryService1 extends ota.client12.IFactoryService {
  // Methods:
  /**
   * @param entityCLSID Mandatory GUID parameter.
   * @param owner Mandatory com4j.Com4jObject parameter.
   * @param isVirtualFather Mandatory boolean parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject createGenericFactory(
    GUID entityCLSID,
    com4j.Com4jObject owner,
    boolean isVirtualFather);


  // Properties:
}
