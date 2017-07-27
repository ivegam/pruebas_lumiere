KUBERNETES_SERVICE_NAME=thor-operator-api

# starts tomcat on localhost:8080
.PHONY: run
run:
	./gradlew bootRun --no-daemon

# runs unit tests
.PHONY: test
test:
	SPRING_PROFILES_ACTIVE=test ./gradlew test

# bundles app for production
.PHONY: build
build:
	./gradlew build

# builds the development docker image
.PHONY: build-dev-image
build-dev-image:
	docker build -t ${KUBERNETES_SERVICE_NAME}-dev -f Dockerfile.dev ${ARGS} .

# builds the production docker image
.PHONY: build-prod-image
build-prod-image: build
	docker build -t ${KUBERNETES_SERVICE_NAME} -f Dockerfile.prod ${ARGS} .

# starts the development docker image on localhost:8080
.PHONY: run-dev-image
run-dev-image:
	docker run -p 8080:8080 ${KUBERNETES_SERVICE_NAME}-dev

# starts the production docker image on localhost:8080
.PHONY: run-prod-image
run-prod-image:
	docker run -p 8080:8080 ${KUBERNETES_SERVICE_NAME}


###############################################################################################
# The tasks below will only work on CircleCI (or if you download the necessary scripts first) #
###############################################################################################

# download scripts for build & deploy
.PHONY: get-deployment-scripts
get-deployment-scripts:
	aws configure set default.region eu-central-1
	aws s3 cp s3://continuous-deployment-scripts/deployment-scripts.tar.gz .
	tar -xzf deployment-scripts.tar.gz
