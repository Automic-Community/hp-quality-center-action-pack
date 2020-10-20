package ota.client12  ;

import com4j.*;

/**
 * Diagrams
 */
@IID("{E61E09B0-91F9-43F4-96F8-43A1DFE7ACE9}")
public interface IDiagram extends ota.client12.IBaseFieldExMail {
  // Methods:
  /**
   * <p>
   * The Diagram name.
   * </p>
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(24)
  java.lang.String name();


  /**
   * <p>
   * The Diagram name.
   * </p>
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param pVal Mandatory java.lang.String parameter.
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(25)
  void name(
    java.lang.String pVal);


  /**
   * <p>
   * The ID of the Diagram's parent req.
   * </p>
   * <p>
   * Getter method for the COM property "ParentId"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(26)
  int parentId();


  /**
   * <p>
   * The ID of the Diagram's parent req.
   * </p>
   * <p>
   * Setter method for the COM property "ParentId"
   * </p>
   * @param pVal Mandatory int parameter.
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(27)
  void parentId(
    int pVal);


  /**
   * <p>
   * The description of the Diagram.
   * </p>
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(28)
  java.lang.String description();


  /**
   * <p>
   * The description of the Diagram.
   * </p>
   * <p>
   * Setter method for the COM property "Description"
   * </p>
   * @param pVal Mandatory java.lang.String parameter.
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(29)
  void description(
    java.lang.String pVal);


  /**
   * <p>
   * The DiagramElementFactory object.
   * </p>
   * <p>
   * Getter method for the COM property "DiagramElementFactory"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(19) //= 0x13. The runtime will prefer the VTID if present
  @VTID(30)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject diagramElementFactory();


  // Properties:
}
