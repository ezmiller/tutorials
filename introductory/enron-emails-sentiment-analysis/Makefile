build-dataset:
	docker build --force-rm -t enron-sentmail-dataset -f Dockerfile.dataset .

build-notebook:
	docker build --no-cache --force-rm -t enron-analysis .

start:
	docker run --rm -it -v "$(shell pwd)":/home/jovyan/notebooks -p 8888:8888 enron-analysis
