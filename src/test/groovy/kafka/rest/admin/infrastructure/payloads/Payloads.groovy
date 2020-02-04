package kafka.rest.admin.infrastructure.payloads

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
  "links": [],
  "content": [
    {
      "content": "content",
      "links": [
        {
          "rel": "raw",
          "href": "http://localhost/messages/topic/oneRandomTopicName/partition/0/offset/0/raw"
        }
      ]
    }
  ]
}
		"""
	}

	static def consumerGroupOffsetsPayload() {
		"""
			{
  "links": [
    {
      "rel": "self",
      "href": "http://localhost/consumer-groups/oneConsumerGroup/offsets"
    }
  ],
  "content": [
    {
      "topicName": "oneRandomTopicName",
      "partition": 0,
      "offset": 1,
      "metadata": "",
      "links": [
        {
          "rel": "topic",
          "href": "http://localhost/topics/oneRandomTopicName"
        }
      ]
    }
  ]
}
		"""
	}

	static def consumerGroupsPayload() {
		"""
			{
  "links": [
    {
      "rel": "self",
      "href": "http://localhost/consumer-groups"
    }
  ],
  "content": [
    {
      "id": "oneConsumerGroup",
      "links": [
        {
          "rel": "self",
          "href": "http://localhost/consumer-groups/oneConsumerGroup"
        }
      ]
    }
  ]
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


