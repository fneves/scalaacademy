package com.zendesk.scalaacademy.controller

import com.zendesk.scalaacademy.models.{Datastore, Partition, Topic}
import org.json4s.jackson.Serialization
import org.scalatra.{Accepted, Ok}

class OffsetsController extends RestController {
  get("/") {
    Map("message" -> "Hello Scala!")
  }

  get("/topics/:topic/offsets") {
    val Some(topicName) = params.get("topic")
    params.get("timestamp") match {
      case Some(ts: String) =>
        val offsets = getOffsets(ts.toLong, topicName)
        Ok(offsets)
      case None => throw new IllegalArgumentException("Invalid Request")
    }
  }

  post("/topics/:topic/offsets") {
    val Some(topicName) = params.get("topic")
    val snapshot = Serialization.read[TopicSnapshotRequest](request.body)
    Datastore.store(snapshot.timestamp, snapshot.toTopic(topicName))
    Accepted(Map("message" -> s"Stored snapshot for topic ${topicName}"))
  }

  def getOffsets(ts: Long, topicName: String): Map[String, List[Partition]] = {
    Datastore.get(ts.toLong, topicName) match {
      case Some(topic: Topic) => Map("partitions" -> topic.partitions)
      case _ => Map("partitions" -> List.empty)
    }
  }
}

case class TopicSnapshotRequest(timestamp: Long, offsets: List[PartitionOffset]) {
  def toTopic(topicName: String): Topic = {
    val partitions = offsets.map(_.toPartition(timestamp))
    Topic(topicName, partitions)
  }
}

case class PartitionOffset(id: Int, offset: Long) {
  def toPartition(timestamp: Long): Partition = Partition(id, timestamp, offset)
}




