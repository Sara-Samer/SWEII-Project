docker build -t product:latest D:\FCI\Codes\SWEII-Project\Online-Store-Platform
docker tag product:latest salma27/repo:latest
docker push salma27/repo:latest
docker image rm -f product:latest
docker image rm -f salma27/repo:latest
