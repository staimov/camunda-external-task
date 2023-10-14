# camunda-external-task
External tasks for Camunda BPM process + saving process data to MongoDB.

## Usage

1. Rebuild the project:
```
mvn clean install
```
2. Build and run docker containers (camunda. mongodb, mongo-express and the external tasks app):
```
docker compose up -d
```
3. Download and start Camunda Modeler (https://camunda.com/download/modeler/). Open process model [innovations_process.bpmn](./innovations_process.bpmn) and deploy it to Camunda Platform container:

![screenshot2](/screenshots/modeler.png?raw=true)

4. Check the deployment in Camunda Cockpit (http://localhost:8080/camunda/app/cockpit/default/#/processes):

![screenshot2](/screenshots/deployments.png?raw=true)

Default Camunda username/password: demo/demo

5. Use Postman to start Camunda process instance with input variables (innovationId, innovationCost, innovationEffect):

POST http://localhost:8080/engine-rest/process-definition/key/innovations_process/start 

Body:
```
{
	"variables": {
		"innovationCost": {
			"value":2100,
			"type":"integer"
		},
        	"innovationEffect": {
			"value":5600,
			"type":"integer"
		},
		"innovationId": {
			"value": "01-2023"
		}
	}
}
```

![screenshot2](/screenshots/postman.png?raw=true)

Default Camunda username/password: demo/demo

Authorization: Basic

6. Start multiple instances of the process with different input variable values.
7. Open Mongo Express UI and check the contents of innovation_db database (http://localhost:8081/db/innovations_db/innovation):

![screenshot2](/screenshots/mongo-express.png?raw=true)

Default Mongo Express username/password: root/root
