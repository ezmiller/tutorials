FROM simplect/clojupyter:0.2.2
EXPOSE 8888

USER root
RUN apt-get install -y maven

USER jovyan
WORKDIR /home/jovyan/notebooks

# Install vader library locally in maven
RUN wget -O ../vader.zip https://github.com/nunoachenriques/vader-sentiment-analysis/releases/download/v2.0.1/vader-sentiment-analysis-2.0.1.zip
RUN unzip ../vader.zip -d ..
RUN mvn install:install-file -Dfile=/home/jovyan/vader-sentiment-analysis-2.0.1/vader-sentiment-analysis-2.0.1.jar -Durl=file:repo -DgroupId=local -DartifactId=vader -Dversion=2.0.1 -Dpackaging=jar

# Acquire and unzip dataset from `enron-sentmail-dataset:latest`
# Note: This uses docker's multistage build functionality:
#       https://docs.docker.com/develop/develop-images/multistage-build/
COPY --from=enron-sentmail-dataset:latest /enron_sent_mail.tar.gz /home/jovyan
RUN sh -c 'tar -xzvf /home/jovyan/enron_sent_mail.tar.gz -C /home/jovyan'

ENTRYPOINT ./scripts/setup-and-run.sh
