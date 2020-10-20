package ota.client12  ;

import com4j.*;

@IID("{0000000C-0000-0000-C000-000000000046}")
public interface IStream extends ota.client12.ISequentialStream {
  // Methods:
      /**
       * @param grfCommitFlags Mandatory int parameter.
       */

      @VTID(8)
      void commit(
        int grfCommitFlags);


      /**
       */

      @VTID(9)
      void revert();


          /**
           * @return  Returns a value of type ota.client12.IStream
           */

          @VTID(13)
          ota.client12.IStream clone();


          // Properties:
        }
