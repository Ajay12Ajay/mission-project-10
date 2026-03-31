#Use the officical nginx image as a base image
FROM nginx:alpine

# Copy already-built Angular dist
COPY dist/ /usr/share/nginx/html/

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

