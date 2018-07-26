FROM ubuntu:16.04

MAINTAINER "Li Zi" <li.zi30@zte.com.cn>

EXPOSE 9518

#install openjdk-1.8
#RUN sed -i 's#http://archive.ubuntu.com#http://mirrors.163.com#g' /etc/apt/sources.list
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk

#configure the JDK
#RUN sed -i 's|#networkaddress.cache.ttl=-1|networkaddress.cache.ttl=10|' /usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/jre/lib/security/java.security
#ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)
#ENV PATH $PATH:/usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/jre/bin:/usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/bin
#ENV CLASSPATH .:${JAVA_HOME}/lib:${JRE_HOME}/lib
#ENV JRE_HOME ${JAVA_HOME}/jre

#add esr related resources to the docker image
RUN mkdir /home/esr
WORKDIR /home/esr
ADD esr-server-*-linux64.tar.gz /home/esr/
RUN chmod 755 /home/esr/bin/*.sh

#ENTRYPOINT /home/esr/bin/run.sh
ENTRYPOINT ["/bin/bash" "/home/esr/docker/docker-entrypoint.sh"]

