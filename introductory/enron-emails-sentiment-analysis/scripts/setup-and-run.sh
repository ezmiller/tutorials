#!/bin/bash

ln -s /home/jovyan/data data
sh -c 'jupyter notebook --log-level=ERROR --allow-root'
