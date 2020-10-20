package ota.client12  ;

import com4j.*;

/**
 * Services for managing coverage by criteria.
 */
@IID("{3ECD417C-877F-4AE4-A500-93182CE8D5F1}")
public interface ICriterionCoverageFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * Sets cycle context to be used in NewList.
   * </p>
   * @param coverageByCycles Mandatory ota.client12.IList parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  void setCyclesContext(
    ota.client12.IList coverageByCycles);


  /**
   * <p>
   * Resets cycle context.
   * </p>
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(18)
  void resetCyclesContext();


  // Properties:
}
