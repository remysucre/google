for j in $( find SJava -name *.java ); do
  echo $j; stack exec joogle-exe $j
done
