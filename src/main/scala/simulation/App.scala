package simulation

import org.apache.spark.sql.{ DataFrame, SparkSession }
import org.apache.spark.sql.functions.{ pow, rand, sqrt }

object App {
    private val sparkMaster = "local[*]"

    private val spark = SparkSession.builder.master(sparkMaster).getOrCreate
    import spark.implicits._

    private def montecarloPi(n:Long) =
        spark.range(n)
            .withColumn("x", rand)
            .withColumn("y", rand)
            .withColumn("inside", sqrt(pow($"x", 2d) + pow($"y", 2d)) <= 1)
            .drop("id")

    def main(arg:Array[String]) = {
        val n = sys.env("NUM").trim.toLong
        val url = sys.env("URL").trim

        val data = montecarloPi(n)
        val inside = data.filter($"inside").count
        val aproxPi = inside * 4.0 / n
        
        println(s"Saving sample data to $url")
        data.write.mode("overwrite").option("header", true).csv(url)
        
        println(s"MontecarloPi with $n samples is $aproxPi")
    }
}