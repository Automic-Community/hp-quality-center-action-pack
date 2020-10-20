package ota.client12  ;

import com4j.*;

/**
 * Extends IBaseFactory
 */
@IID("{33A6EC9D-8D08-4598-AD39-A614D9409ECC}")
public interface _IFactorySupportBatchLock extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Locks the given items.
   * </p>
   * @param entities Mandatory ota.client12.IList parameter.
   */

  @VTID(3)
  void lockList(
    ota.client12.IList entities);


  // Properties:
}
