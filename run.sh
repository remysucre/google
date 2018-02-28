# how to run: put your pattern in src/Lib.hs (look for PATTERN HERE)
# then pass the directory containing java code as argument
# e.g. bash run.sh SJava

for j in $( find $1 -name *.java ); do
  echo $j; stack exec joogle-exe $j
done
