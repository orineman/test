# Put this into .bashrc so you can just type in updateserver and have
# the server run the update script

alias updateserver='cd /root/jodie; /root/jodie/updateserver.sh 2>j.err 1>j.out; if test `echo $?` -eq 0; then  echo + COMPLETED +; else echo +++++ ABORTED ++++++; fi; echo Changing directory back to ; cd -'
