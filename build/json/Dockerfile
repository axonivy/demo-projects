FROM node:12-alpine
# forked from svenwal/jsonplaceholder:latest

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

#Add files
COPY build/json/db.json /usr/src/app

# Install app dependencies
RUN npm install -g json-server 

EXPOSE 3000
CMD [ "node", "/usr/local/bin/json-server", "--watch", "db.json", "--host", "0.0.0.0"]
