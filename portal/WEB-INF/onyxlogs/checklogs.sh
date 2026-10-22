#
# grep JTRACE /lle/system/portal/WEB-INF/proto* >> /lle/system/portal/WEB-INF/onyxlogs/log.current
# sort -u /lle/system/portal/WEB-INF/onyxlogs/log.current > /lle/system/portal/WEB-INF/onyxlogs/log.new
# cp /lle/system/portal/WEB-INF/onyxlogs/log.new /lle/system/portal/WEB-INF/onyxlogs/log.`date +%m%d%y`.txt
# gzip /lle/system/portal/WEB-INF/onyxlogs/*.txt
# mv -f /lle/system/portal/WEB-INF/onyxlogs/log.new /lle/system/portal/WEB-INF/onyxlogs/log.current

rm -f /lle/system/portal/WEB-INF/onyxlogs/log.current
touch /lle/system/portal/WEB-INF/onyxlogs/log.current
for i in `find /lle/system/portal/WEB-INF -name 'proto*' -mtime 0`
do
  grep JTRACE $i >> /lle/system/portal/WEB-INF/onyxlogs/log.current
done
sort -u /lle/system/portal/WEB-INF/onyxlogs/log.current > /lle/system/portal/WEB-INF/onyxlogs/archive/log.`date +%m%d%y`.txt
gzip /lle/system/portal/WEB-INF/onyxlogs/archive/*.txt

