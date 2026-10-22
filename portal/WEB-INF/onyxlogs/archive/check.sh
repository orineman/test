for i in `ls *.gz`; do gunzip $i; grep -i 2a2526fcd364 *.txt; echo +++++ COMPLETED $i +++++; gzip *.txt; done
