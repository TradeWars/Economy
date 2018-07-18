VERSION := $(shell cat VERSION)
NEW_VERSION := $(shell date -u +%yw%W.%w.%H)

#
# This file should contain tasks for building and running the service
#


# -
# Docker
# -

docker-build:
	docker build -t southclaws/ssc:$(VERSION) .

docker-push:
	docker push southclaws/ssc:$(VERSION)

docker-run:
	docker run \
		--name ssc \
		southclaws/ssc:$(VERSION)
