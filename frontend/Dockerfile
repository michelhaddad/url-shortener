FROM node:18.9-alpine

# This should be set depending on env
ENV NODE_ENV production

WORKDIR /app

COPY . .

RUN yarn install --frozen-lockfile

EXPOSE 3000

ENTRYPOINT [ "yarn", "start" ]