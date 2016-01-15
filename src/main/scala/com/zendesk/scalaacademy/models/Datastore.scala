package com.zendesk.scalaacademy.models

import scala.collection.mutable


object Datastore {
  val data = mutable.Map[String, Map[Long, Topic]]()

  def store(timestamp: Long, topic: Topic): Unit = {
    data.put(topic.name, Map(timestamp -> topic))
  }

  def get(timestamp: Long, topicName: String): Option[Topic] = data.get(topicName) match {
    case Some(tsMap: Map[Long, Topic]) => tsMap.get(timestamp)
    case _ => None
  }
}
