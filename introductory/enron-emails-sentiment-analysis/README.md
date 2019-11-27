Time-Series Sentiment Analysis of Enron Email Dataset
==================================================================

## Setup Instructions

The easiest way to do the setup necessary to run this tutorial is to run
it inside a docker container.

To do that, do the following:

1. Prepare the dataset:

   ```
   make build-dataset
   ```
   
2. Build the notebook container:

   ```
   make build-notebook
   ```
   
3. Run the notebook container:

   ```
   make start
   ```
   
   Once you run the notebook, you should see output like this:

   ```
   [C 20:32:27.522 NotebookApp]

    To access the notebook, open this file in a browser:
        file:///home/jovyan/.local/share/jupyter/runtime/nbserver-13-open.html
    Or copy and paste one of these URLs:
        http://(387f04a9abc8 or 127.0.0.1):8888/?token=dace64893ef45c73ab51a7552190cc54845bce375d34d457

   ```
   
   Follow the instructions.
