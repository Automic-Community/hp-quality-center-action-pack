package ota.client12  ;

import com4j.*;

/**
 * For HP use. _IBaseFactory Interface.
 */
@IID("{47E4C767-87DA-409F-A70E-04B3FCE28BCE}")
public interface _IBaseFactory extends Com4jObject {
  // Methods:
  /**
   * <p>
   * For HP use.
   * </p>
   * @param filter Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(3)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object listRefresh(
    java.lang.String filter);


  /**
   * <p>
   * For HP use.
   * </p>
   * @param filter Mandatory java.lang.String parameter.
   */

  @VTID(4)
  void removeCachedList(
    java.lang.String filter);


  /**
   * <p>
   * For HP use.
   * </p>
   * @param filter Mandatory java.lang.String parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @VTID(5)
  ota.client12.IList getCachedList(
    java.lang.String filter);


  /**
   * <p>
   * For HP use.
   * </p>
   * @param filter Mandatory java.lang.String parameter.
   * @param pList Mandatory ota.client12.IList parameter.
   */

  @VTID(6)
  void putCachedList(
    java.lang.String filter,
    ota.client12.IList pList);


  // Properties:
}
