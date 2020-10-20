package ota.client12  ;

import com4j.*;

/**
 * For HP use. Services to manage Dashboard Pages.
 */
@IID("{1001E71F-5744-4624-8000-633698EB940D}")
public interface IDashboardPageFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * Returns those dashboard page objects from the specified ID list that include private analysis items.
   * </p>
   * @param pIdsList Mandatory ota.client12.IList parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList getPagesWithPrivateItems(
    ota.client12.IList pIdsList);


  // Properties:
}
