package ota.client12  ;

import com4j.*;

/**
 * For HP use. The properties of an extension as they appear in the extension.xml file.
 */
@IID("{28F27B65-8D35-4A8F-B9A1-6BAFA55F591B}")
public interface IExtensionData3 extends ota.client12.IExtensionData2 {
  // Methods:
  /**
   * <p>
   * The extension list on which it depends.
   * </p>
   * <p>
   * Getter method for the COM property "DependsOn"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList dependsOn();


  @VTID(17)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object dependsOn(
    int index);

  // Properties:
}
