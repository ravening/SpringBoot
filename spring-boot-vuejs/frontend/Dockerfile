FROM node:lts-alpine

# install simple http server for serving static content
RUN npm install -g http-server --silent

# make the 'app' folder the current working directory
WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install --silent

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

EXPOSE 8080
CMD [ "http-server", "dist" ]


# build stage
# FROM node:lts-alpine as build-stage
# WORKDIR /app
# COPY package*.json ./
# RUN npm install --silent
# RUN npm install @vue/cli -g
# COPY . .
# RUN npm run build

# FROM nginx:stable-alpine as production-stage
# COPY --from=build-stage /app/dist /usr/share/nginx/html
# RUN rm /etc/nginx/conf.d/default.conf
# COPY nginx/nginx.conf /etc/nginx/conf.d
# COPY nginx/frontend.conf /etc/nginx/frontend.conf
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]

# FROM alpine:3.7

# # Add nginx and nodejs
# RUN apk add --update nginx nodejs

# # Create the directories we will need
# RUN mkdir -p /tmp/nginx/vue-single-page-app
# RUN mkdir -p /var/log/nginx
# RUN mkdir -p /var/www/html

# # Copy the respective nginx configuration files
# COPY nginx/nginx.conf /etc/nginx/nginx.conf
# COPY nginx/default.conf /etc/nginx/conf.d/default.conf
# COPY nginx/frontend.conf /etc/nginx/frontend.conf

# # Set the directory we want to run the next commands for
# WORKDIR /tmp/nginx/vue-single-page-app
# # Copy our source code into the container
# COPY . .
# # Install the dependencies, can be commented out if you're running the same node version
# RUN npm install

# # run webpack and the vue-loader
# RUN npm run build

# # copy the built app to our served directory
# RUN cp -r dist/* /var/www/html

# # make all files belong to the nginx user
# RUN chown nginx:nginx /var/www/html

# # start nginx and keep the process from backgrounding and the container from quitting
# CMD ["nginx", "-g", "daemon off;"]