# Lending

How to deploy:

git clone https://github.com/samwaithaka/lending
git clone https://github.com/samwaithaka/trandata

cd ../trandata

docker build -t trandata-backend:v1 .

cd ../lending

docker build -t lending-backend:v1 .

docker compose down && docker compose up --build -d && docker logs -f
