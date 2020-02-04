package kafka.rest.admin.infrastructure.payloads

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic

class Payloads {
	static def messagePayload() {
		"""
			{ 
				"content": "content" 
			}
		"""
	}

	static def messagesPayload() {
		"""
		{
		  "_embedded": {
		    "messageList": [
		      {
		        "content": "content"
		      }
		    ]
		  }
		}
		"""
	}

	static def consumerGroupOffsetsPayload() {
		"""
			{
			  "_embedded": {
			    "consumerGroupOffsetList": [
			      {
			        "topicName": "${oneTopic().name}",
			        "partition": 0,
			        "offset": 1,
			        "metadata": "",
			        "_links": {
			          "topic": {
			            "href": "http://localhost/topics/${oneTopic().name}"
			          }
			        }
			      }
			    ]
			  },
			  "_links": {
			    "self": {
			      "href": "http://localhost/consumer-groups/${oneConsumerGroup().id}/offsets"
			    }
			  }
			}
		"""
	}

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
		"""
{
  "links": [
    {
      "rel": "self",
      "href": "http://localhost/topics"
    }
  ],
  "content": [
    {
      "name": "oneRandomTopicName",
      "links": [
        {
          "rel": "self",
          "href": "http://localhost/topics/oneRandomTopicName"
        }
      ]
    },
    {
      "name": "anotherRandomTopicName",
      "links": [
        {
          "rel": "self",
          "href": "http://localhost/topics/anotherRandomTopicName"
        }
      ]
    }
  ]
}
		"""
	}
}


