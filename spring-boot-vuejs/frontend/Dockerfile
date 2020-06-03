# build stage
FROM node:lts-alpine as build-stage
RUN mkdir /app
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY ./ .
RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /app
# COPY --from=build-stage /app/dist /usr/share/nginx/html
RUN apk add curl
# RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY nginx/frontend.conf /etc/nginx/conf.d/
# WORKDIR /usr/share/nginx/html
# RUN chown root /usr/share/nginx/html/*
RUN chown root /app

# RUN chmod 755 /usr/share/nginx/html/*
RUN chmod 755 /app

# RUN chown nginx:nginx /var/www/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
