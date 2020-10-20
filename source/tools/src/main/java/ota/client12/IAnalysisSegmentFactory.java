package ota.client12  ;

import com4j.*;

/**
 * For HP use. Services to manage analysis segments.
 */
@IID("{BA2A4EE4-E252-434E-82E7-C79C0953CE59}")
public interface IAnalysisSegmentFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * Returns the list of segments per extension.
   * </p>
   * @param extension Mandatory java.lang.String parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList getExtensionSegments(
    java.lang.String extension);


  // Properties:
}
