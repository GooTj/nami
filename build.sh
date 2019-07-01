mvn clean package -Dmaven.test.skip=ture

docker build -t hub.c.163.com/jrxpay/nami  .

docker tag merry hub.c.163.com/jrxpay/nami

docker push hub.c.163.com/jrxpay/nami