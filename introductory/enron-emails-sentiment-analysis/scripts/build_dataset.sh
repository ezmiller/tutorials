#!/bin/sh

mkdir -p data/enron_mail
wget --show-progress --progress=bar:force https://www.cs.cmu.edu/~./enron/enron_mail_20150507.tar.gz
tar -xzvf enron_mail_20150507.tar.gz -C data/enron_mail --wildcards "*_sent_mail*"
tar -czvf enron_sent_mail.tar.gz data/enron_mail
rm enron_mail_20150507.tar.gz
