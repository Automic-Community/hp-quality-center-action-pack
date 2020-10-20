package ota.client12  ;

import com4j.*;

/**
 * Services to support parameterization.
 */
@IID("{118EEC21-E08B-4E25-AA04-2BE09714FDF3}")
public interface ISupportParameterValues extends Com4jObject {
  // Methods:
  /**
   * <p>
   * The parameter value factory that manages the parameter values of the current object.
   * </p>
   * <p>
   * Getter method for the COM property "ParameterValueFactory"
   * </p>
   * @return  Returns a value of type ota.client12.IParameterValueFactory
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  ota.client12.IParameterValueFactory parameterValueFactory();


  // Properties:
}
