package simulation

import org.apache.spark.sql.DataFrame

import org.scalatest.FlatSpec

class AppTest extends FlatSpec {
    private val numRows = 10
    "App" should s"generate a montecarlo with $numRows rows" in {
        val method = App.getClass.getDeclaredMethod("montecarloPi", classOf[Long])
        method.setAccessible(true)
        
        val data = method.invoke(App, 10.asInstanceOf[Object]).asInstanceOf[DataFrame]

        assert(data.count == numRows)
    }
}