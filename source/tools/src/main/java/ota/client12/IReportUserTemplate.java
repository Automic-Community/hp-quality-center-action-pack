package ota.client12  ;

import com4j.*;

/**
 * For HP use. Represents report user custom template.
 */
@IID("{12115F37-FA99-4010-9B6D-B64FFEEB6EAC}")
public interface IReportUserTemplate extends ota.client12.IBaseField {
  // Methods:
  /**
   * <p>
   * The name of the file that defines this template
   * </p>
   * <p>
   * Getter method for the COM property "FileName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(20)
  java.lang.String fileName();


  /**
   * <p>
   * The name of the file that defines this template
   * </p>
   * <p>
   * Setter method for the COM property "FileName"
   * </p>
   * @param pVal Mandatory java.lang.String parameter.
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(21)
  void fileName(
    java.lang.String pVal);


  /**
   * <p>
   * A list of fields used by this template
   * </p>
   * <p>
   * Getter method for the COM property "Fields"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(22)
  ota.client12.IList fields();


  @VTID(22)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object fields(
    int index);

  /**
   * <p>
   * A list of fields used by this template
   * </p>
   * <p>
   * Setter method for the COM property "Fields"
   * </p>
   * @param pVal Mandatory ota.client12.IList parameter.
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(23)
  void fields(
    ota.client12.IList pVal);


  /**
   * <p>
   * The ID of the AnalysisItem that uses this template
   * </p>
   * <p>
   * Getter method for the COM property "ReportID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(24)
  int reportID();


  /**
   * <p>
   * The ID of the AnalysisItem that uses this template
   * </p>
   * <p>
   * Setter method for the COM property "ReportID"
   * </p>
   * @param pVal Mandatory int parameter.
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(25)
  void reportID(
    int pVal);


  /**
   * <p>
   * The type of this template. For example, Tabular.
   * </p>
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(26)
  java.lang.String type();


  /**
   * <p>
   * The type of this template. For example, Tabular.
   * </p>
   * <p>
   * Setter method for the COM property "Type"
   * </p>
   * @param pVal Mandatory java.lang.String parameter.
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(27)
  void type(
    java.lang.String pVal);


  // Properties:
}
