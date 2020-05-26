FROM openjdk:8-jre-slim


RUN sed -i 's|#networkaddress.cache.ttl=-1|networkaddress.cache.ttl=10|' /usr/lib/jvm/java-8-openjdk-$(dpkg --print-architecture)/jre/lib/security/java.security

ARG user=esr
ARG group=esr
RUN groupadd -r $group && useradd -ms /bin/bash $user -g $group && \
    mkdir /opt/$user && \
    chown -R $user:$group /opt/$user &&  \
    mkdir /var/log/$user && \
    chown -R $user:$group /var/log/$user  
USER $user
WORKDIR /opt/$user

ADD esr-server-*-linux64.tar.gz /opt/$user
RUN chmod 755 /opt/$user/bin/*.sh

ENTRYPOINT ["/bin/bash", "/home/esr/docker/docker-entrypoint.sh"]