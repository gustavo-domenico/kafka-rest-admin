package kafka.rest.admin.infrastructure.payloads

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.anotherTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic

class Payloads {
	static def consumerGroupsPayload() {
		"""
			{
			  "_embedded": {
			    "consumerGroupList": [
			      {
			        "id": "${oneConsumerGroup().id}",
			        "_links": {
			          "self": {
			            "href": "http://localhost/consumer-groups/${oneConsumerGroup().id}"
			          }
			        }
			      }
			    ]
			  },
			  "_links": {
			    "self": {
			      "href": "http://localhost/consumer-groups"
			    }
			  }
			}
		"""
	}

	static def topicsPayload() {
		"""{
			"_embedded": {
			"topicList": [
					{
						"name": "${oneTopic().name}",
						"_links": {
						"self": {
							"href": "http://localhost/topics/${oneTopic().name}"
						}
					}
					},
					{
						"name": "${anotherTopic().name}",
						"_links": {
						"self": {
							"href": "http://localhost/topics/${anotherTopic().name}"
						}
					}
					}
			]
		},
			"_links": {
			"self": {
				"href": "http://localhost/topics"
			}
		}
		}
		"""
	}
}


