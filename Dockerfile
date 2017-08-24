########################################################
# Dockerfile to demonstrate the behaviour of CMD
########################################################
# Build from base image busybox:latest
FROM busybox:latest
# Author: Phan Vu Dinh
MAINTAINER Phan Vu Dinh <itphanvudinh@gmail.com>
# Set command for CMD
CMD ["echo", "Dockerfile CMD"]


