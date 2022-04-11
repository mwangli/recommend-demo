package online.mwang.utils

import com.mongodb.casbah.Imports.MongoClientURI
import com.mongodb.casbah.MongoClient
import org.apache.spark.sql.{DataFrame, SparkSession}

object MongoUtils {

  val MONGODB_URI = "mongodb://test1:27017/recommend"

  lazy val mongoClient = MongoClient(MongoClientURI(MONGODB_URI))

  def readFromMongoDB(spark: SparkSession, collection: String): DataFrame = {
    spark.read
      .option("uri", MONGODB_URI)
      .option("collection", collection)
      .format("com.mongodb.spark.sql")
      .load()
  }

  def save2MongoDB(df: DataFrame, collection: String) = {
    df.write
      .option("uri", MONGODB_URI)
      .option("collection", collection)
      .mode("overwrite")
      .format("com.mongodb.spark.sql")
      .save()
  }
}
