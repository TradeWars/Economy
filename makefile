VERSION := $(shell cat VERSION)
NEW_VERSION := $(shell date -u +%yw%W.%w.%H)

#
# This file should contain tasks for building and running the service
#


# -
# Docker
# -

build: static
	docker build -t southclaws/ssc:$(VERSION) .

push:
	docker push southclaws/ssc:$(VERSION)

run:
	docker run \
		--name ssc \
		--env-file .env \
southclaws/ssc:$(VERSION)