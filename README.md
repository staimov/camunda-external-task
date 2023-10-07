# camunda-external-task
External tasks for Camunda BPM process + saving process data to MongoDB.

## Usage

1. Rebuild the project:
```
mvn clean install
```
2. Build and run docker containers (camunda. mongodb, mongo-express and the app):
```
docker compose up -d
```
3. Download and start Camunda Modeler (https://camunda.com/download/modeler/). Open process model [innovations_process.bpmn](./innovations_process.bpmn) and deploy it to Camunda Platform container:
4. Check the deployment in Camunda Cockpit (http://localhost:8080/camunda/app/cockpit/default/#/processes):

Default Camunda username/password: demo/demo

6. Use Postman (https://www.postman.com/downloads/) to start Camunda process instances:

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
7. Start multiple instances of the process with different input variable values (innovationId, innovationCost, innovationEffect).
8. Open Mongo Express UI and check the contents of innovation_db database (http://localhost:8081/db/innovations_db/innovation): 