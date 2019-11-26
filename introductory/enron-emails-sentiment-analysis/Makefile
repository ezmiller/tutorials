build-dataset:
	docker build --force-rm -t enron-sentmail-dataset -f Dockerfile.dataset .

build:
	docker build --no-cache --force-rm -t enron-analysis .

start:

	docker run -it -v "$(shell pwd)":/home/jovyan/notebooks -p 8888:8888 enron-analysis
